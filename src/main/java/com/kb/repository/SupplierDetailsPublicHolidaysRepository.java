package com.kb.repository;

import com.kb.domain.SupplierDetailsPublicHolidays;
import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the SupplierDetailsPublicHolidays entity.
 */
public interface SupplierDetailsPublicHolidaysRepository extends JpaRepository<SupplierDetailsPublicHolidays,Long> {

}
