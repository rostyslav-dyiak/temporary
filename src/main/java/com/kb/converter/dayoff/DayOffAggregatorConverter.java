package com.kb.converter.dayoff;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.joda.time.DateTime;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import com.kb.converter.AbstractConverter;
import com.kb.domain.DayOff;
import com.kb.web.rest.dto.dayoff.AggregatedDayOffDto;
import com.kb.web.rest.dto.dayoff.DayOffDto;

@Component("dayOffAggregatedConverter")
public class DayOffAggregatorConverter extends AbstractConverter<Page<DayOff>, AggregatedDayOffDto> {

	@Override
	public AggregatedDayOffDto convert(final Page<DayOff> source, final AggregatedDayOffDto target) {
		Map<String, List<DayOffDto>> map = new TreeMap<String, List<DayOffDto>>();
		SimpleDateFormat simpleDtf = new SimpleDateFormat("MM/dd/yyyy");
		
		for (DayOff dayOff: source) {
			
			DateTime dateTime = dayOff.getDate();
			Date date = dateTime.toDate();
			String dateString = simpleDtf.format(date);
			
			// Cast to some custom date
			List<DayOffDto> result = map.get(dateString);
			
			if (result == null) {
				result = new ArrayList<DayOffDto>();
				map.put(dateString, result);
			}
			
			result.add(convertDayOff(dayOff));
		}
		
		target.setDates(map);
		target.setTotal(source.getTotalElements());
		
		return target;
	}

	private DayOffDto convertDayOff(final DayOff source) {
		
		DayOffDto dayOffDto = new DayOffDto();
		dayOffDto.setId(source.getId());
		dayOffDto.setAbsenceType(source.getAbsenceType());
		dayOffDto.setDate(source.getDate());
		dayOffDto.setDescription(source.getDescription());
		
		dayOffDto.setCreatedBy(source.getCreatedBy());
		dayOffDto.setCreatedDate(source.getCreatedDate());
		dayOffDto.setLastModifiedBy(source.getLastModifiedBy());
		dayOffDto.setLastModifiedDate(source.getLastModifiedDate());

		if (source.getPublicHoliday() != null) {
			dayOffDto.setPublicHolidayId(source.getPublicHoliday().getId());
		}
		
		if (source.getSupplierDetails() != null) {
			dayOffDto.setSupplierDetailsId(source.getSupplierDetails().getId());
		}
		
		return dayOffDto;
	}

	@Override
	public AggregatedDayOffDto convert(final Page<DayOff> source) {
		return convert(source, new AggregatedDayOffDto());
	}

}
