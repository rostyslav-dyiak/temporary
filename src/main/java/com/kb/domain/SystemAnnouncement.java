package com.kb.domain;


import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A SystemAnnouncement.
 */
@Entity
@Table(name = "T_SYSTEMANNOUNCEMENT")
public class SystemAnnouncement implements Serializable {

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

    public void setId(Long id) {
        this.id = id;
    }

    public String getAssignmentType() {
        return assignmentType;
    }

    public void setAssignmentType(String assignmentType) {
        this.assignmentType = assignmentType;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public boolean equals(Object o) {
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
