package ScrapTestCases;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.testng.annotations.Test;
import commonUtilities.common_Utilities;
import utilities.ExcelReader;

public class AllergyScrapping {
	FileOutputStream outputStream;
	List<String> allergyList;
	List<String> filterList;
	List<String> allergyRecipeList;
	String finalpath = "C:\\Users\\Reka\\eclipse-workspace\\webscrapping\\src\\test\\resources\\ScrapeData\\Diabetic_Eliminated_Passed_Recipe.xlsx";
	String path = "C:\\Users\\Reka\\eclipse-workspace\\webscrapping\\src\\test\\resources\\ScrapeData\\Diabetic_Input.xlsx";
	//String 
	String finalpath_New = "C:\\Users\\Reka\\eclipse-workspace\\webscrapping\\src\\test\\resources\\ScrapeData\\allergy_diabetic.xlsx";

	ExcelReadWrite reader ;
	ExcelReader read;
	common_Utilities common = new common_Utilities();
	int noOfRows;
	String[][] data ;
	int noOfColumns;
	int d;
	String filter_data;
	@Test
	public void allergy() throws IOException {
		    List<String[][]> allergyFilteredList = null ;
	        // reading diabetes allergy  eliminated list	
		    reader = new ExcelReadWrite(path);
			String allergy = reader.getCellData("allergy_eliminated", 0, 0);
			int allergyrowSize = reader.getRowCount("allergy_eliminated");
			System.out.println(" row size " + allergyrowSize);
			System.out.println("Reading excel ");
			allergyList = new ArrayList<String>();
			for (int d = 1; d <= allergyrowSize; d++) {
				String allergy_data = reader.getCellData("allergy_eliminated", d, 0);
//				System.out.println(diabetes_data);
				allergyList.add(allergy_data); 
//				System.out.println(diabetes_data);
			}
					// reading  filtered Recpies list
		            	reader = new ExcelReadWrite(finalpath);
//						String filtered = reader.getCellData("filtered_Recp", 0, 0);
						int fil_RecpSize = reader.getRowCount("diabetic");
						System.out.println(" row size " + fil_RecpSize);
						System.out.println("Reading excel ");
						filterList = new ArrayList<String>();						
			              	for(int a = 1; a <= fil_RecpSize; a++) {
							filter_data = reader.getCellData("diabetic", a, 3);
//							String[] recipeData= {recipe_id, reciepename, title,RecpieIngredients, prep_time,cook_time ,     
//									 method,Nutritionlist,url};
//							filterList.add(filter_data); 
							boolean isContainAllergyItems = common.hasAllergyOrRecommendedLists(allergyList, filter_data);
							reader = new ExcelReadWrite(finalpath_New);
//							reader.setCellDataColouring("filtered_Recp",d,2,"allergy item",isContainAllergyItems);
							
							if(isContainAllergyItems) {
								//System.out.println("An allergy item");
								//System.out.println(" Recipe is not recomeneded as it has Ingredients from allergy List");
								reader.setCellData("diabetic", a, 8,"allergy item",true);
								//System.out.println(a);
							}			
							else {
								//System.out.println("Not an allergy item");
								reader.setCellData("diabetic", a, 8,"Not an allergy item",false);
								//System.out.println(a);
							}							
//							System.out.println(filter_data);
				}		
			              	
			              	List<String> addList = getAddList();
			              	reader = new ExcelReadWrite(finalpath);
			              	for(int a1 = 1; a1 <= fil_RecpSize; a1++) {
								filter_data = reader.getCellData("diabetic", a1, 3);
//								String[] recipeData= {recipe_id, reciepename, title,RecpieIngredients, prep_time,cook_time ,     
//										 method,Nutritionlist,url};
//								filterList.add(filter_data); 
								boolean isRecommendedItems = common.hasAllergyOrRecommendedLists(addList, filter_data);
								reader = new ExcelReadWrite(finalpath_New);
//								reader.setCellDataColouring("filtered_Recp",d,2,"allergy item",isContainAllergyItems);
								
								if(isRecommendedItems) {
									//System.out.println("Recomeneded Item");
									reader.setCellData("diabetic", a1, 9,"Recomeneded item",false);
									//System.out.println(a1);
								}			
								else {
									//System.out.println("Not a Recomeneded Item");
									reader.setCellData("diabetic", a1, 9,"Not Recomeneded item",true);
									//System.out.println(a1);
								}							
//								System.out.println(filter_data);
					}	
     	}	
	
	public List<String> getAddList() throws IOException {

        // reading diabetes allergy  eliminated list
	    reader = new ExcelReadWrite(path);
		//String toAdd = reader.getCellData("To_add", 0, 0);
		int toAddSize = reader.getRowCount("To_add");
		System.out.println(" row size " + toAddSize);
		System.out.println("Reading excel ");
		List<String> to_AddList = new ArrayList<String>();
		for (int d = 1; d <= toAddSize; d++) {
			String toAdd_data = reader.getCellData("To_add", d, 0);
//			System.out.println(diabetes_data);
			to_AddList.add(toAdd_data); 
//			System.out.println(diabetes_data);

		}
						
	return to_AddList;
}
}
