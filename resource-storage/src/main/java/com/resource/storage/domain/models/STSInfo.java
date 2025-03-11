package com.resource.storage.domain.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class STSInfo {
    private String accessKeyId;
    private String accessKeySecret;
    private String securityToken;
    private String expiration;
}
