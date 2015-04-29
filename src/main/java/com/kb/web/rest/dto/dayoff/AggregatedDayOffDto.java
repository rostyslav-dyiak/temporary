package com.kb.web.rest.dto.dayoff;

import java.util.List;
import java.util.Map;

public class AggregatedDayOffDto {

	private Map<String, List<DayOffDto>> dates;

	private Long total;
	
	public Map<String, List<DayOffDto>> getDates() {
		return dates;
	}

	public void setDates(final Map<String, List<DayOffDto>> dates) {
		this.dates = dates;
	}

	public Long getTotal() {
		return total;
	}

	public void setTotal(final Long total) {
		this.total = total;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dates == null) ? 0 : dates.hashCode());
		result = prime * result + ((total == null) ? 0 : total.hashCode());
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
		AggregatedDayOffDto other = (AggregatedDayOffDto) obj;
		if (dates == null) {
			if (other.dates != null) {
				return false;
			}
		} else if (!dates.equals(other.dates)) {
			return false;
		}
		if (total == null) {
			if (other.total != null) {
				return false;
			}
		} else if (!total.equals(other.total)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "AggregatedDayOffDto [dates=" + dates + ", total=" + total + "]";
	}

}
