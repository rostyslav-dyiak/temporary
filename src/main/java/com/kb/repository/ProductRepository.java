package com.kb.repository;

import com.kb.domain.Company;
import com.kb.domain.Product;
import com.kb.domain.Unit;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


/**
 * Spring Data JPA repository for the Product entity.
 */
public interface ProductRepository extends JpaRepository<Product,Long> {

    Page<Product> findByUnit(Unit unit, Pageable page);

    Page<Product> findByCompany(Company company, Pageable page);

}
