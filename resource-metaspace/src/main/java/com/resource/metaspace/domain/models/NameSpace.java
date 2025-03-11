package com.resource.metaspace.domain.models;

import com.resource.core.domain.Resource;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@Accessors(chain = true)
public class NameSpace extends Resource {
}
