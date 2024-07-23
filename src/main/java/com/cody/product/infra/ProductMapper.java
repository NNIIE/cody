package com.cody.product.infra;

import com.cody.product.domain.entity.Product;
import com.cody.product.ui.request.ProductCreateRequest;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    Product product(final ProductCreateRequest request);

}
