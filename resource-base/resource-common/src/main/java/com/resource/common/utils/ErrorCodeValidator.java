package com.resource.common.utils;

public class ErrorCodeValidator {

    // 0~9999为系统专用
    private static final int SYSTEM_MIN = 0;
    private static final int SYSTEM_MAX = 9999;

    /**
     * 校验 code 是否在指定范围内
     */
    public static void validate(int code, int batchId) {
        if ((code >= SYSTEM_MIN && code <= SYSTEM_MAX) ||
                (code >= batchId && code <= batchId + SYSTEM_MAX)) {
            return; // 符合范围
        }
        throw new IllegalArgumentException("Invalid error code: " + code + ". Valid ranges are: " +
                "System: " + SYSTEM_MIN + "-" + SYSTEM_MAX);
    }
}