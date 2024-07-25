package com.cody.product.ui;

import com.cody.common.utils.JsonUtil;
import com.cody.common.struct.ProductCategory;
import com.cody.common.struct.Product;
import com.cody.product.fixture.ProductFixture;
import com.cody.product.ui.request.ProductCreateRequest;
import com.cody.product.ui.request.ProductUpdateRequest;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class ProductTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DisplayName("브랜드 별 상품 조회 테스트")
    void getProductsByBrandTest() throws Exception {
        String brand = "A";

        MvcResult response = mockMvc.perform(get("/product/brand/{brand}", brand)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn();

        List<Product> products = objectMapper.readValue(response.getResponse().getContentAsString(), new TypeReference<>() {});

        assertThat(products.size()).isEqualTo(8);
    }

    @Test
    @DisplayName("브랜드 별 상품 조회 테스트 - 브랜드 또는 상품이 없는 경우")
    void getProductsByBrandEmptyTest() throws Exception {
        String brand = "AAAAAA";

        MvcResult response = mockMvc.perform(get("/product/brand/{brand}", brand)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn();

        List<Product> products = objectMapper.readValue(response.getResponse().getContentAsString(), new TypeReference<>() {});

        assertThat(products.size()).isEqualTo(0);
    }

    @Test
    @DisplayName("상품 등록 테스트")
    void createProductTest() throws Exception {
        ProductCreateRequest productCreateRequest = ProductFixture.productCreateRequest("Z", ProductCategory.OUTER, BigDecimal.valueOf(10000));
        String request = JsonUtil.objectToJson(productCreateRequest);

        mockMvc.perform(post("/product")
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

    @Test
    @DisplayName("상품 변경 테스트 - 성공 케이스")
    void updateProductSuccessTest() throws Exception {
        Long id = 1L;
        ProductUpdateRequest productUpdateRequest = ProductFixture.productUpdateRequest(BigDecimal.valueOf(100000));
        String request = JsonUtil.objectToJson(productUpdateRequest);

        mockMvc.perform(patch("/product/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(request))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    @DisplayName("상품 변경 테스트 - 실패 케이스")
    void updateProductFailTest() throws Exception {
        Long id = 1000L;
        ProductUpdateRequest productUpdateRequest = ProductFixture.productUpdateRequest(BigDecimal.valueOf(100000));
        String request = JsonUtil.objectToJson(productUpdateRequest);

        mockMvc.perform(patch("/product/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(request))
                .andExpect(status().isBadRequest())
                .andDo(print());
    }

}