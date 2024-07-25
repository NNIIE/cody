package com.cody.order.ui;

import com.cody.order.ui.response.BestBrandResponse;
import com.cody.order.ui.response.BestPriceResponse;
import com.cody.order.ui.response.LowestProductsResponse;
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

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class OrderTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DisplayName("카테고리 별 최저가 브랜드의 상품가격 및 총액 조회 테스트")
    void getLowestProductByCategoryTest() throws Exception {
        MvcResult response = mockMvc.perform(get("/order/lowest-product")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn();

        LowestProductsResponse lowestProductsResponse = objectMapper.readValue(response.getResponse().getContentAsString(), new TypeReference<>() {});

        assertThat(lowestProductsResponse.getTotalPrice()).isEqualTo(new BigDecimal("34100"));
        assertThat(lowestProductsResponse.getProducts().size()).isEqualTo(9);
    }

    @Test
    @DisplayName("모든상품 최저가 브랜드 상품가격 및 총액 조회 테스트")
    void getBestBrandTest() throws Exception {
        MvcResult response = mockMvc.perform(get("/order/best-brand")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn();

        BestBrandResponse bestBrandResponse = objectMapper.readValue(response.getResponse().getContentAsString(), new TypeReference<>() {});

        assertThat(bestBrandResponse.getBrand()).isEqualTo("D");
        assertThat(bestBrandResponse.getTotalPrice()).isEqualTo(new BigDecimal("36100"));
    }

    @Test
    @DisplayName("카테고리 별 최저가/최고가 조회 테스트")
    void getBestPriceToCategoryTest() throws Exception {
        String category = "TOP";

        MvcResult response = mockMvc.perform(get("/order/category/{category}/best-price", category)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn();

        BestPriceResponse bestPriceResponse = objectMapper.readValue(response.getResponse().getContentAsString(), new TypeReference<>() {});

        assertThat(bestPriceResponse.getLowestProduct().getPrice()).isEqualTo(new BigDecimal("10000"));
        assertThat(bestPriceResponse.getHighestProduct().getPrice()).isEqualTo(new BigDecimal("11400"));
    }

}