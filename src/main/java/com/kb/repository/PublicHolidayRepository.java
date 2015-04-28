package com.kb.repository;

import com.kb.domain.PublicHoliday;
import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the PublicHoliday entity.
 */
public interface PublicHolidayRepository extends JpaRepository<PublicHoliday,Long> {

}
