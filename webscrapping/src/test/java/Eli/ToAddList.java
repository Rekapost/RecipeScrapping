
package Eli;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import commonUtilities.common_Utilities;

public class ToAddList {
	
		List<String> to_AddList;
		List<String> filterList;
		String path = "C:\\Users\\Reka\\eclipse-workspace\\webscrapping\\src\\test\\resources\\recipes_final_s.xlsx";
		ExcelReadWrite reader = new ExcelReadWrite(path);
		common_Utilities common = new common_Utilities();

		public void allergy() throws IOException {

		        // reading diabetes allergy  eliminated list		
				String toAdd = reader.getCellData("To_add", 0, 0);
				int toAddSize = reader.getRowCount("To_add");
				System.out.println(" row size " + toAddSize);
				System.out.println("Reading excel ");
				to_AddList = new ArrayList<String>();
				for (int d = 1; d <= toAddSize; d++) {
					String toAdd_data = reader.getCellData("To_add", d, 0);
//					System.out.println(diabetes_data);
					to_AddList.add(toAdd_data); 
//					System.out.println(diabetes_data);
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
				
				boolean isContainADDItems = common.hasToAddLists(filterList, to_AddList);
				
				if(isContainADDItems) {
					System.out.println(" Recipe is  recomeneded as it has Ingredients from ADD List");					
					common.writeDataToExcel(null , "ToAddList" );
				}			
				else {				
					System.out.println(" Recipe need to be printed");	
				}
		}


}
