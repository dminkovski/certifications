package com.davidminkovski.certifications.config;

import org.springframework.context.annotation.Configuration;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;

@Configuration
public class SwaggerConfig {
    private ApiInfo apiInfo(){
        return new ApiInfo("Spring Boot Rest APIs",
                "API Documentation",
                "1",
                "Terms of service",
                "David minkovski",
                "License API",
                "License URL"
        );
    }

}
