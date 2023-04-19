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
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import io.github.bonigarcia.wdm.WebDriverManager;
import utilities.ConfigReader;
import java.util.List;
import java.util.Map;
import org.testng.annotations.Parameters;
public class TarladalalWebScrapping extends BaseClass{
	//public static WebDriver driver;
	List<WebElement> joblinks;
	List<WebElement> recipeTitle ;
	String eachRecipeTitle;
	List<WebElement> recipeCookingTime ;
	String eachRecipecookTime;
	String alljobcards;
	String allCompanyNames;
	String allLocation;
	List<WebElement> companyName;
	List<WebElement> companyLocation;	
	String eachcompany;
	String eachLocation;
	
//	@BeforeTest
//	public void beforeTest() {
//		WebDriverManager.chromedriver().setup();
//		ChromeOptions chromeOptions = new ChromeOptions();
//		chromeOptions.setPageLoadStrategy(PageLoadStrategy.NORMAL);
//		chromeOptions.setAcceptInsecureCerts(true);
//		chromeOptions.setScriptTimeout(Duration.ofSeconds(30));
//		chromeOptions.setPageLoadTimeout(Duration.ofMillis(30000));
//		chromeOptions.setImplicitWaitTimeout(Duration.ofSeconds(20));
//		chromeOptions.addArguments("--remote-allow-origins=*");
//		driver = new ChromeDriver(chromeOptions);
//		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
//		//driver.get("www.tarladalal.com");
//	}

//	@AfterTest
//	public void tearDown() {
//		driver.quit(); // ************************
//	}

	@Test(priority=1)
	public void jobSearchTestCase() throws InterruptedException, IOException, InvalidFormatException {
//		List<String> companyLocationList = new ArrayList<String>();	
//		List<String> jobCardList = new ArrayList<String>();
//		List<String> companyNameList= new ArrayList<String>();
//		FileWriter writer;
		//driver.get("www.tarladalal.com");
		System.out.println("Opening tarladalal website ");
		driver.manage().window().maximize();
		
		WebElement search=driver.findElement(By.xpath("//div[@id='search']/input[@id='ctl00_txtsearch']"));
		search.sendKeys("vegan diabetic"+Keys.ENTER);
//		WebElement clickSearch=driver.findElement(By.xpath("//div[@id='search']/input[@type='submit']"));
//		clickSearch.submit();
		
//		driver.findElement(By.xpath("//div[@id='ctl00_MenuNavigation_EnglishMenu']//ul/li[1]/a")).click();
//		driver.findElement(By.xpath("https://www.tarladalal.com/RecipeCategories.aspx?focus=health")).click();
//		driver.findElement(By.xpath("//*[@id='nav']/li[1]/ul/li[2]/ul/li[2]/a")).click();
//		health =//*[@id="nav"]/li[1]/ul/li[2]/a
	}
		
		@Test(priority=2)
		public void readFromExcel() throws InvalidFormatException, IOException {
			List<String> recipeTitleList = new ArrayList<String>();
			List<String> recipeTimeList = new ArrayList<String>();
		ConfigReader readConfig=new ConfigReader();
		//String Excelpath = readConfig.getexcelfilepath();
		String Excelpath="C:\\Users\\Reka\\Desktop\\scraping doc.xlsx";
		//ExcelReader reader = new ExcelReader( );
		ExcelReadWrite reader = new ExcelReadWrite(Excelpath );
		String diabetes=reader.getCellData("Sheet1", 0, 0);  
		int rowSize=reader.getRowCount("Sheet1");
		System.out.println(" row size s"+ rowSize);
		//String diabetes = testData.//.get("Diabetes"); 
		System.out.println("Reading excel ");
//		List<Map<String, String>> testData=reader.getData(Excelpath,"Sheet1");
//		String diabetes=testData.get(rowNumber).get("Diabetes"); // Column heading//.get(rownum).get("Diabetes"); //COLUMN HEADING
	
		
		 reader.setCellData("Sheet2", 0, 0, "Title");
		 reader.setCellData("Sheet2", 0, 1, "RecipeCookTime");
//		 reader.setCellData("Sheet1", 0, 2, "CompanyLocation");
		for(int i=1;i<=rowSize;i++) {
		String data=reader.getCellData("Sheet1", i, 0);
			System.out.println(data);	
		}
		
		List<WebElement> pagination = driver.findElements(By.xpath("//div[@id='maincontent']//div[2]//a[@class='respglink']"));
		int pgSize = pagination.size();
		System.out.println(" pagination size = " + pgSize);

			for (int j = 1; j <= pgSize; j++) 
			{
				  if (j >1) {
					//Thread.sleep(2000);
					WebElement pagei = driver.findElement(By.xpath("//div[@id='maincontent']//div[2]//a[@class='respglink' and text()='"+j+"']"));
					pagei.click();
				  }	
				    recipeTitle = driver.findElements(By.xpath("//div[@id='maincontent']//span[@class='rcc_recipename']['+j+']"));
					for (WebElement eachTitle : recipeTitle) {
						recipeTitleList.add(eachTitle.getText());			
					}
					recipeCookingTime = driver.findElements(By.xpath("//*[@id=\"ctl00_cntrightpanel_pnlRecipeScale\"]/section/p[2]/text()[2]['+j+']"));
					for (WebElement eachTime : recipeCookingTime) {
						recipeTimeList.add(eachTime.getText());			
					}				
					
			}
						
			int i=1;
 			 for (String eachRecipeList : recipeTitleList ) {
 				eachRecipeTitle=eachRecipeList;
 				 // writing the data in excel sheet	
 				reader.setCellData("Sheet2", i, 0, eachRecipeTitle);
 				i++;
 				System.out.println(eachRecipeList);
 				eachRecipeTitle=null;
 			 }
 			int j=1;
			 for (String eachIDList : recipeTimeList ) {
				 eachRecipecookTime=eachIDList;
				 // writing the data in excel sheet	
				reader.setCellData("Sheet2", j, 1, eachRecipecookTime);
				i++;
				System.out.println(eachIDList);
				eachRecipecookTime=null;
			 }
			
		}
}
