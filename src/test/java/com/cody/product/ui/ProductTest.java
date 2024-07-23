package com.cody.product.ui;

import com.cody.common.utils.JsonUtil;
import com.cody.product.domain.ProductCategory;
import com.cody.product.fixture.ProductFixture;
import com.cody.product.ui.request.ProductCreateRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
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

    @Test
    @DisplayName("상품 삭제 테스트 - 성공 케이스")
    void deleteProductSuccessTest() throws Exception {
        Long id = 1L;

        mockMvc.perform(delete("/product/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent())
                .andDo(print());
    }

    @Test
    @DisplayName("상품 삭제 테스트 - 실패 케이스")
    void deleteProductFailTest() throws Exception {
        Long id = 1000L;

        mockMvc.perform(delete("/product/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andDo(print());
    }

}