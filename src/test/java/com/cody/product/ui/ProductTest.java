package com.cody.product.ui;

import com.cody.common.utils.JsonUtil;
import com.cody.product.domain.ProductCategory;
import com.cody.product.fixture.ProductFixture;
import com.cody.product.ui.request.ProductCreateRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class ProductTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("상품 등록 테스트")
    void createProductTest() throws Exception {
        ProductCreateRequest productCreateRequest = ProductFixture.productCreateRequest("Z", ProductCategory.OUTER, BigDecimal.valueOf(10000));
        String request = JsonUtil.objectToJson(productCreateRequest);

        mockMvc.perform(post("/product/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(request))
                .andExpect(status().isCreated())
                .andDo(print());
    }

}