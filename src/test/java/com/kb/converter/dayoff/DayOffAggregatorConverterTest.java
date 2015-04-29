package com.kb.converter.dayoff;

import static org.junit.Assert.assertEquals;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.junit.Test;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import com.kb.domain.DayOff;
import com.kb.web.rest.dto.dayoff.AggregatedDayOffDto;
import com.kb.web.rest.dto.dayoff.DayOffDto;

public class DayOffAggregatorConverterTest {


	private static final String dateString1 = "11/15/2013 08:00:00";
	private static final String dateString2 = "11/14/2013 08:00:00";
	private static final String dateString4 = "11/13/2013 08:00:00";
	private static final String dateString5 = "11/12/2013 08:00:00";

	private DayOffAggregatorConverter unit = new DayOffAggregatorConverter();
	
	private DateTimeFormatter dtf = DateTimeFormat.forPattern("MM/dd/yyyy HH:mm:ss");
	
	@Test
	public void testConvert() throws Exception {
		// Given
		DateTime dateTime1 = dtf.parseDateTime(dateString1);
		DateTime dateTime2 = dtf.parseDateTime(dateString2);
		DateTime dateTime4 = dtf.parseDateTime(dateString4);
		DateTime dateTime5 = dtf.parseDateTime(dateString5);

		DayOff dayOff1 = new DayOff();
		dayOff1.setDate(dateTime1);
		
		DayOff dayOff2 = new DayOff();
		dayOff2.setDate(dateTime2);

		DayOff dayOff3 = new DayOff();
		dayOff3.setDate(dateTime2);
		
		DayOff dayOff4 = new DayOff();
		dayOff4.setDate(dateTime4);
		
		DayOff dayOff5 = new DayOff();
		dayOff5.setDate(dateTime5);
		
		Long total = 9L;
		List<DayOff> listElements = Arrays.asList(dayOff1, dayOff2, dayOff3, dayOff4, dayOff5);
		Page<DayOff> source = new PageImpl<>(listElements, null, total);
		
		Map<String, List<DayOffDto>> dates = new TreeMap<String, List<DayOffDto>>();

		SimpleDateFormat simpleDtf = new SimpleDateFormat("MM/dd/yyyy");
		
		DayOffDto dayOffDto1 = new DayOffDto();
		dayOffDto1.setDate(dateTime1);
		
		DayOffDto dayOffDto2 = new DayOffDto();
		dayOffDto2.setDate(dateTime2);

		DayOffDto dayOffDto3 = new DayOffDto();
		dayOffDto3.setDate(dateTime2);

		DayOffDto dayOffDto4 = new DayOffDto();
		dayOffDto4.setDate(dateTime4);
		
		DayOffDto dayOffDto5 = new DayOffDto();
		dayOffDto5.setDate(dateTime5);
		
		List<DayOffDto> list1 = Arrays.asList(dayOffDto1);
		List<DayOffDto> list2 = Arrays.asList(dayOffDto2, dayOffDto3);
		List<DayOffDto> list4 = Arrays.asList(dayOffDto4);
		List<DayOffDto> list5 = Arrays.asList(dayOffDto5);
		
		dates.put(simpleDtf.format(dateTime1.toDate()), list1);
		dates.put(simpleDtf.format(dateTime2.toDate()), list2);
		dates.put(simpleDtf.format(dateTime4.toDate()), list4);
		dates.put(simpleDtf.format(dateTime5.toDate()), list5);
		
		AggregatedDayOffDto expected = new AggregatedDayOffDto();
		expected.setDates(dates);
		expected.setTotal(total);
		
		// When
		AggregatedDayOffDto actual = unit.convert(source);

		// Then
		assertEquals(expected.toString(), actual.toString());
	}
}
