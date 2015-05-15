package com.kb.web.rest.dto.product;

import java.math.BigDecimal;

public class ProductDto {

    private Long id;

    private String title;

    private String description;

    private String brand;

    private String origin;

    private Boolean certifiedHalal;

    private Boolean codeGenerate;

    private String unitDescription;

    private Boolean unitHide;

    private Boolean available;

    private String code;

    private Integer quantity;

    private BigDecimal basePrice;
    
    private Long categoryId;

    private Long subCategoryId;

    private Long subSubCategoryId;

    private Long unitId;

    private Long pictureId;

    private Long companyId;

	public Long getId() {
		return id;
	}

	public void setId(final Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(final String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(final String description) {
		this.description = description;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(final String brand) {
		this.brand = brand;
	}

	public String getOrigin() {
		return origin;
	}

	public void setOrigin(final String origin) {
		this.origin = origin;
	}

	public Boolean getCertifiedHalal() {
		return certifiedHalal;
	}

	public void setCertifiedHalal(final Boolean certifiedHalal) {
		this.certifiedHalal = certifiedHalal;
	}

	public Boolean getCodeGenerate() {
		return codeGenerate;
	}

	public void setCodeGenerate(final Boolean codeGenerate) {
		this.codeGenerate = codeGenerate;
	}

	public String getUnitDescription() {
		return unitDescription;
	}

	public void setUnitDescription(final String unitDescription) {
		this.unitDescription = unitDescription;
	}

	public Boolean getUnitHide() {
		return unitHide;
	}

	public void setUnitHide(final Boolean unitHide) {
		this.unitHide = unitHide;
	}

	public Boolean getAvailable() {
		return available;
	}

	public void setAvailable(final Boolean available) {
		this.available = available;
	}

	public String getCode() {
		return code;
	}

	public void setCode(final String code) {
		this.code = code;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(final Integer quantity) {
		this.quantity = quantity;
	}

	public BigDecimal getBasePrice() {
		return basePrice;
	}

	public void setBasePrice(final BigDecimal basePrice) {
		this.basePrice = basePrice;
	}

	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(final Long categoryId) {
		this.categoryId = categoryId;
	}

	public Long getSubCategoryId() {
		return subCategoryId;
	}

	public void setSubCategoryId(final Long subCategoryId) {
		this.subCategoryId = subCategoryId;
	}

	public Long getSubSubCategoryId() {
		return subSubCategoryId;
	}

	public void setSubSubCategoryId(final Long subSubCategoryId) {
		this.subSubCategoryId = subSubCategoryId;
	}

	public Long getUnitId() {
		return unitId;
	}

	public void setUnitId(final Long unitId) {
		this.unitId = unitId;
	}

	public Long getPictureId() {
		return pictureId;
	}

	public void setPictureId(final Long pictureId) {
		this.pictureId = pictureId;
	}

	public Long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(final Long companyId) {
		this.companyId = companyId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((available == null) ? 0 : available.hashCode());
		result = prime * result
				+ ((basePrice == null) ? 0 : basePrice.hashCode());
		result = prime * result + ((brand == null) ? 0 : brand.hashCode());
		result = prime * result
				+ ((categoryId == null) ? 0 : categoryId.hashCode());
		result = prime * result
				+ ((certifiedHalal == null) ? 0 : certifiedHalal.hashCode());
		result = prime * result + ((code == null) ? 0 : code.hashCode());
		result = prime * result
				+ ((codeGenerate == null) ? 0 : codeGenerate.hashCode());
		result = prime * result
				+ ((companyId == null) ? 0 : companyId.hashCode());
		result = prime * result
				+ ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((origin == null) ? 0 : origin.hashCode());
		result = prime * result
				+ ((pictureId == null) ? 0 : pictureId.hashCode());
		result = prime * result
				+ ((quantity == null) ? 0 : quantity.hashCode());
		result = prime * result
				+ ((subCategoryId == null) ? 0 : subCategoryId.hashCode());
		result = prime
				* result
				+ ((subSubCategoryId == null) ? 0 : subSubCategoryId.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		result = prime * result
				+ ((unitDescription == null) ? 0 : unitDescription.hashCode());
		result = prime * result
				+ ((unitHide == null) ? 0 : unitHide.hashCode());
		result = prime * result + ((unitId == null) ? 0 : unitId.hashCode());
		return result;
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		ProductDto other = (ProductDto) obj;
		if (available == null) {
			if (other.available != null) {
				return false;
			}
		} else if (!available.equals(other.available)) {
			return false;
		}
		if (basePrice == null) {
			if (other.basePrice != null) {
				return false;
			}
		} else if (!basePrice.equals(other.basePrice)) {
			return false;
		}
		if (brand == null) {
			if (other.brand != null) {
				return false;
			}
		} else if (!brand.equals(other.brand)) {
			return false;
		}
		if (categoryId == null) {
			if (other.categoryId != null) {
				return false;
			}
		} else if (!categoryId.equals(other.categoryId)) {
			return false;
		}
		if (certifiedHalal == null) {
			if (other.certifiedHalal != null) {
				return false;
			}
		} else if (!certifiedHalal.equals(other.certifiedHalal)) {
			return false;
		}
		if (code == null) {
			if (other.code != null) {
				return false;
			}
		} else if (!code.equals(other.code)) {
			return false;
		}
		if (codeGenerate == null) {
			if (other.codeGenerate != null) {
				return false;
			}
		} else if (!codeGenerate.equals(other.codeGenerate)) {
			return false;
		}
		if (companyId == null) {
			if (other.companyId != null) {
				return false;
			}
		} else if (!companyId.equals(other.companyId)) {
			return false;
		}
		if (description == null) {
			if (other.description != null) {
				return false;
			}
		} else if (!description.equals(other.description)) {
			return false;
		}
		if (id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!id.equals(other.id)) {
			return false;
		}
		if (origin == null) {
			if (other.origin != null) {
				return false;
			}
		} else if (!origin.equals(other.origin)) {
			return false;
		}
		if (pictureId == null) {
			if (other.pictureId != null) {
				return false;
			}
		} else if (!pictureId.equals(other.pictureId)) {
			return false;
		}
		if (quantity == null) {
			if (other.quantity != null) {
				return false;
			}
		} else if (!quantity.equals(other.quantity)) {
			return false;
		}
		if (subCategoryId == null) {
			if (other.subCategoryId != null) {
				return false;
			}
		} else if (!subCategoryId.equals(other.subCategoryId)) {
			return false;
		}
		if (subSubCategoryId == null) {
			if (other.subSubCategoryId != null) {
				return false;
			}
		} else if (!subSubCategoryId.equals(other.subSubCategoryId)) {
			return false;
		}
		if (title == null) {
			if (other.title != null) {
				return false;
			}
		} else if (!title.equals(other.title)) {
			return false;
		}
		if (unitDescription == null) {
			if (other.unitDescription != null) {
				return false;
			}
		} else if (!unitDescription.equals(other.unitDescription)) {
			return false;
		}
		if (unitHide == null) {
			if (other.unitHide != null) {
				return false;
			}
		} else if (!unitHide.equals(other.unitHide)) {
			return false;
		}
		if (unitId == null) {
			if (other.unitId != null) {
				return false;
			}
		} else if (!unitId.equals(other.unitId)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "ProductDto [id=" + id + ", title=" + title + ", description="
				+ description + ", brand=" + brand + ", origin=" + origin
				+ ", certifiedHalal=" + certifiedHalal + ", codeGenerate="
				+ codeGenerate + ", unitDescription=" + unitDescription
				+ ", unitHide=" + unitHide + ", available=" + available
				+ ", code=" + code + ", quantity=" + quantity + ", basePrice="
				+ basePrice + ", categoryId=" + categoryId + ", subCategoryId="
				+ subCategoryId + ", subSubCategoryId=" + subSubCategoryId
				+ ", unitId=" + unitId + ", pictureId=" + pictureId
				+ ", companyId=" + companyId + "]";
	}
	
}
