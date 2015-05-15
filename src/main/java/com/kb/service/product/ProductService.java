package com.kb.service.product;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.kb.search.model.ProductSearch;
import com.kb.web.rest.dto.product.ProductDto;

public interface ProductService {

	List<ProductDto> findAll(Pageable pageable, ProductSearch seatchDto, Boolean applySearch);

}
