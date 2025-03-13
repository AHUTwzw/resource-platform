package com.resource.configuration;

import com.resource.common.exception.BaseException;
import com.resource.common.vo.RD;
import jakarta.annotation.PostConstruct;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import reactor.core.publisher.Mono;

@ControllerAdvice
public class GlobalExceptionHandler {

    @PostConstruct
    public void init() {
        System.out.println("GlobalExceptionHandler init.");
    }

    @ExceptionHandler(BaseException.class)
    public ResponseEntity<Mono<RD<String>>> handleBaseException(BaseException ex) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(RD.err(ex));
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Mono<RD<String>>> handleRuntimeException(RuntimeException ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(RD.err(ex));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Mono<RD<String>>> handleException(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(RD.err(ex));
    }
}
