package ScrapTestCases;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import commonUtilities.common_Utilities;
import org.testng.annotations.Test;

public class ToAddList {
	FileOutputStream outputStream;
		List<String> to_AddList;
		List<String> filterList;
		String ToAdddata;
		ExcelReadWrite reader;
		common_Utilities common = new common_Utilities();
		String finalpath = "C:\\Users\\Reka\\eclipse-workspace\\webscrapping\\src\\test\\resources\\ScrapeData\\recipes_final.xlsx";
		String path = "C:\\Users\\Reka\\eclipse-workspace\\webscrapping\\src\\test\\resources\\ScrapeData\\Diabetic_Input.xlsx";
		int d;
		
		@Test
		public void allergy() throws IOException {

		        // reading diabetes allergy  eliminated list
			    reader = new ExcelReadWrite(path);
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
				            reader = new ExcelReadWrite(finalpath);
							//String filtered = reader.getCellData("filtered_Recp", 0, 0);
							int fil_RecpSize = reader.getRowCount("filtered_Recp");
							System.out.println(" row size " + fil_RecpSize);
							System.out.println("Reading excel ");
							filterList = new ArrayList<String>();
							for ( d = 1; d <= fil_RecpSize; d++) {
								ToAdddata = reader.getCellData("filtered_Recp", d, 2);
								boolean isContainAllergyItems = common.hasToAddLists(to_AddList, ToAdddata);
								
//								reader.setCellDataColouring("filtered_Recp",d,2,"allergy item",isContainAllergyItems);
								
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
//								System.out.println(filter_data);
					}						
	     	}										
								
	/*							
								filterList.add(ToAdddata); 
								System.out.println(ToAdddata);
							}		
				
				boolean isContainADDItems = common.hasToAddLists(filterList, to_AddList);
				
				if(isContainADDItems) {
					System.out.println(" Recipe is  recomeneded as it has Ingredients from ADD List");					
					reader.setCellDataColouring(finalpath, d, 2, ToAdddata, isContainADDItems);			
				}			
				else {				
					System.out.println(" Recipe need to be printed");	
				}
	*/
		
}
