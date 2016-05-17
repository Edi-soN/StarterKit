package repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import service.DataLayoutFormatter;
import service.TableService;

public class TableRepository {

	private static final Logger logger = LogManager.getLogger(TableService.class);

	private static final String SCHEMA_NAME = "forum";
	private static final String USER = "user";
	private static final String PASSWORD = "user";

	@SuppressWarnings("resource")
	public List<String> queryDB(String tableName, int fromRowIdx, int toRowIdx) throws SQLException {
		if (tableName.matches("\\W")) {
			return null;
		}
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		List<String> formattedResultSet = new ArrayList<String>();
		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + SCHEMA_NAME + "?user=" + USER
					+ "&password=" + PASSWORD + "&useSSL=true");
			String query = String.format("SELECT COUNT(*) FROM %s;", tableName);
			// PreparedStatement can be used only for column values
			preparedStatement = connection.prepareStatement(query);
			resultSet = preparedStatement.executeQuery();
			resultSet.next();
			int offset = resultSet.getInt(1);
			if (toRowIdx != -1) {
				offset = toRowIdx;
			}
			query = String.format("SELECT * FROM %s LIMIT ?,?", tableName);
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, fromRowIdx);
			preparedStatement.setInt(2, offset);
			resultSet = preparedStatement.executeQuery();
			formattedResultSet = new DataLayoutFormatter().ResultSet2String(resultSet, tableName);
		} catch (SQLException ex) {
			logger.debug("SQLException: " + ex.getMessage());
			logger.debug("SQLState: " + ex.getSQLState());
			logger.debug("VendorError: " + ex.getErrorCode());
		} finally {
			if (resultSet != null) {
				resultSet.close();
			}
			if (preparedStatement != null) {
				preparedStatement.close();
			}
			if (connection != null) {
				connection.close();
			}
		}
		return formattedResultSet;
	}

	public void insertIntoDataBaseTable(String tableName, List<List<String>> dataToInsert) throws SQLException {
		if (tableName.matches("\\W")) {
			throw new SQLException("Incorrect table name");
		}
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + SCHEMA_NAME + "?user=" + USER
					+ "&password=" + PASSWORD + "&useSSL=true");
			String query = String.format(
					"SELECT COLUMN_NAME, DATA_TYPE, IS_NULLABLE, COLUMN_DEFAULT FROM INFORMATION_SCHEMA.COLUMNS WHERE table_name = '%s' AND table_schema = 'test';",
					tableName);
			preparedStatement = connection.prepareStatement(query);
			StringBuilder values = new StringBuilder();
			for (int j = 1; j < dataToInsert.get(0).size(); j++) {
				resultSet = preparedStatement.executeQuery();
				resultSet.next();
				values.append("(");
				for (int i = 0; i < dataToInsert.size(); i++) {
					if ("varchar".equals(resultSet.getString(2))) {
						values.append("'" + dataToInsert.get(i).get(j) + "', ");
					} else {
						values.append(dataToInsert.get(i).get(j) + ", ");
					}
					resultSet.next();
				}
				values.delete(values.length() - 2, values.length());
				values.append("), ");
			}
			values.delete(values.length() - 2, values.length() - 1);
			StringBuilder columns = new StringBuilder();
			columns.append("(");
			for (List<String> header : dataToInsert) {
				columns.append(header.get(0) + ", ");
			}
			columns.delete(columns.length() - 2, columns.length());
			columns.append(")");
			query = String.format("INSERT INTO %s " + columns.toString() + " VALUES " + values.toString() + ";",
					tableName);
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.executeUpdate();
			logger.debug("Table name: " + tableName);
			logger.debug("Table columns: " + columns.toString());
			logger.debug("Values to insert: " + values.toString());
			logger.debug("Query to execute: " + query);
		} catch (SQLException ex) {
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
		} finally {
			if (resultSet != null) {
				resultSet.close();
			}
			if (preparedStatement != null) {
				preparedStatement.close();
			}
			if (connection != null) {
				connection.close();
			}
		}
		logger.debug("Done");
	}
}
