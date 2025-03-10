package com.resource.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.resource")
public class ResourceMetaSpaceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ResourceMetaSpaceApplication.class, args);
    }

}
