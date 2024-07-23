package com.cody.product.application;

import com.cody.product.domain.entity.Product;
import com.cody.product.infra.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public void createProduct(final Product product) {
        productRepository.createProduct(product);
    }

}
