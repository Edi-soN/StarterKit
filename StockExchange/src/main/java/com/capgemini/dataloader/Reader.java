package com.capgemini.dataloader;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Reader {
	private static final Logger logger = LogManager.getLogger(Reader.class);
	private static final String PATH = "src\\main\\resources\\dane.csv";

	public List<String> getData() {
		Parser parser = new Parser();
		List<String> shareList = new ArrayList<>();
		Scanner scanner = null;
		try {
			scanner = new Scanner(new File(PATH));
			while (scanner.hasNextLine()) {
				String share = parser.getSingleShare(scanner.nextLine());
				if (share != null) {
					shareList.add(share);
				}
			}
		} catch (FileNotFoundException e) {
			logger.debug("File not found");
			e.printStackTrace();
		} finally {
			if (scanner != null) {
				scanner.close();
			}
		}
		return shareList;
	}
}
