package com.activeedgetech.employeemanager.security;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * A custom filter created to verify the token provided
 * by a user
 */
@Slf4j
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    /**
     * Verifies if the token provided is correct then sets an instance of Authentication class
     * to the security context
     *
     * @param httpServletRequest the request payload
     * @param httpServletResponse the http response
     * @param filterChain filter chain
     * @throws ServletException exception
     * @throws IOException Input or Output exception
     */

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest,
                                    HttpServletResponse httpServletResponse,
                                    FilterChain filterChain) throws ServletException, IOException {

        try{
            String jwt = getJwtFromHeader(httpServletRequest);

            boolean jwtIsAuthentic = jwtTokenProvider.validateToken(jwt);

            if(StringUtils.hasText(jwt) && jwtIsAuthentic){

                TokenAuthentication authentication = new TokenAuthentication(jwt, AuthorityUtils.NO_AUTHORITIES);
                SecurityContextHolder.getContext().setAuthentication(authentication);
                log.info("Authentication successfully set on the security context");
            }

        }catch(Exception e){
            log.error("Could not set user authentication in security context - {}", e.getMessage(), e);
        }

        //Pass on the request and response to the next filter to do it's job
        filterChain.doFilter(httpServletRequest,httpServletResponse);

    }

    /**
     * Fetch Bearer token from the request header
     *
     * @param request the request payload
     * @return jwt token if available
     */
    public String getJwtFromHeader(HttpServletRequest request){
        String bearerToken = request.getHeader("Authorization");
        if(StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")){
            return bearerToken.substring(7);
        }
        return null;
    }
}
