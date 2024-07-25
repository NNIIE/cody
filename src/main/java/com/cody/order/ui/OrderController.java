package com.cody.order.ui;

import com.cody.common.struct.ProductCategory;
import com.cody.order.application.OrderService;
import com.cody.order.ui.response.BestPriceResponse;
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

    @GetMapping("/category/{category}/best-price")
    @ResponseStatus(HttpStatus.OK)
    public BestPriceResponse getBestPriceToCategory(@PathVariable final ProductCategory category) {
        return orderService.getBestPriceToCategory(category);
    }


}
