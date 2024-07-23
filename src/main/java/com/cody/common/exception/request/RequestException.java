package com.cody.common.exception.request;

import lombok.Getter;

@Getter
public class RequestException extends RuntimeException {

    private final RequestExceptionCode code;

    public RequestException(final RequestExceptionCode code) {
        this.code = code;
    }

}
