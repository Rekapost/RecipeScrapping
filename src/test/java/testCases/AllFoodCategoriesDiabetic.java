package testCases;

import java.awt.AWTException;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import utilities.ConfigReader;

public class AllFoodCategoriesDiabetic extends BaseClass{
	List<WebElement> recipeTitle ;
	String eachRecipeNutri;
	List<WebElement> recipeCookingTime ;
	String eachRecipecookTime;
	List<WebElement> recipeID ;
	String eachRecipeID;
	List<WebElement> recipeMethod ;
	String eachRecipeMethod;
	List<WebElement> recipeNutrition ;
	String eachRecipeNutrition;
	List<WebElement> recipeIngredients ;
	String eachRecipeIng;
	String nutri;
	int noOfRows;
	int i;
	
	@DataProvider(name="loginData")
	public String[][] getExcelData() throws IOException
	{		
		String path="C:\\Users\\janga\\eclipse-workspace\\webscrapping\\src\\test\\resources\\Eliminated_List2.xlsx";
		ExcelReadWrite reader = new ExcelReadWrite(path );
		noOfRows = reader.getRowCount("Sheet5");
	    int noOfColumns = reader.getCellCount("Sheet5", 1); //1 is rownumber
	    
	    String[][] dataTable = new String[noOfRows][noOfColumns];
	    for (int m = 1; m <=noOfRows; m++) {  
	    	// 0 row is header , so i=1
	        for (int j = 0; j < noOfColumns; j++) {   
	        	  //j=0 ,<  ; j=1 ,<=
	            dataTable[m-1][j] = reader.getCellData("Sheet5",m,j);   //0,0= 1,0
	        }
	    }
	    return dataTable;
	}	

