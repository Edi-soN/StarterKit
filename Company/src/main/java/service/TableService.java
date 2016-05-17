package service;

import java.sql.SQLException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import repository.TableRepository;

public class TableService {

	private static final Logger logger = LogManager.getLogger(TableService.class);

	public void printTable(String tableName, int fromRowIdx, int toRowIdx) throws SQLException {
		if (fromRowIdx < 1) {
			fromRowIdx = 1;
		}
		if (toRowIdx < fromRowIdx) {
			toRowIdx = -1;
		}
		new TableRepository().queryDB(tableName, fromRowIdx - 1, toRowIdx).forEach(line -> logger.debug(line));
	}
}
