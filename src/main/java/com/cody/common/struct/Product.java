package com.cody.common.struct;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.math.BigDecimal;

@Getter
@ToString
public class Product {

    private final Long id;
    private final String brand;
    private final ProductCategory category;
    private final BigDecimal price;

    @Builder
    public Product(final Long id, final String brand, final ProductCategory category, final BigDecimal price) {
        this.id = id;
        this.brand = brand;
        this.category = category;
        this.price = price;
    }

}
