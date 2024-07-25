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

    /**
     * 예제로 주신 데이터에 최저가 중복이 있습니다. (스니커즈)
     * 총액에 대한 최저가 제품 중복처리 대한 요구사항이 없어서
     * 유저에게 최저가 리스트는 중복이 있어도 그대로 보여주고, 총액에서는 중복제거 하는 방식으로 설계했습니다.
     * 이방식이 유저의 니즈에 제일 적합하다고 생각했습니다.
     */
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
