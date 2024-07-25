package com.cody.cache.infra;

import com.cody.common.struct.LowestBrand;
import com.cody.common.struct.ProductCategory;
import com.cody.cache.struct.CategoryPriceProduct;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class CacheRepository {

    private final JdbcTemplate jdbcTemplate;

    public LowestBrand getBestBrand() {
        return jdbcTemplate.queryForObject("select brand, total_price from (select brand, sum(price) as total_price from product group by brand order by total_price asc LIMIT 1 ) as best_brand", bestBrandRowMapper());
    }

    public List<CategoryPriceProduct> getLowestProductsByCategory() {
        return jdbcTemplate.query("select p.category, p.brand, p.price from product p inner join (select category, min(price) as min_price from product group by category) as min_prices on p.category = min_prices.category and p.price = min_prices.min_price", categoryPriceProductRowMapper());
    }

    public List<CategoryPriceProduct> getHighestProductsByCategory() {
        return jdbcTemplate.query("select p.category, p.brand, p.price from product p inner join (select category, max(price) as min_price from product group by category) as min_prices on p.category = min_prices.category and p.price = min_prices.min_price", categoryPriceProductRowMapper());
    }

    private RowMapper<LowestBrand> bestBrandRowMapper() {
        return (rs, rowNum) -> new LowestBrand(
                rs.getString("brand"),
                rs.getBigDecimal("total_price")
        );
    }

    private RowMapper<CategoryPriceProduct> categoryPriceProductRowMapper() {
        return (rs, rowNum) -> new CategoryPriceProduct(
                ProductCategory.fromName(rs.getString("category")),
                rs.getString("brand"),
                rs.getBigDecimal("price")
        );
    }

}
