package com.resource.core.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.List;
import java.util.Map;

/**
 * 元数据MetaData
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Accessors(chain = true)
public class Resource extends ExtMetaInfo {
    /**
     * 资源唯一id
     */
    private String resId;

    /**
     * 版本信息
     */
    private String version;

    /**
     * 标签信息
     */
    private List<String> tags;

    /**
     * 文件校验值（如 MD5）
     */
    private String checksum;

    //加密标识
    private boolean encryption;
}
