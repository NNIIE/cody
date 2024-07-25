package com.cody.order.ui.response;

import com.cody.cache.struct.CategoryPriceProduct;
import com.cody.common.exception.server.ServerException;
import com.cody.common.exception.server.ServerExceptionCode;
import com.cody.common.struct.ProductCategory;
import com.cody.order.domain.BestPriceProduct;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Comparator;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)

@ToString
public class BestPriceResponse {

    private ProductCategory category;
    private BestPriceProduct lowestProduct;
    private BestPriceProduct highestProduct;

    public BestPriceResponse(
            final ProductCategory category,
            final List<CategoryPriceProduct> lowestProducts,
            final List<CategoryPriceProduct> highestProducts
    ) {
        this.category = category;
        this.lowestProduct = findLowestProduct(lowestProducts);
        this.highestProduct = findHighestProduct(highestProducts);
    }

    private BestPriceProduct findLowestProduct(final List<CategoryPriceProduct> lowestProducts) {
        return lowestProducts.stream()
                .filter(product -> product.getCategory().equals(category))
                .min(Comparator.comparing(CategoryPriceProduct::getPrice))
                .map(BestPriceProduct::new)
                .orElseThrow(() -> new ServerException(ServerExceptionCode.CACHE_MISS));
    }

    private BestPriceProduct findHighestProduct(final List<CategoryPriceProduct> highestProduct) {
        return highestProduct.stream()
                .filter(product -> product.getCategory().equals(category))
                .max(Comparator.comparing(CategoryPriceProduct::getPrice))
                .map(BestPriceProduct::new)
                .orElseThrow(() -> new ServerException(ServerExceptionCode.CACHE_MISS));
    }

}
