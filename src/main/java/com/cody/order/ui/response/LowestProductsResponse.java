package com.cody.order.ui.response;

import com.cody.cache.struct.CategoryPriceProduct;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class LowestProductsResponse {

    private BigDecimal totalPrice;
    private List<CategoryPriceProduct> products;

    public LowestProductsResponse(final List<CategoryPriceProduct> products) {
        this.products = products;
        this.totalPrice = calculateTotalPrice(products);
    }

    private BigDecimal calculateTotalPrice(final List<CategoryPriceProduct> products) {
        return products.stream()
                .distinct()
                .map(CategoryPriceProduct::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

}
