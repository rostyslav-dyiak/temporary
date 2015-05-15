package com.kb.domain;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.kb.domain.util.CustomDateTimeDeserializer;
import com.kb.domain.util.CustomDateTimeSerializer;
import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A SupplierDetailsPublicHolidays.
 */
@Entity
@Table(name = "T_DAY_OFF")
public class DayOff extends AbstractAuditingEntity implements Serializable {
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

    @Enumerated(EnumType.STRING)
    @Column(name = "absence_type")
	private AbsenceType absenceType;

    @Column(name = "description")
    private String description;

    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    @JsonSerialize(using = CustomDateTimeSerializer.class)
    @JsonDeserialize(using = CustomDateTimeDeserializer.class)
    @Column(name = "date")
    private DateTime date;

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

	public AbsenceType getAbsenceType() {
		return absenceType;
	}

	public void setAbsenceType(final AbsenceType absenceType) {
		this.absenceType = absenceType;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(final String description) {
		this.description = description;
	}

	public DateTime getDate() {
		return date;
	}

	public void setDate(final DateTime date) {
		this.date = date;
	}

	@Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        DayOff supplierDetailsPublicHolidays = (DayOff) o;

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
