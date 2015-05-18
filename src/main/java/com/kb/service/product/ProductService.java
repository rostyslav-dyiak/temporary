package com.kb.service.product;

import com.kb.domain.Company;
import com.kb.domain.Product;
import com.kb.search.model.ProductSearch;
import com.kb.web.rest.dto.product.ProductDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductService {

	List<ProductDto> findAll(Pageable pageable, ProductSearch seatchDto, Boolean applySearch);

	void save(Product product);

	Page<Product> findByCompany(Company company, Pageable generatePageRequest);

	List<Product> findByCompanyAndSubSubCategoryIsNull(Company company);

	Product findOne(Long id);

	void delete(Long id);

}
