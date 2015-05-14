package com.kb.repository;

import com.kb.domain.Unit;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Spring Data JPA repository for the Unit entity.
 */
public interface UnitRepository extends JpaRepository<Unit,Long> {

}
