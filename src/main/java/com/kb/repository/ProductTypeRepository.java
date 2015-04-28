package com.kb.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kb.domain.ProductType;

/**
 * Spring Data JPA repository for the ProductType entity.
 */
public interface ProductTypeRepository extends JpaRepository<ProductType,Long> {

}
