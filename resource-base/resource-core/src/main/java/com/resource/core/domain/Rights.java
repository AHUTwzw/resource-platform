package com.resource.core.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.resource.common.exception.BaseException;
import com.resource.common.utils.AESUtil;
import com.resource.common.utils.RSAUtil;
import lombok.Data;
import lombok.SneakyThrows;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Rights {
    // 许可证类型（如 CC BY 4.0, MIT License 等）
    private String licenseType;

    // 访问控制（如公开、私有、受限等）
    private String accessControl;

    // 使用限制（如禁止商业用途、禁止修改等）
    private List<String> arnRules;

    // 权限持有者
    private String accessKey;

    // 权限秘钥
    private String secretKey;

    // 临时凭证
    private String token;

    // 权限生效时间
    private String effectiveDate;

    // 权限过期时间
    private String expirationDate;

    @SneakyThrows
    public Rights genSecretKey2Rights() {
        if (this.accessKey == null || this.licenseType == null) {
            throw new BaseException(1001, "error");
        }
        String secretKey;
        switch (this.licenseType) {
            case "AES":
                this.secretKey = AESUtil.generateKey();
                break;
            default:
                throw new BaseException(1001, "error");
        }
        return this;
    }
}
