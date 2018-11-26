package com.slokam.automation.commons.utils.data;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelHandler {

	public void test1() {

		File file = new File("D:\\Data.xls");
		try {
			FileInputStream fis = new FileInputStream(file);

			// Getting workbook

			HSSFWorkbook workbook = new HSSFWorkbook(fis);
			HSSFSheet sheet = workbook.getSheet("Sheet1");

			HSSFRow row = sheet.getRow(2);
			HSSFCell cell = row.getCell(1);
			System.out.println(getValue(cell));
			cell = row.getCell(0);
			System.out.println(getValue(cell));

			cell = row.getCell(2);
			System.out.println(getValue(cell));

			row = sheet.getRow(3);

			cell = row.getCell(1);
			System.out.println(getValue(cell));

			cell = row.getCell(2);
			System.out.println(getValue(cell));

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public String getValue(Cell cell) {
		String value = "";
		if(cell!=null) {
			
		
		switch (cell.getCellTypeEnum()) {
		case BLANK:
			value = "";
			break;
		case BOOLEAN:
			boolean b = cell.getBooleanCellValue();
			value = String.valueOf(b);
			break;
		case ERROR:

			break;
		case FORMULA:
			value = cell.getCellFormula();
			break;
		case NUMERIC:
			int d = (int) cell.getNumericCellValue();
			value = String.valueOf(d);
			break;
		case STRING:
			value = cell.getStringCellValue();
			break;
		default:
			value = "";
			break;
		}
		}
		return value;
	}

	public static void main(String[] args) {
		new ExcelHandler().writeToExcelxlsx();
	}

	public void writeToExcel() {

		HSSFWorkbook book = new HSSFWorkbook();
		HSSFSheet sheet = book.createSheet("TestSheet");

		HSSFRow row = sheet.createRow(1);
		HSSFCell cell = row.createCell(1, CellType.STRING);
		cell.setCellValue("Username");
		cell = row.createCell(2, CellType.STRING);
		cell.setCellValue("Password");

		// Row 2
		row = sheet.createRow(2);
		cell = row.createCell(1, CellType.STRING);
		cell.setCellValue("Bharath");
		cell = row.createCell(2, CellType.STRING);
		cell.setCellValue("Bharath123");

		// Row 3
		row = sheet.createRow(3);
		cell = row.createCell(1, CellType.STRING);
		cell.setCellValue("Kumar");
		cell = row.createCell(2, CellType.STRING);
		cell.setCellValue("Kumar123");

		File file = new File("D:\\sample_write.xls");
		FileOutputStream fos = null;
		try {
			fos =  new FileOutputStream(file);
			book.write(fos);
			book.close();
			fos.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public void readXlsX() {

		File file = new File("D:\\Studets_TestCases.xlsx");
		try {
			FileInputStream fis = new FileInputStream(file);

			// Getting workbook

			XSSFWorkbook workbook = new XSSFWorkbook(fis);
			XSSFSheet sheet = workbook.getSheet("Sheet1");

			XSSFRow row = sheet.getRow(2);
			XSSFCell cell = row.getCell(1);
			System.out.println(getValue(cell));
			cell = row.getCell(0);
			System.out.println(getValue(cell));

			cell = row.getCell(2);
			System.out.println(getValue(cell));

			row = sheet.getRow(3);

			cell = row.getCell(1);
			System.out.println(getValue(cell));

			cell = row.getCell(2);
			System.out.println(getValue(cell));
			workbook.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void writeToExcelxlsx() {

		XSSFWorkbook book = new XSSFWorkbook();
		XSSFSheet sheet = book.createSheet("TestSheet");

		XSSFRow row = sheet.createRow(1);
		XSSFCell cell = row.createCell(1, CellType.STRING);
		cell.setCellValue("CREATED");
		cell = row.createCell(2, CellType.STRING);
		cell.setCellValue("SHEET");

		// Row 2
		row = sheet.createRow(2);
		cell = row.createCell(1, CellType.STRING);
		cell.setCellValue("SUDARSHAN");
		cell = row.createCell(2, CellType.STRING);
		cell.setCellValue("SUDARSHAN434");

		// Row 3
		row = sheet.createRow(3);
		cell = row.createCell(1, CellType.STRING);
		cell.setCellValue("SELENIUM");
		cell = row.createCell(2, CellType.STRING);
		cell.setCellValue("SELENIUM123");

		File file = new File("D:\\sample_write.xlsx");
		FileOutputStream fos = null;
		try {
			fos =  new FileOutputStream(file);
			book.write(fos);
			book.close();
			fos.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
