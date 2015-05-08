package com.kb.web.rest.dto.announcement;

import java.util.List;

import org.joda.time.DateTime;

import com.kb.domain.AssignmentType;

public class SystemAnnouncementResponseDto {

	private Long id;
	private String subject;
	private String content;
	private AssignmentType assignmentType;
	private DateTime updateDate;
	private DateTime createDate;
    private Boolean isAdmin;
    private Boolean isNormalUser;
	private List<UserAnnouncementDto> viewed;
	private List<UserAnnouncementDto> sent;
	
	public Long getId() {
		return id;
	}

	public void setId(final Long id) {
		this.id = id;
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
	
	public void setContent(final String content) {
		this.content = content;
	}
	
	public AssignmentType getAssignmentType() {
		return assignmentType;
	}
	
	public void setAssignmentType(final AssignmentType assignmentType) {
		this.assignmentType = assignmentType;
	}
	
	public DateTime getUpdateDate() {
		return updateDate;
	}
	
	public void setUpdateDate(final DateTime updateDate) {
		this.updateDate = updateDate;
	}
	
	public DateTime getCreateDate() {
		return createDate;
	}
	
	public void setCreateDate(final DateTime createDate) {
		this.createDate = createDate;
	}
	public List<UserAnnouncementDto> getViewed() {
		return viewed;
	}
	
	public void setViewed(final List<UserAnnouncementDto> viewed) {
		this.viewed = viewed;
	}
	
	public List<UserAnnouncementDto> getSent() {
		return sent;
	}
	
	public void setSent(final List<UserAnnouncementDto> sent) {
		this.sent = sent;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((assignmentType == null) ? 0 : assignmentType.hashCode());
		result = prime * result + ((content == null) ? 0 : content.hashCode());
		result = prime * result
				+ ((createDate == null) ? 0 : createDate.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((isAdmin == null) ? 0 : isAdmin.hashCode());
		result = prime * result
				+ ((isNormalUser == null) ? 0 : isNormalUser.hashCode());
		result = prime * result + ((sent == null) ? 0 : sent.hashCode());
		result = prime * result + ((subject == null) ? 0 : subject.hashCode());
		result = prime * result
				+ ((updateDate == null) ? 0 : updateDate.hashCode());
		result = prime * result + ((viewed == null) ? 0 : viewed.hashCode());
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
		SystemAnnouncementResponseDto other = (SystemAnnouncementResponseDto) obj;
		if (assignmentType != other.assignmentType) {
			return false;
		}
		if (content == null) {
			if (other.content != null) {
				return false;
			}
		} else if (!content.equals(other.content)) {
			return false;
		}
		if (createDate == null) {
			if (other.createDate != null) {
				return false;
			}
		} else if (!createDate.equals(other.createDate)) {
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
		if (sent == null) {
			if (other.sent != null) {
				return false;
			}
		} else if (!sent.equals(other.sent)) {
			return false;
		}
		if (subject == null) {
			if (other.subject != null) {
				return false;
			}
		} else if (!subject.equals(other.subject)) {
			return false;
		}
		if (updateDate == null) {
			if (other.updateDate != null) {
				return false;
			}
		} else if (!updateDate.equals(other.updateDate)) {
			return false;
		}
		if (viewed == null) {
			if (other.viewed != null) {
				return false;
			}
		} else if (!viewed.equals(other.viewed)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "SystemAnnouncementResponseDto [id=" + id + ", subject="
				+ subject + ", content=" + content + ", assignmentType="
				+ assignmentType + ", updateDate=" + updateDate
				+ ", createDate=" + createDate + ", isAdmin=" + isAdmin
				+ ", isNormalUser=" + isNormalUser + ", viewed=" + viewed
				+ ", sent=" + sent + "]";
	}
	
}
