package com.cody.common.exception.server;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ServerExceptionCode {

    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, 1000, "Internal server error."),
    SQL_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, 1001, "Internal server error."),
    CACHE_MISS(HttpStatus.INTERNAL_SERVER_ERROR, 1002, "Internal server error."),
    JSON_PARSER_EXCEPTION(HttpStatus.INTERNAL_SERVER_ERROR, 1003, "JSON parsing error");

    private final HttpStatus status;
    private final int code;
    private final String message;

    ServerExceptionCode(final HttpStatus status, final int code, final String message) {
        this.status = status;
        this.code = code;
        this.message = message;
    }

}
