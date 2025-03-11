package com.resource.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "meta.service")
public class MetaConfig {
    /**
     * 命名空间-
     */
    private String namespace;
    /**
     * 业务号：领域层
     */
    private String business;
    private String appKey;
    private String appSecret;
}
