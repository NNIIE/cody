package com.cody.order.application;

import com.cody.order.domain.CategoryPriceProduct;
import com.cody.common.struct.LowestBrand;
import com.cody.order.infra.OrderCacheProviderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderCacheProviderService {

    private final OrderCacheProviderRepository orderCacheProviderRepository;

    public LowestBrand getLowestBrand() {
        return orderCacheProviderRepository.getLowestBrand();
    }

    public List<CategoryPriceProduct> getLowestProductsByCategory() {
        return orderCacheProviderRepository.getLowestProductsByCategory();
    }

    public List<CategoryPriceProduct> getHighestProductsByCategory() {
        return orderCacheProviderRepository.getHighestProductsByCategory();
    }

}
