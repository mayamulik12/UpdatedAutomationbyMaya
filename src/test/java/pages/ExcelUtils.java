package pages;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelUtils {
	public static Object[][] getTestData(String filePath, String sheetName) throws IOException {

		// This dynamically finds your project folder
		String excelPath = System.getProperty("user.dir") + "/src/test/resources/TestData.xlsx";

		FileInputStream fis = new FileInputStream(filePath);

		Workbook workbook = WorkbookFactory.create(fis);
		Sheet sheet = workbook.getSheet(sheetName);

		int rowCount = sheet.getLastRowNum();
		int colCount = sheet.getRow(0).getLastCellNum();

		Object[][] data = new Object[rowCount][colCount];
		DataFormatter formatter = new DataFormatter(); // Correctly formats all cell types to String

		for (int i = 1; i <= rowCount; i++) { // Skip header row
			Row row = sheet.getRow(i);
			for (int j = 0; j < colCount; j++) {
				data[i - 1][j] = formatter.formatCellValue(row.getCell(j));
			}
		}
		workbook.close();
		return data;
	}
}
