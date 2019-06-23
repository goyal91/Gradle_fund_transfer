package com.innoviti.retail.fundTransfer.fileGeneration;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.innoviti.retail.fundTransfer.utils.IFundTransferDto;
import com.innoviti.retail.fundTransfer.utils.ReportConstants;

@Component
public class ExcelFileGenerationImpl {

	private static Logger logger = LoggerFactory.getLogger(ExcelFileGenerationImpl.class);

	SimpleDateFormat formatter = new SimpleDateFormat("ddMMyyyy");
	SimpleDateFormat formatter1 = new SimpleDateFormat("dd/MM/yyyy");

	public void generateExcel(XSSFWorkbook workBook, List<Object> excelSheetData) {
		try {
			logger.info("INSIDE: FundTransferReportDetails generateExcel Method START:");

			XSSFFont headerFont = workBook.createFont();
			headerFont.setBold(true);
			headerFont.setFontHeightInPoints((short) 12);
			headerFont.setColor(IndexedColors.BLACK.getIndex());

			CellStyle headerCellStyle = workBook.createCellStyle();
			headerCellStyle.setFont(headerFont);
			List<String[][]> headerCoulms = new ArrayList<>();
			headerCoulms.add(ReportConstants.aggrHeaderColumns);
			headerCoulms.add(ReportConstants.txnHeaderColumns);
			headerCoulms.add(ReportConstants.brandAggrHeaderColumns);
			headerCoulms.add(ReportConstants.brandTxnHeaderColumns);

			String[] sheetNames = ReportConstants.sheetNames;

			for (int i = 0; i < sheetNames.length; i++) {
				generateAggregateExcelSheet(workBook.createSheet(sheetNames[i]), headerCoulms.get(i), headerCellStyle,
						excelSheetData.get(i));
			}
			autoSizeColumns(workBook);
			getDataStyle(workBook);
			logger.info("Exitting: FundTransferReportDetails generateExcel Method:");
		} catch (Exception e) {
			logger.error("EXITTING: FundTransferReportDetails With Error:" + e.getMessage(), e);
		}
	}

	private void generateAggregateExcelSheet(XSSFSheet xssfSheet, String[][] columns, CellStyle headerCellStyle,
			Object excelSheetData) throws Exception {
		generateExcelHeader(xssfSheet, columns, headerCellStyle);
		generateExcelSheet(excelSheetData, xssfSheet, columns);
	}

	@SuppressWarnings("unchecked")
	private void generateExcelSheet(Object excelSheetData, XSSFSheet xssfSheet, String[][] columns) throws Exception {

		List<IFundTransferDto> sheetData = null;
		if (null != excelSheetData) {
			sheetData = (List<IFundTransferDto>) excelSheetData;
			for (int i = 0; i < sheetData.size(); i++) {
				int counter = 0;
				XSSFRow excelRow = xssfSheet.createRow(i + 1);
				IFundTransferDto rowData = (IFundTransferDto) sheetData.get(i);
				for (int j = 0; j < columns.length; j++) {

					try {

						Cell cell = excelRow.createCell(counter++);
						Object result = rowData.getClass().getMethod(columns[j][1]).invoke(rowData, (Object[]) null);
						setCellValue(cell, result);

					} catch (Exception e) {
						logger.error("Fatal Error! Could not write transaction data to file for sheet - *{}*.",
								xssfSheet.getSheetName(), e);
						throw e;
					}

				}
			}
		}

	}

	private void setCellValue(Cell cell, Object value) {
		if (value != null) {
			if (value instanceof String) {
				cell.setCellValue((String) value);
			} else if (value instanceof Long) {
				cell.setCellValue((Long) value);
			} else if (value instanceof Integer) {
				cell.setCellValue((Integer) value);
			} else if (value instanceof Double) {
				cell.setCellValue((Double) value);
			} else if (value instanceof Date) {
				cell.setCellValue((Date) value);
			}
		}
	}

	private void generateExcelHeader(XSSFSheet sheet, String[][] colummns, CellStyle headerCellStyle) {
		XSSFRow excelHeader = sheet.createRow(0);
		for (int i = 0; i < colummns.length; i++) {
			Cell cell = excelHeader.createCell(i);
			cell.setCellValue(colummns[i][0]);
			cell.setCellStyle(headerCellStyle);
		}

	}

	private void autoSizeColumns(XSSFWorkbook workBook) {

		for (int i = 0; i < workBook.getNumberOfSheets(); i++) {
			XSSFSheet xSSFSheet = workBook.getSheetAt(i);
			int colIndex = xSSFSheet.getRow(0).getLastCellNum();

			for (int j = 0; j < colIndex; j++)
				xSSFSheet.autoSizeColumn(j);
		}

	}

	private XSSFCellStyle getDataStyle(XSSFWorkbook workbook) {
		XSSFCellStyle cellStyle = workbook.createCellStyle();
		XSSFFont font = workbook.createFont();
		font.setBold(false);
		font.setItalic(false);
		font.setFontName("ARIAL");
		cellStyle.setFont(font);
		cellStyle.setAlignment(HorizontalAlignment.CENTER);
		cellStyle.setBorderBottom(BorderStyle.THIN);
		cellStyle.setBottomBorderColor(IndexedColors.BLACK.getIndex());
		cellStyle.setBorderLeft(BorderStyle.THIN);
		cellStyle.setLeftBorderColor(IndexedColors.BLACK.getIndex());
		cellStyle.setBorderRight(BorderStyle.THIN);
		cellStyle.setRightBorderColor(IndexedColors.BLACK.getIndex());
		cellStyle.setBorderTop(BorderStyle.THIN);
		cellStyle.setTopBorderColor(IndexedColors.BLACK.getIndex());
		cellStyle.setAlignment(HorizontalAlignment.CENTER);
		return cellStyle;
	}

}