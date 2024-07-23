package com.cody.product.ui.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;

@Getter
@NoArgsConstructor
@ToString
public class ProductUpdateRequest {

    @NotNull(message = "상품가격은 필수 입력 입니다.")
    @Positive
    private BigDecimal price;

    public ProductUpdateRequest(BigDecimal price) {
        this.price = price;
    }

}
