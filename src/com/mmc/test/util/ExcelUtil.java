package com.mmc.test.util;

import java.awt.Color;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellValue;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Name;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.ss.util.AreaReference;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xssf.usermodel.extensions.XSSFCellBorder.BorderSide;


public class ExcelUtil {
	private static class Temp {
		static ExcelUtil instance = new ExcelUtil();
	}

	public static ExcelUtil getInstance() {
		return Temp.instance;
	}
	
	private ExcelUtil(){}

	/**
	 * 从传入的EXCEL对象中读取内容，并返回集合
	 * @param file  传入EXCEL文档对象
	 * @param sheetNum  读取第几个数据表(0开始)
	 * @param beginRow  从数据表的第几行开始读取数据(有些标题占一行，表头占一行)
	 * @return  返回EXCEL对象内容集合(key-表头；value-单元格值)
	 */
	public List<Map<String, String>> getDataFromTemplate(File file, int sheetNum, int beginRow) {
		List<Map<String, String>> data = new ArrayList<Map<String, String>>();
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(file);
			Workbook workbook = WorkbookFactory.create(fis);
			Sheet sheet = workbook.getSheetAt(sheetNum);
			Row titleRow = sheet.getRow(beginRow - 1);
			for (int r = beginRow; r < sheet.getPhysicalNumberOfRows(); r++) {
				Row row = sheet.getRow(r);
				Map<String, String> map = new LinkedHashMap<String, String>();
				for (int c = 0; c < row.getLastCellNum(); c++) {
					String title = getCalculateCellValue(titleRow.getCell(c));
					String content = getCalculateCellValue(row.getCell(c));
					map.put(title, content);
				}
				boolean blankRow = true;
				for (Map.Entry<String, String> entry : map.entrySet()) {
					if (StringUtils.isNotBlank(entry.getValue())) {
						blankRow = false;
					}
				}
				if (!blankRow) {
					data.add(map);
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (InvalidFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (fis != null) {
					fis.close();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return data;
	}

	public List<Map<String, String>> getDataFromProblemGeneralTemplate(File file) {
		List<Map<String, String>> data = new ArrayList<Map<String, String>>();
		try {
			FileInputStream fis = new FileInputStream(file);
			Workbook workbook = WorkbookFactory.create(fis);
			fis.close();
			Sheet sheet = workbook.getSheetAt(0);
			Row titleRow = sheet.getRow(1);
			for (int r = 2; r < sheet.getPhysicalNumberOfRows(); r++) {
				Row row = sheet.getRow(r);
				Map<String, String> map = new LinkedHashMap<String, String>();
				for (int c = 0; c < row.getLastCellNum(); c++) {
					String title = getCalculateCellValue(titleRow.getCell(c));
					String content = getCalculateCellValue(row.getCell(c));
					map.put(title, content);
				}
				boolean blankRow = true;
				for (Map.Entry<String, String> entry : map.entrySet()) {
					if (StringUtils.isNotBlank(entry.getValue())) {
						blankRow = false;
					}
				}
				if (!blankRow) {
					data.add(map);
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (InvalidFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return data;
	}

	/**
	 * 从传入的EXCEL对象中读取内容，并返回集合 
	 * @param file  传入EXCEL文档对象
	 * @param sheetNum  读取第几个数据表(0开始)
	 * @param beginRow  从数据表的第几行开始读取数据(有些标题占一行，表头占一行)
	 * @return  返回EXCEL对象内容集合(key-表头下标；value-单元格值)
	 */
	public List<Map<Integer, String>> getImportDataFromTemplate(File file, int sheetNum, int beginRow) {
		List<Map<Integer, String>> data = new ArrayList<Map<Integer, String>>();
		try {
			FileInputStream fis = new FileInputStream(file);
			Workbook workbook = WorkbookFactory.create(fis);
			fis.close();
			Sheet sheet = workbook.getSheetAt(sheetNum);
			Row titleRow = sheet.getRow(beginRow - 1);
			for (int r = beginRow; r < sheet.getPhysicalNumberOfRows(); r++) {
				Row row = sheet.getRow(r);
				Map<Integer, String> map = new LinkedHashMap<Integer, String>();
				for (int c = 0; c < row.getLastCellNum(); c++) {
					String title = getCalculateCellValue(titleRow.getCell(c));
					String content = getCalculateCellValue(row.getCell(c));
					map.put(c, content);
				}
				boolean blankRow = false;
				for (Map.Entry<Integer, String> entry : map.entrySet()) {
					if (StringUtils.isNotBlank(entry.getValue())) {
						blankRow = true;
					}
				}
				if (!blankRow) {
					data.add(map);
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (InvalidFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return data;
	}

	/**
	 * 获取表头集合
	 * @param file
	 * @return
	 */
	public List<String> getTitleFromTemplate(File file) {
		List<String> data = new ArrayList<String>();
		try {
			FileInputStream fis = new FileInputStream(file);
			Workbook workbook = WorkbookFactory.create(fis);
			fis.close();
			Sheet sheet = workbook.getSheetAt(0);
			Row titleRow = sheet.getRow(0);
			for (int c = 0; c < titleRow.getLastCellNum(); c++) {
				String title = getCalculateCellValue(titleRow.getCell(c));
				data.add(title);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (InvalidFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return data;
	}

	/**
	 * 返回单元格真实类型的数值
	 * @param cell
	 * @return
	 */
	public String getCellValue(Cell cell) {
		String value = null;
		if (cell != null) {
			switch (cell.getCellType()) {
			case Cell.CELL_TYPE_FORMULA:// 废弃
				System.out.print(cell.getCellFormula() + " ");
				try {
					value = String.valueOf(cell.getNumericCellValue());
					if (DateUtil.isCellDateFormatted(cell)) {
						SimpleDateFormat sdf = new SimpleDateFormat("yyyy-M-dd");
						value = sdf.format(DateUtil.getJavaDate(cell.getNumericCellValue()));
					}
				} catch (IllegalStateException e) {
					value = String.valueOf(cell.getRichStringCellValue());
				}
				break;
			case Cell.CELL_TYPE_NUMERIC:
				if (DateUtil.isCellDateFormatted(cell)) {
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-M-dd");
					value = sdf.format(DateUtil.getJavaDate(cell.getNumericCellValue()));
				} else {
					value = NumberToTextConverter.toText(cell.getNumericCellValue());
					String[] split = value.split("\\.");
					if (split.length == 2 && (split[1].equals("0") || split[1].equals("00") || split[1].equals("000"))) {
						value = split[0];
					}
				}
				break;
			case Cell.CELL_TYPE_STRING:
				value = cell.getStringCellValue();
				break;
			case Cell.CELL_TYPE_BOOLEAN:
				value = String.valueOf(cell.getBooleanCellValue());
				break;
			case Cell.CELL_TYPE_BLANK:
				value = "";
				break;
			default:
				value = "unknown";
				break;
			}
		}
		String[] split = value.split("\\.");
		if (split.length == 2
				&& (split[1].equals("0") || split[1].equals("00") || split[1].equals("000"))) {
			value = split[0];
		}
		return value;
	}

	public String getCellValue(Cell cell, int floatLength) {
		String value = null;
		if (cell != null) {
			switch (cell.getCellType()) {
			case Cell.CELL_TYPE_FORMULA:// 废弃
				System.out.print(cell.getCellFormula() + " ");
				try {
					value = String.valueOf(cell.getNumericCellValue());
				} catch (IllegalStateException e) {
					value = String.valueOf(cell.getRichStringCellValue());
				}
				break;
			case Cell.CELL_TYPE_NUMERIC:
				if (DateUtil.isCellDateFormatted(cell)) {
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-M-dd HH:mm");
					value = sdf.format(DateUtil.getJavaDate(cell.getNumericCellValue()));
				} else {
					value = String.format("%." + floatLength + "f", cell.getNumericCellValue());
				}
				break;
			case Cell.CELL_TYPE_STRING:
				value = cell.getStringCellValue();
				break;
			case Cell.CELL_TYPE_BOOLEAN:
				value = String.valueOf(cell.getBooleanCellValue());
				break;
			case Cell.CELL_TYPE_BLANK:
				value = "";
				break;
			default:
				value = "unknown";
				break;
			}
		}
		return value;
	}

	public Row createRow(Sheet sheet, Integer rowIndex) {
		Row row = null;
		if (sheet.getRow(rowIndex) != null) {
			int lastRowNo = sheet.getLastRowNum();
			sheet.shiftRows(rowIndex, lastRowNo, 1);
		}
		row = sheet.createRow(rowIndex);
		return row;
	}

	public Map<String, DataModel> getResultMapFromPreferenceRuleExcel(File file) {
		try {
			FileInputStream fis = new FileInputStream(file);
			Workbook workbook = WorkbookFactory.create(fis);
			fis.close();
			Sheet sheetAt = workbook.getSheetAt(0);
			Row titleRow = sheetAt.getRow(2);
			Row valueRow = sheetAt.getRow(3);
			Row errorRow = sheetAt.getRow(4);
			Map<String, Cell[]> cellMap = new LinkedHashMap<String, Cell[]>();
			for (int i = 2; i < titleRow.getPhysicalNumberOfCells(); i++) {
				Cell cell = titleRow.getCell(i);
				if (cell != null) {
					Cell cell2 = valueRow.getCell(i);
					cell2 = (cell2 == null ? valueRow.createCell(i) : cell2);
					Cell cell3 = errorRow.getCell(i);
					cell3 = (cell3 == null ? valueRow.createCell(i) : cell3);
					cellMap.put(cell.getStringCellValue(), new Cell[] { cell2, cell3 });
				}
			}
			// cellMap.get("M流程数").setCellValue(9);
			System.out.println("map:" + cellMap);
			Map<String, DataModel> dataMap = new LinkedHashMap<String, DataModel>();
			for (Map.Entry<String, Cell[]> entry : cellMap.entrySet()) {
				String key = entry.getKey();
				Cell[] value = entry.getValue();
				String resultCellValue = getCalculateCellValue(value[0]);
				String errorCellvalue = getCalculateCellValue(value[1]);
				/*
				 * if(value[0].getCellType() == Cell.CELL_TYPE_FORMULA) {
				 * resultCellValue = getFormulaCellValue(workbook, value[0]); }
				 * else { resultCellValue = getCellValue(value[0]); }
				 * if(value[1].getCellType() == Cell.CELL_TYPE_FORMULA) {
				 * errorCellvalue = getFormulaCellValue(workbook, value[1]); }
				 * else { errorCellvalue = getCellValue(value[1]); }
				 */
				dataMap.put(key, new DataModel(resultCellValue, errorCellvalue));
			}
			System.out.println("dataMap:" + dataMap);
			return dataMap;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (InvalidFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public Map<String, DataModel> getCalculateMapFromMap(Map<String, Cell[]> cellMap) {
		// System.out.println("map:" + cellMap);
		Map<String, DataModel> dataMap = new LinkedHashMap<String, DataModel>();
		for (Map.Entry<String, Cell[]> entry : cellMap.entrySet()) {
			String key = entry.getKey();
			Cell[] value = entry.getValue();
			String resultCellValue;
			String errorCellvalue;
			resultCellValue = getCalculateCellValue(value[0]);
			errorCellvalue = getCalculateCellValue(value[1]);
			dataMap.put(key, new DataModel(resultCellValue, errorCellvalue));
		}
		System.out.println("dataMap:" + dataMap);
		return dataMap;
	}

	public Map<String, Cell[]> getMapFromPreferenceRuleExcel(Workbook workbook) {
		// FileInputStream fis = new FileInputStream(file);
		// Workbook workbook = WorkbookFactory.create(fis);
		// fis.close();
		Sheet sheetAt = workbook.getSheetAt(0);
		Row titleRow = sheetAt.getRow(2);
		Row valueRow = sheetAt.getRow(3);
		Row errorRow = sheetAt.getRow(4);
		Map<String, Cell[]> cellMap = new LinkedHashMap<String, Cell[]>();
		for (int i = 2; i < titleRow.getLastCellNum() + 1; i++) {
			Cell cell = titleRow.getCell(i);
			if (cell != null) {
				Cell cell2 = valueRow.getCell(i);
				cell2 = (cell2 == null ? valueRow.createCell(i) : cell2);
				Cell cell3 = errorRow.getCell(i);
				cell3 = (cell3 == null ? errorRow.createCell(i) : cell3);
				cellMap.put(cell.getStringCellValue(), new Cell[] { cell2, cell3 });
			}
		}
		return cellMap;
	}

	public String getCalculateCellValue(Cell cell) {
		String value = null;
		if (cell != null) {
			switch (cell.getCellType()) {
			case Cell.CELL_TYPE_FORMULA:
				FormulaEvaluator evaluator = cell.getSheet().getWorkbook().getCreationHelper().createFormulaEvaluator();
				CellValue evaluate = evaluator.evaluate(cell);
				switch (evaluate.getCellType()) {
				case Cell.CELL_TYPE_STRING:
					return evaluate.getStringValue();
				case Cell.CELL_TYPE_NUMERIC:
					String dataFormat = cell.getCellStyle().getDataFormatString();
					if (DateUtil.isCellDateFormatted(cell)) {
						SimpleDateFormat sdf = new SimpleDateFormat("yyyy-M-dd");
						value = sdf.format(cell.getDateCellValue());
					} else if ("@".equals(dataFormat)) {
						DecimalFormat df = new DecimalFormat("#");
						value = df.format(evaluate.getNumberValue());
					} else {
						value = NumberToTextConverter.toText(evaluate.getNumberValue());
					}
					return value;
				case Cell.CELL_TYPE_BOOLEAN:
					return evaluate.getBooleanValue() + "";
				case Cell.CELL_TYPE_BLANK:
					return "";
				default:
					return "";
				}
			case Cell.CELL_TYPE_NUMERIC:
				if (DateUtil.isCellDateFormatted(cell)) {
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-M-dd HH:mm");
					value = sdf.format(DateUtil.getJavaDate(cell.getNumericCellValue()));
				} else {
					value = NumberToTextConverter.toText(cell.getNumericCellValue());
				}
				break;
			case Cell.CELL_TYPE_STRING:
				value = cell.getStringCellValue().trim();
				break;
			case Cell.CELL_TYPE_BOOLEAN:
				value = String.valueOf(cell.getBooleanCellValue());
				break;
			case Cell.CELL_TYPE_BLANK:
				value = "";
				break;
			default:
				value = "unknown";
				break;
			}
		}
		return value;
	}

	public List<Map<String, String>> getExcelLOVData(File file) {
		List<Map<String, String>> data = new ArrayList<Map<String, String>>();
		try {
			FileInputStream fis = new FileInputStream(file);
			Workbook workbook = WorkbookFactory.create(fis);
			fis.close();
			List<String> title = new ArrayList<String>();
			Sheet sheetAt = workbook.getSheetAt(0);
			Row titleRow = sheetAt.getRow(1);
			for (int c = 0; c < titleRow.getLastCellNum(); c++) {
				Cell cell = titleRow.getCell(c);
				title.add(ExcelUtil.getInstance().getCellValue(cell));
			}
			for (int r = 2; r < sheetAt.getPhysicalNumberOfRows(); r++) {
				Row row = sheetAt.getRow(r);
				Map<String, String> map = new LinkedHashMap<String, String>();
				for (int c = 0; c < title.size(); c++) {
					Cell cell = row.getCell(c);
					String cellValue = getCellValue(cell);
					// System.out.println("r:" + r + " c:" + c);
					map.put(title.get(c), cellValue);
				}
				data.add(map);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (InvalidFormatException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return data;
	}

	public List<Map<String, String>> getExcelSPLOVData(File file) {
		try {
			FileInputStream fis = new FileInputStream(file);
			Workbook workbook = WorkbookFactory.create(fis);
			fis.close();
			List<Map<String, String>> data = new ArrayList<Map<String, String>>();
			List<String> title = new ArrayList<String>();
			Sheet sheetAt = workbook.getSheetAt(0);
			Row titleRow = sheetAt.getRow(0);
			for (int c = 0; c < titleRow.getLastCellNum(); c++) {
				Cell cell = titleRow.getCell(c);
				title.add(getCellValue(cell));
			}
			for (int r = 1; r < sheetAt.getPhysicalNumberOfRows(); r++) {
				Row row = sheetAt.getRow(r);
				Map<String, String> map = new LinkedHashMap<String, String>();
				for (int c = 0; c < row.getLastCellNum(); c++) {
					Cell cell = row.getCell(c);
					String cellValue = getCellValue(cell);
					map.put(title.get(c), cellValue);
				}
				data.add(map);
			}
			return data;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (InvalidFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public Map<String, List<String>> getLinkData(List<Map<String, String>> data, Map<String, String> search) {
		if (data != null) {
			List<Map<String, String>> tmpData = new ArrayList<Map<String, String>>();
			for (int i = 0; i < data.size(); i++) {
				Map<String, String> map = data.get(i);
				boolean check = true;
				for (Map.Entry<String, String> entry : search.entrySet()) {
					String key = entry.getKey();
					String value = entry.getValue();
					System.out.println("map:" + map);
					if (map.get(key) == null || !value.equals(map.get(key))) {
						check = false;
						break;
					}
				}
				if (check) {
					tmpData.add(map);
				}
			}
			Map<String, String> map2 = data.get(0);
			List<String> title = new ArrayList<String>();
			for (Map.Entry<String, String> entry : map2.entrySet()) {
				String key = entry.getKey();
				title.add(key);
			}
			Map<String, List<String>> result = new LinkedHashMap<String, List<String>>();
			for (int i = 0; i < tmpData.size(); i++) {
				Map<String, String> map = tmpData.get(i);
				for (Map.Entry<String, String> entry : map.entrySet()) {
					String key = entry.getKey();
					String value = entry.getValue();
					if (!search.containsKey(key)) {
						if (result.containsKey(key)) {
							List<String> list = result.get(key);
							if (!list.contains(value)) {
								list.add(value);
							}
						} else {
							List<String> content = new ArrayList<String>();
							content.add(value);
							result.put(key, content);
						}
					}
				}
			}
			return result;
		}
		return null;
	}

	public static void main(String[] args) throws InvalidFormatException, IOException {
		File file = new File("d:/lhhexcel.xlsx");
		FileInputStream fis = new FileInputStream(file);
		Workbook workbook = WorkbookFactory.create(fis);
		fis.close();
		/*
		 * Sheet sheet = workbook.getSheetAt(0); int lastRowNum =
		 * sheet.getLastRowNum(); int physicalNumberOfRows =
		 * sheet.getPhysicalNumberOfRows(); System.out.println("lastRowNum:" +
		 * lastRowNum);//2 System.out.println("physicalNumberOfRows:" +
		 * physicalNumberOfRows);//3 short lastCellNum =
		 * sheet.getRow(0).getLastCellNum(); int physicalNumberOfCells =
		 * sheet.getRow(0).getPhysicalNumberOfCells();
		 * System.out.println("lastCellNum:" + lastCellNum);//4
		 * System.out.println("physicalNumberOfCells:" +
		 * physicalNumberOfCells);//4
		 */
		System.out.println(ExcelUtil.getInstance().getDataByCustomCellName(workbook, "hf8_item_id2"));
	}

	public String getDataFromExcelByRowAndColumn(Sheet sheet, int rowIndex, int columnIndex) {
		Row row = sheet.getRow(rowIndex);
		if (row != null) {
			Cell cell = row.getCell(columnIndex);
			if (cell != null) {
				return getCellValue(cell);
			}
		}
		return "";
	}

	public String getDataFromExcelByRowAndColumn2(Sheet sheet, int rowIndex, int columnIndex) {
		Row row = sheet.getRow(rowIndex);
		if (row != null) {
			Cell cell = row.getCell(columnIndex);
			if (cell != null) {
				return getCalculateCellValue(cell);
			}
		}
		return "";
	}

	public String getDataByCustomCellName(Workbook wb, String cellName) {
		int namedCellIdx = wb.getNameIndex(cellName);
		if (namedCellIdx < 0) {
			return "";
		}
		Name aNamedCell = wb.getNameAt(namedCellIdx);
		AreaReference[] arefs = AreaReference.generateContiguous(aNamedCell.getRefersToFormula());
		for (int i = 0; i < arefs.length; i++) {
			CellReference[] crefs = arefs[i].getAllReferencedCells();
			for (int j = 0; j < crefs.length; j++) {
				// Check it turns into real stuff
				Sheet s = wb.getSheet(crefs[j].getSheetName());
				Row r = s.getRow(crefs[j].getRow());
				if (r != null) {
					Cell cell = r.getCell(crefs[j].getCol());
					if (cell != null) {
						return getCellValue(cell);
					}
				}
			}
		}
		return "";
	}

	public String getDataByCellName(Sheet sheet, String cellName) {
		int row = 0, column = 0;
		char[] c = cellName.toUpperCase().toCharArray();
		int index = 0;
		while (index < c.length) {
			if (c[index] < '0' || c[index] > '9') {
				column = column * 26 + (c[index] - 'A' + 1);
			} else {
				row = row * 10 + (c[index] - '0');
			}
			index++;
		}
		row--;
		column--;
		return getDataFromExcelByRowAndColumn(sheet, row, column);
	}

	/**
	 * 边框及边框颜色设置
	 * @param workBook
	 * @param color
	 * @return
	 */
	public CellStyle createCellStypeWithBorderColor(XSSFWorkbook workBook, Color color) {
		XSSFCellStyle cellStyle = workBook.createCellStyle();
		cellStyle.setBorderBottom(XSSFCellStyle.BORDER_THIN);
		cellStyle.setBorderLeft(XSSFCellStyle.BORDER_THIN);
		cellStyle.setBorderRight(XSSFCellStyle.BORDER_THIN);
		cellStyle.setBorderTop(XSSFCellStyle.BORDER_THIN);
		XSSFColor xssfColor = new XSSFColor(color);
		cellStyle.setBorderColor(BorderSide.TOP, xssfColor);
		cellStyle.setBorderColor(BorderSide.LEFT, xssfColor);
		cellStyle.setBorderColor(BorderSide.BOTTOM, xssfColor);
		cellStyle.setBorderColor(BorderSide.RIGHT, xssfColor);
		return cellStyle;
	}

	/**
	 * 边框及单元格颜色设置
	 * @param workBook
	 * @param color
	 * @return
	 */
	public CellStyle createCellStypeWithForegroundColor(XSSFWorkbook workBook, Color color) {
		XSSFCellStyle cellStyle = workBook.createCellStyle();
		cellStyle.setBorderBottom(XSSFCellStyle.BORDER_THIN);
		cellStyle.setBorderLeft(XSSFCellStyle.BORDER_THIN);
		cellStyle.setBorderRight(XSSFCellStyle.BORDER_THIN);
		cellStyle.setBorderTop(XSSFCellStyle.BORDER_THIN);
		XSSFColor xssfColor = new XSSFColor(color);
		cellStyle.setFillForegroundColor(xssfColor);
		cellStyle.setFillForegroundColor(xssfColor);
		cellStyle.setFillForegroundColor(xssfColor);
		cellStyle.setFillForegroundColor(xssfColor);
		cellStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
		return cellStyle;
	}

	/**
	 * 复制单元表内容
	 * @param fromsheet
	 * @param newsheet
	 */
	public void copySheet(Sheet fromsheet, Sheet newsheet) {
		int firstrow = fromsheet.getFirstRowNum();
		int lastrow = fromsheet.getLastRowNum();
		if ((firstrow == -1) || (lastrow == -1) || lastrow < firstrow) {
			return;
		}
		// 拷贝合并的单元格
		for (int i = 0; i < fromsheet.getNumMergedRegions(); i++) {
			CellRangeAddress mergedRegion = fromsheet.getMergedRegion(i);
			newsheet.addMergedRegion(mergedRegion);
		}
		Row fromRow = null;
		Row newRow = null;
		Cell newCell = null;
		Cell fromCell = null;
		// 设置列宽
		for (int i = firstrow; i <= lastrow; i++) {
			fromRow = fromsheet.getRow(i);
			if (fromRow != null) {
				for (int j = fromRow.getLastCellNum(); j >= fromRow
						.getFirstCellNum(); j--) {
					int colnum = fromsheet.getColumnWidth((short) j);
					if (colnum > 100) {
						newsheet.setColumnWidth((short) j, (short) colnum);
					}
					if (colnum == 0) {
						newsheet.setColumnHidden((short) j, true);
					} else {
						newsheet.setColumnHidden((short) j, false);
					}
				}
				break;
			}
		}
		// 拷贝行并填充数据
		for (int i = 0; i <= lastrow; i++) {
			fromRow = fromsheet.getRow(i);
			if (fromRow == null) {
				continue;
			}
			newRow = newsheet.createRow(i - firstrow);
			newRow.setHeight(fromRow.getHeight());
			for (int j = fromRow.getFirstCellNum(); j < fromRow
					.getPhysicalNumberOfCells(); j++) {
				fromCell = fromRow.getCell((short) j);
				if (fromCell == null) {
					continue;
				}
				newCell = newRow.createCell((short) j);
				CellStyle cellStyle = fromCell.getCellStyle();

				// HSSFCellStyle newStyle =
				// newsheet.getWorkbook().createCellStyle();
				// newStyle.cloneStyleFrom(cellStyle);
				// newCell.setCellStyle(newStyle);

				// HSSFCellStyle cellStyle2 = newCell.getCellStyle();
				// cellStyle2.setFillForegroundColor(cellStyle.getFillForegroundColor());
				// cellStyle2.setFillPattern(cellStyle.getFillPattern());
				// cellStyle2.setAlignment(cellStyle.getAlignment());
				// cellStyle2.setVerticalAlignment(cellStyle.getVerticalAlignment());

				int cType = fromCell.getCellType();
				newCell.setCellType(cType);
				switch (cType) {
				case Cell.CELL_TYPE_STRING:
					newCell.setCellValue(fromCell.getRichStringCellValue());
					break;
				case Cell.CELL_TYPE_NUMERIC:
					newCell.setCellValue(fromCell.getNumericCellValue());
					break;
				case Cell.CELL_TYPE_FORMULA:
					newCell.setCellFormula(fromCell.getCellFormula());
					break;
				case Cell.CELL_TYPE_BOOLEAN:
					newCell.setCellValue(fromCell.getBooleanCellValue());
					break;
				case Cell.CELL_TYPE_ERROR:
					newCell.setCellValue(fromCell.getErrorCellValue());
					break;
				default:
					newCell.setCellValue(fromCell.getRichStringCellValue());
					break;
				}
			}
		}
	}
}