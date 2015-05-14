package com.kb.repository;

import com.kb.domain.ProductAlias;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Spring Data JPA repository for the ProductAlias entity.
 */
public interface ProductAliasRepository extends JpaRepository<ProductAlias,Long> {

}
