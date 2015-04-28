package com.kb.domain;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * A SupplierDetailsPublicHolidays.
 */
@Entity
@Table(name = "T_SUPPLIERDETAILSPUBLICHOLIDAYS")
public class SupplierDetailsPublicHolidays extends AbstractAuditingEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

	@ManyToOne
	@JoinColumn(name = "supplier_details_id")
	private SupplierDetails supplierDetails;

	@ManyToOne
	@JoinColumn(name = "public_holiday_id")
	private PublicHoliday publicHoliday;
	
    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public SupplierDetails getSupplierDetails() {
		return supplierDetails;
	}

	public void setSupplierDetails(final SupplierDetails supplierDetails) {
		this.supplierDetails = supplierDetails;
	}

	public PublicHoliday getPublicHoliday() {
		return publicHoliday;
	}

	public void setPublicHoliday(final PublicHoliday publicHoliday) {
		this.publicHoliday = publicHoliday;
	}

	@Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        SupplierDetailsPublicHolidays supplierDetailsPublicHolidays = (SupplierDetailsPublicHolidays) o;

        if ( ! Objects.equals(id, supplierDetailsPublicHolidays.id)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

	@Override
	public String toString() {
		return "SupplierDetailsPublicHolidays [id=" + id + "]";
	}
    
}
