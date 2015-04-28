package com.kb.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kb.domain.DayOff;

/**
 * Spring Data JPA repository for the SupplierDetailsPublicHolidays entity.
 */
public interface DayOffRepository extends JpaRepository<DayOff,Long> {

}
