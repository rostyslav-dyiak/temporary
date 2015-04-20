package com.kb.domain;


import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A Eatery_member.
 */
@Entity
@Table(name = "T_EATERY_MEMBER")
public class EateryMember implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "salutation")
    private String salutation;

    @Column(name = "contactNumber")
    private String contactNumber;

    @Column(name = "email")
    private String email;

    @Column(name = "status")
    private Boolean status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSalutation() {
        return salutation;
    }

    public void setSalutation(String salutation) {
        this.salutation = salutation;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contact_number) {
        this.contactNumber = contact_number;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
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
                ", contactNumber='" + contactNumber + "'" +
                ", email='" + email + "'" +
                ", status='" + status + "'" +
                '}';
    }
}
