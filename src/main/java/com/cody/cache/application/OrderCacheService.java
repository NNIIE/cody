package com.cody.cache.application;

import com.cody.cache.infra.CodyApiCache;
import com.cody.order.domain.CategoryPriceProduct;
import com.cody.common.struct.LowestBrand;
import com.cody.order.application.OrderCacheProviderService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderCacheService {

    /**
     * 관리자가 브랜드 및 상품을 추가/업데이트/삭제 하는 트래픽보다
     * 유저가 조회하는 트래픽이 월등할것 같아서 데이터를 캐싱하는 전략을 사용했습니다.
     */

    private final OrderCacheProviderService orderCacheProviderService;
    private final CodyApiCache codyApiCache;

    private static final String LOWEST_BRAND = "lowestBrand";
    private static final String LOWEST_PRODUCTS = "lowestProducts";
    private static final String HIGHEST_PRODUCTS = "highestProducts";

    @PostConstruct
    public void cacheLowestBrand() {
        final LowestBrand lowestBrand = orderCacheProviderService.getLowestBrand();
        codyApiCache.put(LOWEST_BRAND, lowestBrand);
    }

    @PostConstruct
    public void cacheLowestProductsByCategory() {
        List<CategoryPriceProduct> categoryPriceProducts = orderCacheProviderService.getLowestProductsByCategory();
        codyApiCache.put(LOWEST_PRODUCTS, categoryPriceProducts);
    }

    @PostConstruct
    public void cacheHighestProductsByCategory() {
        List<CategoryPriceProduct> highestProducts = orderCacheProviderService.getHighestProductsByCategory();
        codyApiCache.put(HIGHEST_PRODUCTS, highestProducts);
    }

    public LowestBrand getLowestBrand() {
        LowestBrand lowestBrand = codyApiCache.getObject(LOWEST_BRAND, LowestBrand.class);

        if (lowestBrand == null) {
            cacheLowestBrand();
        }

        return codyApiCache.getObject(LOWEST_BRAND, LowestBrand.class);
    }

    public List<CategoryPriceProduct> getLowestProducts() {
        List<CategoryPriceProduct> categoryPriceProducts = codyApiCache.getList(LOWEST_PRODUCTS, CategoryPriceProduct.class);

        if (categoryPriceProducts == null) {
            cacheLowestProductsByCategory();
        }

        return codyApiCache.getList(LOWEST_PRODUCTS, CategoryPriceProduct.class);
    }

    public List<CategoryPriceProduct> getHighestProducts() {
        List<CategoryPriceProduct> categoryPriceProducts = codyApiCache.getList(HIGHEST_PRODUCTS, CategoryPriceProduct.class);

        if (categoryPriceProducts == null) {
            cacheHighestProductsByCategory();
        }

        return codyApiCache.getList(HIGHEST_PRODUCTS, CategoryPriceProduct.class);
    }

    public void reCache() {
        codyApiCache.remove(LOWEST_BRAND);
        codyApiCache.remove(LOWEST_PRODUCTS);
        codyApiCache.remove(HIGHEST_PRODUCTS);

        cacheLowestBrand();
        cacheLowestProductsByCategory();
        cacheHighestProductsByCategory();
    }

}
