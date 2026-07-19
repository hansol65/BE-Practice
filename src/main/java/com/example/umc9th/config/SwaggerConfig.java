package com.example.umc9th.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI swagger() {
        Info info = new Info()
                .title("UMC 9th API")
                .description("UMC 9th Spring Boot 프로젝트 API 명세서")
                .version("1.0.0");

        return new OpenAPI()
                .info(info)
                .addServersItem(
                        new Server().url("/")
                );
    }
}
