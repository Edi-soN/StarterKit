package com.capgemini.main;

import java.util.Date;

public class CustomCalendar {

	private static Date currentDate;

	public static Date getCurrentDate() {
		return currentDate;
	}

	public static void setCurrentDate(Date currentDate) {
		CustomCalendar.currentDate = currentDate;
	}

}
