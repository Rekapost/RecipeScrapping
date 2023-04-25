package selenium;
import java.io.File;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class FileDownload {
	@Test
	public void downloadFile() {
		System.setProperty("webdriver.chrome.driver", "C://Users//Reka//Drivers//chromedriver.exe/");
		WebDriverManager.chromedriver().setup();
		WebDriver driver=new ChromeDriver();
		driver.get("https://demo.automationtesting.in/FileDownload.html");
		WebElement downloadFile=driver.findElement(By.xpath("/html/body/section/div[1]/div/div/div[1]/a"));
		downloadFile.click();
		File fileLocation=new File("C:\\Users\\Reka\\Downloads");
		File[] totalFiles=fileLocation.listFiles();
		for (File eachFile : totalFiles) {
				eachFile.getName().equals("samplefile.pdf");
				System.out.println("file  downloaded");
				break;
			}	
		driver.close();
		}
}
