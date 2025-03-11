package com.resource.storage.config;

import com.resource.storage.domain.service.IStorageService;
import com.resource.storage.domain.service.impl.MinIOStorageService;
import com.resource.storage.domain.service.impl.OSSStorageService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class StorageConfig {

    @Bean
    @Primary
    public IStorageService ossStorageService() {
        return new OSSStorageService("", "","your-oss-endpoint", "your-access-key-id", "your-access-key-secret");
    }

    @Bean
    public IStorageService minIOStorageService() {
        return new MinIOStorageService("your-minio-endpoint", "your-access-key", "your-secret-key");
    }
}
