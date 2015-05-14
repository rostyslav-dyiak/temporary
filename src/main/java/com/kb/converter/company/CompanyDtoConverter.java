package com.kb.converter.company;

import com.kb.converter.AbstractConverter;
import com.kb.domain.Company;
import com.kb.domain.CompanyType;
import com.kb.web.rest.dto.company.CompanyDto;
import com.kb.web.rest.dto.company.EateryDto;
import com.kb.web.rest.dto.company.SupplierDto;

/**
 * Created by root on 5/14/15.
 */
public class CompanyDtoConverter extends AbstractConverter<Company, CompanyDto> {

    @Override
    public CompanyDto convert(Company company, CompanyDto companyDto) {
        CompanyDto convertedDto;
        if(company.getCompanyType().equals(CompanyType.EATERY))
            convertedDto = convertCompanyToEateryDto(company);
        else
            convertedDto = convertCompanyToSupplierDto(company);

        convertedDto.setName(company.getName());
        convertedDto.setEmail(company.getEmail());
        convertedDto.setContactNumber(company.getContactNumber());
        convertedDto.setCompanyType(company.getCompanyType());
        convertedDto.setStatus(company.getStatus());
        convertedDto.setCode(company.getCode());
        convertedDto.setUsers(company.getUsers());
        convertedDto.setLogo(company.getLogo());

        return convertedDto;
    }

    @Override
    public CompanyDto convert(Company company) {
        CompanyDto entityToConvert;
        if(company.getCompanyType().equals(CompanyType.EATERY))
            entityToConvert = new EateryDto();
        else
            entityToConvert = new SupplierDto();

        return convert(company, entityToConvert);
    }

    public CompanyDto convertCompanyToSupplierDto(Company company){

        SupplierDto supplierDto = new SupplierDto();
        supplierDto.setId(company.getId());
        supplierDto.setMainPicture(company.getSupplierDetails().getMainPicture());
        supplierDto.setBusRegNumber(company.getSupplierDetails().getBusRegNumber());
        supplierDto.setBusDescription(company.getSupplierDetails().getBusDescription());
        supplierDto.setAddress(company.getSupplierDetails().getAddress());
        supplierDto.setFaxNumber(company.getSupplierDetails().getFaxNumber());
        supplierDto.setGstRegistered(company.getSupplierDetails().getGstRegistered());
        supplierDto.setGstRegistrationNumber(company.getSupplierDetails().getGstRegistrationNumber());
        supplierDto.setHolidays(company.getSupplierDetails().getHolidays());
        supplierDto.setPictures(company.getSupplierDetails().getPictures());
        supplierDto.setContacts(company.getContacts());
        supplierDto.setProducts(company.getProducts());

        return supplierDto;
    }

    public CompanyDto convertCompanyToEateryDto(Company company){

        EateryDto eateryDto = new EateryDto();

        eateryDto.setId(company.getId());
        eateryDto.setOutlets(company.getOutlets());
        eateryDto.setTopRightPicture(company.getEateryDetails().getTopRightPicture());
        eateryDto.setContactPerson(company.getEateryDetails().getContactPerson());
        eateryDto.setBusRegNumber(company.getEateryDetails().getBusRegNumber());
        eateryDto.setBillingAddress(company.getEateryDetails().getBillingAddress());
        eateryDto.setPostalCode(company.getEateryDetails().getPostalCode());
        eateryDto.setFaxNumber(company.getEateryDetails().getFaxNumber());
        eateryDto.setBusinessType(company.getEateryDetails().getBusinessType());

        return eateryDto;
    }

}
