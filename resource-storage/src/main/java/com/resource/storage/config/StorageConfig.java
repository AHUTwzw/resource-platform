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
    public IStorageService ossStorageService(OSSConfig ossConfig) {
        return new OSSStorageService(ossConfig.getEndpoint(), ossConfig.getAccessKeyId(),ossConfig.getAccessKeySecret(), ossConfig.getRoleArn(), ossConfig.getRoleSessionName());
    }

    @Bean
    public IStorageService minIOStorageService(OSSConfig ossConfig) {
        return new MinIOStorageService(ossConfig.getEndpoint(), ossConfig.getAccessKeyId(),ossConfig.getAccessKeySecret());
    }
}
