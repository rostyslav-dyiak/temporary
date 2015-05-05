package com.kb.service.company;

import com.kb.domain.Company;
import com.kb.domain.EateryDetails;
import com.kb.domain.SupplierDetails;
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
    private CompanyRepository companyRepository;

    @Override
    @Transactional
    public void save(Company company) {
        companyRepository.save(initializeDetails(company));
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

    private Company initializeDetails(Company company) {
        EateryDetails eateryDetails = company.getEateryDetails();
        SupplierDetails supplierDetails = company.getSupplierDetails();
        if (eateryDetails != null && eateryDetails.getEatery() == null) {
            eateryDetails.setEatery(company);
        }
        if (supplierDetails != null && supplierDetails.getSupplier() == null) {
            supplierDetails.setSupplier(company);
        }
        return company;
    }
}
