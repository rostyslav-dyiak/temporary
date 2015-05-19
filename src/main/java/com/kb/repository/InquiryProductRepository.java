package com.kb.repository;

import com.kb.domain.InquiryProduct;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Spring Data JPA repository for the InquiryProduct entity.
 */
public interface InquiryProductRepository extends JpaRepository<InquiryProduct,Long> {

}
