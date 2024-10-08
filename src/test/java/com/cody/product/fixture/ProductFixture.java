package com.cody.product.fixture;

import com.cody.common.struct.ProductCategory;
import com.cody.product.ui.request.ProductCreateRequest;
import com.cody.product.ui.request.ProductUpdateRequest;

import java.math.BigDecimal;

public class ProductFixture {

    public static ProductCreateRequest productCreateRequest(String brand, ProductCategory category, BigDecimal price) {
        return new ProductCreateRequest(brand, category, price);
    }

    public static ProductUpdateRequest productUpdateRequest(BigDecimal price) {
        return new ProductUpdateRequest(price);
    }

}
