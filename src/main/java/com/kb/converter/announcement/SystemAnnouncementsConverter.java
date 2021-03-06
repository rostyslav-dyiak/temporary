package com.kb.converter.announcement;

import com.kb.converter.AbstractConverter;
import com.kb.converter.Converter;
import com.kb.domain.SystemAnnouncement;
import com.kb.web.rest.dto.announcement.SystemAnnouncementResponseDto;
import com.kb.web.rest.dto.announcement.SystemAnnouncementsDto;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

@Component("systemAnnouncementsConverter")
public class SystemAnnouncementsConverter extends AbstractConverter<Page<SystemAnnouncement>, SystemAnnouncementsDto> {

	@Resource(name = "systemAnnouncementEntityConverter")
	private Converter<SystemAnnouncement, SystemAnnouncementResponseDto> entityConverter;

    @Override
    public SystemAnnouncementsDto convert(final Page<SystemAnnouncement> source, final SystemAnnouncementsDto target) {

        List<SystemAnnouncementResponseDto> announcements = null;

        if (source.getContent() != null) {
            announcements = source.getContent()
					.stream()
					.map(e -> entityConverter.convert(e))
					.collect(Collectors.toList());
		}

        target.setTotal(source.getTotalElements());
        target.setAnnouncements(announcements);

        return target;
    }

	@Override
	public SystemAnnouncementsDto convert(final Page<SystemAnnouncement> source) {
		return convert(source, new SystemAnnouncementsDto());
	}

}
