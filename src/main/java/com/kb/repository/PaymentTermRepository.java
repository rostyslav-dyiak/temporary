package com.kb.repository;

import com.kb.domain.PaymentTerm;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Spring Data JPA repository for the PaymentTerm entity.
 */
public interface PaymentTermRepository extends JpaRepository<PaymentTerm,Long> {

}
