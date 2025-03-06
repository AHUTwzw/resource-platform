package com.resource.application.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Locale;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI(MicroServiceConfig microServiceConfig) {
        return new OpenAPI()
                .info(new Info()
                        .title(String.format("%s WEB API", microServiceConfig.getAppName().toUpperCase(Locale.ROOT)))
                        .version("1.0")
                        .description("API文档"));
    }
}
