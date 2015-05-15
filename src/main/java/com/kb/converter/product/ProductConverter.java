package com.kb.converter.product;

import org.springframework.stereotype.Component;

import com.kb.converter.AbstractConverter;
import com.kb.domain.Product;
import com.kb.web.rest.dto.product.ProductDto;

@Component("productConverter")
public class ProductConverter extends AbstractConverter<Product, ProductDto> {

	@Override
	public ProductDto convert(final Product source, final ProductDto target) {

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
	public ProductDto convert(final Product source) {
		return convert(source, new ProductDto());
	}

}
