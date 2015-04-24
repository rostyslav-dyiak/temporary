package com.kb.repository;

import com.kb.domain.Company;
import com.kb.domain.Outlet;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the Outlet entity.
 */
public interface OutletRepository extends JpaRepository<Outlet, Long> {

	public Page<Outlet> findByCompany(Company company, Pageable page);

}
