package com.kb.converter.dayoff;

import org.springframework.stereotype.Component;

import com.kb.converter.AbstractConverter;
import com.kb.domain.DayOff;
import com.kb.domain.PublicHoliday;
import com.kb.domain.SupplierDetails;
import com.kb.web.rest.dto.dayoff.DayOffDto;

@Component("dayOffDtoConverter")
public class DayOffDtoConverter extends AbstractConverter<DayOffDto, DayOff> {

	@Override
	public DayOff convert(final DayOffDto source, final DayOff target) {
		
		target.setAbsenceType(source.getAbsenceType());
		target.setDate(source.getDate());
		target.setDescription(source.getDescription());
		target.setId(source.getId());
		
		target.setCreatedBy(source.getCreatedBy());
		target.setCreatedDate(source.getCreatedDate());
		target.setLastModifiedBy(source.getLastModifiedBy());
		target.setLastModifiedDate(source.getLastModifiedDate());
		
		if (source.getPublicHolidayId() != null) {
			PublicHoliday publicHoliday = new PublicHoliday();
			publicHoliday.setId(source.getPublicHolidayId());
			target.setPublicHoliday(publicHoliday);
		}
		
		if(source.getSupplierDetailsId() != null) {
			SupplierDetails supplierDetails = new SupplierDetails();
			supplierDetails.setId(source.getSupplierDetailsId());
			target.setSupplierDetails(supplierDetails);
		}
		
		return target;
	}

	@Override
	public DayOff convert(final DayOffDto source) {
		return convert(source, new DayOff());
	}

}
