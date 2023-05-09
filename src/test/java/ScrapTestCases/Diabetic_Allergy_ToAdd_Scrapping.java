package ScrapTestCases;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.testng.annotations.Test;
import commonUtilities.common_Utilities;
import utilities.ExcelReader;

public class Diabetic_Allergy_ToAdd_Scrapping {
	FileOutputStream outputStream;
	List<String> allergyList;
	List<String> filterList;
	List<String> allergyRecipeList;
	String finalpath = "C:\\Users\\Reka\\eclipse-workspace\\webscrapping\\src\\test\\resources\\ScrapeData\\recipes_Diabetic.xlsx";
	String input_path = "C:\\Users\\Reka\\eclipse-workspace\\webscrapping\\src\\test\\resources\\ScrapeData\\Recipes_Input.xlsx";
	//String finalpath_New = "C:\\Users\\Reka\\eclipse-workspace\\webscrapping\\src\\test\\resources\\ScrapeData\\allergy_ToAdd_diabetic.xlsx";
	ExcelReadWrite reader;
	ExcelReader read;
	common_Utilities checkList = new common_Utilities();
	int noOfRows;
	String[][] data;
	int noOfColumns;
	int d;
	String allergy_filter_data;
	String ToAdd_filter_data;
	
	@Test
	public void allergy() throws IOException {
		//List<String[][]> allergyFilteredList = null;
		
// **********************Reading diabetes allergy  eliminated list	and saving it in an Arraylist 
		reader = new ExcelReadWrite(input_path);
		String allergy = reader.getCellData("allergy_eliminated", 0, 0);
		int allergyrowSize = reader.getRowCount("allergy_eliminated");
		System.out.println("allergy row size " + allergyrowSize);
		System.out.println("Reading excel ");
		allergyList = new ArrayList<String>();
		for (int d = 1; d <= allergyrowSize; d++) {
			String allergy_data = reader.getCellData("allergy_eliminated", d, 0);
//				System.out.println(diabetes_data);
			allergyList.add(allergy_data);
//				System.out.println(diabetes_data);
		}
	
//******************** reading  filtered Recpies list 
		reader = new ExcelReadWrite(finalpath);
    	//String filtered = reader.getCellData("diabetic", 0, 0);
		int fil_RecpSize = reader.getRowCount("Diabetic_recipes");
		System.out.println(" filtered recipes row size " + fil_RecpSize);
		System.out.println("Reading excel ");
		filterList = new ArrayList<String>();
		for (int a = 1; a <= fil_RecpSize; a++) {
			allergy_filter_data = reader.getCellData("Diabetic_recipes", a, 3);
//							String[] recipeData= {recipe_id, reciepename, title,RecpieIngredients, prep_time,cook_time ,     
//									 method,Nutritionlist,url};
//							filterList.add(filter_data);			
			
//**************************** checking for allergy item  in recipe							
			boolean checkContainAllergyItems = checkList.hasAllergyOrRecommendedLists(allergyList, allergy_filter_data);
			reader = new ExcelReadWrite(finalpath);
//							reader.setCellDataColouring("filtered_Recp",d,2,"allergy item",isContainAllergyItems);

			if (checkContainAllergyItems) {
				// System.out.println("An allergy item");
				// System.out.println(" Recipe is not recomeneded as it has Ingredients from
				// allergy List");
				reader.setCellData("Diabetic_recipes", a, 9, "allergy item", true);
				// System.out.println(a);
			} else {
				// System.out.println("Not an allergy item");
				reader.setCellData("Diabetic_recipes", a, 9, "Not an allergy item", false);
				// System.out.println(a);
			}
//							System.out.println(filter_data);
		}
//**************************** checking for ToAdd item in recipe		              	
		List<String> addList = getAddList();
		reader = new ExcelReadWrite(finalpath);
		for (int a1 = 1; a1 <= fil_RecpSize; a1++) {
			ToAdd_filter_data = reader.getCellData("Diabetic_recipes", a1, 3);
//								String[] recipeData= {recipe_id, reciepename, title,RecpieIngredients, prep_time,cook_time ,     
//										 method,Nutritionlist,url};
//								filterList.add(filter_data); 
			boolean checkRecommendedItems = checkList.hasAllergyOrRecommendedLists(addList, ToAdd_filter_data);
			reader = new ExcelReadWrite(finalpath);
//								reader.setCellDataColouring("filtered_Recp",d,2,"allergy item",isContainAllergyItems);

			if (checkRecommendedItems) {
				// System.out.println("Recomeneded Item");
				reader.setCellData("Diabetic_recipes", a1, 10, "Recomended item", true);
				// System.out.println(a1);
			} else {
				// System.out.println("Not a Recomeneded Item");
				reader.setCellData("Diabetic_recipes", a1, 10, "Not Recomended item", false);
				// System.out.println(a1);
			}
//								System.out.println(filter_data);
		}
	}
	
//************************* Getting ToAdd items from excel and saving it in array 
	public List<String> getAddList() throws IOException {

		// reading diabetes allergy eliminated list
		reader = new ExcelReadWrite(input_path);
		// String toAdd = reader.getCellData("To_add", 0, 0);
		int toAddSize = reader.getRowCount("diabetic_ToAdd");
		System.out.println(" row size " + toAddSize);
		System.out.println("Reading excel ");
		List<String> to_AddList = new ArrayList<String>();
		for (int d = 1; d <= toAddSize; d++) {
			String toAdd_data = reader.getCellData("diabetic_ToAdd", d, 0);
//			System.out.println(diabetes_data);
			to_AddList.add(toAdd_data);
//			System.out.println(diabetes_data);

		}
		return to_AddList;
	}
}
