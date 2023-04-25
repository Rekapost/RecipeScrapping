package ScrapTestCases;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;	
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
			 
			 public void fillGreenColor(String sheetName, int rownum,int column) throws IOException
			 {
				fileinput=new FileInputStream("C:\\Users\\Reka\\eclipse-workspace\\webscrapping\\src\\test\\resources\\ScrapeData\\recipes_final.xlsx");
				workbook=new XSSFWorkbook(fileinput);
				sheet=workbook.getSheet(sheetName);
				
				row=sheet.getRow(rownum);
				cell=row.getCell(column);
				
				style=workbook.createCellStyle();
				
				style.setFillForegroundColor(IndexedColors.GREEN.getIndex());
				style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
				
				cell.setCellStyle(style);
	        	 String filename = "C:\\Users\\Reka\\eclipse-workspace\\webscrapping\\src\\test\\resources\\ScrapeData\\to_Add_diabetic.xlsx";
	        	 FileOutputStream  fileoutput = new FileOutputStream(filename);
				workbook.write(fileoutput);
				workbook.close();
				fileinput.close();
				fileoutput.close();
			 }
			 
			 FileOutputStream fo;
			 FileInputStream fi;
			 //XSSFCellStyle cellStyle;
			 public void setCellData(String sheetName,int rowNum,int colNum,
			 				String data,boolean allergeFound) throws IOException {
			 			File xlFile = new File(path);
			 			// if file not exists then create a new file
			 			if (!xlFile.exists()) {         
			 			workbook = new XSSFWorkbook();
			 			fo = new FileOutputStream(path);
			 			workbook.write(fo);
			 			}
			 			fi = new FileInputStream(path);
			 			workbook = new XSSFWorkbook(fi);
			 			// if Sheet not exists then create a new sheet
			 			if (workbook.getSheetIndex(sheetName) == -1) {
			 				workbook.createSheet(sheetName);
			 			}
			 			sheet = workbook.getSheet(sheetName);
			 			// if row not exists then create a new row
			 			if (sheet.getRow(rowNum) == null) {
			 				sheet.createRow(rowNum);
			 			}
			 			row = sheet.getRow(rowNum);
			 		    cell = row.createCell(colNum);
			 			cell.setCellValue(data);
			 			fo = new FileOutputStream(path);
			 			//style=workbook.createCellStyle();
			 			if(allergeFound) {
			 				style = workbook.createCellStyle();
			 				style.setFillForegroundColor(IndexedColors.RED.getIndex());
			 				style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
			 				 cell.setCellStyle(style);
			 			} else {
			 				style = workbook.createCellStyle();
			 				style.setFillForegroundColor(IndexedColors.SEA_GREEN.getIndex());
			 				style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
			 				 cell.setCellStyle(style);
			 			}
			 			workbook.write(fo);
			 			workbook.close();
			 			fi.close();
			 			fo.close();
			 			}
			 
//			 public void fillRedColor(String sheetName, int rownum,int column,String value,FileOutputStream fileinput) throws IOException
//			 {
//				//fileinput=new FileInputStream("C:\\Users\\Reka\\eclipse-workspace\\webscrapping\\src\\test\\resources\\ScrapeData\\Diabetic_Eliminated_Passed_Recipe.xlsx");
////
////				workbook=new XSSFWorkbook(fileinput);
////				sheet=workbook.getSheet(sheetName);
//////				row=sheet.getRow(rownum); --
////				
////				row = sheet.getRow(rownum);
////				if (row == null) row = sheet.createRow(rownum++);
////				System.out.println("rownum"+rownum);
////				cell=row.getCell(column);
////		        cell = row.createCell(8);
////		        cell.setCellValue(value);
////
//////		      cell.setCellValue(data); --
////				
////	        	 String filename = "C:\\Users\\Reka\\eclipse-workspace\\webscrapping\\src\\test\\resources\\ScrapeData\\allergy_diabetic.xlsx";
////	        	 FileOutputStream  fileoutput = new FileOutputStream(filename);
//				
//				 FileOutputStream
//				style=workbook.createCellStyle();				
//				style.setFillForegroundColor(IndexedColors.RED.getIndex());
//				style.setFillPattern(FillPatternType.SOLID_FOREGROUND);				
//				cell.setCellStyle(style);
//
//				workbook.write(fileoutput);
//				workbook.close();
//				//fileinput.close();
//				fileoutput.close();
//				
//			 }
			 
			 
	 
			 public void setCellDataColouring(String sheetName,int rowNum,int colNum,
				      String data,boolean allergeFound) throws IOException {
				      File xlFile = new File("C:\\Users\\Reka\\eclipse-workspace\\webscrapping\\src\\test\\resources\\ScrapeData\\recipes_final.xlsx");
				      // if file not exists then create a new file
				      if (!xlFile.exists()) {         
				      workbook = new XSSFWorkbook();
				      fileoutput = new FileOutputStream("C:\\Users\\Reka\\eclipse-workspace\\webscrapping\\src\\test\\resources\\ScrapeData\\recipetest_allergy_final.xlsx");
				      workbook.write(fileoutput);
				      }
				      fileinput = new FileInputStream("C:\\Users\\Reka\\eclipse-workspace\\webscrapping\\src\\test\\resources\\ScrapeData\\recipes_final.xlsx");
				      workbook = new XSSFWorkbook(fileinput);
				      // if Sheet not exists then create a new sheet
				      if (workbook.getSheetIndex(sheetName) == -1) {
				        workbook.createSheet(sheetName);
				      }
				      sheet = workbook.getSheet(sheetName);
				      // if row not exists then create a new row
				      if (sheet.getRow(rowNum) == null) {
				        sheet.createRow(rowNum);
				      }
				      row = sheet.getRow(rowNum);
				        cell = row.createCell(colNum);
				      cell.setCellValue(data);
				      fileoutput = new FileOutputStream("C:\\Users\\Reka\\eclipse-workspace\\webscrapping\\src\\test\\resources\\ScrapeData\\recipes_allergyfinal.xlsx");
				      
				      if(!allergeFound) {
				      style = workbook.createCellStyle();
				      style.setFillForegroundColor(IndexedColors.SEA_GREEN.getIndex());
				      style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
				      cell.setCellStyle(style);
				      }
				       
				      workbook.write(fileoutput);
				      workbook.close();
				      fileinput.close();
				      fileoutput.close();
				      }
			
}

