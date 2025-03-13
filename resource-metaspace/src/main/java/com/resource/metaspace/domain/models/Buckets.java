package com.resource.metaspace.domain.models;

import com.resource.common.annotation.Domain;
import com.resource.core.domain.Resource;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@Accessors(chain = true)
@Domain("buckets")
public class Buckets extends Resource {
}
