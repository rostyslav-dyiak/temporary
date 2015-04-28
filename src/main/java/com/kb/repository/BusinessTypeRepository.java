package com.kb.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kb.domain.BusinessType;

/**
 * Spring Data JPA repository for the BusinessType entity.
 */
public interface BusinessTypeRepository extends JpaRepository<BusinessType,Long> {

}
