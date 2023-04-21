package testCases;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import java.util.ArrayList;
import java.time.Duration;
import java.util.concurrent.TimeUnit;
import javax.imageio.ImageIO;
import java.util.List;
import java.awt.Robot;
import java.awt.Toolkit;
import java.io.File;
import java.util.Scanner;
import java.awt.AWTException;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.image.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import io.github.bonigarcia.wdm.WebDriverManager;
import utilities.ConfigReader;
import java.util.Map;
import org.testng.annotations.Parameters;
public class VeganHypertensionScraping extends BaseClass{
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
	
//  ********************************** LAUNCH BROWSER AND OPEN PAGE  ********************************** 	
	@Test(priority=1)
	public void hypertensionCategory() throws InterruptedException, IOException, InvalidFormatException {
		System.out.println("Opening tarladalal website ");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		WebElement search=driver.findElement(By.xpath("//div[@id='search']/input[@id='ctl00_txtsearch']"));
		search.sendKeys("vegan recipes for high blood pressure"+Keys.ENTER);
	}

// *******************   READING AND WRITING DATA INTO EXCEL 	***************************
		@Test(priority=2)
		public void readwriteFromExcel() throws InvalidFormatException, IOException, InterruptedException, AWTException {
			List<String> recipeIDList = new ArrayList<String>();
			List<String> recipeIngredientList = new ArrayList<String>();
			List<String> recipeNutritionList = new ArrayList<String>();

			//ArrayList<String> eliminatedArray = new ArrayList<>();
			List<String> eliminatedArray = new ArrayList<String>();
			List<String> filteredKeywords = new ArrayList<String>();
			
		String Excelpath="C:\\Users\\janga\\OneDrive\\Desktop\\Eliminated_List.xlsx";
		ExcelReadWrite reader = new ExcelReadWrite(Excelpath );
		
// ****************************  READ DATA **************************************		
		String hypertension=reader.getCellData("Sheet1", 0, 2);  
		int rowSize=reader.getRowCount("Sheet1");
		System.out.println(" row size "+ rowSize);
		System.out.println("Reading excel ");  

		for(int i=1;i<=rowSize;i++) 
		{
		String data=reader.getCellData("Sheet1", i, 2);
			System.out.println(data);
			eliminatedArray.add(data);  ///*******************
		}	

//  ************************ CREATE EXCEL SHEET  WITH COLUMN HEADING ***************
		 reader.setCellData("VeganHypertension", 0, 0, "RecipeID");
		 reader.setCellData("VeganHypertension", 0, 1, "RecipeName");
		 reader.setCellData("VeganHypertension", 0, 2, "RecipeCategory");
		 reader.setCellData("VeganHypertension", 0, 3, "FoodCategory");
		 reader.setCellData("VeganHypertension", 0, 4, "PreparationMethod");
		 reader.setCellData("VeganHypertension", 0, 5, "Ingredients");
		 reader.setCellData("VeganHypertension", 0, 6, "CookingTime");
		 reader.setCellData("VeganHypertension", 0, 7, "PreparationTime");
		 reader.setCellData("VeganHypertension", 0, 8, "NutrientValues");
		 reader.setCellData("VeganHypertension", 0, 9, "MorbidCondition");
		 reader.setCellData("VeganHypertension", 0, 10, "RecipeURL");

		 	 
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
					
// *********************** RETRIEVE DATA FOR EACH RECIPE IN EACH PAGE ************************************					
					for(int r=1;r<=noOfRecipePerPage;r++)  
					{
					Thread.sleep(500);
					js.executeScript("window.scrollBy(0,275)", "");
					WebElement recipeTitle=driver.findElement(By.xpath("/html/body/div[2]/form/div[3]/div[2]/div/div[1]/div[2]/div["+r+"]/div[3]/span[1]/a"));
					recipeTitle.click();
					
					//js.executeScript("arguments[0].click()", recipeTitle);
					WebElement recipeTitlehead = driver.findElement(By.xpath("//div[@id='recipehead']//h1//span[@itemprop='name']"));
					String recipename=recipeTitlehead.getText();
					System.out.println(recipename);
					
					//WebElement recipeID = driver.findElement(By.xpath("//span[contains(text(), 'Recipe#')]"));

					String url=driver.getCurrentUrl();			
					System.out.println(driver.getCurrentUrl());
					
					String pageTiltle=driver.getTitle();
					System.out.println(driver.getTitle());
					
					js.executeScript("window.scrollBy(0,2000)", "");
					WebElement cookTime = driver.findElement(By.xpath("//time[@itemprop=\"cookTime\"]"));			
					String cook_time=cookTime.getText();
					
					WebElement prepTime = driver.findElement(By.xpath("//time[@itemprop=\"prepTime\"]"));			
					String prep_time=prepTime.getText();
				
					WebElement recipeMethod = driver.findElement(By.xpath("//*[@id='ctl00_cntrightpanel_pnlRcpMethod']")); 								
					String prepmethod=recipeMethod.getText();
					
					WebElement recipeNutrition = driver.findElement(By.xpath("//div[@id=\"accompaniments\"]"));
					String Nutritionlist=recipeNutrition.getText();
							
					WebElement recipeIngredients = driver.findElement(By.xpath("//*[@id='rcpinglist']")); 								
					String ingredientslist=recipeIngredients.getText();
					
					driver.navigate().back();
					
//    *************** WRITING DATA INTO EXCEL ***********************************		
					//js.executeScript("arguments[0].click()", recipeTitle);
					// reader.setCellData("VeganHypertension", 0, 0, "RecipeID");
					 reader.setCellData("VeganHypertension", r, 1, recipename);
					 reader.setCellData("VeganHypertension", r, 2, "");
					 reader.setCellData("VeganHypertension", r, 3, "Vegetarian");
					 reader.setCellData("VeganHypertension", r, 4, prepmethod);
					 reader.setCellData("VeganHypertension", r, 5, ingredientslist);
					 reader.setCellData("VeganHypertension", r, 6, cook_time);
					 reader.setCellData("VeganHypertension", r, 7, prep_time);
					 reader.setCellData("VeganHypertension", r, 8, Nutritionlist);
					 reader.setCellData("VeganHypertension", r, 9, "Hypertension");
					 reader.setCellData("VeganHypertension", r, 10, url);

	
			int i=1;
 			 for (String eachRecipeNutritionList : recipeNutritionList ) {
 				eachRecipeNutri=eachRecipeNutritionList;
 				//Join(recipeNutritionList, ",");
 				 // writing the data in excel sheet	
 				  // Join(arr, ",")

 				reader.setCellData("VeganHypertension", i, 8, eachRecipeNutri);
 				i++;
 				System.out.println(eachRecipeNutritionList);
 				eachRecipeNutri=null;
 			 		}
 			int m=1;
			 for (String eachrecipeIngredientList : recipeIngredientList ) {
				eachRecipeIng=eachrecipeIngredientList;
				 // writing the data in excel sheet	
				reader.setCellData("VeganHypertension", m, 8, eachRecipeIng);
				m++;
				System.out.println(eachrecipeIngredientList);
				eachRecipeIng=null;
			 		}
			 
			 int k=1;
			 for (String eachIDList : recipeIDList ) {
				 eachRecipeID=eachIDList;
				 // writing the data in excel sheet	
				reader.setCellData("VeganHypertension", k, 0, eachRecipeID);
				k++;
				System.out.println(eachIDList);
				eachRecipeID=null;
			 }
		
			 if(recipeIngredientList != eliminatedArray)
					  filteredKeywords= recipeIngredientList;		 
			 			System.out.println((filteredKeywords));  
				}
			}
		}
}
	 
	 
// int k=1;
// for (String eachMethodList : recipeMethodList ) {
//	 eachRecipeMethod=eachMethodList;
//	 // writing the data in excel sheet	
//	 reader.setCellData("Sheet2", k, 2, eachRecipeMethod);
//	k++;
//	System.out.println(eachMethodList);
//	eachRecipeMethod=null;
// }