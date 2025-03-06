package com.resource.core.domain;

import java.util.List;

public class Rights {
    // 许可证类型（如 CC BY 4.0, MIT License 等）
    private String licenseType;

    // 访问控制（如公开、私有、受限等）
    private String accessControl;

    // 使用限制（如禁止商业用途、禁止修改等）
    private List<String> usageRestrictions;

    // 权限持有者
    private String rightsHolder;

    // 权限生效时间
    private String effectiveDate;

    // 权限过期时间
    private String expirationDate;
}
