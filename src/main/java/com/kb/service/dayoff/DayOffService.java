package com.kb.service.dayoff;

import com.kb.web.rest.dto.dayoff.AggregatedDayOffDto;
import com.kb.web.rest.dto.dayoff.DayOffDto;
import org.springframework.data.domain.Pageable;

public interface DayOffService {

	AggregatedDayOffDto findAll(Pageable generatePageRequest);

	void save(DayOffDto dto);

	DayOffDto findOne(Long id);

	void delete(Long id);

}
