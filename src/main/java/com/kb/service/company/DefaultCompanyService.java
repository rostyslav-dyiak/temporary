package com.kb.service.company;

import com.kb.domain.Company;
import com.kb.domain.EateryDetails;
import com.kb.domain.SupplierDetails;
import com.kb.repository.CompanyRepository;
import com.kb.repository.EateryDetailsRepository;
import com.kb.repository.SupplierDetailsRepository;
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
    @Inject
    private SupplierDetailsRepository supplierDetailsRepository;
    @Inject
    private EateryDetailsRepository eateryDetailsRepository;

    @Override
    @Transactional
    public void save(final Company company) {
        companyRepository.save(initializeDetails(company));
    }

    @Override
    @Transactional
    public Page<Company> findAll(final Pageable page) {
        return companyRepository.findAll(page);
    }

    @Override
    @Transactional
    public Company find(final Long id) {
        return companyRepository.getOne(id);
    }

    @Override
    public void delete(final Long id) {
        companyRepository.delete(id);
    }

    @Override
    @Transactional
    public Company findBySupplierDetails(Long id) {
        SupplierDetails supplierDetails = supplierDetailsRepository.findOne(id);
        return supplierDetails.getSupplier();
    }

    @Override
    @Transactional
    public Company findByEateryDetails(Long id) {
        EateryDetails eateryDetails = eateryDetailsRepository.findOne(id);
        return eateryDetails.getEatery();
    }

    private Company initializeDetails(final Company company) {
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
