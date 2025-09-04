package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class MSAutomationExcel {
	static File file = new File("..\\CapstoneProject\\TestData\\SeleniumTestData.xlsx");

	
	// Retrieving the browser data from excel sheet, Data Driven testing using
		// apache poi
		public static String getExcelData(String sheetName, int rowNum, int cellNum) throws Exception {

			//Create Fileinputstream
			FileInputStream fis = new FileInputStream(file);
			
			//create Workbook
			XSSFWorkbook workbook = new XSSFWorkbook(fis);
			
			//create sheet
			XSSFSheet sheet = workbook.getSheet(sheetName);
			
			//create row 
			XSSFRow row =sheet.getRow(rowNum);
			
			//cell
			String data = row.getCell(cellNum).getStringCellValue();
			
			String s=data.toString();
			
			workbook.close();
			
			return data;
			
			

		}
		
		public static void setExcelData(String sheetName, int rowNum, int cellNum, String data) throws Exception {

			FileInputStream fis = new FileInputStream(file);

			// Open the workbook
			XSSFWorkbook workbook = new XSSFWorkbook(fis);

			// Get the sheet
			XSSFSheet sheet = workbook.getSheet(sheetName);

			// Get the control of the Row
			XSSFRow row = sheet.getRow(rowNum);

			XSSFCell cell = row.createCell(cellNum);

			FileOutputStream fos = new FileOutputStream(file);

			cell.setCellValue(data);

			workbook.write(fos);

			workbook.close();

		}

}
