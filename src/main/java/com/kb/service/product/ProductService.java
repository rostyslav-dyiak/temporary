package com.kb.service.product;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.kb.domain.Company;
import com.kb.domain.Product;
import com.kb.search.model.ProductSearch;
import com.kb.web.rest.dto.product.ProductDto;

public interface ProductService {

	List<ProductDto> findAll(Pageable pageable, ProductSearch seatchDto, Boolean applySearch);

	void save(Product product);

	Page<Product> findByCompany(Company company, Pageable generatePageRequest);

	List<Product> findByCompanyAndCategoryIsNull(Company company);

	Product findOne(Long id);

	void delete(Long id);

}
