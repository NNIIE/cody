package com.cody.order.infra;

import com.cody.common.struct.Product;
import com.cody.common.struct.ProductCategory;
import com.cody.order.ui.response.BestPriceProduct;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class OrderRepository {

    private final JdbcTemplate jdbcTemplate;

    public List<BestPriceProduct> getHighestProductToCategory(final ProductCategory category) {
        return jdbcTemplate.query("select * from product where CATEGORY = ? order by price desc limit 1", bestPriceProductRowMapper(), category.getName());
    }

    public List<BestPriceProduct> getLowestProductToCategory(final ProductCategory category) {
        return jdbcTemplate.query("select * from product where CATEGORY = ? order by price asc limit 1", bestPriceProductRowMapper(), category.getName());
    }

    private RowMapper<BestPriceProduct> bestPriceProductRowMapper() {
        return (rs, rowNum) -> new BestPriceProduct(
                rs.getString("brand"),
                rs.getBigDecimal("price")
        );
    }



}
