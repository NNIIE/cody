package com.cody.product.ui.request;

import com.cody.common.struct.ProductCategory;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
@ToString
public class ProductCreateRequest {

    @NotBlank(message = "상품이름은 필수 입력 입니다.")
    private String brand;

    @NotNull(message = "상품 카테고리는 필수 입력 입니다.")
    private ProductCategory category;

    @NotNull(message = "상품가격은 필수 입력 입니다.")
    @Positive
    private BigDecimal price;

}
