package commonUtilities;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

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
			if (recipeIngredients.contains(avoiditem.toUpperCase())) {
				return true;
			}
		}
		return false;
	}

	public boolean hasToAddLists(List<String> ToAddItems, String recipeIngredients) {
		for (String avoiditem : ToAddItems) {
			if (recipeIngredients.contains(avoiditem.toUpperCase())) {
				return true;
			}
		}
		return false;
	}

	public void writeDataToExcel(List<String[]> data, String sheetName, String searchinput) throws IOException {
		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet sheet = workbook.createSheet(sheetName);

		// Create header row
		String[] headers = { "ReceipeID", "Recipe Name", "Page Title", "Ingredients", "Preparation Time",
				"Cooking Time", "Method", "Nutrient Values", "URL" };
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
		if (searchinput.equalsIgnoreCase("Vegan Diabetic")) {
			filename = "C:\\Users\\Reka\\eclipse-workspace\\webscrapping\\src\\test\\resources\\ScrapeData\\recipes_Diabetic_vegan.xlsx";
			outputStream = new FileOutputStream(filename);
		} else if (searchinput.equalsIgnoreCase("Jain Diabetic")) {
			filename = "C:\\Users\\Reka\\eclipse-workspace\\webscrapping\\src\\test\\resources\\ScrapeData\\recipes_jain_diabetic.xlsx";
			outputStream = new FileOutputStream(filename);
		} else if (searchinput.equalsIgnoreCase("Non Veg Diabetic")) {
			filename = "C:\\Users\\Reka\\eclipse-workspace\\webscrapping\\src\\test\\resources\\ScrapeData\\recipes_non_veg_Diabetic.xlsx";
			outputStream = new FileOutputStream(filename);
		} else if (searchinput.equalsIgnoreCase("Vegetarian Diabetic")) {
			filename = "C:\\Users\\Reka\\eclipse-workspace\\webscrapping\\src\\test\\resources\\ScrapeData\\recipes_veg_Diabetic.xlsx";
			outputStream = new FileOutputStream(filename);
		} else if (searchinput.equalsIgnoreCase("Diabetic recipes")) {
			filename = "C:\\Users\\Reka\\eclipse-workspace\\webscrapping\\src\\test\\resources\\ScrapeData\\recipes_Diabetic.xlsx";
			outputStream = new FileOutputStream(filename);
		}
		//////////////////////////////////////////////////////////////
		else if (searchinput.equalsIgnoreCase("Hypothyroid Breakfast")) {
			filename = "C:\\Users\\Reka\\eclipse-workspace\\webscrapping\\src\\test\\resources\\ScrapeData\\recipes_hypothyroid.xlsx";
			outputStream = new FileOutputStream(filename);
		}
		else if (searchinput.equalsIgnoreCase("Hypothyroid snack")) {
			filename = "C:\\Users\\Reka\\eclipse-workspace\\webscrapping\\src\\test\\resources\\ScrapeData\\recipes_jain_hypothyroid.xlsx";
			outputStream = new FileOutputStream(filename);
		}
		else if (searchinput.equalsIgnoreCase("Jain Hypothyroid")) {
			filename = "C:\\Users\\Reka\\eclipse-workspace\\webscrapping\\src\\test\\resources\\ScrapeData\\recipes_non_veg_hypothyroid.xlsx";
			outputStream = new FileOutputStream(filename);
		}
		else if (searchinput.equalsIgnoreCase("Non Veg Hypothyroid")) {
			filename = "C:\\Users\\Reka\\eclipse-workspace\\webscrapping\\src\\test\\resources\\ScrapeData\\recipes_veg_hypothyroid.xlsx";
			outputStream = new FileOutputStream(filename);
		}
		else if (searchinput.equalsIgnoreCase("Vegetarian Hypothyroid")) {
			filename = "C:\\Users\\Reka\\eclipse-workspace\\webscrapping\\src\\test\\resources\\ScrapeData\\recipes_hypertension.xlsx";
			outputStream = new FileOutputStream(filename);
		}
		//////////////////////////////////////////////////////////////////////////
		else if (searchinput.equalsIgnoreCase("Vegan Hypertension")) {
			filename = "C:\\Users\\Reka\\eclipse-workspace\\webscrapping\\src\\test\\resources\\ScrapeData\\recipes_hypertension.xlsx";
			outputStream = new FileOutputStream(filename);
		}
		else if (searchinput.equalsIgnoreCase("Jain Hypertension")) {
			filename = "C:\\Users\\Reka\\eclipse-workspace\\webscrapping\\src\\test\\resources\\ScrapeData\\recipes_jain_hypertension.xlsx";
			outputStream = new FileOutputStream(filename);
		}
		else if (searchinput.equalsIgnoreCase("Non Veg Hypertension ")) {
			filename = "C:\\Users\\Reka\\eclipse-workspace\\webscrapping\\src\\test\\resources\\ScrapeData\\recipes_non_veg_hypertension.xlsx";
			outputStream = new FileOutputStream(filename);
		}
		else if (searchinput.equalsIgnoreCase("Vegetarian Hypertension")) {
			filename = "C:\\Users\\Reka\\eclipse-workspace\\webscrapping\\src\\test\\resources\\ScrapeData\\recipes_veg_hypertension.xlsx";
			outputStream = new FileOutputStream(filename);
		}
		////////////////////////////////////////////////////////////////////////////////////////
		
		else if (searchinput.equalsIgnoreCase("PCOS breakfast")) {
			filename = "C:\\Users\\Reka\\eclipse-workspace\\webscrapping\\src\\test\\resources\\ScrapeData\\PCOS_breakfast.xlsx";
			outputStream = new FileOutputStream(filename);
		}
		
		else if (searchinput.equalsIgnoreCase("PCOS Weight Loss")) {
			filename = "C:\\Users\\Reka\\eclipse-workspace\\webscrapping\\src\\test\\resources\\ScrapeData\\PCOS_Weight Loss";
			outputStream = new FileOutputStream(filename);
		}
		else if (searchinput.equalsIgnoreCase("PCOS Indian")) {
			filename = "C:\\Users\\Reka\\eclipse-workspace\\webscrapping\\src\\test\\resources\\ScrapeData\\PCOS_Indian.xlsx";
			outputStream = new FileOutputStream(filename);
		}
		
//        String filename = "C:\\Users\\Reka\\eclipse-workspace\\webscrapping\\src\\test\\resources\\recipes_final_s.xlsx";
//        outputStream = new FileOutputStream(filename);
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