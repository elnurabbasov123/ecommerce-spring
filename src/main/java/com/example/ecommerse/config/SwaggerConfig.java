package com.example.ecommerse.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(info = @Info(
        title = "ecommerce",
        description = "lorem ipsum",
        contact = @Contact(name = "cabbarovali",email = "ali.cab@div.edu.az"),
        version = "1.0.0"
))
public class SwaggerConfig {
}
