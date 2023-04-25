	package Selenium;
	import java.io.IOException;
	import java.time.Duration;
	import org.openqa.selenium.By;
	import org.openqa.selenium.JavascriptExecutor;
	import org.openqa.selenium.Keys;
	import org.openqa.selenium.WebDriver;
	import org.openqa.selenium.WebElement;
	import org.openqa.selenium.chrome.ChromeDriver;
	import org.testng.annotations.Test;

	public class VijayCovid {
		WebDriver driver=null;
			
			@Test
		public void MissionHumanScrappingWestBengal() throws IOException, InterruptedException {	
				System.setProperty("webdriver.chrome.driver","C:\\Users\\Reka\\Drivers\\chromedriver.exe");
				WebDriver driver=new ChromeDriver();
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
				driver.manage().window().maximize();
				driver.get("https://westbengal.covidsafe.in/");				
				
				//// To click all links 
				for(int i=1;i<12;i++) {
					Thread.sleep(1000);
			   		driver.findElement(By.cssSelector("button[class='btn btn-primary']")).sendKeys(Keys.ENTER);
				}
			
				int rows=driver.findElements(By.tagName("tr")).size();
				System.out.println("No.of Rows in a page:"+rows);
			
				for(int r=1;r<=rows;r++)
				{
			    WebElement hosp_name=driver.findElement(By.xpath("//tbody/tr["+r+"]/td//div[@class='p-1']"));
				JavascriptExecutor js = (JavascriptExecutor) driver;
				//js.executeScript("window.scrollBy(0,100)", "");
				js.executeScript("arguments[0].click()", hosp_name);									
				System.out.println("Name of Hospital:" +hosp_name.getText());
				WebElement oxygenbed=driver.findElement(By.xpath("(//tr[1]/td[@class='text-center'])[2]"));
	            System.out.println("The no.of Oxygen bed:" +oxygenbed.getText());
	            WebElement ICU_vent = driver.findElement(By.xpath("(//td[@class='text-center'])[4]"));
	            System.out.println("The no.of ICU bed with Ventilator:" +ICU_vent.getText());
	            WebElement phone_num = driver.findElement(By.xpath("//td/p/span[contains(text(),'Phone')]"));
				System.out.println("Phone Number:" +phone_num.getText());
	           	WebElement Address=	driver.findElement(By.xpath("//td/p/span[contains(text(),'Address')]"));
				System.out.println("Address:" +Address.getText());
	          	WebElement last_updated = driver.findElement(By.cssSelector("span[class='text-muted font-weight-bold']"));
	          	System.out.println("Last updated:" +last_updated.getText());
				js.executeScript("arguments[0].click()", hosp_name);
				}
				System.out.println("WebScrapping Done successfully");
				driver.close();
				
		}
	   }

