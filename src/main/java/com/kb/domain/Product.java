package com.kb.domain;


import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

/**
 * A Product.
 */
@Entity
@Table(name = "PRODUCT")
public class Product extends AbstractAuditingEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@org.springframework.data.annotation.Id // For Elastic Search
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "brand")
    private String brand;

    @Column(name = "origin")
    private String origin;

    @Column(name = "certified_halal")
    private Boolean certifiedHalal;

    @Column(name = "unit_description")
    private String unitDescription;

    @Column(name = "unit_hide")
    private Boolean unitHide;

    @Column(name = "available")
    private Boolean available;

    @Column(name = "code")
    private String code;

    @Column(name = "code_generate")
    private Boolean codeGenerate;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "base_price")
    private BigDecimal basePrice;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "product")
    private Set<ProductAlias> productAliasSet = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToOne
    @JoinColumn(name = "sub_category_id")
    private Category subCategory;

    @ManyToOne
    @JoinColumn(name = "sub_sub_category_id")
    private Category subSubCategory;

    @ManyToOne
    @JoinColumn(name = "unit_id")
    private Unit unit;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "picture_id")
    private Picture picture;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "company_id")
    private Company company;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "product")
    private Set<ProductPricingGroup> productPricingGroups = new HashSet<ProductPricingGroup>();

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

    public Boolean getCodeGenerate() {
        return codeGenerate;
    }

    public void setCodeGenerate(final Boolean codeGenerate) {
        this.codeGenerate = codeGenerate;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(final Integer quantity) {
        this.quantity = quantity;
    }

    public Unit getUnit() {
        return unit;
    }

    public void setUnit(final Unit unit) {
        this.unit = unit;
    }

    public Picture getPicture() {
        return picture;
    }

    public void setPicture(final Picture picture) {
        this.picture = picture;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(final Category category) {
        this.category = category;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(final Company company) {
        this.company = company;
    }

    public BigDecimal getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(final BigDecimal basePrice) {
        this.basePrice = basePrice;
    }

    public Set<ProductAlias> getProductAliasSet() {
        return productAliasSet;
    }

    public void setProductAliasSet(final Set<ProductAlias> productAliasSet) {
        this.productAliasSet = productAliasSet;
    }

    public Category getSubCategory() {
        return subCategory;
    }

    public void setSubCategory(final Category subCategory) {
        this.subCategory = subCategory;
    }

    public Category getSubSubCategory() {
        return subSubCategory;
    }

    public void setSubSubCategory(final Category subSubCategory) {
        this.subSubCategory = subSubCategory;
    }

    public Set<ProductPricingGroup> getProductPricingGroups() {
        return productPricingGroups;
    }

    public void setProductPricingGroups(final Set<ProductPricingGroup> productPricingGroups) {
        this.productPricingGroups = productPricingGroups;
    }
}
