package com.cody.product.domain.entity;

import com.cody.product.domain.ProductCategory;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.math.BigDecimal;

@Getter
@ToString
public class Product {

    private Long id;
    private String brand;
    private String category;
    private BigDecimal price;

    @Builder
    public Product(final Long id, final String brand, final ProductCategory category, final BigDecimal price) {
        this.id = id;
        this.brand = brand;
        this.category = category.getName();
        this.price = price;
    }

}
