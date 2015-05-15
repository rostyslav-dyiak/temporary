package com.kb.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kb.domain.Unit;

/**
 * Spring Data JPA repository for the Unit entity.
 */
public interface UnitRepository extends JpaRepository<Unit,Long> {

}
