package com.resource.admin.domain.models;

import com.resource.core.domain.Resource;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@EqualsAndHashCode(callSuper = true)
@Data
@Accessors(chain = true)
public class Buckets extends Resource {
}
