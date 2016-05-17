package service;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DataLayoutFormatter {
	public List<String> ResultSet2String(ResultSet rs, String tableName) throws SQLException {
		StringBuilder sb = new StringBuilder();
		List<String> formattedData = new ArrayList<String>();
		ResultSetMetaData rsmd = rs.getMetaData();
		int columnsNumber = rsmd.getColumnCount();
		sb.append("| ");
		for (int i = 1; i < columnsNumber + 1; i++) {
			sb.append(rsmd.getColumnLabel(i)
					+ String.format("%" + (rsmd.getColumnDisplaySize(i) - rsmd.getColumnLabel(i).length()) + "s", "")
					+ "| ");
		}
		formattedData.add(sb.toString());
		while (rs.next()) {
			sb.setLength(0);
			sb.append("| ");
			for (int i = 1; i < columnsNumber + 1; i++) {
				sb.append(rs.getString(i)
						+ String.format("%" + (rsmd.getColumnDisplaySize(i) - rs.getString(i).length()) + "s", "")
						+ "| ");
			}
			formattedData.add(sb.toString());
		}
		String interline = String.format("%" + (formattedData.get(0).length() - 1) + "s", "").replace(" ", "-");
		formattedData.add(1, interline);
		formattedData.add(0, interline);
		formattedData.add(0, "Table: " + tableName);
		formattedData.add(0, interline);
		formattedData.add(interline);
		return formattedData;
	}
}
