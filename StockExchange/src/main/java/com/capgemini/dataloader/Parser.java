package com.capgemini.dataloader;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Parser {

	private static final Logger logger = LogManager.getLogger(Parser.class);

	public String getSingleShare(String nextLine) {

		List<String> columnValue = Arrays.asList(nextLine.split("\\s*,\\s*"));
		String share = null;

		try {
			Date date = new SimpleDateFormat("yyyyMMdd").parse((columnValue.get(1)));
			String formattedDate = new SimpleDateFormat("yyyy-MM-dd").format(date);
			share = String.format(Locale.ENGLISH, "('%s', date('%s'), %.2f)", columnValue.get(0), formattedDate,
					Float.parseFloat(columnValue.get(2)));
		} catch (NumberFormatException | ParseException ex) {
			logger.debug("Cannot convert entry: " + columnValue.get(0) + " " + columnValue.get(1) + " "
					+ columnValue.get(2));
			return null;
		}

		return share;
	}

}
