package com.resource.common.constant;

import com.resource.common.utils.ErrorCodeValidator;
import lombok.Getter;

import java.util.Arrays;

@Getter
public enum ErrorCode implements IErrorCode {

    SUCCESS(200, "success"),
    NOT_FOUND(404, "not found");

    private final int code;
    private final String message;

    ErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public void validate(int batchId) {
        Arrays.stream(ErrorCode.values()).forEach(errorCode ->
                ErrorCodeValidator.validate(errorCode.getCode(), batchId));
    }
}
