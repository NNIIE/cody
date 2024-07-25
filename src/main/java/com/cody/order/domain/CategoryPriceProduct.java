package com.cody.order.domain;

import com.cody.common.struct.ProductCategory;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.math.BigDecimal;

@Getter
@EqualsAndHashCode(of = {"category", "price"})
@ToString
public class CategoryPriceProduct {

    private final ProductCategory category;
    private final String brand;
    private final BigDecimal price;

    @Builder
    public CategoryPriceProduct(final ProductCategory category, final String brand, final BigDecimal price) {
        this.category = category;
        this.brand = brand;
        this.price = price;
    }

}
