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
public class Version extends DublinCoreResource{
    /**
     * 版本信息Id
     */
    private String versionId;

    /**
     * 版本信息
     */
    private String version;

    /**
     * 标签信息
     */
    private String tag;

    /**
     * 标签信息
     */
    private List<String> tags;
}
