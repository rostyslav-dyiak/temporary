package com.kb.web.rest.dto.announcement;

import org.joda.time.DateTime;

import com.kb.domain.ViewedType;

public class UserAnnouncementDto {

	private Long id;
	private DateTime viewedDate;
	private String role;
	private ViewedType viewedType;
    private String firstName;
    private String lastName;

    public UserAnnouncementDto() {
		super();
	}

	public UserAnnouncementDto(final Long id, final DateTime viewedDate, final String role,
			final ViewedType viewedType, final String firstName, final String lastName) {
		super();
		this.id = id;
		this.viewedDate = viewedDate;
		this.role = role;
		this.viewedType = viewedType;
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public Long getId() {
		return id;
	}
	
	public void setId(final Long id) {
		this.id = id;
	}
	
	public DateTime getViewedDate() {
		return viewedDate;
	}
	
	public void setViewedDate(final DateTime viewedDate) {
		this.viewedDate = viewedDate;
	}
	
	public String getRole() {
		return role;
	}
	
	public void setRole(final String role) {
		this.role = role;
	}
	
	public ViewedType getViewedType() {
		return viewedType;
	}

	public void setViewedType(final ViewedType viewedType) {
		this.viewedType = viewedType;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(final String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(final String lastName) {
		this.lastName = lastName;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result
				+ ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result + ((role == null) ? 0 : role.hashCode());
		result = prime * result
				+ ((viewedDate == null) ? 0 : viewedDate.hashCode());
		result = prime * result
				+ ((viewedType == null) ? 0 : viewedType.hashCode());
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
		UserAnnouncementDto other = (UserAnnouncementDto) obj;
		if (firstName == null) {
			if (other.firstName != null) {
				return false;
			}
		} else if (!firstName.equals(other.firstName)) {
			return false;
		}
		if (id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!id.equals(other.id)) {
			return false;
		}
		if (lastName == null) {
			if (other.lastName != null) {
				return false;
			}
		} else if (!lastName.equals(other.lastName)) {
			return false;
		}
		if (role == null) {
			if (other.role != null) {
				return false;
			}
		} else if (!role.equals(other.role)) {
			return false;
		}
		if (viewedDate == null) {
			if (other.viewedDate != null) {
				return false;
			}
		} else if (!viewedDate.equals(other.viewedDate)) {
			return false;
		}
		if (viewedType != other.viewedType) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "UserAnnouncementDto [id=" + id + ", viewedDate=" + viewedDate
				+ ", role=" + role + ", viewedType=" + viewedType
				+ ", firstName=" + firstName + ", lastName=" + lastName + "]";
	}

}
