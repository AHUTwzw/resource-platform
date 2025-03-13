package com.resource.core.domain;

import com.resource.common.constant.MediaType;
import lombok.Data;

import java.util.Map;

@Data
public class Format {
    /**
     * 媒体类型，例如 "image/jpeg", "text/html"
     */
    private MediaType mediaType;

    /**
     * 所需软件
     */
    private String softwareRequirements;

    /**
     * 所需硬件
     */
    private String hardwareRequirements;

    /**
     * 文件大小，单位可以是字节等
     */
    private Long fileSize;

    /**
     * 文件校验值（如 MD5）非必须
     */
    private String checksum;

    /**
     * 其他信息
     */
    private Map<String, String> additionalInformation;
}
