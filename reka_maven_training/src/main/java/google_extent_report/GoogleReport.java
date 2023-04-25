package google_extent_report;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class GoogleReport {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
			//WebDriver driver=new ChromeDriver();
			System.setProperty("webdriver.chrome.driver", "C://Users//Reka//Drivers//chromedriver.exe/");
			WebDriver driver = new ChromeDriver();
			driver.get("http://www.google.com");
			
//			JavascriptExecutor executor = (JavascriptExecutor)driver;
//			executor.executeScript(document.getElementById("username").value="Whatsup Duck!";);
			
//			• How to handle hidden elements in selenium webdriver?
//					JavascriptExecuter js = (JavascriptExecutor)driver;
//					js.excuteScript(“document.getElementById(“<<displayed_text>>”).value=’Hiddentext);
			
			
			driver.findElement(By.xpath("//input[@title='Search']")).sendKeys("Whatsup Duck!");
			driver.findElement(By.xpath("//div[@class='FPdoLc lJ9FBc']//input[@name='btnK']")).submit();
			driver.quit();		
	}

}

//<!DOCTYPE suite SYSTEM "http://testng.org/testng-1.0.dtd" >
