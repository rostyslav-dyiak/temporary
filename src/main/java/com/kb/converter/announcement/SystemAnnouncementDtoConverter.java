package com.kb.converter.announcement;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.kb.converter.AbstractConverter;
import com.kb.domain.SystemAnnouncement;
import com.kb.domain.SystemAnnouncementUser;
import com.kb.web.rest.dto.announcement.SystemAnnouncementDto;

@Component("systemAnnouncementDtoConverter")
public class SystemAnnouncementDtoConverter extends AbstractConverter<SystemAnnouncementDto	, SystemAnnouncement> {

	@Override
	public SystemAnnouncement convert(final SystemAnnouncementDto source, final SystemAnnouncement target) {

		Set<SystemAnnouncementUser> users = source.getUsers().stream()
				.map(id -> new SystemAnnouncementUser(id))
				.collect(Collectors.toSet());
		
		target.setUsers(users);
		target.setAssignmentType(source.getAssignmentType());
		target.setId(source.getId());
		target.setIsAdmin(source.getIsAdmin());
		target.setIsNormalUser(target.getIsNormalUser());
		target.setMessage(source.getContent());
		target.setSubject(source.getSubject());
		
		return target;
	}

	@Override
	public SystemAnnouncement convert(final SystemAnnouncementDto source) {
		return convert(source, new SystemAnnouncement());
	}

}
