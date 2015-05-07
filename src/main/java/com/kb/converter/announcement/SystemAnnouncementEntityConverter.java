package com.kb.converter.announcement;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.kb.converter.AbstractConverter;
import com.kb.domain.SystemAnnouncement;
import com.kb.domain.ViewedType;
import com.kb.web.rest.dto.announcement.SystemAnnouncementResponseDto;
import com.kb.web.rest.dto.announcement.UserAnnouncementDto;

@Component("systemAnnouncementEntityConverter")
public class SystemAnnouncementEntityConverter extends AbstractConverter<SystemAnnouncement, SystemAnnouncementResponseDto> {

	@Override
	public SystemAnnouncementResponseDto convert(final SystemAnnouncement source, final SystemAnnouncementResponseDto target) {
		
		List<UserAnnouncementDto> sent = getSentUsers(source);
		List<UserAnnouncementDto> viewed = sent.stream()
				.filter(e -> ViewedType.READ.equals(e.getViewedType()))
				.collect(Collectors.toList());
		
		target.setAssignmentType(source.getAssignmentType());
		target.setSubject(source.getSubject());
		target.setContent(source.getMessage());
		target.setSent(sent);
		target.setViewed(viewed);
		
		target.setUpdateDate(source.getLastModifiedDate());
		target.setCreateDate(source.getCreatedDate());
		
		return target;
	}

	private List<UserAnnouncementDto> getSentUsers(final SystemAnnouncement source) {
		return source.getUsers().stream()
			.map(user -> new UserAnnouncementDto(user.getUser().getId(), 
							user.getLastModifiedDate(), 
							user.getUser().getAuthorities().stream().findFirst().get().getName(), 
							user.getStatus(),
							user.getUser().getFirstName(),
							user.getUser().getLastName()))
			.collect(Collectors.toList());
	}

	@Override
	public SystemAnnouncementResponseDto convert(final SystemAnnouncement source) {
		return convert(source, new SystemAnnouncementResponseDto());
	}

}
