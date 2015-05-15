package com.kb.service.announcement;

import com.kb.converter.Converter;
import com.kb.domain.SystemAnnouncement;
import com.kb.repository.SystemAnnouncementRepository;
import com.kb.web.rest.dto.announcement.SystemAnnouncementDto;
import com.kb.web.rest.dto.announcement.SystemAnnouncementResponseDto;
import com.kb.web.rest.dto.announcement.SystemAnnouncementsDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.transaction.Transactional;

@Service
@Transactional
public class DefaultSystemAnnouncementService implements SystemAnnouncementService {

	@Inject
	private SystemAnnouncementRepository repository;

	@Resource(name = "systemAnnouncementDtoConverter")
	private Converter<SystemAnnouncementDto	, SystemAnnouncement> dtoConverter;

    @Resource(name = "systemAnnouncementsConverter")
    private Converter<Page<SystemAnnouncement>, SystemAnnouncementsDto> systemAnnouncementsConverter;

    @Resource(name = "systemAnnouncementEntityConverter")
    private Converter<SystemAnnouncement, SystemAnnouncementResponseDto> entityConverter;

    @Override
    public void save(final SystemAnnouncementDto systemAnnouncement) {
		SystemAnnouncement entity = dtoConverter.convert(systemAnnouncement);
		repository.save(entity);
	}

	@Override
	public SystemAnnouncementsDto findAll(final Pageable pageable) {
		Page<SystemAnnouncement> page = repository.findAll(pageable);
		return systemAnnouncementsConverter.convert(page);
	}

	@Override
	public void delete(final Long id) {
		repository.delete(id);
	}

	@Override
	public SystemAnnouncementResponseDto findOne(final Long id) {
		return entityConverter.convert(repository.findOne(id));
	}


}
