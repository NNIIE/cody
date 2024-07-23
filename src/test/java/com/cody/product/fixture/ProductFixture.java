package com.cody.product.fixture;

import com.cody.product.domain.ProductCategory;
import com.cody.product.domain.entity.Product;
import com.cody.product.ui.request.ProductCreateRequest;

import java.math.BigDecimal;

public class ProductFixture {

    public static ProductCreateRequest productCreateRequest(String brand, ProductCategory category, BigDecimal price) {
        return new ProductCreateRequest(brand, category, price);
    }

}
