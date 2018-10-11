package com.mmc.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class TitleCompareTest {
	public Map<Integer, Integer> compareTitle(List<String> listTitle, File file, String sheetName, int titleRowNum) {
		Map<Integer, Integer> resultMap = null;

		if(file == null || listTitle == null || listTitle.size() == 0){
			return resultMap;
		}
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(file);
			XSSFWorkbook wb = new XSSFWorkbook(fis);
			XSSFSheet sheet = wb.getSheet(sheetName);
			XSSFRow row = sheet.getRow(titleRowNum);
			
			XSSFCell cell = null;
			for(int x = 0;x<row.getPhysicalNumberOfCells();x++){
				cell = row.getCell(x);
				if(cell.getCellType()==Cell.CELL_TYPE_STRING){
					String cellTitle = cell.getStringCellValue();
					for(int y = 0;y< listTitle.size();y++){
						if(cellTitle.equals(listTitle.get(y))){
							resultMap.put(x, y);
						}
					}
				}
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resultMap;
	}
}
