package com.kb.repository;

import com.kb.domain.TaxType;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Spring Data JPA repository for the TaxType entity.
 */
public interface TaxTypeRepository extends JpaRepository<TaxType, Long> {

}
