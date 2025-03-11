package com.resource.security;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.resource")
public class ResourceSecurityApplication {

    public static void main(String[] args) {
        SpringApplication.run(ResourceSecurityApplication.class, args);
    }

}
