package com.cody.order.infra;

import com.cody.common.struct.ProductCategory;
import com.cody.order.domain.BestBrandProduct;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class OrderRepository {

    private final JdbcTemplate jdbcTemplate;

    public List<BestBrandProduct> getBestBrand(final String brand) {
        return jdbcTemplate.query("select category, price from product where brand = ?", bestBrandRowMapper(), brand);
    }

    private RowMapper<BestBrandProduct> bestBrandRowMapper() {
        return (rs, rowNum) -> new BestBrandProduct(
                ProductCategory.fromName(rs.getString("category")),
                rs.getBigDecimal("price")
        );
    }

}
