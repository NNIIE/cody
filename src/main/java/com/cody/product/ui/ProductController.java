package com.cody.product.ui;

import com.cody.product.application.ProductService;
import com.cody.product.domain.entity.Product;
import com.cody.product.infra.ProductMapper;
import com.cody.product.ui.request.ProductCreateRequest;
import com.cody.product.ui.request.ProductUpdateRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Tag(name = "Product")
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;
    private final ProductMapper productMapper;

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "상품 등록")
    public void createProduct(@RequestBody @Valid final ProductCreateRequest request) {
        final Product product = productMapper.product(request);
        productService.createProduct(product);
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "상품 수정")
    public void updateProduct(
            @PathVariable final Long id,
            @RequestBody @Valid final ProductUpdateRequest request
    ) {
        productService.updateProduct(id, request.getPrice());
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "상품 삭제")
    public void deleteProduct(@PathVariable final Long id) {
        productService.deleteProduct(id);
    }

}
