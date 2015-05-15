package com.kb.service.product;

import java.util.List;

import javax.annotation.Resource;
import javax.inject.Inject;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.kb.converter.Converter;
import com.kb.domain.Product;
import com.kb.repository.ProductRepository;
import com.kb.search.model.ProductSearch;
import com.kb.search.repository.product.ProductSearchRepository;
import com.kb.web.rest.dto.product.ProductDto;

@Service
public class DefaultProductService implements ProductService {

	@Inject
	private ProductRepository productRepository;
	
	@Inject
	private ProductSearchRepository productSearchRepository;
	
	@Resource(name = "productSearchConverter")
	private Converter<ProductSearch, ProductDto> productSearchConverter;

	@Resource(name = "productConverter")
	private Converter<Product, ProductDto> productConverter;
	
	@Override
	public List<ProductDto> findAll(final Pageable pageable, final ProductSearch searchDto, final Boolean applySearch) {
		List<ProductDto> products = null;
		
		if (applySearch) {
			List<ProductSearch> productEntities = productSearchRepository.findByTitle(searchDto.getTitle(), searchDto.getDescription(), pageable).getContent();
			products = productSearchConverter.convertAll(productEntities);
		} else {
			List<Product> productEntities = productRepository.findAll();
			products = productConverter.convertAll(productEntities);
		}
		
		return products;
	}

}
