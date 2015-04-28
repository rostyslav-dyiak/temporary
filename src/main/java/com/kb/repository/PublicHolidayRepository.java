package com.kb.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kb.domain.PublicHoliday;

/**
 * Spring Data JPA repository for the PublicHoliday entity.
 */
public interface PublicHolidayRepository extends JpaRepository<PublicHoliday,Long> {

}
