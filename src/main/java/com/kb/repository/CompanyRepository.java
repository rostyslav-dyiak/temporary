package com.kb.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kb.domain.Company;

/**
 * Spring Data JPA repository for the Company entity.
 */
public interface CompanyRepository extends JpaRepository<Company,Long> {

}
