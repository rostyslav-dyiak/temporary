package com.kb.domain;


import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * A District.
 */
@Entity
@Table(name = "T_DISTRICT")
public class District implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "locations")
    private String locations;

    @OneToMany(mappedBy = "district", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<PostalCode> postalCodes;

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

    public String getLocations() {
        return locations;
    }

    public void setLocations(final String locations) {
        this.locations = locations;
    }

    public Set<PostalCode> getPostalCodes() {
        return postalCodes;
    }

    public void setPostalCodes(final Set<PostalCode> postalCodes) {
        this.postalCodes = postalCodes;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        District district = (District) o;

        if (!Objects.equals(id, district.id)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "District{" +
            "id=" + id +
            ", name='" + name + "'" +
            ", description='" + description + "'" +
            ", locations='" + locations + "'" +
            '}';
    }
}
