package pages;

import org.apache.poi.ss.usermodel.*;
import java.io.FileInputStream;

public class ExcelUtils 
{
    public static Object[][] getTableArray(String filePath, String sheetName) throws Exception 
    
    {
        FileInputStream excelFile = new FileInputStream(filePath);
        Workbook workbook = WorkbookFactory.create(excelFile);
        Sheet sheet = workbook.getSheet(sheetName);
        
        int rowCount = sheet.getLastRowNum();
        int colCount = sheet.getRow(0).getLastCellNum();
       
        Object[][] data = new Object[rowCount][colCount];

        for (int i = 1; i <= rowCount; i++) 
        { 
            Row row = sheet.getRow(i);
            for (int j = 0; j < colCount; j++) {
                data[i-1][j] = row.getCell(j).toString();
            }
        }
        return data;
    }
}
