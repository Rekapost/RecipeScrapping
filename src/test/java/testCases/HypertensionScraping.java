package testCases;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import java.util.ArrayList;
import java.time.Duration;
import java.util.concurrent.TimeUnit;
import java.util.List;
import java.util.Scanner;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import io.github.bonigarcia.wdm.WebDriverManager;
import utilities.ConfigReader;
import java.util.List;
import java.util.Map;
import org.testng.annotations.Parameters;

public class HypertensionScraping extends BaseClass{	
	List<WebElement> recipeTitle ;
	String eachRecipeTitle;
	
	List<WebElement> recipeID ;
	String eachRecipeID;
	
	List<WebElement> recipeCategory ;
	String eachrecipeCategory ;
	
	List<WebElement> foodCategory ;
	String eachfoodCategory ;
	
	List<WebElement> prepMethod ;
	String eachprepMethod ;
	
	List<WebElement> ingredientsList ;
	String eachIngredient;
	
	List<WebElement> preparationTime ;
	String eachPrepTime;
	
	List<WebElement> cookingTime ;
	String eachCookTime;
	
	List<WebElement> recipeNutrition;
	String eachNutrientValue;
	
	List<WebElement> RecipeURLlist;
	String eachRecipeURL;
	
	//Tragttedmrobidcondition

	@Test(priority=1)
	public void jobSearchTestCase() throws InterruptedException, IOException, InvalidFormatException {
		System.out.println("Opening tarladalal website ");
		driver.manage().window().maximize();
		
		Actions action = new Actions(driver);
		//WebElement recipesoption = driver.findElement(By.xpath("//a[@href=\"RecipeCategories.aspx\"]/div"));
		WebElement recipesoption = driver.findElement(By.xpath("//div[contains(text(), 'RECIPES')]"));
		action.moveToElement(recipesoption).build().perform();
		
		//WebElement healthoption = driver.findElement(By.xpath("//a[@href=\"RecipeCategories.aspx?focus=health\"]"));
		
		WebElement healthoption = driver.findElement(By.xpath("//li//a[contains(text(), 'Health')]"));

		//WebElement healthoption = driver.findElement(By.xpath("//ul[@style=\"margin-top: 18px; visibility: hidden;\"]/li[2]/a[@href=\"RecipeCategories.aspx?focus=health\"]"));
		action.moveToElement(healthoption).build().perform();
		Thread.sleep(2000);

		WebElement highbprecipes = driver.findElement(By.xpath("//ul/li/a[@href=\"recipes-for-high-blood-pressure-644\"]"));
		action.moveToElement(highbprecipes).build().perform();
		highbprecipes.click();
	}
		
