package com.kb.converter.announcement;

import com.kb.converter.AbstractConverter;
import com.kb.domain.SystemAnnouncement;
import com.kb.domain.ViewedType;
import com.kb.web.rest.dto.announcement.SystemAnnouncementResponseDto;
import com.kb.web.rest.dto.announcement.UserAnnouncementDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component("systemAnnouncementEntityConverter")
public class SystemAnnouncementEntityConverter extends AbstractConverter<SystemAnnouncement, SystemAnnouncementResponseDto> {

	@Override
	public SystemAnnouncementResponseDto convert(final SystemAnnouncement source, final SystemAnnouncementResponseDto target) {

		List<UserAnnouncementDto> sent = getSentUsers(source);
		List<UserAnnouncementDto> viewed = null;

        if (sent != null) {
            viewed = sent.stream()
					.filter(e -> ViewedType.READ.equals(e.getViewedType()))
					.collect(Collectors.toList());
		}

        target.setAssignmentType(source.getAssignmentType());
        target.setSubject(source.getSubject());
		target.setContent(source.getMessage());
		target.setSent(sent);
		target.setViewed(viewed);

        target.setUpdateDate(source.getLastModifiedDate());
        target.setCreateDate(source.getCreatedDate());
		target.setId(source.getId());
		target.setIsNormalUser(source.getIsNormalUser());
		target.setIsAdmin(source.getIsAdmin());

        return target;
    }

	private List<UserAnnouncementDto> getSentUsers(final SystemAnnouncement source) {
		List<UserAnnouncementDto> users = null;
		if (source.getUsers() != null) {
			users = source.getUsers().stream()
                .map(user -> new UserAnnouncementDto(user.getUser().getId(),
                    user.getLastModifiedDate(),
                    user.getUser().getAuthorities().stream().findFirst().get().getName(),
                    user.getStatus(),
                    user.getUser().getFirstName(),
								user.getUser().getLastName()))
				.collect(Collectors.toList());
		}

        return users;
    }

	@Override
	public SystemAnnouncementResponseDto convert(final SystemAnnouncement source) {
		return convert(source, new SystemAnnouncementResponseDto());
	}

}
