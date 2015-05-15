package com.kb.repository;

import com.kb.domain.Company;
import com.kb.domain.Contact;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Spring Data JPA repository for the Contact entity.
 */
public interface ContactRepository extends JpaRepository<Contact,Long> {

	public Page<Contact> findByCompany(Company company, Pageable page);

}
