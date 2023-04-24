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
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

import utilities.ConfigReader;
public class VeganDiabeticWebScrapping extends BaseClass{
	//public static WebDriver driver;
// ***************************************  CLASS LEVEL VARIABLES	***********************************
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
	WebElement prepTime ;
	
//  ********************************** LAUNCH BROWSER AND OPEN PAGE  ********************************** 	
	@Test(priority=1)
	public void jobSearchTestCase() throws InterruptedException, IOException, InvalidFormatException {
		System.out.println("Opening tarladalal website ");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		WebElement search=driver.findElement(By.xpath("//div[@id='search']/input[@id='ctl00_txtsearch']"));
		search.sendKeys("Vegetarian diabetic"+Keys.ENTER);
	}

// *******************   READING AND WRITING DATA INTO EXCEL 	***************************
		@Test(priority=2)
		public void readFromExcel() throws InvalidFormatException, IOException, InterruptedException, AWTException {
			List<String> recipeIDList = new ArrayList<String>();
			List<String> recipeNutritionList = new ArrayList<String>();
			List<String> recipeIngredientList = new ArrayList<String>();
			//ArrayList<String> eliminatedArray = new ArrayList<>();
			List<String> eliminatedArray = new ArrayList<String>();
			List<String> filteredKeywords = new ArrayList<String>();
			List<String> allergyArray = new ArrayList<String>();
			
			ConfigReader readConfig=new ConfigReader();
			//String Excelpath = readConfig.getexcelfilepath();
			String Excelpath="C:\\Users\\Reka\\eclipse-workspace\\webscrapping\\src\\test\\resources\\Eliminated_List.xlsx";
			//ExcelReader reader = new ExcelReader( );
			ExcelReadWrite reader = new ExcelReadWrite(Excelpath );
		
// ****************************  READ DIABETES DATA **************************************		
		String diabetes=reader.getCellData("Sheet1", 0, 0);  
		int rowSize=reader.getRowCount("Sheet1");
		System.out.println(" row size "+ rowSize);
		System.out.println("Reading excel ");  

		for(int i=1;i<=rowSize;i++) 
		{
			String diabetes_data=reader.getCellData("Sheet1", i, 0);
			System.out.println(diabetes_data);
			eliminatedArray.add(diabetes_data);  ///*******************
		}	
		
// ************************ READ ALLERGY DATA ***************************
		String allergy =reader.getCellData("Sheet1", 0, 4);  
		int allergySize=reader.getRowCount("Sheet1");
		System.out.println(" row size "+ allergySize);
		System.out.println("Reading excel ");  
		JavascriptExecutor js = (JavascriptExecutor) driver;
		for(int i=1;i<=allergySize;i++) 
		{
		String allergy_data=reader.getCellData("Sheet1", i, 4);
			System.out.println(allergy_data);
			allergyArray.add(allergy_data);  ///*******************
		}	
//  ************************ CREATE EXCEL SHEET  WITH COLUMN HEADING ***************
		 reader.setCellData("Sheet3", 0, 0, "Recipe ID"); 
		 reader.setCellData("Sheet3", 0, 1, "Recipe Name");
		 reader.setCellData("Sheet3", 0, 2, "Page Title");
		 reader.setCellData("Sheet3", 0, 3, "Vegan Diabetic Food Category");
		 reader.setCellData("Sheet3", 0, 4, "Ingredients");
		 reader.setCellData("Sheet3", 0, 5, "RecipePrepTime");
		 reader.setCellData("Sheet3", 0, 6, "RecipeCookTime");
		 reader.setCellData("Sheet3", 0, 7, "Preparation Method");
		 reader.setCellData("Sheet3", 0, 8, "Nutrient values");
		 reader.setCellData("Sheet3", 0, 9, "Targetted morbid conditions (Diabeties/Hypertension/Hypothyroidism)");
		 reader.setCellData("Sheet3", 0, 10, "Recipe URL");

		 	 
// *******************  PAGINATION SIZE AND ROW SIZE ****************************		
//		List<WebElement> pagination = driver.findElements(By.xpath("//div[@id='maincontent']//div[2]//a[@class='respglink']"));
//		int pgSize = pagination.size();
//		System.out.println(" pagination size = " + pgSize);
		
		List<WebElement> pagelist=driver.findElements(By.xpath("//*[@id='cardholder']/div[2]/a"));
		int pgSize = pagelist.size(); 
		System.out.println(" page size no:"+ pagelist.size());
		   
		int noOfRecipePerPage=driver.findElements(By.xpath("//div[@id='maincontent']//div[@class='rcc_recipecard']")).size();
		System.out.println("No.of Rows in a page:"+noOfRecipePerPage);

		
// ********************  NAVIGATING THROUGH EACH PAGE AND IN EACH PAGE CLICK RECIPE AND GET ALL DETAILS ***********
//			for (int j = 2; j <= pgSize; j++) 
//			{		
				
//   ********************************* CLICK EACH PAGE ******************************					
				    Thread.sleep(1000);   
//					WebElement pagei = driver.findElement(By.xpath("//*[@id='cardholder']/div[2]/a['"+j+"']"));
//					pagei.click();
					
					   List<WebElement> pagelist1=driver.findElements(By.xpath("//*[@id='cardholder']/div[2]/a"));
					   System.out.println(" page size no:"+ pagelist1.size());
					   int j=2;
					   for (WebElement eachpage : pagelist) {   
				                        ////*[@id='cardholder']/div[2]/a['"+j+"']
						   String tr =eachpage.getText();
						   Integer z=Integer.valueOf(tr);  
 							   WebElement pagei=driver.findElement(By.xpath("//div[@id='maincontent']//div[2]//a[@class='respglink' and text()='"+tr+"']"));   ///html/body/div[2]/form/div[3]/div[2]/div/div[1]/div[1]/div[2]/a['"+j+"']
				
 							   System.out.println("test1"+pagei.getText());
							   Thread.sleep(1000);
							   pagei.click();
							   
						   }				
					   j++;
					   }}
