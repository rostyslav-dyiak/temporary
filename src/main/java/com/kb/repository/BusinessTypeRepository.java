package com.kb.repository;

import com.kb.domain.BusinessType;
import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the BusinessType entity.
 */
public interface BusinessTypeRepository extends JpaRepository<BusinessType,Long> {

}
