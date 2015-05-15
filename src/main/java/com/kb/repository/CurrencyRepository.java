package com.kb.repository;

import com.kb.domain.Currency;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Spring Data JPA repository for the Currency entity.
 */
public interface CurrencyRepository extends JpaRepository<Currency,Long> {

}
