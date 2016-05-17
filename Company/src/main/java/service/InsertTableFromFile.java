package service;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

import repository.TableRepository;

public class InsertTableFromFile {
	public void storeDate(File file) throws IOException, SQLException {
		new TableRepository().insertIntoDataBaseTable(file.getName().replace("insert-", "").replace(".csv", ""),
				new Parser().parseData(file));
	}
}
