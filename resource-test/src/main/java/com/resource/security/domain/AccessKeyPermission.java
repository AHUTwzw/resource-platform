package com.resource.security.domain;

import lombok.Data;

import java.util.List;

@Data
public class AccessKeyPermission {
    private String accessKey;
    private String accessSecret;
    private List<String> arnRules; // 权限范围
    private String description;
}
