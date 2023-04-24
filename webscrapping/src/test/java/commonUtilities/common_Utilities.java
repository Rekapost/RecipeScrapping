package commonUtilities;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.apache.poi.openxml4j.util.ZipSecureFile;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;


public class common_Utilities {
	String filename;
	  FileOutputStream outputStream;
	
	public boolean hasEliminatedLists(List<String> eliminateItems, String recipeIngredients) {
		for (String avoiditem : eliminateItems) {
			if (recipeIngredients.toUpperCase().contains(avoiditem.toUpperCase())) {
				return true;
			}
			
		}
		return false;
		
	}
	
	public boolean hasAllergyLists(List<String> allergyItems, String recipeIngredients) {
		for (String avoiditem : allergyItems) {
			if (recipeIngredients.toUpperCase().contains(avoiditem.toUpperCase())) {
				return true;
			}			
		}
		return false;		
	}
	
	public boolean hasToAddLists(List<String> ToAddItems, String recipeIngredients) {
		for (String avoiditem : ToAddItems) {
			if (recipeIngredients.toUpperCase().contains(avoiditem.toUpperCase())) {
				return true;
			}			
		}
		return false;		
	}
	
/*	
	public  void writeToExcel(List<String[]> data) throws IOException, Exception
	{
	String filename = "C:\\Users\\Reka\\eclipse-workspace\\webscrapping\\src\\test\\resources\\recipes.xlsx";
	String currentDir = System.getProperty("user.dir");
	 System.out.println("Current dir using System:" + currentDir);
	 ZipSecureFile.setMinInflateRatio(0);

	XSSFWorkbook workbook = new XSSFWorkbook(new File(filename));
	XSSFSheet sheet = workbook.createSheet("Recipes");

	// Create header row
	String[] headers = {"ReceipeID" ,"Recipe Name","Ingredients","URL","Method","Nutrient Values","Preparation Time","Cooking Time"};
	 XSSFRow headerRow = sheet.getRow(0).getCell(0)==null ? sheet.createRow(0):null;
	try 
	{
	 
	 for (int i = 0; i < headers.length; i++) 
	 {
	    XSSFCell cell = headerRow.createCell(i);
	    cell.setCellValue(headers[i]);
	    XSSFCellStyle style = workbook.createCellStyle();
	    style.setWrapText(true);
	    cell.setCellStyle(style);
	}
	}
	catch(Exception e)
	{
	 System.out.print("Header exists");
	}

	// Add data to rows
	int rowNumber = 1;
	for (String[] rowData : data) {
	    XSSFRow row = sheet.createRow(rowNumber++);
	    int cellNumber = 0;
	    
	    for (String cellData : rowData)
	    {
	        XSSFCell cell = row.createCell(cellNumber++);
	        cell.setCellValue((String)cellData);
//	        if (cellData instanceof String )
//	            cell.setCellValue((String)cellData);
//	           if (cellData instanceof Integer)
//	            cell.setCellValue((Integer)cellData);
//	           if (cellData instanceof Boolean)
//	            cell.setCellValue((Boolean)cellData);
//	          
	         XSSFCellStyle style = workbook.createCellStyle();
	        style.setWrapText(true);
	        cell.setCellStyle(style);
	    }
	    
 	}

	// Save the workbook to a file
	//String filename1 = "recipes.xlsx";
	FileOutputStream outputStream = new FileOutputStream(filename, true);
	workbook.write(outputStream);
	workbook.close();
	System.out.println("Data saved to " + filename);
	}
*/	
	public  void writeDataToExcel(List<String[]> data,String sheetName) throws IOException { 
        XSSFWorkbook workbook = new XSSFWorkbook(); 
        XSSFSheet sheet = workbook.createSheet(sheetName); 
         
        // Create header row 
        String[] headers = {"ReceipeID" ,"Recipe Name","Ingredients","Preparation Time","Cooking Time","Method","URL","Nutrient Values"};
        XSSFRow headerRow = sheet.createRow(0); 
        for (int i = 0; i < headers.length; i++) { 
            XSSFCell cell = headerRow.createCell(i); 
            cell.setCellValue(headers[i]); 
            XSSFCellStyle style = workbook.createCellStyle(); 
            style.setWrapText(true); 
            cell.setCellStyle(style); 
        } 
         
        // Add data to rows 
        int rowNumber = 1; 
        for (String[] rowData : data) { 
            XSSFRow row = sheet.createRow(rowNumber++); 
            int cellNumber = 0; 
            for (String cellData : rowData) { 
                XSSFCell cell = row.createCell(cellNumber++); 
                cell.setCellValue(cellData); 
                XSSFCellStyle style = workbook.createCellStyle(); 
                style.setWrapText(true); 
                cell.setCellStyle(style); 
            } 
        } 
         
        // Save the workbook to a file 
//        if(searchinput.equalsIgnoreCase("Vegan Diabetic")) {
//        	String filename = "C:\\Users\\Reka\\eclipse-workspace\\webscrapping\\src\\test\\resources\\recipes_vegan.xlsx";
//        	   outputStream = new FileOutputStream(filename);
//        }
//        else if (searchinput.equalsIgnoreCase("Jain Diabetic")){
//        	String filename = "C:\\Users\\Reka\\eclipse-workspace\\webscrapping\\src\\test\\resources\\recipes_jaindiabetic.xlsx";
//        	   outputStream = new FileOutputStream(filename);
//        }
//        else if (searchinput.equalsIgnoreCase("Non Veg Diabetic")){
//        	String filename = "C:\\Users\\Reka\\eclipse-workspace\\webscrapping\\src\\test\\resources\\recipes_non_veg.xlsx";
//        	   outputStream = new FileOutputStream(filename);
//        }
//        else if (searchinput.equalsIgnoreCase("Vegetarian Diabetic")){
//        	String filename = "C:\\Users\\Reka\\eclipse-workspace\\webscrapping\\src\\test\\resources\\recipes_veg.xlsx";
//        	   outputStream = new FileOutputStream(filename);
//        }
         
        String filename = "C:\\Users\\Reka\\eclipse-workspace\\webscrapping\\src\\test\\resources\\recipes_final_s.xlsx";
        outputStream = new FileOutputStream(filename);
        try {
			workbook.write(outputStream);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
        workbook.close(); 
        System.out.println("Data saved to " + filename); 
    }
	
	
	
}
