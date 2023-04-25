package stepDefinitions;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;
import Constants.Constants;
import io.cucumber.java.Before;
import io.cucumber.java.en.*;
import pageObjects.LoginPage;

//public class Steps {
public class Steps extends Common_Step_Definition{
 //WebDriver driver; as u extended from parent base
	
	@Before
	public void setup() throws IOException {
		
//******** adding logger ( instantiating logger)******************
		logger=Logger.getLogger("chrome Browser");
		PropertyConfigurator.configure("log4j.properties");
		
//*************** instantiating config properties( reading property file)*************
//		configProperties=new Properties();
//		FileInputStream configPropFile=new FileInputStream("config.properties");
//		configProperties.load(configPropFile);
		
//		configProperties.load(getClass().getResourceAsStream("/config.properties"));
		
		
//		configProperties=new Properties();
//		FileInputStream configPropFile=new FileInputStream("config.properties");
//		Properties props=new Properties();
//		props.load(configPropFile);
		
//******************* launching chrome browser**************************
//		System.setProperty(configProperties.getProperty("CHROME_DRIVER"),configProperties.getProperty("CHROME_DRIVER_LOCATION"));
		
//		System.setProperty(props.getProperty("CHROME_DRIVER"),props.getProperty("CHROME_DRIVER_LOCATION"));
		
		System.setProperty(Constants.CHROME_DRIVER,Constants.DRIVER_LOCATION);
		driver=new ChromeDriver();
		
//		String brow=configProperties.getProperty("BROWSER");
//		if(brow.equals("chrome"))		
//		{
//			System.setProperty(configProperties.getProperty("CHROME_DRIVER"),configProperties.getProperty("CHROME_DRIVER_LOCATION"));
//			driver=new ChromeDriver();
//		}
//		else if (brow.equals("firefox"))		
//		{
//			System.setProperty(configProperties.getProperty("FIREFOX_DRIVER"),configProperties.getProperty("FIREFOX_DRIVER_LOCATION"));
//			driver=new FirefoxDriver();
//		}
	}
 	
	@Given("open browser")
	public void open_browser() {
		System.out.println("Opening Browser:");
				lp=new LoginPage(driver);
	}

	@When("open nopcommerce link {string}")
	public void open_nopcommerce_link(String string) {
		logger.info(" launching url");
		System.out.println("Open nop commerce site: ");
		driver.get(Constants.APP_URL);
		driver.manage().window().maximize();
	}

	@Then("enter user id {string} and password {string}")
	public void enter_user_id_and_password(String string, String string2) {
		logger.info("providing login details");
		System.out.println("Enter credentials:");
//		driver.findElement(By.id("Email")).clear();
//		driver.findElement(By.id("Email")).sendKeys("admin@yourstore.com");
//		driver.findElement(By.id("Password")).clear();
//		driver.findElement(By.id("Password")).sendKeys("admin");
		PageFactory.initElements(driver, LoginPage.class);
		lp.username.clear();
		lp.username.sendKeys(Constants.username);
		lp.password.clear();
		lp.password.sendKeys(Constants.password);	
	}

	@Then("dashboard page opens {string}")
	public void dashboard_page_opens(String string) {
		logger.info(" openeing dashboard page");
		System.out.println("opening dashboard page:");
		PageFactory.initElements(driver, LoginPage.class);
		//driver.findElement(By.xpath("//button[normalize-space()='Log in']")).click();
		LoginPage.loginButton.click();
	}

	@Then("click logout")
	public void click_logout() {
		System.out.println("loging out of nop commerce:");
		if(driver.getTitle()=="Dashboard / nopCommerce administration")
			{System.out.println(" successful login");}
		//Assert.assertEquals(expected: true, status);     for boolean output boolean status isDisplayed();
		//Assert.assertEquals("Dashboard / nopCommerce administration", driver.getTitle());
	}

	@Then("come to home page")
	public void come_to_home_page() {
		System.out.println("back to home page :");
		driver.close();	
	}
	
	
//	@When(" Enter Customer Email")
//	public void enter_customerEmail() {
//		searchcust=new SearchCustomerPage(driver);
//		searchcust.setEmail("reka@gmail.com");
//	}
	
//	@Then(" user should find email in search table")
//	public void user_should_find_email_in_search_table() {
//		boolean status=searchCust.searchCustomerByEmail("reka@gmail.com");
//      Assert.assertEquals(true,status);
//	}
	
//	@When("Enter customer firstName")
//	public void enter_customer_firstName() {
//		searchCust=new searchCustomerPage(driver);
//		searchCust.setFirstName("Reka")
//	}
	
	
	
	
	
}
