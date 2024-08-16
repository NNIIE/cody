package com.cody.order.ui;

import com.cody.common.struct.ProductCategory;
import com.cody.order.application.OrderService;
import com.cody.order.ui.response.BestBrandResponse;
import com.cody.order.ui.response.BestPriceResponse;
import com.cody.order.ui.response.LowestProductsResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Tag(name = "Order")
@RequestMapping("/order")
public class OrderController {

    private final OrderService orderService;

    @GetMapping("lowest-product")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "카테고리 별 최저가 브랜드의 상품가격 및 총액 조회")
    public LowestProductsResponse getLowestProductByCategory() {
        return orderService.getLowestProductsByCategory();
    }

    @GetMapping("/best-brand")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "모든상품 최저가 브랜드 상품가격 및 총액 조회")
    public BestBrandResponse getBestBrand() {
        return orderService.getBestBrand();
    }

    @GetMapping("/category/{category}/best-price")
    @Operation(summary = "최저, 최고 가격 브랜드와 상품 가격 조회")
    @ResponseStatus(HttpStatus.OK)
    public BestPriceResponse getBestPriceToCategory(@PathVariable final ProductCategory category) {
        return orderService.getBestPriceToCategory(category);
    }

}
