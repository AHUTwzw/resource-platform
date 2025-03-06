package com.resource.core.domain;

import co.elastic.clients.elasticsearch._types.GeoLocation;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class Coverage {
    // 地理信息（可以是GeoPoint、GeoShape等）
    private GeoLocation geoLocation;
    // 时间描述（可以是时间点、时间范围、时间区间等）
    private TimeDescription timeDes;
}
