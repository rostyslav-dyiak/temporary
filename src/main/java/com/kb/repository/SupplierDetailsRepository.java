package com.kb.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kb.domain.SupplierDetails;

/**
 * Spring Data JPA repository for the SupplierDetails entity.
 */
public interface SupplierDetailsRepository extends JpaRepository<SupplierDetails,Long> {

}
