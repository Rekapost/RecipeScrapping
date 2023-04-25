package WithoutFindByAndFindElementBy;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;
import io.github.bonigarcia.wdm.WebDriverManager;

public class TestCase {
public static WebElement Password;
	@Test
	public void login() throws InterruptedException{
		System.setProperty("driver.webdriver.chrome","C:\\Users\\Reka\\Drivers\\chromedriver.exe");
		WebDriverManager.chromedriver().setup();
		WebDriver driver=new ChromeDriver();
		driver.get("https://admin-demo.nopcommerce.com/login");
		
		PageFactory.initElements(driver, TestCase.class);
		
		//using page factory without annotation
		PageObject.userName(driver).clear();
		PageObject.userName(driver).sendKeys("admin@yourstore.com");
		
		//without using FindBy and findElementBy  only with id and name
		Password.clear();
		Password.sendKeys("admin");
		Thread.sleep(1000);
		
		//using page factory with annotation FindBy
		PageObject.clicklogin.click();
		
		
		
	}

}
