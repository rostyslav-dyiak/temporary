package com.kb.converter.announcement;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import com.kb.domain.AssignmentType;
import com.kb.domain.SystemAnnouncement;
import com.kb.domain.SystemAnnouncementUser;
import com.kb.domain.User;
import com.kb.web.rest.dto.announcement.SystemAnnouncementDto;

public class SystemAnnouncementDtoConverterTest {

	private SystemAnnouncementDtoConverter unit = new SystemAnnouncementDtoConverter();
	
	@Test
	public void testConvert() throws Exception {
		// Given
		AssignmentType assignmentType = AssignmentType.ALL;
		Boolean isAdmin = true;
		Boolean isNormalUser = true;
		String message = "fdsfdsfds";
		String subject = "fsdfds";
		Long id = 2453L;
		
		Long id1 = 1L;
		Long id2 = 2L;
		Long id3 = 3L;
		Set<Long> users = new HashSet<>(Arrays.asList(id1, id2, id3));
		SystemAnnouncementDto source = new SystemAnnouncementDto();
		source.setUsers(users);
		source.setAssignmentType(assignmentType);
		source.setId(id);
		source.setIsAdmin(isAdmin);
		source.setIsNormalUser(isNormalUser);
		source.setContent(message);
		source.setSubject(subject);
		
		
		SystemAnnouncementUser systemAnnouncementUser1 = new SystemAnnouncementUser();
		User user1 = new User();
		user1.setId(id1);
		systemAnnouncementUser1.setUser(user1);
		
		SystemAnnouncementUser systemAnnouncementUser2 = new SystemAnnouncementUser();
		User user2 = new User();
		user2.setId(id2);
		systemAnnouncementUser2.setUser(user2);
		
		SystemAnnouncementUser systemAnnouncementUser3 = new SystemAnnouncementUser();
		User user3 = new User();
		user3.setId(id3);
		systemAnnouncementUser3.setUser(user3);
		
		Set<SystemAnnouncementUser> users1 = new HashSet<SystemAnnouncementUser>(Arrays.<SystemAnnouncementUser>asList(systemAnnouncementUser1, systemAnnouncementUser2, systemAnnouncementUser3));
		SystemAnnouncement expected = new SystemAnnouncement();
		expected.setUsers(users1);
		expected.setAssignmentType(assignmentType);
		expected.setId(id);
		expected.setIsAdmin(isAdmin);
		expected.setIsNormalUser(isNormalUser);
		expected.setMessage(message);
		expected.setSubject(subject);
		
		// When
		SystemAnnouncement actual = unit.convert(source);

		// Then
		assertEquals(expected, actual);
	}
	
	@Test
	public void testConvertWithEmptyUsers() throws Exception {
		// Given
		AssignmentType assignmentType = AssignmentType.ALL;
		Boolean isAdmin = true;
		Boolean isNormalUser = true;
		String message = "fdsfdsfds";
		String subject = "fsdfds";
		Long id = 2453L;
		
		SystemAnnouncementDto source = new SystemAnnouncementDto();
		source.setAssignmentType(assignmentType);
		source.setId(id);
		source.setIsAdmin(isAdmin);
		source.setIsNormalUser(isNormalUser);
		source.setContent(message);
		source.setSubject(subject);
		
		Set<SystemAnnouncementUser> users1 = null;
		SystemAnnouncement expected = new SystemAnnouncement();
		expected.setUsers(users1);
		expected.setAssignmentType(assignmentType);
		expected.setId(id);
		expected.setIsAdmin(isAdmin);
		expected.setIsNormalUser(isNormalUser);
		expected.setMessage(message);
		expected.setSubject(subject);
		
		// When
		SystemAnnouncement actual = unit.convert(source);
		
		// Then
		assertEquals(expected, actual);
	}
}
