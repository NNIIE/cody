package com.cody.product.domain.entity;

import com.cody.product.domain.ProductCategory;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.math.BigDecimal;

@Getter
@ToString
public class Product {

    private final String brand;
    private final String category;
    private final BigDecimal price;

    @Builder
    public Product(final String brand, final ProductCategory category, final BigDecimal price) {
        this.brand = brand;
        this.category = category.getCode();
        this.price = price;
    }

}
