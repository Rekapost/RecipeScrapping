package testCases;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.File;	
public class ExcelReadWrite {
	
			public static FileInputStream fileinput;
			public static FileOutputStream fileoutput;
			public static XSSFWorkbook workbook;
			public static XSSFSheet sheet;
			public static XSSFRow row;
			public static XSSFCell cell;
			public CellStyle style;
			String path;
			 int sheetCount;
			
			public ExcelReadWrite(String path){
				this.path=path;
			}
			
			public int getRowCount(String sheetName) throws IOException 
			{
				fileinput=new FileInputStream(path);
				workbook=new XSSFWorkbook(fileinput);
				sheet=workbook.getSheet(sheetName);
				int rowcount=sheet.getLastRowNum();
				workbook.close();
				fileinput.close();
				return rowcount;		
			}
			
			
			public int getCellCount(String sheetName,int rownum) throws IOException
			{
				fileinput=new FileInputStream(path);
				workbook=new XSSFWorkbook(fileinput);
				sheet=workbook.getSheet(sheetName);
				row=sheet.getRow(rownum);
				int cellcount=row.getLastCellNum();
				workbook.close();
				fileinput.close();
				return cellcount;
			}
			
			
			public  String getCellData(String sheetName,int rownum,int colnum) throws IOException
			{
				fileinput=new FileInputStream(path);
				workbook=new XSSFWorkbook(fileinput);
				sheet=workbook.getSheet(sheetName);
				
				row=sheet.getRow(rownum);
				cell=row.getCell(colnum);
				
				String data;
				try 
				{
					DataFormatter formatter = new DataFormatter();
		            data = formatter.formatCellValue(cell);// returns the formatted value of a cell as a string regardless of the 
		            
				}
				catch (Exception e) 
				{
					data="";
				}
				workbook.close();
				fileinput.close();
				return data;
			}
			
//			public static void setCellData(String xlfile,String xlsheet,int rownum,int colnum,String data) throws IOException
//			{
//				fileinput=new FileInputStream(xlfile);
//				workbook=new XSSFWorkbook(fileinput);
//				sheet=workbook.getSheet(xlsheet);
//				row=sheet.getRow(rownum);
//				cell=row.createCell(colnum);
//				cell.setCellValue(data);
//				fileoutput=new FileOutputStream(xlfile);
//				workbook.write(fileoutput);		
//				workbook.close();
//				fileinput.close();
//				fileoutput.close();
//			}
			
			// to write data into excel sheet
			public void setCellData(String sheetName, int rownum, int column, String data) throws IOException
			{
			File xlfile=new File(path);			
			if(!xlfile.exists())   // if file not exists then create new file
			{
				workbook=new XSSFWorkbook();
				fileoutput=new FileOutputStream(path);
				workbook.write(fileoutput);
			}
			fileinput=new FileInputStream(path);
			workbook=new XSSFWorkbook(fileinput);
			 
			 if(workbook.getSheetIndex(sheetName)==-1)  // if sheet not exists then create new sheet
				 workbook.createSheet(sheetName);
			 sheet=workbook.getSheet(sheetName);
			sheetCount = workbook.getNumberOfSheets();
			 if(sheet.getRow(rownum)==null)  // if row not exists then create new row
				 sheet.createRow(rownum);
			 row=sheet.getRow(rownum);
			 
			 cell=row.createCell(column);
			 cell.setCellValue(data);
			 fileoutput=new FileOutputStream(path);
			 workbook.write(fileoutput);
			 workbook.close();
			 fileinput.close();
			 fileoutput.close();
			}
			
//			public void setCellData(String sheetName, int rownum, int column, List<String> data) {
//				// TODO Auto-generated method stub
//				File xlfile=new File(path);			
//				if(!xlfile.exists())   // if file not exists then create new file
//				{
//					workbook=new XSSFWorkbook();
//					fileoutput=new FileOutputStream(path);
//					workbook.write(fileoutput);
//				}
//				fileinput=new FileInputStream(path);
//				workbook=new XSSFWorkbook(fileinput);
//				 
//				 if(workbook.getSheetIndex(sheetName)==-1)  // if sheet not exists then create new sheet
//					 workbook.createSheet(sheetName);
//				 
//				 sheet=workbook.getSheet(sheetName);
//				 
//				 if(sheet.getRow(rownum)==null)  // if row not exists then create new row
//					 sheet.createRow(rownum);
//				 row=sheet.getRow(rownum);
//				 
//				 cell=row.createCell(column);
//				 cell.setCellValue(data);
//				 fileoutput=new FileOutputStream(path);
//				 workbook.write(fileoutput);
//				 workbook.close();
//				 fileinput.close();
//				 fileoutput.close();
//			}
			 
			 public void fillGreenColor(String sheetName, int rownum,int column) throws IOException
			 {
				fileinput=new FileInputStream(path);
				workbook=new XSSFWorkbook(fileinput);
				sheet=workbook.getSheet(sheetName);
				
				row=sheet.getRow(rownum);
				cell=row.getCell(column);
				
				style=workbook.createCellStyle();
				
				style.setFillForegroundColor(IndexedColors.GREEN.getIndex());
				style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
				
				cell.setCellStyle(style);
				workbook.write(fileoutput);
				workbook.close();
				fileinput.close();
				fileoutput.close();
			 }
			 
			 public void fillRedColor(String sheetName, int rownum,int column) throws IOException
			 {
				fileinput=new FileInputStream(path);
				workbook=new XSSFWorkbook(fileinput);
				sheet=workbook.getSheet(sheetName);
				
				row=sheet.getRow(rownum);
				cell=row.getCell(column);
				
				style=workbook.createCellStyle();
				
				style.setFillForegroundColor(IndexedColors.RED.getIndex());
				style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
				
				cell.setCellStyle(style);
				workbook.write(fileoutput);
				workbook.close();
				fileinput.close();
				fileoutput.close();
			 }

			
	
}
