package com.kb.search.repository.product;

import java.math.BigDecimal;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.kb.search.model.ProductSearch;

public interface ProductSearchRepository extends ElasticsearchRepository<ProductSearch, Long> {

	@Query(name = "productGlobalSearch", value = ProductQuery.QUERY)
	Page<ProductSearch> findGlobal(String title, 
			String description, 
			String brand,
			String origin,
			Boolean certifiedHalal,
			Boolean codeGenerate,
			String unitDescription,
			Boolean unitHide,
			Boolean available,
			String code, 
			Integer quantity,
			BigDecimal basePrice,
			Long categoryId,
			Long subCategoryId,
			Long subSubCategoryId,
			Long unitId,
			Long pictureId, 
			Long companyId,     
			Pageable pageable);
	
}
