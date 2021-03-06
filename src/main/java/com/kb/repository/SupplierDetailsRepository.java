package com.kb.repository;

import com.kb.domain.SupplierDetails;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Spring Data JPA repository for the SupplierDetails entity.
 */
public interface SupplierDetailsRepository extends JpaRepository<SupplierDetails,Long> {

}
