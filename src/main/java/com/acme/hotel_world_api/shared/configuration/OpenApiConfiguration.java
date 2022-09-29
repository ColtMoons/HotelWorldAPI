package com.acme.hotel_world_api.shared.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class OpenApiConfiguration {
    @Bean(name = "hotelOpenApi")
    public OpenAPI hotelOpenApi(){
        return new OpenAPI().components(new Components()).info(new Info().title("HotelWorld Aplication API").description("Hotel World API implemented with SpringBoot RESTful service and documented using springdoc-openapi and OpenAPI 3.0"));
    }
}
