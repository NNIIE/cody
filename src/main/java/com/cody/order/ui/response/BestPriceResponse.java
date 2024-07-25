package com.cody.order.ui.response;

import com.cody.common.struct.ProductCategory;

import java.util.List;

public record BestPriceResponse(ProductCategory category,
                                List<BestPriceProduct> lowestProduct,
                                List<BestPriceProduct> highestProduct) {
}
