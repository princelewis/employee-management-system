package com.activeedgetech.employeemanager.security;

import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Date;

@Slf4j
@Component
public class JwtTokenProvider {

    //Fetch JWT credentials form the properties files
    @Value("${jwtSecret}")
    private String jwtSecret;

    @Value("${jwt.expirationTime}")
    private Long jwtExpirationInMs;

    /**
     * This method signs and generates authentication token for user
     *
     * @return JWT token
     */
    public String generateToken() {

        log.info("About to generate jwt token *****");
        Date now = new Date();

        Date expiryDate = new Date(now.getTime() + jwtExpirationInMs);

        //Build the Jwt token here
        return Jwts.builder()
                .setIssuedAt(new Date())
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }

    /**
     * This methods takes the token received from the Header and
     * validates it against the JWT secret word
     *
     * @param authToken Jwt token received from the request Header
     * @return boolean
     */
    public boolean validateToken(String authToken){

        log.info("validating token from the Header");

        try{
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);

            log.info("Token successfully validated");
            return true;
        }catch (SignatureException e){
            log.error("Invalid JWT signature");
        }catch(MalformedJwtException e){
            log.error("Invalid JWT token");
        } catch (ExpiredJwtException e){
            log.error("Expired JWT token");
        } catch (UnsupportedJwtException e){
            log.error("Unsupported JWT token");
        } catch (IllegalArgumentException e){
            log.error("JWT claims string is empty.");
        }
        return false;
    }


}
