package Selenium;
import java.io.File;
import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

public class TechlisticForm {
		WebDriver driver;
		
		@Test(priority=1)
		public void launch_Browser() {
			System.setProperty("webdriver.chrome.driver","C:\\Users\\Reka\\Drivers\\chromedriver.exe");
			driver=new ChromeDriver();		
		}
		
		@Test(priority=2)
		public void openURL() throws InterruptedException, IOException {
			driver.get("https://www.techlistic.com/p/selenium-practice-form.html");
			driver.manage().window().maximize();
			JavascriptExecutor jse = (JavascriptExecutor)driver;
			jse.executeScript("scroll(0, 350)"); // if the element is on bottom.
			WebElement first_name=driver.findElement(By.name("firstname"));
			first_name.sendKeys("Reka");
			WebElement last_name=driver.findElement(By.name("lastname"));
			last_name.sendKeys("NV");
			WebElement gender_female=driver.findElement(By.id("sex-1"));
			gender_female.click();
			WebElement exp=driver.findElement(By.id("exp-3"));
			exp.click();
			WebElement date=driver.findElement(By.id("datepicker"));
			date.sendKeys("15/08/1983");
			WebElement prof=driver.findElement(By.id("profession-1"));
			prof.click();
			WebElement tool=driver.findElement(By.id("tool-2"));
			tool.click();
			WebElement continenets=driver.findElement(By.id("continents"));
			continenets.click();
			Select drp_down=new Select(continenets);
			drp_down.selectByVisibleText("North America");

			TakesScreenshot screenshot3 =( TakesScreenshot) driver; 
			File sourceFile3=screenshot3.getScreenshotAs(OutputType.FILE);
			File destinationFile3 = new File("C://Users//Reka//eclipse-workspace//Reka_Selenium_NN//src//test//java//Selenium//sample.png");  
			//FileUtils.copyFile(sourceFile3, destinationFile3); 
			FileHandler.copy(sourceFile3, destinationFile3);
			
			WebElement comands=driver.findElement(By.id("selenium_commands"));  
			comands.click();
			Select cmd_drp_down=new Select(comands);
			cmd_drp_down.selectByVisibleText("WebElement Commands");
			//jse.executeScript("scroll(0, 500)");
			jse.executeScript("window.scrollBy(0,700)");
			//WebElement file_uplaod=driver.findElement(By.id("photo"));
			//file_uplaod.sendKeys("C://Users//Pictures//Screenshots//upload.png");
			//WebElement file_down=driver.findElement(By.linkText("Click here to Download File"));
			//jse.executeScript("arguments[0].click()", file_down);  
			//file_down.click();
			//Thread.sleep(2000);
			WebElement submit=driver.findElement(By.id("submit"));
			submit.click();
			//jse.executeScript("window.scrollTo(0,675.5555419921875)"); 
		}
}
