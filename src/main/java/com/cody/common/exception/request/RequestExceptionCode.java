package com.cody.common.exception.request;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum RequestExceptionCode {

    PARAMETER_BINDING(HttpStatus.BAD_REQUEST, 2000, "Request parameter is invalid."),
    ENUM_NOT_MATCH(HttpStatus.BAD_REQUEST, 2001, "type match error.");

    private final HttpStatus status;
    private final int code;
    private final String message;

    RequestExceptionCode(final HttpStatus status, final int code, final String message) {
        this.status = status;
        this.code = code;
        this.message = message;
    }

}
