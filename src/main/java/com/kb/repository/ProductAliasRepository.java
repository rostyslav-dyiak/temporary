package com.kb.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kb.domain.ProductAlias;

/**
 * Spring Data JPA repository for the ProductAlias entity.
 */
public interface ProductAliasRepository extends JpaRepository<ProductAlias,Long> {

}
