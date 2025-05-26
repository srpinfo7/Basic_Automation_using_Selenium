package Utils;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.InputStream;

public class ExcelUtils {

    public static Object[][] getTestData(String fileName, String sheetName) {
        Object[][] data = null;
        try {
            InputStream is = ExcelUtils.class.getClassLoader().getResourceAsStream(fileName);
            if (is == null) {
                throw new RuntimeException("❌ Excel file not found: " + fileName);
            }
            Workbook workbook = new XSSFWorkbook(is);
            Sheet sheet = workbook.getSheet(sheetName);

            int rows = sheet.getPhysicalNumberOfRows();
            int cols = sheet.getRow(0).getPhysicalNumberOfCells();
            data = new Object[rows - 1][cols];

            for (int i = 1; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    data[i - 1][j] = sheet.getRow(i).getCell(j).toString();
                }
            }

            workbook.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }
}
