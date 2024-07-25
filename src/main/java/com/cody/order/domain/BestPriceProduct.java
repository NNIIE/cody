package com.cody.order.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public final class BestPriceProduct {

    private String brand;
    private BigDecimal price;

    public BestPriceProduct(final CategoryPriceProduct categoryPriceProduct) {
        this.brand = categoryPriceProduct.getBrand();
        this.price = categoryPriceProduct.getPrice();
    }

}
