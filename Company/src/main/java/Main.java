import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import service.InsertTableFromFile;
import service.TableService;

public class Main {
	public static void main(String[] args) throws SQLException, IOException {

		// Zadanie.1. Utwórz serwis i metodę w nim o sygnaturze, public
		// printTable(String tableName, int fromRowIdx, int toRowIdx); która
		// wyświetli na System.out dane z zadanej parametrem tabeli.

		new TableService().printTable("answers", 1, -1);

		// ---------------------------------------------------------------------

		// Zadanie.2. Napisz funkcję która odczyta dane z pliku tekstowego CSV i
		// wstawi je do tabeli. public void storeDate(File file) Plik zawiera w
		// nagłówku nazwy kolumn. Nazwa pliku ma format instert-NAZWATABELI.csv

		//new InsertTableFromFile().storeDate(
			//	new File("C:\\Users\\ADDZIEDZ\\workspace\\Company\\src\\main\\resources\\insert-answers.csv"));
	}
}
