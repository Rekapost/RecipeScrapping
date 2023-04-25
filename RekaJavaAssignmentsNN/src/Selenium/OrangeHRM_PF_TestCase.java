package Selenium;
import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

public class OrangeHRM_PF_TestCase {
	WebDriver driver;
	String URL="https://opensource-demo.orangehrmlive.com/web/index.php/auth/login";
//	@FindBy(how=How.NAME, using="username")
//	WebElement userName;
//	@FindBy(name="password")
//	WebElement passWord;
//	@FindBy(xpath="//button[@type='submit']")
//	WebElement logIn;
	 
// ********************  browser   **********************************
	@Test(priority=1)
	public void launch_Browser() {
		System.setProperty("webdriver.chrome.driver","C:\\Users\\Reka\\Drivers\\chromedriver.exe");
		driver=new ChromeDriver();	
	}
		
	@Test(priority=2)
	public void loginPage() throws InterruptedException  {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get(URL);
		PageFactory.initElements(driver, OrangeHRM_PF_Objects.class);  // for null exception 
		OrangeHRM_PF_Objects.userName.sendKeys("Admin");
		OrangeHRM_PF_Objects.passWord.sendKeys("admin123");
		OrangeHRM_PF_Objects.logIn.click();
	}
	
	
}





