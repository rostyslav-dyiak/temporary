package com.kb.converter.product;

import org.springframework.stereotype.Component;

import com.kb.converter.AbstractConverter;
import com.kb.search.model.ProductSearch;
import com.kb.web.rest.dto.product.ProductDto;

@Component("productSearchConverter")
public class ProductSearchConverter extends AbstractConverter<ProductSearch, ProductDto> {

	@Override
	public ProductDto convert(final ProductSearch source, final ProductDto target) {
		
		target.setAvailable(source.getAvailable());
		target.setBasePrice(source.getBasePrice());
		target.setBrand(source.getBrand());
		target.setCategoryId(source.getCategoryId());
		target.setCertifiedHalal(source.getCertifiedHalal());
		target.setCode(source.getCode());
		target.setCodeGenerate(source.getCodeGenerate());
		target.setCompanyId(source.getCompanyId());
		target.setDescription(source.getDescription());
		target.setId(source.getId());
		target.setOrigin(source.getOrigin());
		target.setPictureId(source.getPictureId());
		target.setQuantity(source.getQuantity());
		target.setSubCategoryId(source.getSubCategoryId());
		target.setSubSubCategoryId(source.getSubSubCategoryId());
		target.setTitle(source.getTitle());
		target.setUnitDescription(source.getUnitDescription());
		target.setUnitHide(source.getUnitHide());
		target.setUnitId(source.getUnitId());
		
		return target;
	}

	@Override
	public ProductDto convert(final ProductSearch source) {
		return convert(source, new ProductDto());
	}

}
