package com.cody.common.exception;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ExceptionResponse {

    private final int code;
    private final String message;
    private final String description;

    @Builder
    public ExceptionResponse(final int code, final String message, final String description) {
        this.code = code;
        this.message = message;
        this.description = description;
    }

}
