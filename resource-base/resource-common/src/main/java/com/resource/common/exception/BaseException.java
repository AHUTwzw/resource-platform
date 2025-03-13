package com.resource.common.exception;

import com.resource.common.constant.IErrorCode;
import lombok.Getter;

/**
 * 自定义基础异常类，继承自 RuntimeException
 */
@Getter
public class BaseException extends RuntimeException {
    /**
     * -- GETTER --
     *  获取错误码
     *
     * @return 错误码
     */
    // 错误码
    private final int errorCode;
    /**
     * -- GETTER --
     *  获取错误信息
     *
     * @return 错误信息
     */
    // 错误信息
    private final String errorMessage;

    /**
     * 构造方法
     *
     * @param errorCode    错误码
     * @param errorMessage 错误信息
     */
    public BaseException(int errorCode, String errorMessage) {
        super(errorMessage); // 调用父类构造方法，设置异常信息
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    /**
     * 构造方法
     *
     * @param errorCode    错误码
     */
    public BaseException(IErrorCode errorCode) {
        super(errorCode.getMessage()); // 调用父类构造方法，设置异常信息
        this.errorCode = errorCode.getCode();
        this.errorMessage = errorCode.getMessage();
    }

    /**
     * 构造方法（支持传入异常原因）
     *
     * @param errorCode    错误码
     * @param errorMessage 错误信息
     * @param cause        异常原因
     */
    public BaseException(int errorCode, String errorMessage, Throwable cause) {
        super(errorMessage, cause); // 调用父类构造方法，设置异常信息和原因
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    @Override
    public String toString() {
        return "BaseException{" +
                "errorCode='" + errorCode + '\'' +
                ", errorMessage='" + errorMessage + '\'' +
                '}';
    }
}
