package com.kb.web.rest.dto;

import javax.validation.constraints.NotNull;

import org.joda.time.DateTime;

import com.kb.domain.AbsenceType;

public class DayOffDto extends BaseDto {

    private Long id;

    @NotNull
	private Long supplierDetailsId;

	private Long publicHolidayId;
	
	private AbsenceType absenceType;
	
    private String description;

    private DateTime date;

	public Long getId() {
		return id;
	}

	public void setId(final Long id) {
		this.id = id;
	}

	public Long getSupplierDetailsId() {
		return supplierDetailsId;
	}

	public void setSupplierDetailsId(final Long supplierDetailsId) {
		this.supplierDetailsId = supplierDetailsId;
	}

	public Long getPublicHolidayId() {
		return publicHolidayId;
	}

	public void setPublicHolidayId(final Long publicHolidayId) {
		this.publicHolidayId = publicHolidayId;
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
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((absenceType == null) ? 0 : absenceType.hashCode());
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result
				+ ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result
				+ ((publicHolidayId == null) ? 0 : publicHolidayId.hashCode());
		result = prime
				* result
				+ ((supplierDetailsId == null) ? 0 : supplierDetailsId
						.hashCode());
		return result;
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		DayOffDto other = (DayOffDto) obj;
		if (absenceType != other.absenceType) {
			return false;
		}
		if (date == null) {
			if (other.date != null) {
				return false;
			}
		} else if (!date.equals(other.date)) {
			return false;
		}
		if (description == null) {
			if (other.description != null) {
				return false;
			}
		} else if (!description.equals(other.description)) {
			return false;
		}
		if (id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!id.equals(other.id)) {
			return false;
		}
		if (publicHolidayId == null) {
			if (other.publicHolidayId != null) {
				return false;
			}
		} else if (!publicHolidayId.equals(other.publicHolidayId)) {
			return false;
		}
		if (supplierDetailsId == null) {
			if (other.supplierDetailsId != null) {
				return false;
			}
		} else if (!supplierDetailsId.equals(other.supplierDetailsId)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "DayOffDto [id=" + id + ", supplierDetailsId="
				+ supplierDetailsId + ", publicHolidayId=" + publicHolidayId
				+ ", absenceType=" + absenceType + ", description="
				+ description + ", date=" + date + "]";
	}
	
}
