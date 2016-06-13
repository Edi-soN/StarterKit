package com.capgemini.main;

import java.sql.SQLException;
import java.util.Calendar;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.capgemini.simulation.Simulation;
import com.capgemini.simulation.impl.SimulationImpl;

public class Main {
	
	private static final int START_DAY = 2;
	private static final int START_MONTH = 0;	// 0-based
	private static final int START_YEAR = 2013;
	private static final int END_DAY = 31;
	private static final int END_MONTH = 0;
	private static final int END_YEAR = 2013;
	
	
	@SuppressWarnings("resource")
	public static void main(String[] args) throws SQLException{
		
		ApplicationContext context = new ClassPathXmlApplicationContext("spring-context.xml");
		
		Simulation simulation = context.getBean(SimulationImpl.class);
		
		Calendar startDate = Calendar.getInstance();
		startDate.set(START_YEAR, START_MONTH, START_DAY);
		
		Calendar endDate = Calendar.getInstance();
		endDate.set(END_YEAR, END_MONTH, END_DAY);
		
		simulation.run(startDate.getTime(), endDate.getTime());
		
	}

}
