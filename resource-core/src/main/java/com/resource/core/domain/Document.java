package com.resource.core.domain;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class Document implements Value {
    /**
     * 资源唯一id
     * 对于数据库唯一id，（mysql的自增主键 es-doc的_id）
     * 逻辑主键,不建议用作业务处理
     */
    private String id;
}
