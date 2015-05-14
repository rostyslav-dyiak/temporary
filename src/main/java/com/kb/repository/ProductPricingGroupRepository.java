package com.kb.repository;

import com.kb.domain.ProductPricingGroup;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Spring Data JPA repository for the ProductPricingGroup entity.
 */
public interface ProductPricingGroupRepository extends JpaRepository<ProductPricingGroup,Long> {

}
