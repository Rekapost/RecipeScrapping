package interview;
import java.awt.AWTException;
import java.time.Duration;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class questions {
	public  static WebDriver driver;
	@Test
	public void links() throws AWTException, InterruptedException {
		
		//System.setProperty("webdriver.chrome.driver", "C://Users//Reka//Drivers//chromedriver.exe/");
//		WebDriverManager.chromedriver().setup();
//		WebDriver driver=new ChromeDriver();
		WebDriverManager.chromedriver().setup();
		ChromeOptions chromeOptions = new ChromeOptions();
		chromeOptions.setPageLoadStrategy(PageLoadStrategy.NORMAL);
		chromeOptions.setAcceptInsecureCerts(true);
		chromeOptions.setScriptTimeout(Duration.ofSeconds(30));
		chromeOptions.setPageLoadTimeout(Duration.ofMillis(30000));
		chromeOptions.setImplicitWaitTimeout(Duration.ofSeconds(20));
		chromeOptions.addArguments("--remote-allow-origins=*");	  
		driver =new ChromeDriver(chromeOptions);
		String url="https://www.google.com";
		driver.get(driver.getCurrentUrl());
		Thread.sleep(2000);
		JavascriptExecutor executor= (JavascriptExecutor) driver;
		executor.executeScript("location.reload()");
		Thread.sleep(2000);
		JavascriptExecutor executor1= (JavascriptExecutor) driver;
		executor1.executeScript("history.go(0)");
		Thread.sleep(2000);
		
		//driver.findElement(By.name("q")).sendKeys(Keys.F5);
	//JavascriptExecutor jse=(JavascriptExecutor)driver;
		//jse.executeScript("window.location = \'"+url+"\'");)
//****************************************************		
		//driver.get("http://www.google.co.in");
//		driver.get("https://cosmocode.io/automation-practice/");
//		JavascriptExecutor jse= (JavascriptExecutor) driver;     
//		jse.executeScript("window.scrollBy(0,850)", "");
//		List<WebElement> checkBoxes=driver.findElements(By.xpath("//input[@type='Checkbox']"));
//		for (WebElement eachBox : checkBoxes) {
//			eachBox.click();
//		}
//************* *********************sending input types 
//		WebElement searchBox=driver.findElement(By.name("q"));   //1
//		searchBox.click();
//	    searchBox.sendKeys("Reka"); 
		
//		JavascriptExecutor jse= (JavascriptExecutor) driver;     // 2  did not work  
//		jse.executeScript("document.getElementsByName(‘q’)[0].value='Reka'","");   // did not work
//        jse.executeScript("arguments[0].value='Reka'", searchBox);
		
//		driver.switchTo().activeElement();    //3
//		Robot robot =new Robot();
//		robot.keyPress(KeyEvent.VK_R);
//		robot.keyRelease(KeyEvent.VK_R);
//		robot.keyPress(KeyEvent.VK_E);
//		robot.keyRelease(KeyEvent.VK_E);
//		robot.keyPress(KeyEvent.VK_K);
//		robot.keyRelease(KeyEvent.VK_K);
//		robot.keyPress(KeyEvent.VK_A);
//		robot.keyRelease(KeyEvent.VK_A);
//**************************		
//		WebElement searchBox=driver.findElement(By.name("q"));
//		searchBox.click();
//****************************************		
		//searchBox.sendKeys("selenium" + Keys.ENTER);
		
//**************************  to print all urls in the page :
		/*List<WebElement>  links=driver.findElements(By.xpath("//a"));
		for (WebElement eachLink : links) {
			System.out.println(eachLink.getAttribute("href"));} */	
		
		//***************************  to print all cite links (main link)	
		/*List<WebElement>  citeLinks=driver.findElements(By.xpath("//a//cite"));
		for (WebElement eachCiteLink : citeLinks) {
		System.out.println(eachCiteLink.getText());	
			}*/
		
//******************************    different ways to enter/ click search / submit 
		//1.searchBox.sendKeys("selenium" + Keys.ENTER);
		
		/*2. searchBox.sendKeys("selenium");
		        searchBox.submit();  */
		
		/*3.searchBox.sendKeys("selenium");
		Robot robot= new Robot();
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);*/
		
		/*4. searchBox.sendKeys("selenium  \n");  */
//***************************************************************
	/*	How to verify whether an element is underlined ?
	//	driver.get("http://www.google.co.in");
		WebElement privacy_underline=driver.findElement(By.linkText("Privacy"));
		String before_hover=privacy_underline.getCssValue("text-decoration");
		System.out.println(" before hovering :  " + before_hover);
		Actions action =new Actions(driver);
		action.moveToElement(privacy_underline);
		action.perform();
		String after_hover=privacy_underline.getCssValue("text-decoration");
		System.out.println(" after hovering :  " +after_hover);
		*/
		
//		 DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy ");
//		 Date date = new Date();
//		 String date1= dateFormat.format(date);
//		 System.out.println(date1);		 
		 
	/*	 Random randomGenerator=new Random();
		 int randomInt=randomGenerator.nextInt(1000);
		 Object String =("EliteForce-"+"SDET-"+randomInt);
		 System.out.println(String);
		 //Jan23-TeamName-ProgramName-serialnumber
		 //Ex: Jan23-EliteForce-SDET-001
	*/	
		
//		    int day, year;
//		   int month;
//		    int secnd, minute, hour;
//		    GregorianCalendar date = new GregorianCalendar();
//
//		    day = date.get(Calendar.DAY_OF_MONTH);
//		    month = date.get(Calendar.MONTH);
//		    year = date.get(Calendar.YEAR);
//
//		    secnd = date.get(Calendar.SECOND);
//		    minute = date.get(Calendar.MINUTE);
//		    hour = date.get(Calendar.HOUR);
//
//		   System.out.println(day+ ":"+month+":"+year+":"+hour+":"+minute+":"+secnd);	 
		 
//			emailuserName.click();			
//			Random randomGenerator=new Random();
//			int randomInt=randomGenerator.nextInt(1000);
//			//emailuserName.sendKeys("reka"+randomInt+"@gmail.com");
//			Object String = ("reka"+randomInt+"@gmail.com");
//			emailuserName.sendKeys((CharSequence[]) String);
//		}
//*******************************************************	
//	@Test(invocationCount = 3)
//	public void repeatRun() {
//		System.out.println(" Reka NV");		
//	}
//**************************************************	
//	@Test(timeOut = 2000)
//	public void repeatRun() throws InterruptedException {
//		Thread.sleep(3000);
//		System.out.println(" Reka NV");		
//	}
//********************************************************	
//	@Test(timeOut = 2000, expectedExceptions =ThreadTimeoutException.class)
//	public void repeatRun() throws InterruptedException {
//		Thread.sleep(3000);
//		System.out.println(" Reka NV");		
//	}
//*********************************************************	
//	@Test(timeOut = 2000)
//	public void parentsAcceptance() throws InterruptedException {
//		Thread.sleep(3000);
//		System.out.println(" permission given");		
//	}
//	
//	@Test(dependsOnMethods = "parentsAcceptance", alwaysRun = true)
//	public void loveMarriage()  {
//		System.out.println(" marriage happened");		
//	}
//	
//************************	
/*	driver.get("http://www.google.com");
//	System.out.println(driver.getTitle());
//	System.out.println(driver.getWindowHandle());
	if (driver.getTitle()== "Google") // Google
	{
		System.out.println(driver.getTitle());
		System.out.println(driver.getWindowHandle());    // 8C67F758AAE0036AF64128AEF9A7D75F
		Assert.assertTrue(true);
		System.out.println(" successful login");	
	}
	else
		System.out.println(" not matched");
	
	
//	if (driver.getTitle().equals("Google"))
//	{
//		System.out.println(driver.getTitle());    	   //     Google
//	
//		System.out.println(driver.getWindowHandle());   //    EBF29D0CD733EB4542073BFEFB29FDFB
//		System.out.println(" matched");	
//		}
//	else
//	{	
//		System.out.println(" not matched");
//	
//	}
 
 */
		
	}
}
