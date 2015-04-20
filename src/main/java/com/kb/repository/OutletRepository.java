package com.kb.repository;

import com.kb.domain.Outlet;
import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the Outlet entity.
 */
public interface OutletRepository extends JpaRepository<Outlet,Long> {

}