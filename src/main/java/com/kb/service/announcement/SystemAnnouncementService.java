package com.kb.service.announcement;

import com.kb.web.rest.dto.announcement.SystemAnnouncementDto;
import com.kb.web.rest.dto.announcement.SystemAnnouncementResponseDto;
import com.kb.web.rest.dto.announcement.SystemAnnouncementsDto;
import org.springframework.data.domain.Pageable;

public interface SystemAnnouncementService {

	void save(SystemAnnouncementDto systemAnnouncement);

	SystemAnnouncementsDto findAll(Pageable generatePageRequest);

	void delete(Long id);

    SystemAnnouncementResponseDto findOne(Long id);

}
