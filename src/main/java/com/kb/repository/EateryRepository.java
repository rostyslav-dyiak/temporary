package com.kb.repository;

import com.kb.domain.Eatery;
import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the Eatery entity.
 */
public interface EateryRepository extends JpaRepository<Eatery,Long> {

}
