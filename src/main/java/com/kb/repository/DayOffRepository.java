package com.kb.repository;

import com.kb.domain.DayOff;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Spring Data JPA repository for the SupplierDetailsPublicHolidays entity.
 */
public interface DayOffRepository extends JpaRepository<DayOff,Long> {

}
