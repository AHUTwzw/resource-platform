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
public class ExtMetaInfo extends DublinCoreResource {

    // 额外权限信息（使用 Tuple 存储）
    private Map<String, String> additional;

    //自定义属性
    private Map<String, String> properties;

    //自定义属性
    private Map<String, String> attributes;

    //自定义属性
    private Map<String, List<String>> attributes2;

    //自定义属性
    private Map<String, Map<String, String>> attributes3;

    //自定义属性
    private Map<String, Map<String, List<String>>> attributes4;
}
