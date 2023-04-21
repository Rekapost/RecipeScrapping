package testCases;
import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.concurrent.TimeUnit;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.io.FileHandler;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import io.github.bonigarcia.wdm.WebDriverManager;
import utilities.ConfigReader;
import utilities.Loggerload;

public class BaseClass  {
	public static WebDriver driver;
	ConfigReader readConfig=new ConfigReader();
	//public  String browser="chrome";
	//public  String BROWSER=readConfig.getBrowserType();
	public  String CHROME_DRIVER="webdriver.chrome.driver";
	public  String APP_URL=readConfig.getApplicationUrl();
	public static Logger logger;
	
	@Parameters("browser")    // passing browser type through testNg.xml file 
	@BeforeClass
	public void setup(String br)  // so passing that parameter browser as br  
	{			
		// LOG4J LOGGER CONFIGURATION
		logger=Logger.getLogger("Indeed ");  // create object for Logger class
		PropertyConfigurator.configure("C:\\Users\\Reka\\eclipse-workspace\\webscrapping\\src\\test\\resources\\log4j2.properties");
		//else if(browser.equalsIgnoreCase("chrome")){
		if(br.equalsIgnoreCase("chrome"))
		{
		WebDriverManager.chromedriver().setup();
		ChromeOptions chromeOptions = new ChromeOptions();
		chromeOptions.setPageLoadStrategy(PageLoadStrategy.NORMAL);
		chromeOptions.setAcceptInsecureCerts(true);
//		chromeOptions.setScriptTimeout(Duration.ofSeconds(30));
//		chromeOptions.setPageLoadTimeout(Duration.ofMillis(30000));
//      chromeOptions.setImplicitWaitTimeout(Duration.ofSeconds(20));
		
		chromeOptions.addArguments("--remote-allow-origins=*");	  
		driver =new ChromeDriver(chromeOptions);			
		}
		
		else if(br.equalsIgnoreCase("firefox")){
			Loggerload.info("Testing on firefox");
			WebDriverManager.firefoxdriver().setup();
			FirefoxOptions firefoxOptions = new FirefoxOptions();
			firefoxOptions.setPageLoadStrategy(PageLoadStrategy.NORMAL);
			firefoxOptions.setAcceptInsecureCerts(true);
			firefoxOptions.setScriptTimeout(Duration.ofSeconds(30));
			firefoxOptions.setPageLoadTimeout(Duration.ofMillis(30000));
			firefoxOptions.setImplicitWaitTimeout(Duration.ofSeconds(20));			  
			driver =new FirefoxDriver(firefoxOptions);				
		}
		 
		 else if (br.equalsIgnoreCase("edge")) {
			    Loggerload.info("Testing on Edge");
			 	WebDriverManager.edgedriver().setup();
				EdgeOptions edgeOptions = new EdgeOptions();
				edgeOptions.setPageLoadStrategy(PageLoadStrategy.NORMAL);
				edgeOptions.setAcceptInsecureCerts(true);
				edgeOptions.setScriptTimeout(Duration.ofSeconds(30));
				edgeOptions.setPageLoadTimeout(Duration.ofMillis(30000));
				edgeOptions.setImplicitWaitTimeout(Duration.ofSeconds(20));				  
				driver =new EdgeDriver(edgeOptions);		
		}
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		driver.get(APP_URL);
		
	}	
	
	@AfterClass
	public void tearDown()
	{
	//	driver.quit();	 // ************************	
	}
	
	public void captureScreen(WebDriver driver, String tname) throws IOException
	{
	    TakesScreenshot screenshot=(TakesScreenshot) driver;
	    File sourceFile=screenshot.getScreenshotAs(OutputType.FILE);
		File destinationFile = new File("C:\\Users\\Reka\\eclipse-workspace\\webscrapping\\Screenshots\\Screenshots"+tname+".png");  
		//FileUtils.copyFile(sourceFile3, destinationFile3); 
		FileHandler.copy(sourceFile, destinationFile);
		System.out.println("Screenshot Taken");
	}	
}

