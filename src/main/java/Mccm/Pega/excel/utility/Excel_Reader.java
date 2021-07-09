package Mccm.Pega.excel.utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import Mccm.Pega.Outbound.PegaTestBase.TestBase;

public class Excel_Reader extends TestBase {
	private File file;
	private FileInputStream fis;
	private FileOutputStream fos;
	private XSSFWorkbook wb;
	private XSSFSheet sheet;
	private XSSFCell cell;
	private XSSFRow row;

	public Excel_Reader(String FilePath) {
		file = new File(FilePath);
		try {
			fis = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			wb = new XSSFWorkbook(fis);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			fis.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String findFirstSheetName() {
		String sheetName = wb.getSheetName(0);
		return sheetName;

	}

	public void changeSheetName(String newSheetName) {
		wb.setSheetName(0, newSheetName);
		try {
			// Save the sheet after changing the file name
			write();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public int getRowCount(String sheetName) {
		XSSFSheet sheet = wb.getSheet(sheetName);
		if (sheet == null) {
			return 0;
		}
		return sheet.getLastRowNum();

	}

	public int getrowsnum(String sheetName) {
		XSSFSheet sheet = wb.getSheet(sheetName);
		return sheet.getPhysicalNumberOfRows();
	}

	public XSSFRow getfirstrow(String sheetName) {
		XSSFSheet sheet = wb.getSheet(sheetName);
		return sheet.getRow(0);
	}

	public XSSFRow getfirstrow1(String sheetName, int i) {
		XSSFSheet sheet = wb.getSheet(sheetName);
		return sheet.getRow(i + 1);
	}

	public XSSFCell getcell1(String sheetName, int j) {
		int i = 0;
		XSSFSheet sheet = wb.getSheet(sheetName);
		row = sheet.getRow(i + 1);
		cell = row.getCell(j);
		return cell;
	}

	public int getlastcellnum(String sheetName) {
		XSSFSheet sheet = wb.getSheet(sheetName);
		XSSFRow row = sheet.getRow(0);

		return row.getLastCellNum();

	}

	public int getCellCount(String sheetName, int rowIndex) {
		XSSFSheet sheet = wb.getSheet(sheetName);
		if (sheet == null) {
			return 0;
		}
		XSSFRow row = sheet.getRow(rowIndex);
		if (row == null) {
			return 0;
		}
		return row.getLastCellNum();

	}

	public String getCellValue(String sheetName, int row1, int cellIndex) {
		XSSFSheet sheet = wb.getSheet(sheetName);
		if (sheet == null) {
			return "sheet doesn't exist for sheet name : " + sheetName;
		}
		XSSFRow row = sheet.getRow(row1);
		if (row == null) {
			return "row doesn't exist for index : " + row1;
		}
		XSSFCell cell = row.getCell(cellIndex);
		if (cell == null) {
			return "";
		}
		return cell.toString().trim();
	}

	public void writeNumericCellValue(String sheetName, int rowIndex, int cellIndex, double value) throws IOException {
		XSSFCell cell = getCell(sheetName, rowIndex, cellIndex);
		cell.setCellValue(value);
		write();
	}

	public void writeStringCellValue(String sheetName, int rowIndex, int cellIndex, String value) throws IOException {
		XSSFCell cell = getCell(sheetName, rowIndex, cellIndex);
		cell.setCellValue(value);
		write();
	}

	public void writeBooleanCellValue(String sheetName, int rowIndex, int cellIndex, Boolean value) throws IOException {
		XSSFCell cell = getCell(sheetName, rowIndex, cellIndex);
		cell.setCellValue(value);
		write();
	}

	public void writeDateCellValue(String sheetName, int rowIndex, int cellIndex, Date value) throws IOException {
		XSSFCell cell = getCell(sheetName, rowIndex, cellIndex);
		cell.setCellValue(value);
		write();
	}

	private void write() throws FileNotFoundException, IOException {
		fos = new FileOutputStream(file);
		wb.write(fos);
		fos.close();
	}

	private XSSFCell getCell(String sheetName, int rowIndex, int cellIndex) {
		XSSFSheet sheet = wb.getSheet(sheetName);
		if (sheet == null) {
			sheet = wb.createSheet(sheetName);
		}
		XSSFRow row = sheet.getRow(rowIndex);
		if (row == null) {
			row = sheet.createRow(rowIndex);
		}
		XSSFCell cell = row.getCell(cellIndex);
		if (cell == null) {
			cell = row.createCell(cellIndex);
		}
		return cell;
	}

	public XSSFSheet getSheet(String string) {
		// TODO Auto-generated method stub
		return null;
	}
}
