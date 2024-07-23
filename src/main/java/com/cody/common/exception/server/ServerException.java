package com.cody.common.exception.server;

import lombok.Getter;

@Getter
public class ServerException extends RuntimeException {

    private final ServerExceptionCode code;

    public ServerException(final ServerExceptionCode code) {
        this.code = code;
    }

}
