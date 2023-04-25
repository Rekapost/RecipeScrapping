package Selenium;
import java.time.Duration;
import java.util.Scanner;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

public class Finding_Elements {
	WebDriver driver;

	@Test(priority=1)
	public void launch_Browser() {
		System.setProperty("webdriver.chrome.driver","C:\\Users\\Reka\\Drivers\\chromedriver.exe");
		ChromeOptions chromeOptions = new ChromeOptions();
		chromeOptions.setPageLoadStrategy(PageLoadStrategy.NORMAL);
		chromeOptions.setAcceptInsecureCerts(true);
		chromeOptions.setScriptTimeout(Duration.ofSeconds(30));
		chromeOptions.setPageLoadTimeout(Duration.ofMillis(30000));
		chromeOptions.setImplicitWaitTimeout(Duration.ofSeconds(20));
		chromeOptions.addArguments("--remote-allow-origins=*");
		driver=new ChromeDriver(chromeOptions);		
		//driver=new ChromeDriver();		
	}

	@Test(priority=2)
	public void openURL() throws InterruptedException {
		driver.get("https://cosmocode.io/automation-practice/");
		//driver.manage().window().maximize();
		String title=driver.getTitle();
		System.out.println("Title of first  page :  " + title);

		//		WebElement link=driver.findElement(By.linkText("Click here to reload this page"));
		//		link.click();

		//		WebElement link=driver.findElement(By.tagName("a"));
		//		link.click();

		//driver.findElement(By.id("firstname")).sendKeys("Reka");
		driver.findElement(By.cssSelector("#firstname")).sendKeys("Reka");
		driver.findElement(By.className("lastname")).sendKeys("NV");
		//driver.findElement(By.cssSelector(".lastname")).sendKeys("NV");
		//driver.findElement(By.cssSelector("input.lastname")).sendKeys("NV");
		//~~~~driver.findElement(By.cssSelector("[value='Female']")).sendKeys("NV");
		//driver.findElement(By.cssSelector("input")).click();
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("scroll(0, 900)"); // if the element is on bottom.
		WebElement female_Rd_button=driver.findElement(By.xpath("//input[@value='Female']"));
		female_Rd_button .click();
		WebElement java_lang_ChBox=driver.findElement(By.name("language_java"));
		java_lang_ChBox.click();

		//		Scanner input=new Scanner(System.in);
		//		System.out.println("Enter male or female ");
		//		String gender=input.next();
		//		switch("gender")
		//		{
		//		case "male":
		//		{
		//		WebElement male_Rd_button=driver.findElement(By.xpath("//input[@value='Male']"));
		//		male_Rd_button.click();
		//		}break;
		//		case "female":
		//		{
		//		WebElement female_Rd_button=driver.findElement(By.xpath("//input[@value='Female']"));
		//		female_Rd_button .click();
		//		}break;
		//		default: 
		//		{
		//			WebElement female_Rd_button=driver.findElement(By.xpath("//input[@value='donotknow']"));
		//			female_Rd_button .click();
		//		}break;
		//		}
		//		System.out.println("Enter java/python/c#/c/vbs ");
		//		String language=input.next();
		//		switch("language")
		//		{
		//		case "java":
		//			{
		//		WebElement java_lang_ChBox=driver.findElement(By.name("language_java"));
		//		java_lang_ChBox.click();
		//		break;
		//		}
		//		case "python":
		//		{WebElement java_lang_ChBox=driver.findElement(By.name("language_python"));
		//		java_lang_ChBox.click();
		//		break;
		//		}
		//		case "c#":
		//		{WebElement java_lang_ChBox=driver.findElement(By.name("language_c#"));
		//		java_lang_ChBox.click();
		//		break;
		//		}
		//		case "c":
		//		{WebElement java_lang_ChBox=driver.findElement(By.name("language_c"));
		//		java_lang_ChBox.click();
		//		break;
		//		}
		//		case "vbs":
		//		{WebElement java_lang_ChBox=driver.findElement(By.name("language_vbs"));
		//		java_lang_ChBox.click();
		//		break;
		//		}
		//		default:
		//		{WebElement java_lang_ChBox=driver.findElement(By.name("language_java"));
		//		java_lang_ChBox.click();
		//		break;
		//		}
		//	}

		WebElement age_drpDown=driver.findElement(By.name("age"));
		Select age_select=new Select(age_drpDown);
		//age_select.selectByVisibleText("under 50");
		age_select.selectByIndex(3);


		jse.executeScript("scroll(0, 1200)"); // if the element is on bottom.

		WebElement click_submit=driver.findElement(By.id("submit_htmlform"));
		click_submit.click();	


		driver.findElement(By.tagName("a"));
		WebElement java_alert=driver.findElement(By.partialLinkText("random-text-xyz-i-wont-change"));
		java_alert.click();

		jse.executeScript("scroll(0, 4000)"); // if the element is on bottom.
		Thread.sleep(3000);
		
//		WebElement adCard=driver.findElement(By.id("card"));
//		adCard.click();	
	
		WebElement alert=driver.findElement(By.linkText("Click Me to get Alert"));
		alert.click();
		Alert pop=driver.switchTo().alert();
		pop.accept();

		jse.executeScript("scroll(0, 3000)"); // if the element is on bottom.
		Thread.sleep(3000);

		WebElement newWindow_click=driver.findElement(By.linkText("Click Me to open New Window"));
		newWindow_click.click();
		String titlename=driver.getTitle();
		System.out.println("String second_tilte:  " +titlename);		
		String secondWindow=driver.getWindowHandle();
		System.out.println(" second window: " + secondWindow);
		//driver.navigate().to(secondWindow);
		driver.switchTo().window(secondWindow);	
		driver.navigate().to(secondWindow);
		//driver.navigate().to(secondWindow);	
		jse.executeScript("scroll(0, 3000)"); // if the element is on bottom.
		WebElement third_window=driver.findElement(By.linkText("Click Me To Open Third Window"));
		third_window.click();
		
		String thirdWindow=driver.getWindowHandle();
		System.out.println(" third  window: " + thirdWindow);
		//driver.navigate().to(thirdWindow);
		driver.switchTo().window(thirdWindow);
		System.out.println("String third_tilte=" +driver.getTitle());

	}
}
