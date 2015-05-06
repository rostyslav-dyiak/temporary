package com.kb.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kb.domain.Currency;

/**
 * Spring Data JPA repository for the Currency entity.
 */
public interface CurrencyRepository extends JpaRepository<Currency,Long> {

}
