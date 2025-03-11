package com.resource.security;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.resource")
public class ResourceStorageApplication {

    public static void main(String[] args) {
        SpringApplication.run(ResourceStorageApplication.class, args);
    }

}
