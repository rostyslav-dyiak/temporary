package com.kb.service.dayoff;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.kb.dto.DayOffDto;

public interface DayOffService {

	Page<DayOffDto> findAll(Pageable generatePageRequest);

	void save(DayOffDto dto);

	DayOffDto findOne(Long id);

	void delete(Long id);

}
