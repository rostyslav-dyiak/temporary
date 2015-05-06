package com.kb.domain;


import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * A SystemAnnouncement.
 */
@Entity
@Table(name = "T_SYSTEMANNOUNCEMENT")
public class SystemAnnouncement implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "assignment_type")
    private String assignmentType;

    @Column(name = "subject")
    private String subject;

    @Column(name = "message")
    private String message;

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public String getAssignmentType() {
        return assignmentType;
    }

    public void setAssignmentType(final String assignmentType) {
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