	@Test (dataProvider = "loginData")
	public void loginWithInput(String searchinput) throws IOException, InterruptedException
	{
		System.out.println("Opening tarladalal website ");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		WebElement search=driver.findElement(By.xpath("//div[@id='search']/input[@id='ctl00_txtsearch']"));
		search.sendKeys(searchinput +Keys.ENTER);		
		
/*	
	  //  ********************************** LAUNCH BROWSER AND OPEN PAGE  ********************************** 	
			@Test(priority=1)
			public void jobSearchTestCase() throws InterruptedException, IOException, InvalidFormatException {
				System.out.println("Opening tarladalal website ");
				driver.manage().window().maximize();
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
				
				WebElement search=driver.findElement(By.xpath("//div[@id='search']/input[@id='ctl00_txtsearch']"));
				search.sendKeys("Vegetarian Diabetic"+Keys.ENTER);
			}

		// *******************   READING AND WRITING DATA INTO EXCEL 	***************************
				@Test(priority=2)
				public void readFromExcel() throws InvalidFormatException, IOException, InterruptedException, AWTException {
		// *******************   READING AND WRITING DATA INTO EXCEL 	***************************
				
*/					List<String> recipeIDList = new ArrayList<String>();
					List<String> recipeNutritionList = new ArrayList<String>();
					List<String> recipeIngredientList = new ArrayList<String>();
					//ArrayList<String> eliminatedArray = new ArrayList<>();
					List<String> eliminatedArray = new ArrayList<String>();
					List<String> filteredKeywords = new ArrayList<String>();
					List<String> allergyArray = new ArrayList<String>();
					
					//String Excelpath = readConfig.getexcelfilepath();
					String Excelpath="C:\\Users\\janga\\eclipse-workspace\\webscrapping\\src\\test\\resources\\Eliminated_List2.xlsx";
					//ExcelReader reader = new ExcelReader( );
					ExcelReadWrite reader = new ExcelReadWrite(Excelpath );
				
		// ****************************  READ DIABETES DATA **************************************		
/*				String diabetes=reader.getCellData("Sheet1", 0, 0);  
				int rowSize=reader.getRowCount("Sheet1");
				System.out.println(" row size "+ rowSize);
				System.out.println("Reading excel ");  

				for(int i=1;i<=rowSize;i++) 
				{
				String diabetes_data=reader.getCellData("Sheet1", i, 0);
					System.out.println(diabetes_data);
					eliminatedArray.add(diabetes_data);  ///*******************
				}	
*/			
		// ************************ READ ALLERGY DATA ***************************
/*				String allergy =reader.getCellData("Sheet1", 0, 4);  
				int allergySize=reader.getRowCount("Sheet1");
				System.out.println(" row size "+ allergySize);
				System.out.println("Reading excel ");  

				for(int i=1;i<=allergySize;i++) 
				{
				String allergy_data=reader.getCellData("Sheet1", i, 4);
					System.out.println(allergy_data);
					allergyArray.add(allergy_data);  ///*******************
				}
*/	
		//  ************************ CREATE EXCEL SHEET  WITH COLUMN HEADING ***************
				
				for(int i=1;i<=noOfRows;i++) {
					
				 reader.setCellData("Sheet", 0, 0, "Recipe ID"); 
				 reader.setCellData("Sheet", 0, 1, "Recipe Name");
				 reader.setCellData("Sheet", 0, 2, "Page Title");
				 reader.setCellData("Sheet", 0, 3, "Vegan Diabetic Food Category");
				 reader.setCellData("Sheet", 0, 4, "Ingredients");
				 reader.setCellData("Sheet", 0, 5, "RecipePrepTime");
				 reader.setCellData("Sheet", 0, 6, "RecipeCookTime");
				 reader.setCellData("Sheet", 0, 7, "Preparation Method");
				 reader.setCellData("Sheet", 0, 8, "Nutrient values");
				 reader.setCellData("Sheet", 0, 9, "Targetted morbid conditions (Diabeties/Hypertension/Hypothyroidism)");
				 reader.setCellData("Sheet", 0, 10, "Recipe URL");
				} 	 
		// *******************  PAGINATION SIZE AND ROW SIZE ****************************		
				List<WebElement> pagination = driver.findElements(By.xpath("//div[@id='maincontent']//div[2]//a[@class='respglink']"));
				int pgSize = pagination.size();
				System.out.println(" pagination size = " + pgSize);
				
				int noOfRecipePerPage=driver.findElements(By.xpath("//div[@id='maincontent']//div[@class='rcc_recipecard']")).size();
				System.out.println("No.of Rows in a page:"+noOfRecipePerPage);

				JavascriptExecutor js = (JavascriptExecutor) driver;
				
		// ********************  NAVIGATING THROUGH EACH PAGE AND IN EACH PAGE CLICK RECIPE AND GET ALL DETAILS ***********
/*					for (int j = 1; j <= pgSize; j++) 
					{
							//  if (j >1) {
							//Thread.sleep(2000);
							//WebElement pagei = driver.findElement(By.xpath("//div[@id='maincontent']//div[2]//a[@class='respglink' and text()='"+j+"']"));
		//   ********************************* CLICK EACH PAGE ******************************					
						    Thread.sleep(1000);   
							WebElement pagei = driver.findElement(By.xpath("//*[@id='cardholder']/div[2]/a['"+j+"']"));
							pagei.click();
							
							recipeID = driver.findElements(By.xpath("//div[@id='maincontent']//div[@class='rcc_recipecard']['+j+']"));
							for (WebElement eachRecipeID : recipeID) {
								recipeIDList.add(eachRecipeID.getAttribute("id"));			
							}
							
*/		// *********************** RETRIEVE DATA FOR EACH RECIPE IN EACH PAGE ************************************					
			
				for(int r=1;r<=pgSize;r++)  
							{
							Thread.sleep(500);
							js.executeScript("window.scrollBy(0,250)", "");
							//WebElement recipeTitle=driver.findElement(By.xpath("//div[@id='maincontent']//span[@class='rcc_recipename']["+r+"]"));  //['+j+']   ['"+r+"']  '"+j+"'
							WebElement recipeTitle=driver.findElement(By.xpath("/html/body/div[2]/form/div[3]/div[2]/div/div[1]/div[2]/div["+r+"]/div[3]/span[1]/a"));
							recipeTitle.click();
							
							//js.executeScript("arguments[0].click()", recipeTitle);
							WebElement recipeTitlehead = driver.findElement(By.xpath("//div[@id='recipehead']//h1//span[@itemprop='name']"));
							String title=recipeTitlehead.getText();
							
							String url=driver.getCurrentUrl();			
							System.out.println(driver.getCurrentUrl());
							
							String pageTiltle=driver.getTitle();
							System.out.println(driver.getTitle());
							
							js.executeScript("window.scrollBy(0,2000)", "");
							WebElement cookTime = driver.findElement(By.xpath("//div[@id='maincontent']//*[@id='ctl00_cntrightpanel_pnlRecipeScale']/section/p[2]/time[@itemprop='prepTime']"));			
							String cook_time=cookTime.getText();
							
							WebElement prepTime = driver.findElement(By.xpath("//div[@id='maincontent']//*[@id='ctl00_cntrightpanel_pnlRecipeScale']/section/p[2]/time[@itemprop='cookTime']"));			
							String prep_time=prepTime.getText();
							
							WebElement morbidCondition  = driver.findElement(By.xpath("//img[@src='images/recipe/diabetic.gif']"));
							String morbid_condition=morbidCondition.getAttribute("src");
							
							WebElement recipeMethod = driver.findElement(By.xpath("//*[@id='ctl00_cntrightpanel_pnlRcpMethod']")); 								
							String method=recipeMethod.getText();
							
//							WebElement recipeCategory = driver.findElement(By.xpath("//*[@id='recipe_tags']/a[5]/span"));
//							String RecpieCategory=recipeCategory.getText();
							
							WebElement recipeNutrition = driver.findElement(By.xpath("//div[@id=\"accompaniments\"]"));
							String Nutritionlist=recipeNutrition.getText();
							
							int Ingredientssize = driver.findElements(By.xpath("//*[@id='rcpinglist']/div/span")).size();
							System.out.println(Ingredientssize);
							for(int l=1;l<=Ingredientssize;l++) 
							{
								recipeIngredients = driver.findElements(By.xpath("//*[@id='rcpinglist']/div/span['+i+']"));
								for (WebElement eachIngredient : recipeIngredients)
								{
								recipeIngredientList.add(eachIngredient.getText());
								}
							}
							
							WebElement ingredients= driver.findElement(By.xpath("//*[@id='rcpinglist']"));
							String RecpieIngredients=ingredients.getText(); 
							
							driver.navigate().back();
							//Thread.sleep(1000);
							
//		    *************** WRITING DATA INTO EXCEL ***********************************		
							//js.executeScript("arguments[0].click()", recipeTitle);			
							reader.setCellData("Sheet", r, 1, title);
							reader.setCellData("Sheet", r, 2, pageTiltle);				
							//reader.setCellData("Sheet5", r, 2, RecpieCategory);
							//reader.setCellData("Sheet5", r, 3, FoodCategory);
							reader.setCellData("Sheet", r, 4,RecpieIngredients );
							reader.setCellData("Sheet", r, 5, prep_time);
							reader.setCellData("Sheet", r, 6, cook_time);
							reader.setCellData("Sheet", r, 7, method);
							reader.setCellData("Sheet", r, 8,Nutritionlist);
							reader.setCellData("Sheet", r, 9, morbid_condition);
							reader.setCellData("Sheet", r, 10, url);
					 
					 int k=1;
					 for (String eachIDList : recipeIDList ) {
						 eachRecipeID=eachIDList;
						 // writing the data in excel sheet	
						reader.setCellData("Sheet"+i, k, 0, eachRecipeID);
						k++;
						System.out.println(eachIDList);
						eachRecipeID=null;
					 }
				
					 //if(recipeIngredientList != eliminatedArray)
					 if(recipeIngredientList .equals(eliminatedArray))
						 //eachRecipeID.
							  filteredKeywords= recipeIngredientList;		 
					 			System.out.println((filteredKeywords));  
						}
				}
	//	}

}
