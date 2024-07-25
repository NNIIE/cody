package com.cody.order.ui.response;

import com.cody.order.domain.BestBrandProduct;
import lombok.Getter;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.List;

@Getter
@ToString
public final class BestBrandResponse {

    private final String brand;
    private final List<BestBrandProduct> products;
    private final BigDecimal totalPrice;

    public BestBrandResponse(final String brand, final List<BestBrandProduct> products) {
        this.brand = brand;
        this.products = products;
        this.totalPrice = calculateTotalPrice(products);
    }

    private BigDecimal calculateTotalPrice(final List<BestBrandProduct> products) {
        return products.stream()
                .map(BestBrandProduct::price)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

}