		@Test(priority=2)
		public void readFromExcel() throws InvalidFormatException, IOException, InterruptedException {
			List<String> recipeTitleList = new ArrayList<String>();
			List<String> recipeIDList = new ArrayList<String>();
			List<String> foodCategoryList = new ArrayList<String>();
			List<String> prepMethodList = new ArrayList<String>();
			List<String> ingredientList = new ArrayList<String>();
			List<String> preparationTimeList = new ArrayList<String>();
			List<String> cookingTimeList = new ArrayList<String>();
			List<String> recipeNutritionList = new ArrayList<String>();
			List<String> recipeURLlist = new ArrayList<String>();

// ****************************  READ DATA **************************************
		String Excelpath="C:\\Users\\janga\\OneDrive\\Desktop\\Eliminated_List.xlsx";
		ExcelReadWrite reader = new ExcelReadWrite(Excelpath );
		String hypertension=reader.getCellData("Sheet1", 0, 2);  
		int rowSize=reader.getRowCount("Sheet1");
		System.out.println(" row size "+ rowSize);
		System.out.println("Reading excel ");  

		for(int i=1;i<=rowSize;i++) {
		String data=reader.getCellData("Sheet1", i, 2);
			System.out.println(data);	
		}	
		
//  ************************ CREATE EXCEL SHEET  COLUMN HEADINGS ***************

		 reader.setCellData("Sheet5", 0, 0, "RecipeTitle");
		 reader.setCellData("Sheet5", 0, 1, "RecipeID");
		 reader.setCellData("Sheet5", 0, 2, "RecipeCategory");
		 reader.setCellData("Sheet5", 0, 3, "FoodCategory");
		 reader.setCellData("Sheet5", 0, 4, "PreparationMethod");
		 reader.setCellData("Sheet5", 0, 5, "Ingredients");
		 reader.setCellData("Sheet5", 0, 6, "PreparationTime");
		 reader.setCellData("Sheet5", 0, 7, "CookingTime");
		 reader.setCellData("Sheet5", 0, 8, "NutrientValues");
		 reader.setCellData("Sheet5", 0, 9, "RecipeURL");
		
// *******************  PAGINATION SIZE AND ROW SIZE ****************************		

		 List<WebElement> pagination = driver.findElements(By.xpath("//div[@id='maincontent']//div[2]//a[@class='respglink']"));
			int pgSize = pagination.size();
			System.out.println(" pagination size = " + pgSize);
			
			int noOfRecipePerPage=driver.findElements(By.xpath("//div[@id='maincontent']//div[@class='rcc_recipecard']")).size();
			System.out.println("No.of Rows in a page:"+noOfRecipePerPage);

			JavascriptExecutor js = (JavascriptExecutor) driver;
			
	// ********************  NAVIGATING THROUGH EACH PAGE AND IN EACH PAGE CLICK RECIPE AND GET ALL DETAILS ***********
				for (int j = 1; j <= pgSize; j++) 
				{
					//  if (j >1) {
					//WebElement pagei = driver.findElement(By.xpath("//div[@id='maincontent']//div[2]//a[@class='respglink' and text()='"+j+"']"));
	//   ********************************* CLICK EACH PAGE ******************************					
					    Thread.sleep(1000);   
						//WebElement pagei = driver.findElement(By.xpath("//*[@id='cardholder']/div[2]/a['"+j+"']"));
						WebElement pagei = driver.findElement(By.xpath("//div[@id='pagination']/a['"+j+"']"));					
						pagei.click();
	// *********************** RETRIEVE DATA FOR EACH RECIPE IN EACH PAGE ************************************					
						for(int r=1;r<=noOfRecipePerPage;r++)
						{
						Thread.sleep(1000);
						js.executeScript("window.scrollBy(0,250)", "");
						WebElement recipeTitle=driver.findElement(By.xpath("//div[@id='maincontent']//span[@class='rcc_recipename']["+r+"]"));
						recipeTitle.click();
						
						//js.executeScript("arguments[0].click()", recipeTitle);
						WebElement recipeTitlehead = driver.findElement(By.xpath("//div[@id='recipehead']//h1//span[@itemprop='name']"));
						String title=recipeTitlehead.getText();
						
						//WebElement foodcatgory = driver.findElement(By.xpath("//img[@src='images/recipe/diabetic.gif']"));
						//System.out.println(foodcatgory.getAttribute("src"));
						
						String url=driver.getCurrentUrl();			
						System.out.println( driver.getCurrentUrl());
						
						String pageTiltle=driver.getTitle();
						System.out.println(driver.getTitle());
						
						js.executeScript("window.scrollBy(0,2000)", "");
						WebElement cookTime = driver.findElement(By.xpath("//div[@id='maincontent']//*[@id='ctl00_cntrightpanel_pnlRecipeScale']/section/p[2]/time[@itemprop='prepTime']"));			
						String cook_time=cookTime.getText();
						
						WebElement prepTime = driver.findElement(By.xpath("//div[@id='maincontent']//*[@id='ctl00_cntrightpanel_pnlRecipeScale']/section/p[2]/time[@itemprop='cookTime']"));			
						String prep_time=prepTime.getText();
						
						WebElement recipeMethod = driver.findElement(By.xpath("//ol[@itemprop='recipeInstructions']/li/span	")); //ol[@itemprop=\"recipeInstructions\"]/li/span								
						String prepmethod=recipeMethod.getText();
						
						int rowsize=driver.findElements(By.xpath("//table[@id='rcpnutrients']//tr")).size();
				          System.out.println(rowsize);
				          for(int i=1;i<=rowsize;i++) {
				          recipeNutrition =driver.findElements(By.xpath("//table[@id='rcpnutrients']//tr['+i+']"));
				          for (WebElement eachNutrition : recipeNutrition) {
				          recipeNutritionList.add(eachNutrition.getText());
				          }
						
						//table[@id="rcpnutrients"]//tr
						driver.navigate().back();
						}
						//Thread.sleep(1000);
//	    *************** WRITING DATA INTO EXCEL ***********************************		
						//js.executeScript("arguments[0].click()", recipeTitle);
						reader.setCellData("Sheet5", r, 0, title);
						reader.setCellData("Sheet5", r, 1, title);
						reader.setCellData("Sheet5", r, 2, cook_time);
						reader.setCellData("Sheet5", r, 3, prep_time);
						reader.setCellData("Sheet5", r, 4, prepmethod);
						reader.setCellData("Sheet5", r, 5, pageTiltle);
						reader.setCellData("Sheet5", r, 6, prep_time);
						reader.setCellData("Sheet5", r, 7, cook_time);
						reader.setCellData("Sheet5", r, 8,recipeNutritionList);
						reader.setCellData("Sheet5", r, 9, url);
						}	
					}
					//  }	
					    
//					  	recipeTitle = driver.findElements(By.xpath("//div[@id='maincontent']//span[@class='rcc_recipename']['+j+']"));
//						for (WebElement eachTitle : recipeTitle) {
//							recipeTitleList.add(eachTitle.getText());			
//						}
						
//						recipeID = driver.findElements(By.xpath("//div[@id='maincontent']//div[@class='rcc_recipecard']['+j+']"));
//						for (WebElement eachRecipeID : recipeID) {
//							recipeIDList.add(eachRecipeID.getAttribute("id"));			
//						}
//						reader.setCellData("Sheet3", 0, 3, "CookTime");
//						reader.setCellData("Sheet3", 0, 4, "PrepTime");
//						reader.setCellData("Sheet3", 0, 5, "Method");		
			
	 			 
	 			int i=1;
	 	        for (String eachRecipeNutritionList : recipeNutritionList ) {
	 	         eachRecipeTitle=eachRecipeNutritionList;
	 	          // writing the data in excel sheet  
	 	         reader.setCellData("Sheet5", i, 6, eachRecipeTitle);
	 	         i++;
	 	         System.out.println(eachRecipeNutritionList);
	 	         eachRecipeTitle=null;
	 	        }
	 			 			 
	 			int j=1;
				 for (String eachIDList : recipeIDList ) {
					 eachRecipeID=eachIDList;
					 // writing the data in excel sheet	
					reader.setCellData("Sheet2", j, 1, eachRecipeID);
					j++;
					System.out.println(eachIDList);
					eachRecipeID=null;
				 }
//				 int k=1;
//				 for (String eachMethodList : recipeMethodList ) {
//					 eachRecipeMethod=eachMethodList;
//					 // writing the data in excel sheet	
//					 reader.setCellData("Sheet2", k, 2, eachRecipeMethod);
//					k++;
//					System.out.println(eachMethodList);
//					eachRecipeMethod=null;
//				 }
//				 int l=1;
//				 for (String eachTimeList : recipeTimeList ) {
//					 eachRecipecookTime=eachTimeList;
//					 // writing the data in excel sheet	
//					reader.setCellData("Sheet2", l, 3, eachRecipecookTime);
//					l++;
//					System.out.println(eachTimeList);
//					eachRecipecookTime=null;
//				 }
				 
				}
	}

