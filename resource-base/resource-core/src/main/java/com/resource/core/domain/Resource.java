package com.resource.core.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.resource.common.annotation.Domain;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * 元数据MetaData
 */
@EqualsAndHashCode(callSuper = true)
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@Accessors(chain = true)
@Domain("resource")
public class Resource extends ExtMetaInfo {

    /**
     * 文件校验值（如 MD5）
     */
    private String checksum;

    /**
     * 加密标识
     */
    private boolean encryption;
}
