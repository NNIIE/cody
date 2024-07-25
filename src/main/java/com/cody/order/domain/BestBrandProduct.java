package com.cody.order.domain;

import com.cody.common.struct.ProductCategory;

import java.math.BigDecimal;

public record BestBrandProduct(ProductCategory category, BigDecimal price) {
}
