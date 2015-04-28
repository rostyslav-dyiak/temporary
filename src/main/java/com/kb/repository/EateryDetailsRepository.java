package com.kb.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kb.domain.EateryDetails;

/**
 * Spring Data JPA repository for the EateryDetails entity.
 */
public interface EateryDetailsRepository extends JpaRepository<EateryDetails,Long> {

}