//						   Actions action =new Actions(driver);
//						   action.moveToElement(pagei).click().perform();

//						    recipeID = driver.findElements(By.xpath("//div[@id='maincontent']//div[@class='rcc_recipecard']['+j+']"));
//							for (WebElement eachRecipeID : recipeID) {
//								recipeIDList.add(eachRecipeID.getAttribute("id"));			
//							}					
//					
//// *********************** RETRIEVE DATA FOR EACH RECIPE IN EACH PAGE ************************************					
//					for(int r=1;r<=1;r++)  
//					{
//					Thread.sleep(500);
//					js.executeScript("window.scrollBy(0,250)", "");
//					//WebElement recipeTitle=driver.findElement(By.xpath("//div[@id='maincontent']//span[@class='rcc_recipename']["+r+"]"));  //['+j+']   ['"+r+"']  '"+j+"'
//					WebElement recipeTitle=driver.findElement(By.xpath("/html/body/div[2]/form/div[3]/div[2]/div/div[1]/div[2]/div["+r+"]/div[3]/span[1]/a"));
//					//WebElement recipeTitle=driver.findElement(By.xpath("//div[@id='maincontent']//div[@class='rcc_rcpcore']/span[@class='rcc_recipename']/a["+r+"]"));
//					recipeTitle.click();
//					
////*********************** RETRIEVE DATA FOR EACH RECIPE IN EACH PAGE ************************************					
//
//					//js.executeScript("arguments[0].click()", recipeTitle);
//					WebElement recipeTitlehead = driver.findElement(By.xpath("//div[@id='recipehead']//h1//span[@itemprop='name']"));
//					String title=recipeTitlehead.getText();
//					
//					String url=driver.getCurrentUrl();			
//					System.out.println(driver.getCurrentUrl());
//					
//					String pageTiltle=driver.getTitle();
//					System.out.println(driver.getTitle());
//					
//					js.executeScript("window.scrollBy(0,2000)", "");
//					WebElement cookTime = driver.findElement(By.xpath("//div[@id='maincontent']//*[@id='ctl00_cntrightpanel_pnlRecipeScale']/section/p[2]/time[@itemprop='prepTime']"));			
//					String cook_time=cookTime.getText();
//					
////					try {
////					prepTime = driver.findElement(By.xpath("//div[@id='maincontent']//*[@id='ctl00_cntrightpanel_pnlRecipeScale']/section/p[2]/time[@itemprop='cookTime']"));			
////					String prep_time=prepTime.getText();
////					}catch(Exception e) {
////						System.out.println(" not present ");
////					}
//					
//					WebElement morbidCondition  = driver.findElement(By.xpath("//img[@src='images/recipe/diabetic.gif']"));
//					String morbid_condition=morbidCondition.getAttribute("src");
//					
//					WebElement recipeMethod = driver.findElement(By.xpath("//*[@id='ctl00_cntrightpanel_pnlRcpMethod']")); 								
//					String method=recipeMethod.getText();
//					
////					WebElement recipeCategory = driver.findElement(By.xpath("//*[@id='recipe_tags']/a[5]/span"));
////					String RecpieCategory=recipeCategory.getText();
//					
//					WebElement recipeNutrition = driver.findElement(By.xpath("//div[@id=\"accompaniments\"]"));
//					String Nutritionlist=recipeNutrition.getText();
//					
//					int Ingredientssize = driver.findElements(By.xpath("//*[@id='rcpinglist']/div/span")).size();
//					System.out.println(Ingredientssize);
//					for(int l=1;l<=Ingredientssize;l++) 
//					{
//						recipeIngredients = driver.findElements(By.xpath("//*[@id='rcpinglist']/div/span['+i+']"));
//						for (WebElement eachIngredient : recipeIngredients)
//						{
//						recipeIngredientList.add(eachIngredient.getText());
//						}
//					}
//					
//					WebElement ingredients= driver.findElement(By.xpath("//*[@id='rcpinglist']"));
//					String RecpieIngredients=ingredients.getText(); 
//					
//					driver.navigate().back();
//					//Thread.sleep(1000);
//					
////    *************** WRITING DATA INTO EXCEL ***********************************		
//					//js.executeScript("arguments[0].click()", recipeTitle);			
//					//reader.setCellData("Sheet3", j, 1, title);
//					//reader.setCellData("Sheet3", j, 2, pageTiltle);				
//					//reader.setCellData("Sheet3", r, 2, RecpieCategory);
//					//reader.setCellData("Sheet3", r, 3, FoodCategory);
//					reader.setCellData("Sheet3", j, 4,RecpieIngredients );
//					//reader.setCellData("Sheet3", j, 5, prep_time);
//					reader.setCellData("Sheet3", j, 6, cook_time);
//					reader.setCellData("Sheet3", j, 7, method);
//					reader.setCellData("Sheet3", j, 8,Nutritionlist);
//					reader.setCellData("Sheet3", j, 9, morbid_condition);
//				//	reader.setCellData("Sheet3", j, 10, url);
//			 
//			 int k=1;
//			 for (String eachIDList : recipeIDList ) {
//				 eachRecipeID=eachIDList;
//				 // writing the data in excel sheet	
//				reader.setCellData("Sheet3", k, 0, eachRecipeID);
//				k++;
//				System.out.println(eachIDList);
//				eachRecipeID=null;
//			 	}
//		
//			 //if(recipeIngredientList != eliminatedArray)
//			 if(recipeIngredientList .equals(eliminatedArray))
//				 //eachRecipeID.
//					  filteredKeywords= recipeIngredientList;		 
//			 			System.out.println((filteredKeywords));  
//				}
//		   }
//		}
//	
//}
