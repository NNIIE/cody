package com.cody.product.domain;

import com.cody.common.exception.request.RequestException;
import com.cody.common.exception.request.RequestExceptionCode;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Arrays;

public enum ProductCategory {

    TOP("top", "1"),
    OUTER("outer", "2"),
    PANTS("pants", "3"),
    SNEAKERS("sneakers", "4"),
    BAG("bag", "5"),
    HAT("hat", "6"),
    SOCKS("socks", "7"),
    ACCESSORY("accessory", "8");

    private final String name;
    private final String code;

    ProductCategory(final String name, final String code) {
        this.name = name;
        this.code = code;
    }

    @JsonValue
    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

    @JsonCreator
    public static ProductCategory fromName(final String value) {
        return Arrays.stream(ProductCategory.values())
                .filter(category -> category.name.equals(value))
                .findFirst()
                .orElseThrow(() -> new RequestException(RequestExceptionCode.ENUM_NOT_MATCH));
    }

}
