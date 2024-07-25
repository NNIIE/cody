package com.cody.order.application;

import com.cody.common.struct.ProductCategory;
import com.cody.order.infra.OrderRepository;
import com.cody.order.ui.response.BestPriceProduct;
import com.cody.order.ui.response.BestPriceResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    @Transactional(readOnly = true)
    public BestPriceResponse getBestPriceToCategory(final ProductCategory category) {
        final List<BestPriceProduct> lowestProduct = orderRepository.getLowestProductToCategory(category);
        final List<BestPriceProduct> highestProduct = orderRepository.getHighestProductToCategory(category);

        return new BestPriceResponse(category, lowestProduct, highestProduct);
    }

}
