package com.resource.application.config;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MicroServiceConfig {
    private final ApplicationContext context;

    public MicroServiceConfig(ApplicationContext context) {
        this.context = context;
    }

    public String getAppName() {
        return context.getEnvironment().getProperty("spring.application.name");
    }
}
