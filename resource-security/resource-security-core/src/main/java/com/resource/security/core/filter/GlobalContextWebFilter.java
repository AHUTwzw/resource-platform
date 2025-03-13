package com.resource.security.core.filter;

import com.resource.common.constant.HeaderConstant;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

public class GlobalContextWebFilter implements WebFilter {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        // 从请求头中提取用户ID
        String userId = exchange.getRequest().getHeaders().getFirst(HeaderConstant.X_AUTH_USERID);
        String accessKey = exchange.getRequest().getHeaders().getFirst(HeaderConstant.X_AUTH_ACCESS_KEY);

        // 将用户ID存储到上下文中
        return chain.filter(exchange)
                .contextWrite(context -> context.put("userId", userId));
    }
}
