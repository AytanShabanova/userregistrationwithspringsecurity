package com.example.userserviceapidesign.config.swagger;


import io.swagger.v3.oas.annotations.OpenAPIDefinition;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition
public class SpringDocumentConfig {
    @Bean
    public OpenAPI openAPI(){
        return new OpenAPI().info(new Info().title("Şabanova Aytan tərəfindən Documentasiya")
                .version("0.0.1").description("user service apidesign with security")
                .contact(new Contact()
                        .url("www.google.com")
                        .email("shabanovaaytan@gmail.com")
                        .name("Şabanova Aytan")));
    }
}

