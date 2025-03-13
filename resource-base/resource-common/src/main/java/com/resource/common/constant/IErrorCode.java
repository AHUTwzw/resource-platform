package com.resource.common.constant;

public interface IErrorCode {

    int getCode();
    String getMessage();
    void validate(int batchId);
}
