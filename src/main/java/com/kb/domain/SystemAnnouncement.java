package com.kb.domain;


import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

/**
 * A SystemAnnouncement.
 */
@Entity
@Table(name = "T_SYSTEMANNOUNCEMENT")
public class SystemAnnouncement extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

    @Column(name = "assignment_type")
    @Enumerated(EnumType.STRING)
    private AssignmentType assignmentType;

    @Column(name = "subject")
    private String subject;

    @Column(name = "message")
    private String message;

    @Column(name = "is_admin")
    private Boolean isAdmin;

    @Column(name = "is_normal_user")
    private Boolean isNormalUser;

    @OneToMany
    @JoinColumn(name = "system_announcement_id")
    private Set<SystemAnnouncementUser> users;

    @Override
	public Long getId() {
        return id;
    }

    @Override
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

    public String getMessage() {
        return message;
    }

    public void setMessage(final String message) {
        this.message = message;
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

	public Set<SystemAnnouncementUser> getUsers() {
		return users;
	}

	public void setUsers(final Set<SystemAnnouncementUser> users) {
		this.users = users;
	}

	@Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        SystemAnnouncement systemAnnouncement = (SystemAnnouncement) o;

        if ( ! Objects.equals(id, systemAnnouncement.id)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "SystemAnnouncement{" +
                "id=" + id +
                ", assignmentType='" + assignmentType + "'" +
                ", subject='" + subject + "'" +
                ", message='" + message + "'" +
                '}';
    }
}
