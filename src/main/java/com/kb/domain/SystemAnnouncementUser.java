package com.kb.domain;


import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "T_SYSTEMANNOUNCEMENTUSER")
public class SystemAnnouncementUser extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@ManyToOne
	@JoinColumn(name = "system_announcement_id")
	private SystemAnnouncement systemAnnouncement;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private ViewedType status;

    public SystemAnnouncementUser() {
		super();
	}

	public SystemAnnouncementUser(final Long id) {
		super(id);
	}

	@Override
	public Long getId() {
        return id;
    }

    @Override
	public void setId(final Long id) {
        this.id = id;
    }

    public SystemAnnouncement getSystemAnnouncement() {
		return systemAnnouncement;
	}

	public void setSystemAnnouncement(final SystemAnnouncement systemAnnouncement) {
		this.systemAnnouncement = systemAnnouncement;
	}

	public User getUser() {
		return user;
	}

	public void setUser(final User user) {
		this.user = user;
	}

	public ViewedType getStatus() {
        return status;
    }

    public void setStatus(final ViewedType status) {
        this.status = status;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        SystemAnnouncementUser systemAnnouncementUser = (SystemAnnouncementUser) o;

        if ( ! Objects.equals(id, systemAnnouncementUser.id)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

	@Override
	public String toString() {
		return "SystemAnnouncementUser [id=" + id + ", status=" + status + "]";
	}

}
