package com.resource.storage.domain.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OSSMetadata {
    private String bucketName;
    private String objectKey;
    private String endpoint;
}
