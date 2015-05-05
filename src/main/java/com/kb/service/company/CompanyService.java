package com.kb.service.company;

import com.kb.domain.Company;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Created by rdyyak on 29.04.15.
 */
public interface CompanyService {

    public void save(Company company);

    public Page<Company> findAll(Pageable page);

    public Company find(Long id);

    public void delete(Long id);
}
