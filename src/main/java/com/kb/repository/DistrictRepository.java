package com.kb.repository;

import com.kb.domain.District;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Spring Data JPA repository for the District entity.
 */
public interface DistrictRepository extends JpaRepository<District, Long> {

}
