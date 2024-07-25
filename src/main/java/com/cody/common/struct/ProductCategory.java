package com.cody.common.struct;

import com.cody.common.exception.request.RequestException;
import com.cody.common.exception.request.RequestExceptionCode;

import java.util.Arrays;

public enum ProductCategory {

    TOP("TOP"),
    OUTER("OUTER"),
    PANTS("PANTS"),
    SNEAKERS("SNEAKERS"),
    BAG("BAG"),
    HAT("HAT"),
    SOCKS("SOCKS"),
    ACCESSORY("ACCESSORY");

    private final String name;

    ProductCategory(final String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static ProductCategory fromName(final String value) {
        return Arrays.stream(ProductCategory.values())
                .filter(category -> category.name.equals(value))
                .findFirst()
                .orElseThrow(() -> new RequestException(RequestExceptionCode.ENUM_NOT_MATCH));
    }

}
