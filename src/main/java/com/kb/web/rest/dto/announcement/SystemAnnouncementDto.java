package com.kb.web.rest.dto.announcement;

import com.kb.domain.AssignmentType;

import java.util.Set;

public class SystemAnnouncementDto {

    private Long id;
	private AssignmentType assignmentType;
    private String subject;
    private String content;
    private Boolean isAdmin;
    private Boolean isNormalUser;
    private Set<Long> users;

	public Long getId() {
		return id;
	}

	public void setId(final Long id) {
		this.id = id;
	}

	public AssignmentType getAssignmentType() {
		return assignmentType;
	}

	public void setAssignmentType(final AssignmentType assignmentType) {
		this.assignmentType = assignmentType;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(final String subject) {
		this.subject = subject;
	}

	public String getContent() {
		return content;
	}

	public void setContent(final String message) {
		this.content = message;
	}

	public Boolean getIsAdmin() {
		return isAdmin;
	}

	public void setIsAdmin(final Boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

	public Boolean getIsNormalUser() {
		return isNormalUser;
	}

	public void setIsNormalUser(final Boolean isNormalUser) {
		this.isNormalUser = isNormalUser;
	}

	public Set<Long> getUsers() {
		return users;
	}

	public void setUsers(final Set<Long> users) {
		this.users = users;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((assignmentType == null) ? 0 : assignmentType.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((isAdmin == null) ? 0 : isAdmin.hashCode());
		result = prime * result
				+ ((isNormalUser == null) ? 0 : isNormalUser.hashCode());
		result = prime * result + ((content == null) ? 0 : content.hashCode());
		result = prime * result + ((subject == null) ? 0 : subject.hashCode());
		result = prime * result + ((users == null) ? 0 : users.hashCode());
		return result;
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		SystemAnnouncementDto other = (SystemAnnouncementDto) obj;
		if (assignmentType != other.assignmentType) {
			return false;
		}
		if (id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!id.equals(other.id)) {
			return false;
		}
		if (isAdmin == null) {
			if (other.isAdmin != null) {
				return false;
			}
		} else if (!isAdmin.equals(other.isAdmin)) {
			return false;
		}
		if (isNormalUser == null) {
			if (other.isNormalUser != null) {
				return false;
			}
		} else if (!isNormalUser.equals(other.isNormalUser)) {
			return false;
		}
		if (content == null) {
			if (other.content != null) {
				return false;
			}
		} else if (!content.equals(other.content)) {
			return false;
		}
		if (subject == null) {
			if (other.subject != null) {
				return false;
			}
		} else if (!subject.equals(other.subject)) {
			return false;
		}
		if (users == null) {
			if (other.users != null) {
				return false;
			}
		} else if (!users.equals(other.users)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "SystemAnnouncementDto [id=" + id + ", assignmentType="
				+ assignmentType + ", subject=" + subject + ", message="
				+ content + ", isAdmin=" + isAdmin + ", isNormalUser="
				+ isNormalUser + ", users=" + users + "]";
	}

}
