package com.capgemini.main;

import java.time.LocalDate;

public class Calendar {

	private static LocalDate currentDate;

	public static LocalDate getCurrentDate() {
		return currentDate;
	}

	public static void setCurrentDate(LocalDate currentDate) {
		Calendar.currentDate = currentDate;
	}

}
