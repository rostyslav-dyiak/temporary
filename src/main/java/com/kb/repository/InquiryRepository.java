package com.kb.repository;

import com.kb.domain.Inquiry;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Spring Data JPA repository for the Inquiry entity.
 */
public interface InquiryRepository extends JpaRepository<Inquiry,Long> {

    Page<Inquiry> findByChildIsNull(Pageable page);

}
