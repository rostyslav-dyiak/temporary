package com.kb.service.district;

import com.kb.domain.District;
import com.kb.domain.PostalCode;
import com.kb.repository.DistrictRepository;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.Set;

/**
 * Created by rdyyak on 07.05.15.
 */
@Component
public class DefaultDistrictService implements DistrictService {

    @Inject
    private DistrictRepository repository;

    @Override
    @Transactional
    public District save(District district) {
        Set<PostalCode> postalCodes = district.getPostalCodes();
        postalCodes.forEach((postalCode) -> postalCode.setDistrict(district));
        return repository.save(district);
    }
}
