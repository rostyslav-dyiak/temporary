package com.kb.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kb.domain.PaymentTerm;

/**
 * Spring Data JPA repository for the PaymentTerm entity.
 */
public interface PaymentTermRepository extends JpaRepository<PaymentTerm,Long> {

}
