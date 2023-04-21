package testCases;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import utilities.ConfigReader;

public class JainDiabetic extends BaseClass{

	//public static WebDriver driver;
	// ***************************************  CLASS LEVEL VARIABLES	***********************************
		List<WebElement> recipeTitle ;
		String eachRecipeTitle;
		List<WebElement> recipeCookingTime ;
		String eachRecipecookTime;
		List<WebElement> recipeID ;
		String eachRecipeID;
		List<WebElement> recipeMethod ;
		String eachRecipeMethod;
		List<WebElement> recipeNutrition ;
		String eachRecipeNutrition;
		
	//  ********************************** LAUNCH BROWSER AND OPEN PAGE  ********************************** 	
		@Test(priority=1)
		public void jobSearchTestCase() throws InterruptedException, IOException, InvalidFormatException {
			System.out.println("Opening tarladalal website ");
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
			
			WebElement search=driver.findElement(By.xpath("//div[@id='search']/input[@id='ctl00_txtsearch']"));
			search.sendKeys("Jain diabetic"+Keys.ENTER);
		}

	// *******************   READING AND WRITING DATA INTO EXCEL 	***************************
			@Test(priority=2)
			public void readFromExcel() throws InvalidFormatException, IOException, InterruptedException {
				List<String> recipeTitleList = new ArrayList<String>();
				List<String> recipeIDList = new ArrayList<String>();
				List<String> recipeTimeList = new ArrayList<String>();
				List<String> recipeMethodList = new ArrayList<String>();
				List<String> recipeNutritionList = new ArrayList<String>();
			ConfigReader readConfig=new ConfigReader();
			//String Excelpath = readConfig.getexcelfilepath();
			String Excelpath="C:\\Users\\Reka\\Downloads\\Telegram Desktop\\Eliminated_List.xlsx";
			//ExcelReader reader = new ExcelReader( );
			ExcelReadWrite reader = new ExcelReadWrite(Excelpath );
			
	// ****************************  READ DATA **************************************		
			String diabetes=reader.getCellData("Sheet1", 0, 0);  
			int rowSize=reader.getRowCount("Sheet1");
			System.out.println(" row size "+ rowSize);
			System.out.println("Reading excel ");  

			for(int i=1;i<=rowSize;i++) 
			{
			String data=reader.getCellData("Sheet1", i, 0);
				System.out.println(data);	
			}	

	//  ************************ CREATE EXCEL SHEET  WITH COLUMN HEADING ***************
			 reader.setCellData("Sheet4", 0, 0, "Title");
			 reader.setCellData("Sheet4", 0, 1, "RecipeCookTime");
			 reader.setCellData("Sheet4", 0, 0, "url");
			 reader.setCellData("Sheet4", 0, 1, "Title");
			 reader.setCellData("Sheet4", 0, 2, "RecipeCookTime");
			 reader.setCellData("Sheet4", 0, 3, "RecipePrepTime");
			 reader.setCellData("Sheet4", 0, 4, "method");
			 reader.setCellData("Sheet4", 0, 5, "pagetitle");
			 reader.setCellData("Sheet4", 0, 6, "nutritionValues");
			 reader.setCellData("Sheet4", 0, 7, "MorbidCndition");
			 	 
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
	// *********************** RETRIEVE DATA FOR EACH RECIPE IN EACH PAGE ************************************					
						for(int r=1;r<=noOfRecipePerPage;r++)  
						{
						Thread.sleep(500);
						js.executeScript("window.scrollBy(0,250)", "");
						WebElement recipeTitle=driver.findElement(By.xpath("//div[@id='maincontent']//span[@class='rcc_recipename']["+r+"]"));  //['+j+']   ['"+r+"']  '"+j+"'
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
						String morbid_condition=morbidCondition.getText();
						
						WebElement recipeMethod = driver.findElement(By.xpath("//ol[@itemprop='recipeInstructions']/li/span	")); //ol[@itemprop=\"recipeInstructions\"]/li/span								
						String method=recipeMethod.getText();
						
						int rowsize=driver.findElements(By.xpath("//table[@id='rcpnutrients']//tr")).size();
						System.out.println(rowsize);
						for(int i=1;i<=rowsize;i++) 
						{
						recipeNutrition =driver.findElements(By.xpath("//table[@id='rcpnutrients']//tr['+i+']"));
						for (WebElement eachNutrition : recipeNutrition)
						{
						recipeNutritionList.add(eachNutrition.getText());
						}
						}
						driver.navigate().back();
						//Thread.sleep(1000);
						
//   *************** WRITING DATA INTO EXCEL ***********************************		
						//js.executeScript("arguments[0].click()", recipeTitle);
						reader.setCellData("Sheet4", r, 0, url);
						reader.setCellData("Sheet4", r, 1, title);
						reader.setCellData("Sheet4", r, 2, cook_time);
						reader.setCellData("Sheet4", r, 3, prep_time);
						reader.setCellData("Sheet4", r, 4, method);
						reader.setCellData("Sheet4", r, 5, pageTiltle);	
						reader.setCellData("Sheet4", r, 7, morbid_condition);	
					
				int i=1;
	 			 for (String eachRecipeNutritionList : recipeNutritionList ) {
	 				eachRecipeTitle=eachRecipeNutritionList;
	 				 // writing the data in excel sheet	
	 				reader.setCellData("Sheet4", i, 6, eachRecipeTitle);
	 				i++;
	 				System.out.println(eachRecipeNutritionList);
	 				eachRecipeTitle=null;
	 			 		}
	 			 
					}
			}
		}
}
