package com.cody.product.application;

import com.cody.cache.application.OrderCacheService;
import com.cody.common.struct.Product;
import com.cody.product.infra.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final OrderCacheService orderCacheService;

    @Transactional(readOnly = true)
    public List<Product> getProductsByBrand(final String brand) {
        return productRepository.getProductsByBrand(brand);
    }

    @Transactional
    public void createProduct(final Product product) {
        productRepository.createProduct(product);
        orderCacheService.reCache();
    }

    @Transactional
    public void updateProduct(final Long id, final BigDecimal updatePrice) {
        productRepository.updateProduct(id, updatePrice);
        orderCacheService.reCache();
    }

    @Transactional
    public void deleteProduct(final Long id) {
        productRepository.deleteProduct(id);
        orderCacheService.reCache();
    }

}
