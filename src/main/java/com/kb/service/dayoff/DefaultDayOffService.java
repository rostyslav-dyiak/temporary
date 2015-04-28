package com.kb.service.dayoff;

import java.util.List;

import javax.annotation.Resource;
import javax.inject.Inject;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.kb.converter.Converter;
import com.kb.domain.DayOff;
import com.kb.dto.DayOffDto;
import com.kb.repository.DayOffRepository;

@Service("dayOffService")
public class DefaultDayOffService implements DayOffService {

    @Inject
    private DayOffRepository repository;
	
    @Resource(name = "dayOffConverter")
    private Converter<DayOff, DayOffDto> entityConverter;
    
    @Resource(name = "dayOffDtoConverter")
    private Converter<DayOffDto, DayOff> dtoConverter;
    
	@Override
	public Page<DayOffDto> findAll(final Pageable request) {
		Page<DayOff> page = repository.findAll(request);
		List<DayOffDto> dtos = entityConverter.convertAll(page.getContent());
		return new PageImpl<>(dtos, request, page.getTotalElements());
	}

	@Override
	public void save(final DayOffDto dto) {
		DayOff entity = dtoConverter.convert(dto);
		repository.save(entity);
	}

	@Override
	public DayOffDto findOne(final Long id) {
		DayOff entity = repository.findOne(id);
		return entityConverter.convert(entity);
	}

	@Override
	public void delete(final Long id) {
		repository.delete(id);
	}

}
