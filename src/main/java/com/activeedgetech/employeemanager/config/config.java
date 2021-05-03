package com.activeedgetech.employeemanager.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.Arrays;
import java.util.Collections;

@Configuration
public class config {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2).select().apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any()).build().apiInfo(apiInfo())
                .securitySchemes(Arrays.asList(new ApiKey("JWT", HttpHeaders.AUTHORIZATION, "header")));
    }


    private ApiInfo apiInfo() {
        return new ApiInfo("ActiveEdgeTechnology Service",
                "A back-end service for managing employee data",
                "API TOS", "Terms of service",
                new Contact("IT team", "www.activedgetechnologies.com", "loudsdata@gmail.com"),
                "License of API",
                "API license URL", Collections.emptyList());
    }
}
