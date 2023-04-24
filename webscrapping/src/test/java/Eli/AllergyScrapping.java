package Eli;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import commonUtilities.common_Utilities;

public class AllergyScrapping {
	List<String> allergyList;
	List<String> filterList;
	String path = "C:\\Users\\Reka\\eclipse-workspace\\webscrapping\\src\\test\\resources\\recipes_final_s.xlsx";
	ExcelReadWrite reader = new ExcelReadWrite(path);
	common_Utilities common = new common_Utilities();

	public void allergy() throws IOException {

	        // reading diabetes allergy  eliminated list		
			String allergy = reader.getCellData("allergy_eliminated", 0, 0);
			int allergybrowSize = reader.getRowCount("allergy_eliminated");
			System.out.println(" row size " + allergybrowSize);
			System.out.println("Reading excel ");
			allergyList = new ArrayList<String>();
			for (int d = 1; d <= allergybrowSize; d++) {
				String allergy_data = reader.getCellData("allergy_eliminated", d, 0);
//				System.out.println(diabetes_data);
				allergyList.add(allergy_data); 
//				System.out.println(diabetes_data);
			}
			
						// reading  filtered Recpies list		
						String filtered = reader.getCellData("filtered_Recp", 0, 0);
						int fil_RecpSize = reader.getRowCount("filtered_Recp");
						System.out.println(" row size " + fil_RecpSize);
						System.out.println("Reading excel ");
						filterList = new ArrayList<String>();
						for (int d = 1; d <= fil_RecpSize; d++) {
							String filter_data = reader.getCellData("filtered_Recp", d, 2);
							filterList.add(filter_data); 
							System.out.println(filter_data);
						}		
			
			boolean isContainAllergyItems = common.hasAllergyLists(allergyList, filterList);
			
			if(isContainAllergyItems) {
				System.out.println(" Recipe is not recomeneded as it has Ingredients from allergy List");					
			}			
			else {
				common.writeDataToExcel(null, "alergyList");
				
				System.out.println(" Recipe need to be printed");	
			}
	}
}
