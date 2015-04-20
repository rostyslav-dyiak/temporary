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
 * A Eatery_member.
 */
@Entity
@Table(name = "T_EATERY_MEMBER")
public class EateryMember implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "salutation")
    private String salutation;

    @Column(name = "contact_number")
    private String contact_number;

    @Column(name = "email")
    private String email;

    @Column(name = "status")
    private Boolean status;

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(final String title) {
        this.title = title;
    }

    public String getSalutation() {
        return salutation;
    }

    public void setSalutation(final String salutation) {
        this.salutation = salutation;
    }

    public String getContact_number() {
        return contact_number;
    }

    public void setContact_number(final String contact_number) {
        this.contact_number = contact_number;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(final String email) {
        this.email = email;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(final Boolean status) {
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

        EateryMember eatery_member = (EateryMember) o;

        if ( ! Objects.equals(id, eatery_member.id)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Eatery_member{" +
                "id=" + id +
                ", title='" + title + "'" +
                ", salutation='" + salutation + "'" +
                ", contact_number='" + contact_number + "'" +
                ", email='" + email + "'" +
                ", status='" + status + "'" +
                '}';
    }
}
