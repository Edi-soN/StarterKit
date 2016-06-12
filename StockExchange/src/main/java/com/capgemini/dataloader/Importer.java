package com.capgemini.dataloader;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Importer {
	private static final Logger logger = LogManager.getLogger(Importer.class);
	private static final String SCHEMA_NAME = "stockexchange";
	private static final String USER = "root";
	private static final String PASSWORD = "aramko123";

	public void importDataFromCsv() throws SQLException {
		Reader reader = new Reader();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		logger.debug("Loading data...");
		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + SCHEMA_NAME + "?user=" + USER
					+ "&password=" + PASSWORD + "&useSSL=true");
			preparedStatement = connection.prepareStatement("TRUNCATE TABLE stockshareentity");
			preparedStatement.executeUpdate();
			String baseQuery = "INSERT INTO stockshareentity (sharename, sharedate, shareprice) VALUES ";
			List<String> shareList = reader.getData();
			if (shareList != null) {
				for (String share : shareList) {
					String query = baseQuery + share + ";";
					preparedStatement = connection.prepareStatement(query);
					preparedStatement.executeUpdate();
				}
			}
		} catch (SQLException ex) {
			logger.debug("SQLException: " + ex.getMessage());
			logger.debug("SQLState: " + ex.getSQLState());
			logger.debug("VendorError: " + ex.getErrorCode());
		} finally {
			if (preparedStatement != null) {
				preparedStatement.close();
			}
			if (connection != null) {
				connection.close();
			}
		}
		logger.debug("Data loaded successfully");
	}
}
