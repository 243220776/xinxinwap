package com.app.test;

import java.util.Calendar;
import java.util.Date;

public class TestDate {
	public static void main(String[] args) {
		daysOfTwo();
	}
	private static Integer daysOfTwo() {
		Calendar aCalendar = Calendar.getInstance();
		
		aCalendar.setTime(new Date());

		int day1 = aCalendar.get(Calendar.DAY_OF_YEAR);
		System.err.println(day1);
		
		aCalendar.setTime(new Date());		
		int day2 = aCalendar.get(Calendar.DAY_OF_YEAR);
		System.out.println(day2);
		return day1 - day2;
	}

}
