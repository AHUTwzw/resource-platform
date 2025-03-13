package com.resource.core.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.resource.common.annotation.Domain;
import com.resource.common.utils.VersionUtil;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.Date;
import java.util.UUID;

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
    private Boolean encryption;

    public void create() {
        this.setId(UUID.randomUUID().toString());
        this.setVersion(VersionUtil.getInitialVersion());
        this.setCreatTime(new Date());
    }

    public void update(Resource oldResource) {
        this.setUpdateTime(new Date());
        this.setId(oldResource.getId());
        this.setUri(oldResource.getUri());
        this.setCreatTime(oldResource.getCreatTime());
        this.setCreator(oldResource.getCreator());
        this.setVersion(VersionUtil.getNextVersion(oldResource.getVersion()));
    }
}
