package com.kb.search.repository.product;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.kb.search.model.ProductSearch;

public interface ProductSearchRepository extends ElasticsearchRepository<ProductSearch, Long> {

	@Query(name = "productGlobalSearch", value = ProductQuery.QUERY)
	Page<ProductSearch> findByTitle(String title, String description, Pageable pageable);
	
}
