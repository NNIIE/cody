package com.cody.order.application;

import com.cody.cache.application.OrderCacheService;
import com.cody.common.struct.LowestBrand;
import com.cody.common.struct.ProductCategory;
import com.cody.order.domain.BestBrandProduct;
import com.cody.order.domain.CategoryPriceProduct;
import com.cody.order.infra.OrderRepository;
import com.cody.order.ui.response.BestBrandResponse;
import com.cody.order.ui.response.BestPriceResponse;
import com.cody.order.ui.response.LowestProductsResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderCacheService orderCacheService;

    public LowestProductsResponse getLowestProductsByCategory() {
        final List<CategoryPriceProduct> categoryPriceProducts = orderCacheService.getLowestProducts();

        return new LowestProductsResponse(categoryPriceProducts);
    }

    @Transactional(readOnly = true)
    public BestBrandResponse getBestBrand() {
        final LowestBrand lowestBrand = orderCacheService.getLowestBrand();
        final List<BestBrandProduct> bestBrandProduct = orderRepository.getBestBrand(lowestBrand.name());

        return new BestBrandResponse(lowestBrand.name(), bestBrandProduct);
    }

    @Transactional(readOnly = true)
    public BestPriceResponse getBestPriceToCategory(final ProductCategory category) {
        final List<CategoryPriceProduct> lowestProducts = orderCacheService.getLowestProducts();
        final List<CategoryPriceProduct> highestProducts = orderCacheService.getHighestProducts();

        return new BestPriceResponse(category, lowestProducts, highestProducts);
    }

}
