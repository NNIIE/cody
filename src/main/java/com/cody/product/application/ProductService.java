package com.cody.product.application;

import com.cody.product.domain.entity.Product;
import com.cody.product.infra.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    @Transactional
    public void createProduct(final Product product) {
        productRepository.createProduct(product);
    }

    @Transactional
    public void updateProduct(final Long id, final BigDecimal updatePrice) {
        productRepository.updateProduct(id, updatePrice);
    }

    @Transactional
    public void deleteProduct(final Long id) {
        productRepository.deleteProduct(id);
    }

}
