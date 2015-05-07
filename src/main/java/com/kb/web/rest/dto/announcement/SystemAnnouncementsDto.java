package com.kb.web.rest.dto.announcement;

import java.util.List;

public class SystemAnnouncementsDto {

	private Long total;
	
	private List<SystemAnnouncementResponseDto> announcements;

	public Long getTotal() {
		return total;
	}

	public void setTotal(final Long total) {
		this.total = total;
	}

	public List<SystemAnnouncementResponseDto> getAnnouncements() {
		return announcements;
	}

	public void setAnnouncements(final List<SystemAnnouncementResponseDto> announcements) {
		this.announcements = announcements;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((announcements == null) ? 0 : announcements.hashCode());
		result = prime * result + ((total == null) ? 0 : total.hashCode());
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
		SystemAnnouncementsDto other = (SystemAnnouncementsDto) obj;
		if (announcements == null) {
			if (other.announcements != null) {
				return false;
			}
		} else if (!announcements.equals(other.announcements)) {
			return false;
		}
		if (total == null) {
			if (other.total != null) {
				return false;
			}
		} else if (!total.equals(other.total)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "SystemAnnouncementsDto [total=" + total + ", announcements="
				+ announcements + "]";
	}
	
}
