package com.resource.core.context;

import org.springframework.stereotype.Component;

@Component
public class RxContextManager {

    private final ThreadLocal<String> requestId = new ThreadLocal<>();

    public void setRequestId(String id) {
        this.requestId.set(id);
    }

    public String getRequestId() {
        return this.requestId.get();
    }

    public void clear() {
        this.requestId.remove();
    }
}
