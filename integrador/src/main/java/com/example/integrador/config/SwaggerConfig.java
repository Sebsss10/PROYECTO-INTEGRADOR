package com.example.integrador.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(info = @Info(title = "API Integrador", version = "1.0", description = "API for Proyecto Integrador"))
public class SwaggerConfig {
}