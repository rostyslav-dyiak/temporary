package com.kb.service.company;

import com.kb.domain.Company;
import com.kb.domain.EateryDetails;
import com.kb.repository.CompanyRepository;
import com.kb.repository.EateryDetailsRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import javax.transaction.Transactional;

/**
 * Created by rdyyak on 29.04.15.
 */
@Service
public class DefaultCompanyService implements CompanyService {
    @Inject
    private EateryDetailsRepository eateryDetailsRepository;
    @Inject
    private CompanyRepository companyRepository;

    @Override
    public void saveCompanyWithEateryDetails(Company company) {
        EateryDetails eateryDetails = company.getEateryDetails();
        if (eateryDetails != null) {
            eateryDetailsRepository.save(eateryDetails);
        }
        companyRepository.save(company);
    }

    @Override
    public void save(Company company) {
        companyRepository.save(company);
    }

    @Override
    @Transactional
    public Page<Company> findAll(Pageable page) {
        return companyRepository.findAll(page);
    }

    @Override
    @Transactional
    public Company find(Long id) {
        return companyRepository.getOne(id);
    }

    @Override
    public void delete(Long id) {
        companyRepository.delete(id);
    }

}
