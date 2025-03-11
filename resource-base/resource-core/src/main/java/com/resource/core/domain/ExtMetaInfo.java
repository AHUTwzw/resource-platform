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
public class ExtMetaInfo extends Version {

    /**
     * 标签信息
     */
    private List<String> tags;

    /**
     * 额外信息
     */
    private Map<String, String> additional;

    /**
     * 自定义属性
     */
    private Map<String, String> properties;

    /**
     * 自定义属性
     */
    private Map<String, String> attributes;
}
