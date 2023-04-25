package datadriventesting;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class UsingDataInCode {
	
	String[][] data= {{"admin@yourstore.com", "admin"},
			{"admin@yourstore.com", "admin"},
			{"admin@yourstore.com", "admin"},
			{"admin@yourstore.com", "admin"}};
	
	@DataProvider(name="datainfo")
	public String[][] dataGive() {
		return data;
	}
	
	
	@Test(dataProvider ="datainfo")
	public void usingCode(String userName,String passWord) {
		System.setProperty("driver.webdriver.chrome","C:\\Users\\Reka\\Drivers\\chromedriver.exe");
		WebDriverManager .chromedriver().setup();
		WebDriver driver=new ChromeDriver();
		driver.get("https://admin-demo.nopcommerce.com");
		WebElement UsernameBox=driver.findElement(By.xpath("//input[@id='Email']"));
		UsernameBox.clear();
		UsernameBox.sendKeys(userName);
		WebElement passwordBox=driver.findElement(By.xpath("//input[@id='Password']"));
		passwordBox.clear();
		passwordBox.sendKeys(passWord);
		WebElement login=driver.findElement(By.xpath("//button[normalize-space()='Log in']"));
		login.click();	
		driver.quit();
	}	
		
		
		
	}


