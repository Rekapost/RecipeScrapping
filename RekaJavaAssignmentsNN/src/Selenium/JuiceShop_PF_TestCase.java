package Selenium;
import java.awt.Dimension;
import java.io.IOException;
import java.time.Duration;
import java.util.List;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class JuiceShop_PF_TestCase {
	private static final String URL = "https://juice-shop.herokuapp.com/#/register";
	//private static final String URL ="https://juice-shop.herokuapp.com/#/";
	static WebDriver driver;
	static {
		System.setProperty("webdriver.chrome.driver","C:\\Users\\Reka\\Drivers\\chromedriver.exe");
		ChromeOptions chromeOptions = new ChromeOptions();
		chromeOptions.setPageLoadStrategy(PageLoadStrategy.NORMAL);
		chromeOptions.setAcceptInsecureCerts(true);
		chromeOptions.setScriptTimeout(Duration.ofSeconds(30));
		chromeOptions.setPageLoadTimeout(Duration.ofMillis(30000));
		chromeOptions.setImplicitWaitTimeout(Duration.ofSeconds(20));
		chromeOptions.addArguments("window-size=1920,900");
		driver = new ChromeDriver(chromeOptions);
		//driver.get("http://tutorialsninja.com/demo/index.php");
		//String URL="https://juice-shop.herokuapp.com/#/register";
		}
	
	public static void main(String[] args) throws InterruptedException, IOException {
		browserCapabilities();
		//selenium101();	
	}
	
	private static void browserCapabilities() {	
		//EdgeOptions
		//FirefoxOptions
		//SafariOptions		
		ChromeOptions chromeOptions = new ChromeOptions();

		// This will make Selenium WebDriver to wait for the entire page is loaded.
		chromeOptions.setPageLoadStrategy(PageLoadStrategy.NORMAL);
		
		//This capability checks whether an expired (or) invalid TLS Certificate is used while navigating during a session
		chromeOptions.setAcceptInsecureCerts(true);
		
		//Specifies when to interrupt an executing script in a current browsing context. The default timeout 30,000 ms
		chromeOptions.setScriptTimeout(Duration.ofSeconds(30));
		
		//Specifies the time interval in which web page needs to be loaded in a current browsing context. The default timeout 300,000 ms
		chromeOptions.setPageLoadTimeout(Duration.ofMillis(30000));
		
		//This specifies the time to wait for the implicit element location strategy when locating elements. The default timeout 0 ms
		chromeOptions.setImplicitWaitTimeout(Duration.ofSeconds(10));
	
		//Adding chrome extensions with webdriver
		//chromeOptions.addExtensions(new File("/path/to/extension.crx"));
		
		//chromeOptions.addArguments("start-maximized");
		chromeOptions.addArguments("window-size=750,450");
		//chromeOptions.addArguments("--incognito");
		//chromeOptions.addArguments("--headless"); //invisible mode of execution
	}

//1. Please create a user in https://juice-shop.herokuapp.com/#/register 	
	@Test(priority=1)    //,enabled=false
	public void userRegistration() throws InterruptedException  {
//		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get(URL);
//		Thread.sleep(2000);
		driver.findElement(By.xpath("//body/div[1]/div/a")).click();
		driver.findElement(By.xpath("//*[@id='mat-dialog-0']//button[2]/span[1]/span")).click();
//		Thread.sleep(2000);
		PageFactory.initElements(driver, JuiceShop_PF_Objects.class);  // for null exception 
		JuiceShop_PF_Objects.emailuserName.sendKeys("admin067@gmail.com");
		JuiceShop_PF_Objects.passWord.sendKeys("admin1234");
		JuiceShop_PF_Objects.repeatPassword.sendKeys("admin1234");
		JuiceShop_PF_Objects.securityQuestionClick.click();
		JuiceShop_PF_Objects.securityQuestion.click();
		JuiceShop_PF_Objects.securityAnswer.sendKeys("96");
		//Thread.sleep(500);
		
		//JuiceShop_PF_Objects.register.click();
		WebElement wait= new WebDriverWait(driver,  Duration.ofSeconds(10)).
			       until(ExpectedConditions. elementToBeClickable(JuiceShop_PF_Objects.register));
				wait.click();
				Thread.sleep(2000);
		System.out.println("1. registration done");
	}
	
	
//2.  Please login to the website and Add one or two items to basket (from each page)– scroll, navigate and select product 
	@Test(priority=2)
	public void addToBasket() throws InterruptedException {
		//driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		//Thread.sleep(2000);
		JuiceShop_PF_Objects.nav_Account.click();
		JuiceShop_PF_Objects.nav_LoginButton.click();
		JuiceShop_PF_Objects.e_Mail.sendKeys("admin067@gmail.com");
		JuiceShop_PF_Objects.pass_Word.sendKeys("admin1234");
		//driver.findElement(By.id("rememberMe")).click();
		JuiceShop_PF_Objects.login_Button.click();
		System.out.println("2. logged in");
		Thread.sleep(500);
		JuiceShop_PF_Objects.apple.click();	
		WebElement mugmsgtoadd=driver.findElement(By.xpath("//span[contains(text(),'X')]"));
	    mugmsgtoadd.click();
		System.out.println("2. ordered apple");
		//driver.findElement(By.xpath("Lemon")).click();
//		 Actions action=new Actions(driver);
//		    action.scrollByAmount(0,400);
		JavascriptExecutor jse= (JavascriptExecutor) driver;
		jse.executeScript("scroll(0, 400)");
		Thread.sleep(500);
		
		JuiceShop_PF_Objects.nextPage.click();
//		
//		WebElement wait= new WebDriverWait(driver,  Duration.ofSeconds(10)).
//	       until(ExpectedConditions. elementToBeClickable(JuiceShop_PF_Objects.nextPage));
//		wait.click();
		
		//Actions action =new Actions(driver);
		//action.sendKeys(Keys.PAGE_UP).build().perform();
		//jse.executeScript("window.scrollBy(0,-3050)", "");
		//jse.executeScript("scroll(0, -3050);");
		//jse.executeScript("arguments[0].scrollIntoView()",apple);
		Thread.sleep(1000);
		JuiceShop_PF_Objects.oWASPJuiceShopHoodie.click();
//		WebElement wait1= new WebDriverWait(driver,  Duration.ofSeconds(10)).
//			       until(ExpectedConditions. elementToBeClickable(JuiceShop_PF_Objects.oWASPJuiceShopHoodie));
//		wait1.click();
		WebElement mugmsgtoadd1=driver.findElement(By.xpath("//span[contains(text(),'X')]"));
		mugmsgtoadd1.click();
		System.out.println("2. ordered JuiceShopHoodie");
		//Thread.sleep(500);
		//driver.findElement(By.xpath("//div[contains(text(),' OWASP Juice Shop Mug ')]/../../../div[2]//span[contains(text(),'Add to Basket')]")).click();
		//jse.executeScript("scroll(0, 3050)");
		JuiceShop_PF_Objects.previousPage.click();
		Thread.sleep(1000);
		JuiceShop_PF_Objects.greenSmoothie.click();
		WebElement mugmsgtoadd2=driver.findElement(By.xpath("//span[contains(text(),'X')]"));
		mugmsgtoadd2.click();
		
	}
//3. Go to your basket and increase the quantity of all items by 2 
	@Test(priority=3)
	public void increaseQuantity() throws InterruptedException {
//		JavascriptExecutor jse= (JavascriptExecutor) driver;			
//		jse.executeScript("scroll(0, -3050);");
		JuiceShop_PF_Objects.shoppingcart.click();
		Thread.sleep(1000);
		driver.manage().window().maximize();		
		List<WebElement> rows=driver.findElements(By.xpath("//mat-table//mat-row"));
		int rows_count=rows.size();
		System.out.println(" Rows count"+ rows_count);		
		for(int i=1;i<=rows_count;i++)
		{ 
		driver.findElement(By.xpath("//mat-table//mat-row["+i+"]/mat-cell[3]/button[2]")).click();
		Thread.sleep(500);
		//driver.findElement(By.xpath("//mat-table//mat-row["+i+"]/mat-cell[3]/button[2]")).click();
		//increase.click();
		}	
		WebElement checkout=driver.findElement(By.id("checkoutButton"));	
		checkout.click();		
		//jse.executeScript("scroll(0, 3050)");
		//checkout.click();		
		//Actions action=new Actions(driver);
		//action.sendKeys(Keys.TAB).sendKeys(Keys.TAB).click().perform();
		//Actions action=new Actions(driver);
		//action.moveToElement(checkout).click().perform();
		//jse.executeScript("arguments[0].click()", checkout);
		//checkout.click();
		System.out.println("3. checkout done");
	}
	
//4. Press checkout and add a new address (for all inputs generate random values using code and don’t hard code) 

	@Test(priority=4)
	public void checkout() throws InterruptedException {

//		JavascriptExecutor jse= (JavascriptExecutor) driver;
//		WebElement savedAddress=driver.findElement(By.xpath("//div[@id='cdk-overlay-1']//button[3][@role='menuitem']"));
//		savedAddress.click();
		WebElement addNewAddress=driver.findElement(By.xpath("//button[@aria-label='Add a new address']"));
		addNewAddress.click();
		Thread.sleep(500);
		WebElement country=driver.findElement(By.xpath("//mat-card//input[@placeholder='Please provide a country.']"));
		country.sendKeys("India");
		WebElement name=driver.findElement(By.xpath("//mat-card//input[@placeholder='Please provide a name.']"));
		name.sendKeys("Reenu");
		WebElement mobile=driver.findElement(By.xpath("//mat-card//input[@placeholder='Please provide a mobile number.']"));
		mobile.sendKeys("123456789");
		WebElement zip=driver.findElement(By.xpath("//mat-card//input[@placeholder='Please provide a ZIP code.']"));
		zip.sendKeys("60000");
		WebElement address=driver.findElement(By.xpath("//mat-card//textarea[@placeholder='Please provide an address.']"));
		address.sendKeys("Dubai street");	
		WebElement city=driver.findElement(By.xpath("//mat-card//input[@placeholder='Please provide a city.']"));
		city.sendKeys("Dunbun");
		WebElement state=driver.findElement(By.xpath("//mat-card//input[@placeholder='Please provide a state.']"));
		state.sendKeys("bundun	");
		System.out.println(" 4. entered all details ");
		
		//driver.findElement(By.xpath("//div/button[@id='submitButton']")).click();
		WebElement wait= new WebDriverWait(driver,  Duration.ofSeconds(10)).
			       until(ExpectedConditions. elementToBeClickable(driver.findElement(By.xpath("//div/button[@id='submitButton']"))));
				wait.click();
		
	}
//5. Select the address and any delivery speed 
	@Test(priority=5)
	public void address() {
		WebElement selectAddress=driver.findElement(By.xpath("//span[@class='mat-radio-container']"));
		selectAddress.click();
		WebElement continue1=driver.findElement(By.xpath("//button[@aria-label='Proceed to payment selection']"));
		continue1.click();
		WebElement speedSelect=driver.findElement(By.xpath("//mat-radio-button[@id='mat-radio-42' or @id='mat-radio-44'  or @id='mat-radio-46']"));
		speedSelect.click();
		System.out.println(" 5. selected speed");
		WebElement continueSpeed=driver.findElement(By.xpath("//button[@aria-label='Proceed to delivery method selection']//span/mat-icon"));
		continueSpeed.click();
	}

//6. Add a new card, try to add a fake coupon (10 digits) and verify error message. Select card, review and place the order. 

	@Test(priority=6)
	public void newCard() {
		WebElement newcard=driver.findElement(By.xpath("//mat-panel-title[contains(text(),' Add new card ')]"));
		newcard.click();
		WebElement cardname=driver.findElement(By.xpath("//input[@id='mat-input-14']"));
		cardname.sendKeys("Reka");
		WebElement cardNumber=driver.findElement(By.xpath("//input[@id='mat-input-15']"));
		cardNumber.sendKeys("1122334455667788");
		WebElement month=driver.findElement(By.xpath("//select[@id='mat-input-16']"));
		month.sendKeys("8");
		WebElement year=driver.findElement(By.xpath("//select[@id='mat-input-17']"));
		year.sendKeys("2080");
		System.out.println("6. added card details ");
		WebElement submit=driver.findElement(By.xpath("//button[@id='submitButton']"));
		submit.click();
		JavascriptExecutor jse= (JavascriptExecutor) driver;	
		jse.executeScript("scroll(0, 3050)");
		
//		WebElement wait= new WebDriverWait(driver,  Duration.ofSeconds(10)).
//	       until(ExpectedConditions. elementToBeClickable(By.xpath("//span[@class='mat-content ng-tns-c150-32']")));
//		wait.click();
		
		WebElement addcoupon=driver.findElement(By.xpath("//mat-panel-title[contains(text(),' Add a coupon ')]"));
	
		//jse.executeScript("arguments[0].scrollIntoView()", addcoupon);
		addcoupon.click();	
		
		//jse.executeScript("arguments[0].click()", addcoupon);

		//Actions action=new Actions(driver);
		//action.moveToElement(addcoupon).click().perform();
		//addcoupon.click();
		
		WebElement enetrcoupon=driver.findElement(By.xpath("//div[@id='cdk-accordion-child-1']//input[@placeholder='Please enter your coupon code']"));
		enetrcoupon.sendKeys("1234567890");
		System.out.println(" 6.added coupon ");

		WebElement redeem=driver.findElement(By.xpath("//button[@id='applyCouponButton']"));
		redeem.click();
		WebElement errormessage=driver.findElement(By.xpath("//div[@class='error ng-star-inserted']"));
		System.out.println(errormessage.getText());
		WebElement selectCard=driver.findElement(By.xpath("//span[@class='mat-radio-outer-circle']"));
		selectCard.click();
		System.out.println("6. selected card ");

		WebElement proceedtoreviw=driver.findElement(By.xpath("//button/span/mat-icon[contains(text(),' navigate_next ')]"));
		proceedtoreviw.click();
		WebElement checkout=driver.findElement(By.xpath("//button[@id='checkoutButton']"));
		checkout.click();
		WebElement print=driver.findElement(By.xpath("//mat-card[@class='mat-card mat-focus-indicator mat-elevation-z6 mat-own-card']"));
		System.out.println(print.getText());
		System.out.println(" print ");

		
	}
//7. Verify in Account, order history -> print order, track order 
	@Test(priority=7)
	public void verify() throws InterruptedException {
	driver.findElement(By.id("navbarAccount")).click();
	WebElement orderPaymenet=driver.findElement(By.xpath("//div[@id='cdk-overlay-3']//button[2]//span[contains(text(),' Orders & Payment ')]"));
	orderPaymenet.click();
	WebElement orderHistory=driver.findElement(By.xpath("//*[@id='mat-menu-panel-3']//span[contains(text(),' Order History ')]"));
	orderHistory.click();
//	WebElement trackorder=driver.findElement(By.xpath("//button[@aria-label='Track Your Order']"));
//	trackorder.click();
	
	try {
		WebElement trackorder=driver.findElement(By.xpath("//button[@aria-label='Track Your Order']"));
		trackorder.click();
		
	} catch (StaleElementReferenceException e) {
		// TODO: handle exception
	}
			
			
	System.out.println("7. track order ");
	 Thread.sleep(500);
	int rows=driver.findElements(By.xpath("//div//mat-row[@role='row']")).size();
	System.out.println(" rows count:"+ rows );

	for(int i=1;i<=rows;i++)
		{ 
		WebElement data=driver.findElement(By.xpath("//div//mat-row[@role='row']/mat-cell["+i+"]"));
		String each=data.getText();
		System.out.println();
		System.out.println("7. Verify print" + each );
		}		
	}


//8.Then logout from the application 
	@Test(priority=8)
	public void logout() {
		driver.findElement(By.id("navbarAccount")).click();
		driver.findElement(By.id("navbarLogoutButton")).click();
		
//		• How to handle hidden elements in selenium webdriver?
//				JavascriptExecuter js = (JavascriptExecutor)driver;
//				js.excuteScript(“document.getElementById(“<<displayed_text>>”).value=’Hiddentext);
	}
	
}
