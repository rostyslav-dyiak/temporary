package com.kb.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

/**
 * Created by rdyyak on 07.05.15.
 */
@Entity
@Table(name = "T_POSTALCODE")
public class PostalCode {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "first_two_digits")
    private String firstTwoDigits;

    @ManyToOne
    @JoinColumn(name = "district_id")
    @JsonIgnore
    private District district;

    public District getDistrict() {
        return district;
    }

    public void setDistrict(District district) {
        this.district = district;
    }

    public String getFirstTwoDigits() {
        return firstTwoDigits;
    }

    public void setFirstTwoDigits(String firstTwoDigits) {
        this.firstTwoDigits = firstTwoDigits;
    }

    public Long getId() {

        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PostalCode that = (PostalCode) o;

        if (firstTwoDigits != null ? !firstTwoDigits.equals(that.firstTwoDigits) : that.firstTwoDigits != null)
            return false;
        if (id != null ? !id.equals(that.id) : that.id != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (firstTwoDigits != null ? firstTwoDigits.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "PostalCode{" +
            "id=" + id +
            ", firstTwoDigits='" + firstTwoDigits + '\'' +
            '}';
    }

}
