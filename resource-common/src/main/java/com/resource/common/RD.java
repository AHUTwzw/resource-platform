package com.resource.common;

import lombok.Data;
import reactor.core.publisher.Mono;

@Data
public class RD<T> {
    private int code;       // 状态码
    private String message; // 消息
    private T data;         // 数据

    // 构造方法
    public RD(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    /**
     * 将 Mono<T> 包装为 ApiResponse<T>
     *
     * @param mono   原始 Mono<T>
     * @param code   状态码
     * @param message 消息
     * @return 包装后的 Mono<ApiResponse<T>>
     */
    public static <T> Mono<RD<T>> wrapMono(Mono<T> mono, int code, String message) {
        return mono
                .map(data -> new RD<>(code, message, data))
                .switchIfEmpty(Mono.just(new RD<>(code, message, null)))
                .onErrorResume(e -> Mono.just(new RD<>(500, "Internal Server Error: " + e.getMessage(), null)));
    }

    /**
     * 默认成功返回的包装方法
     *
     * @param mono 原始 Mono<T>
     * @return 包装后的 Mono<ApiResponse<T>>
     */
    public static <T> Mono<RD<T>> ok(Mono<T> mono) {
        return wrapMono(mono, 200, "Success");
    }

    /**
     * 默认错误返回的包装方法
     *
     * @param e 异常
     * @return 包装后的 Mono<ApiResponse<T>>
     */
    public static <T> Mono<RD<T>> err(Throwable e) {
        return Mono.just(new RD<>(500, "Internal Server Error: " + e.getMessage(), null));
    }

    @Override
    public String toString() {
        return "RD{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}
