package com.kb.repository;

import com.kb.domain.Company;
import com.kb.domain.EateryDetails;
import com.kb.domain.SupplierDetails;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Spring Data JPA repository for the Company entity.
 */
public interface CompanyRepository extends JpaRepository<Company,Long> {

    Company findByEateryDetails(EateryDetails eateryDetails);

    Company findBySupplierDetails(SupplierDetails supplierDetails);

}
