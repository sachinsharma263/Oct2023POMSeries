package com.qa.trcrm.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelUtil {

	static Sheet sheet = null;

	public ExcelUtil() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		Object data[][] = getTestData("contacts");

		for (int i = 0; i < data.length; i++) {
			for (int j = 0; j < data[i].length; j++) {
				System.out.print(data[i][j]+"\t");
			}
			System.out.println();
		}

	}

	public static Object[][] getTestData(String sheetName) {
		String path = AppConstants.TEST_SHEET_DATA;
		
		if (sheetName==null) {
			sheetName="contacts";
		}
		try {
			FileInputStream fis = new FileInputStream(path);
			Workbook book = WorkbookFactory.create(fis);
			sheet = book.getSheet(sheetName);
		} catch (IOException | InvalidFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Object data[][] = new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];

		for (int i = 0; i < data.length; i++) {
			for (int j = 0; j < data[i].length; j++) {
				data[i][j] = sheet.getRow(i + 1).getCell(j).toString();
				
			}
		}
		return data;

	}

}
