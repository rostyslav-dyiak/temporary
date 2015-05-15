package com.kb.converter.product;

import org.springframework.stereotype.Component;

import com.kb.converter.AbstractConverter;
import com.kb.domain.Product;
import com.kb.search.model.ProductSearch;

@Component("productEntitySearchConverter")
public class ProductEntitySearchConverter extends AbstractConverter<Product, ProductSearch> {

	@Override
	public ProductSearch convert(final Product source, final ProductSearch target) {

		target.setAvailable(source.getAvailable());
		target.setBasePrice(source.getBasePrice());
		target.setBrand(source.getBrand());
		target.setCertifiedHalal(source.getCertifiedHalal());
		target.setCode(source.getCode());
		target.setCodeGenerate(source.getCodeGenerate());
		target.setDescription(source.getDescription());
		target.setId(source.getId());
		target.setOrigin(source.getOrigin());
		target.setQuantity(source.getQuantity());
		target.setTitle(source.getTitle());
		target.setUnitDescription(source.getUnitDescription());
		target.setUnitHide(source.getUnitHide());
		
		if (source.getCompany() != null) {
			target.setCompanyId(source.getCompany().getId());
		}
		
		if (source.getPicture() != null) {
			target.setPictureId(source.getPicture().getId());
		}
		
		if (source.getSubCategory() != null) {
			target.setSubCategoryId(source.getSubCategory().getId());
		}
		
		if (source.getSubSubCategory() != null) {
			target.setSubSubCategoryId(source.getSubSubCategory().getId());
		}
		
		if (source.getCategory() != null) {
			target.setCategoryId(source.getCategory().getId());
		}
		
		if (source.getUnit() != null) {
			target.setUnitId(source.getUnit().getId());
		}
		
		return target;
	}

	@Override
	public ProductSearch convert(final Product source) {
		return convert(source, new ProductSearch());
	}

}
