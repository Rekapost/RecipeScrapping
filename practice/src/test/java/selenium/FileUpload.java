package selenium;
import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;
import io.github.bonigarcia.wdm.WebDriverManager;

public class FileUpload {
	@Test
	public void uploadFile() throws AWTException {
		System.setProperty("webdriver.driver.chrome","C://Users//Reka//Drivers//chromedriver.exe/");
		WebDriverManager.chromedriver().setup();
		WebDriver  driver=new ChromeDriver();
		driver.get("https://demo.automationtesting.in/FileUpload.html");
		WebElement uploadButton=driver.findElement(By.xpath("//input[@id='input-4']"));
		
		uploadButton.sendKeys("C:\\Users\\Reka\\Desktop\\New\\file-sample_150kB.pdf");
		uploadButton.click();
		
		/*
		String file = "C:\\Users\\Reka\\Desktop\\New\\file-sample_150kB.pdf";
		//StringSelection is a Java class which creates an object 
		//which is capable to transfer the specified string in plain text to the clipboard
		StringSelection toClipboard= new StringSelection(file);
		//get the contents in clipboard using toolKit
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(toClipboard, null);
		//elements in clipboard can be pasted by Ctrl V  and enter 
		Robot robot =new Robot();
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
		*/
	}
}
