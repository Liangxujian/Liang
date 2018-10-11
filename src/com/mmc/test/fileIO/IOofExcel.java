package com.mmc.test.fileIO;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.filechooser.FileSystemView;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class IOofExcel {
	public static void main(String[] args) {
		IOofExcel test = new IOofExcel();
		FileSystemView fsv = FileSystemView.getFileSystemView();
		File com = fsv.getHomeDirectory(); // 这便是读取桌面路径的方法了
		String path = com.getAbsolutePath();
		String s = path + "\\item.xlsx";
		File mFile = new File(s);
		if(mFile.exists()){
			List<Map<String, String>> data = test.readExcelData(mFile, 0, 0);
			for(Map<String, String> map : data){
				System.out.println(map);
			}
		}
	}
	
	public List<Map<String, String>> readExcelData(File mFile, int sheetNum, int titleRow) {
		List<Map<String, String>> data = new ArrayList<Map<String, String>>();
		List<String> titles = null;
		Map<String, String> rowData = null;
		FileInputStream fis = null;
		Workbook wb = null;
		
		try {
			fis = new FileInputStream(mFile);
			// 此构造方式可以避免excel两个不同版本所导致的异常情况
			wb = WorkbookFactory.create(fis);
			// 得到要操作的表
			Sheet sheet = wb.getSheetAt(sheetNum);
			// 先遍历表头
			titles = new ArrayList<String>();
			Row row0 = sheet.getRow(titleRow);
			for (int i = 0; i < row0.getPhysicalNumberOfCells(); i++) {
				Cell cell = row0.getCell(i);
				if(cell != null && cell.getCellType() == Cell.CELL_TYPE_STRING){
					String str = cell.getStringCellValue();
					if(str != null){
						str = str.replace("\n", "");
					}
					titles.add(str);
				}
			}
			for (int x = titleRow + 1; x < sheet.getPhysicalNumberOfRows(); x++) {
				Row row = sheet.getRow(x);
				rowData = new HashMap<>();
				for (int y = 0; y < row.getPhysicalNumberOfCells(); y++) {
					Cell cell = row.getCell(y);
					String str = "";
					DecimalFormat df = new DecimalFormat("#");
					switch (cell.getCellType()) {  
			        case Cell.CELL_TYPE_STRING:  
			        	str = cell.getRichStringCellValue().getString().trim();  
			            break;  
			        case Cell.CELL_TYPE_NUMERIC:  
			        	str = df.format(cell.getNumericCellValue()).toString();  
			            break;  
			        case Cell.CELL_TYPE_BOOLEAN:  
			        	str = String.valueOf(cell.getBooleanCellValue()).trim();  
			            break;  
			        case Cell.CELL_TYPE_FORMULA:  
			        	str = cell.getCellFormula();  
			            break;  
			        default:  
			        	str = "";  
			        }
					str = str.replace("\n", "");
					rowData.put(titles.get(y), str);
				}
				data.add(rowData);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if(fis != null){
					fis.close();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return data;
	}
}
