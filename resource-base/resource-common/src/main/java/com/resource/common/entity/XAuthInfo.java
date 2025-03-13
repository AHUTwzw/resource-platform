package com.resource.common.entity;

import lombok.Data;

@Data
public class XAuthInfo {
    private String userId;
    private String accessKey;
    private String token;
    private String signature;
}
