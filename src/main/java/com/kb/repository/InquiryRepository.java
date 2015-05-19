package com.kb.repository;

import com.kb.domain.Inquiry;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Spring Data JPA repository for the Inquiry entity.
 */
public interface InquiryRepository extends JpaRepository<Inquiry,Long> {

}
