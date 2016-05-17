package service;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

public class Parser {
	@SuppressWarnings("resource")
	public List<List<String>> parseData(File file) throws IOException {
		CSVParser parser = new CSVParser(new FileReader(file), CSVFormat.DEFAULT.withHeader());
		List<List<String>> parsedData = new ArrayList<>();
		for (String header : parser.getHeaderMap().keySet()) {
			List<String> column = new ArrayList<>();
			column.add(header);
			parsedData.add(column);
		}
		for (CSVRecord record : parser) {
			for (int i = 0; i < parser.getHeaderMap().size(); i++) {
				parsedData.get(i).add(record.get(i));
			}
		}
		return parsedData;
	}
}
