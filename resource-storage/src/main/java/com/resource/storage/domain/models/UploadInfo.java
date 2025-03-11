package com.resource.storage.domain.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UploadInfo {
    private STSInfo sts;
    private String uploadUrl;
    private OSSMetadata ossMetadata;
}
