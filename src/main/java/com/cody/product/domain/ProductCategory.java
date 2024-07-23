package com.cody.product.domain;

import com.cody.common.exception.request.RequestException;
import com.cody.common.exception.request.RequestExceptionCode;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Arrays;

public enum ProductCategory {

    TOP("top"),
    OUTER("outer"),
    PANTS("pants"),
    SNEAKERS("sneakers"),
    BAG("bag"),
    HAT("hat"),
    SOCKS("socks"),
    ACCESSORY("accessory");

    private final String name;

    ProductCategory(final String name) {
        this.name = name;
    }

    @JsonValue
    public String getName() {
        return name;
    }


    @JsonCreator
    public static ProductCategory fromName(final String value) {
        return Arrays.stream(ProductCategory.values())
                .filter(category -> category.name.equals(value))
                .findFirst()
                .orElseThrow(() -> new RequestException(RequestExceptionCode.ENUM_NOT_MATCH));
    }

}
