package com.kb.converter.announcement;

import com.kb.converter.AbstractConverter;
import com.kb.domain.SystemAnnouncement;
import com.kb.domain.SystemAnnouncementUser;
import com.kb.domain.User;
import com.kb.web.rest.dto.announcement.SystemAnnouncementDto;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component("systemAnnouncementDtoConverter")
public class SystemAnnouncementDtoConverter extends AbstractConverter<SystemAnnouncementDto	, SystemAnnouncement> {

	@Override
	public SystemAnnouncement convert(final SystemAnnouncementDto source, final SystemAnnouncement target) {
		Set<SystemAnnouncementUser> users = null;
		if (source.getUsers() != null) {
			users = source.getUsers().stream()
					.map(id -> {
						User user = new User();
						user.setId(id);
						SystemAnnouncementUser systemAnnouncementUser = new SystemAnnouncementUser();
						systemAnnouncementUser.setUser(user);
						return systemAnnouncementUser;
					})
					.collect(Collectors.toSet());
		}

        target.setUsers(users);
        target.setAssignmentType(source.getAssignmentType());
		target.setId(source.getId());
		target.setIsAdmin(source.getIsAdmin());
		target.setIsNormalUser(source.getIsNormalUser());
		target.setMessage(source.getContent());
		target.setSubject(source.getSubject());

        return target;
    }

	@Override
	public SystemAnnouncement convert(final SystemAnnouncementDto source) {
		return convert(source, new SystemAnnouncement());
	}

}
