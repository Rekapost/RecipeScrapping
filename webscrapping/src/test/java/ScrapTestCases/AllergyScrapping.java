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
	String finalpath = "C:\\Users\\Reka\\eclipse-workspace\\webscrapping\\src\\test\\resources\\recipes_final.xlsx";
	String path = "C:\\Users\\Reka\\eclipse-workspace\\webscrapping\\src\\test\\resources\\Diabetic_Input.xlsx";
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
						int fil_RecpSize = reader.getRowCount("filtered_Recp");
						System.out.println(" row size " + fil_RecpSize);
						System.out.println("Reading excel ");
						filterList = new ArrayList<String>();						
			              	for(d = 1; d <= fil_RecpSize; d++) {
							filter_data = reader.getCellData("filtered_Recp", d, 2);
//							String[] recipeData= {recipe_id, reciepename, title,RecpieIngredients, prep_time,cook_time ,     
//									 method,Nutritionlist,url};
//							filterList.add(filter_data); 
							boolean isContainAllergyItems = common.hasAllergyLists(allergyList, filter_data);
							
//							reader.setCellDataColouring("filtered_Recp",d,2,"allergy item",isContainAllergyItems);
							
							if(isContainAllergyItems) {
								System.out.println(" Recipe is not recomeneded as it has Ingredients from allergy List");
								reader.fillRedColor("filtered_Recp", d, 8,"allergy item");
								System.out.println(d);
							}			
							else {
								System.out.println("Not an allergy item");
								reader.fillRedColor("filtered_Recp", d, 8,"Not an allergy item");
								System.out.println(d);
							}							
//							System.out.println(filter_data);
				}						
     	}	
		
}
