package com.resource.common.exception;

/**
 * 自定义基础异常类，继承自 RuntimeException
 */
public class BaseException extends RuntimeException {
    // 错误码
    private final String errorCode;
    // 错误信息
    private final String errorMessage;

    /**
     * 构造方法
     *
     * @param errorCode    错误码
     * @param errorMessage 错误信息
     */
    public BaseException(String errorCode, String errorMessage) {
        super(errorMessage); // 调用父类构造方法，设置异常信息
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    /**
     * 构造方法（支持传入异常原因）
     *
     * @param errorCode    错误码
     * @param errorMessage 错误信息
     * @param cause        异常原因
     */
    public BaseException(String errorCode, String errorMessage, Throwable cause) {
        super(errorMessage, cause); // 调用父类构造方法，设置异常信息和原因
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    /**
     * 获取错误码
     *
     * @return 错误码
     */
    public String getErrorCode() {
        return errorCode;
    }

    /**
     * 获取错误信息
     *
     * @return 错误信息
     */
    public String getErrorMessage() {
        return errorMessage;
    }

    @Override
    public String toString() {
        return "BaseException{" +
                "errorCode='" + errorCode + '\'' +
                ", errorMessage='" + errorMessage + '\'' +
                '}';
    }
}
