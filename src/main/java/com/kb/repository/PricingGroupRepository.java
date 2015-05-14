package com.kb.repository;

import com.kb.domain.PricingGroup;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Spring Data JPA repository for the PricingGroup entity.
 */
public interface PricingGroupRepository extends JpaRepository<PricingGroup,Long> {

}
