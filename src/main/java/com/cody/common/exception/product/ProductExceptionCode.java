package com.cody.common.exception.product;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ProductExceptionCode {

    PRODUCT_NOT_FOUND(HttpStatus.BAD_REQUEST, 3000, "Product not found.");

    private final HttpStatus status;
    private final int code;
    private final String message;

    ProductExceptionCode(final HttpStatus status, final int code, final String message) {
        this.status = status;
        this.code = code;
        this.message = message;
    }

}
