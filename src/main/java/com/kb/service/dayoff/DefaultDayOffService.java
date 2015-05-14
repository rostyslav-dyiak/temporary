package com.kb.service.dayoff;

import com.kb.converter.Converter;
import com.kb.domain.DayOff;
import com.kb.repository.DayOffRepository;
import com.kb.web.rest.dto.dayoff.AggregatedDayOffDto;
import com.kb.web.rest.dto.dayoff.DayOffDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.inject.Inject;

@Service("dayOffService")
public class DefaultDayOffService implements DayOffService {

    @Inject
    private DayOffRepository repository;

    @Resource(name = "dayOffConverter")
    private Converter<DayOff, DayOffDto> entityConverter;

    @Resource(name = "dayOffAggregatedConverter")
    private Converter<Page<DayOff>, AggregatedDayOffDto> aggregatedEntityConverter;

    @Resource(name = "dayOffDtoConverter")
    private Converter<DayOffDto, DayOff> dtoConverter;

    @Override
    public AggregatedDayOffDto findAll(final Pageable request) {
		Page<DayOff> page = repository.findAll(request);
		AggregatedDayOffDto result = aggregatedEntityConverter.convert(page);

		return result;
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
