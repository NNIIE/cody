package com.cody.product.infra;

import com.cody.common.exception.product.ProductException;
import com.cody.common.exception.product.ProductExceptionCode;
import com.cody.product.domain.ProductCategory;
import com.cody.product.domain.entity.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
@RequiredArgsConstructor
public class ProductRepository {

    private final JdbcTemplate jdbcTemplate;

    public List<Product> getProductsByBrand(final String brand) {
        return jdbcTemplate.query("select id, brand, category, price from product where brand = ?", productRowMapper(), brand);
    }

    public void createProduct(final Product product) {
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);

        jdbcInsert.withTableName("product")
                .usingColumns("brand", "category", "price", "created_at", "updated_at")
                .usingGeneratedKeyColumns("id");

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("brand", product.getBrand());
        parameters.put("category", product.getCategory());
        parameters.put("price", product.getPrice());
        parameters.put("created_at", LocalDateTime.now());
        parameters.put("updated_at", LocalDateTime.now());

        jdbcInsert.execute(new MapSqlParameterSource(parameters));
    }

    public void updateProduct(final Long id, final BigDecimal updatePrice) {
        int updateProductCount = jdbcTemplate.update("update product set price = ?, updated_at = ? where id = ?", updatePrice, LocalDateTime.now(), id);

        if (updateProductCount == 0) {
            throw new ProductException(ProductExceptionCode.PRODUCT_NOT_FOUND);
        }
    }

    public void deleteProduct(final Long id) {
        int deleteProductCount = jdbcTemplate.update("delete from product where id = ?", id);

        if (deleteProductCount == 0) {
            throw new ProductException(ProductExceptionCode.PRODUCT_NOT_FOUND);
        }
    }

    private RowMapper<Product> productRowMapper() {
        return (rs, rowNum) -> Product.builder()
                .id(rs.getLong("id"))
                .brand(rs.getString("brand"))
                .category(ProductCategory.fromName(rs.getString("category")))
                .price(rs.getBigDecimal("price"))
                .build();
    }

}
