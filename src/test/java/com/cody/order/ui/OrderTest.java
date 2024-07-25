package com.cody.order.ui;

import com.cody.order.ui.response.BestPriceResponse;
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
    @DisplayName("카테고리 별 최저가/최고가 조회 테스트")
    void getBestPriceToCategoryTest() throws Exception {
        String category = "TOP";

        MvcResult response = mockMvc.perform(get("/order/category/{category}/best-price", category)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn();

        BestPriceResponse bestPriceResponse = objectMapper.readValue(response.getResponse().getContentAsString(), new TypeReference<>() {});

        assertThat(bestPriceResponse.lowestProduct().get(0).price()).isEqualTo(new BigDecimal("10000"));
        assertThat(bestPriceResponse.highestProduct().get(0).price()).isEqualTo(new BigDecimal("11400"));
    }

}