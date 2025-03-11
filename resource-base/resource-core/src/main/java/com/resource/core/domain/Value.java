package com.resource.core.domain;

import lombok.experimental.Accessors;

@Accessors(chain = true)
public interface Value {

    String getDomain();
}
