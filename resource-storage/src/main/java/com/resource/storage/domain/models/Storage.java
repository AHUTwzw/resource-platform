package com.resource.storage.domain.models;

import com.resource.common.annotation.Domain;
import com.resource.core.domain.Resource;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@Domain("storage")
public class Storage extends Resource {
}
