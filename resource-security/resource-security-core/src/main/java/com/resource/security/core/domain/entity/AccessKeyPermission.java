package com.resource.security.core.domain.entity;

import com.resource.common.annotation.Domain;
import com.resource.core.domain.Resource;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@Domain("sts")
public class AccessKeyPermission extends Resource {
}
