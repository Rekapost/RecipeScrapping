package Selenium;
import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
public class TutorialsNinja {
static WebDriver driver;
	static {
		System.setProperty("webdriver.chrome.driver","C:\\Users\\Reka\\Drivers\\chromedriver.exe");
		ChromeOptions chromeOptions = new ChromeOptions();
		driver = new ChromeDriver(chromeOptions);
		driver.get("http://tutorialsninja.com/demo/index.php");
		}
	public static void main(String[] args) throws InterruptedException, IOException {
		browserCapabilities();
		searchCurrency();
		canaon();
		iphone();
		macBook();
		coupon();
		register();
		
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
		
		chromeOptions.addArguments("start-maximized");
		//chromeOptions.addArguments("--incognito");
		chromeOptions.addArguments("--headless"); //invisible mode of execution
		//more capabilities at https://sites.google.com/a/chromium.org/chromedriver/capabilities
		//System.setProperty("webdriver.chrome.driver","C:\\Users\\Reka\\Drivers\\chromedriver.exe");
		//driver = new ChromeDriver(chromeOptions); 
		//driver.get("http://tutorialsninja.com/demo/index.php");
	}
		
	// ********************  SELECTING CURRENCY TO EURO    **********************************	
		//@Test(priority=1)
		public static void searchCurrency()  {
		 driver.findElement(By.xpath("//button[@class='btn btn-link dropdown-toggle']")).click();
		 driver.findElement(By.xpath("//ul[@class='dropdown-menu']/li/button[@name='EUR']")).click();
		}
		
		//@Test(priority=2)
		public static void canaon() throws InterruptedException  
		{
		//3.Try to order a canon EOS 5 D camera and collect the error message occurred due to a bug in select option.
		//JavascriptExecutor jse = (JavascriptExecutor)driver;
		WebElement camera=driver.findElement(By.xpath("//a[contains(text(),'Canon EOS 5D')]"));	
		//jse.executeScript("scroll(0, 250)"); // if the element is on bottom.	
		camera.click();
		WebElement addcart=driver.findElement(By.xpath("//button[@id='button-cart']"));
		addcart.click();
		WebElement select_error=driver.findElement(By.xpath("//select[@id='input-option226']"));
		String errormessage=select_error.getText();
		System.out.println("error message for select input box: "+ errormessage);
		Thread.sleep(1000);
		WebElement error=driver.findElement(By.xpath("//div[@id='product']/div[1]/div"));
		String errormessage1=error.getText();
		System.out.println("error message displaying select required: "+ errormessage1);
		}
		
		//@Test(priority=3)
		public static void iphone() throws IOException, InterruptedException 
		{
		
		//4.Move to the home screen, Click on iphone and go to details screen, change the quantity to two then add to cart.
		driver.navigate().back();
		WebElement iphone=driver.findElement(By.xpath("//a[contains(text(),'iPhone')]"));
		//jse.executeScript("scroll(0, 250)"); // if the element is on bottom.
		iphone.click();
		WebElement adding=driver.findElement(By.xpath("//input[@id='input-quantity']"));
		adding.clear();
		adding.sendKeys("2");
		WebElement addiphonecart=driver.findElement(By.xpath(" //button[@id='button-cart']"));
		addiphonecart.click();
		
		//5.Print the success message in the console
		try {
			WebElement suc_msgiph=driver.findElement(By.xpath("//*[@id='product-product']/div[1]"));
			String suc_msg_iph=suc_msgiph.getText();
			System.out.println(" success message after adding iphone :"+suc_msg_iph);
		} catch (StaleElementReferenceException e) {
			// TODO: handle exception
		}
		
		//6.Click on the cart icon (black color) in the right side top then click view cart
		WebElement carticon=driver.findElement(By.id("cart"));
		carticon.click();
		WebElement page=driver.findElement(By.xpath("//ul/li[4]/a"));
		page.click();
		
		//7.Change the quantity of iphone to 3 and click update button 
		WebElement update_cart=driver.findElement(By.xpath("//tbody/tr/td[4]//input"));
		update_cart.clear();
		update_cart.sendKeys("3");
		WebElement refresh=driver.findElement(By.xpath(" //button[@type='submit']"));
		refresh.click();
		
//		TakesScreenshot screenshot3 =( TakesScreenshot) driver; 
//		File sourceFile3=screenshot3.getScreenshotAs(OutputType.FILE);
//		File destinationFile3 = new File("/Reka_Selenium_NN/src/test/java/Selenium");   
//		FileUtils.copyFile(sourceFile3, destinationFile3);  
		
		
		//8.Print the Eco tax and VAT Amount in console and click Checkout button
		WebElement eco=driver.findElement(By.xpath("//*[@id='content']//table/tbody/tr[2]/td[1]/strong"));
		System.out.println("ECO :"+ eco.getText());
		WebElement eco1=driver.findElement(By.xpath("//*[@id='content']/div[2]/div/table/tbody/tr[2]/td[2]"));
		System.out.println("ECO VALUE: "+eco1.getText());
		WebElement vat=driver.findElement(By.xpath("//table/tbody/tr[3]/td[1]/strong"));
		System.out.println("VAT :"+ vat.getText());
		WebElement vat1=driver.findElement(By.xpath("//table/tbody/tr[3]/td[2]"));
		System.out.println("VAT VALUE :"+ vat1.getText());
		WebElement checkout=driver.findElement(By.xpath("//a[contains(text(),'Checkout')]"));
		checkout.click();
		
		//9.Print the error message and remove the product from the cart
		try {
			WebElement chk_error=driver.findElement(By.xpath("//div[@id='checkout-cart']/div[1]"));
			String chk_err_msg=chk_error.getText();
			System.out.println(" Error message  :"+ chk_err_msg);
		} catch (StaleElementReferenceException e) {
			// TODO: handle exception
		}
		WebElement rem_fr_cart=driver.findElement(By.xpath("//tbody/tr/td[4]/div/span/button[@type='button']"));
		rem_fr_cart.click();
		}
		
		//@Test(priority=4)
		public static void macBook() throws IOException, InterruptedException 
		{
		//10.Move to the home screen and click on Mac book, check the default quantity is 1 and click add to cart then verify success message
		Thread.sleep(2000);

		driver.navigate().back();
		//Thread.sleep(500);
		driver.navigate().back();
		//Thread.sleep(500);
		driver.navigate().back();
		//Thread.sleep(500);
		driver.navigate().back();
		WebElement mac_book=driver.findElement(By.xpath("//a[contains(text(),'MacBook')]"));	
		mac_book.click();
		WebElement chk_qty=driver.findElement(By.xpath("//*[@id='input-quantity']"));
		String chk_qty_msg=chk_qty.getText();
		System.out.println(" no of qantity of MacBook :" +chk_qty_msg);
		WebElement add_to_cart=driver.findElement(By.xpath("//button[@id='button-cart']"));
		add_to_cart.click();
		
		try {
			WebElement chk_suc=driver.findElement(By.xpath("//*[@id='product-product']/div[1]"));
			String chk_suc_msg=chk_suc.getText();
			System.out.println(" success message after adding macbook :"+ chk_suc_msg);
		} catch (StaleElementReferenceException e) {
			// TODO: handle exception
		}
		}
		
		//@Test(priority=5)
		public static void coupon() throws IOException, InterruptedException 
		{
		
		//11.Click on the shopping cart link in the top and apply ABCD123 as coupon code to check, print error message
		//WebElement cart_icon=driver.findElement(By.id("cart"));
		//WebElement cart_icon=driver.findElement(By.linkText("Shopping Cart"));
		WebElement cart_icon=driver.findElement(By.xpath("//*[@id='top-links']/ul/li[4]/a"));
		cart_icon.click();
		//WebElement coupon=driver.findElement(By.linkText("Use Coupon Code "));
		//WebElement coupon=driver.findElement(By.xpath("//a[contains(text(),'Use Coupon Code ')]"));
		WebElement coupon=driver.findElement(By.xpath("//h4[1]/a[1][contains(text(),'Use Coupon Code ')]"));
		coupon.click();
		WebElement ent_coupon=driver.findElement(By.xpath("//*[@id='input-coupon']"));
		ent_coupon.sendKeys("ABCD123");
		WebElement app_coupon=driver.findElement(By.xpath("//input[@id='button-coupon']"));
		app_coupon.click();

		try {
			WebElement chk_cou=driver.findElement(By.xpath("//*[@id='checkout-cart']/div[1]"));
			String chk_cou_msg=chk_cou.getText();
			System.out.println(" error message after adding coupon :"+ chk_cou_msg);
		} catch (StaleElementReferenceException e) {
			// TODO: handle exception
		}
		WebElement back_cart_icon=driver.findElement(By.xpath("//*[@id='top-links']/ul/li[4]/a"));
		back_cart_icon.click();
		
		
		//12.Enter AXDFGH123 as gift certificate and perform apply to check, print error message
		//WebElement gft_cer=driver.findElement(By.linkText("Use Gift Certificate "));
		WebElement gft_cer=driver.findElement(By.xpath("//a[contains(text(),'Use Gift Certificate ')]"));
		//WebElement gft_cer=driver.findElement(By.xpath("//input[@id='input-voucher']"));
		gft_cer.click();
		WebElement ent_cer=driver.findElement(By.xpath("//*[@id='input-voucher']"));
		ent_cer.sendKeys("AXDFGH123");
		WebElement app_gft_cer=driver.findElement(By.xpath("//input[@id='button-voucher']"));
		app_gft_cer.click();
		
		
		try {
			WebElement chk_cer=driver.findElement(By.xpath("//*[@id='checkout-cart']/div[1]"));
			String chk_cer_msg=chk_cer.getText();
			System.out.println(" error message after adding gift certificate :"+ chk_cer_msg);
		} catch (StaleElementReferenceException e)
		{
			// TODO: handle exception
		}
		WebElement back2_cart_icon=driver.findElement(By.xpath("//*[@id='top-links']/ul/li[4]/a"));
		back2_cart_icon.click();
		
		//13.Clear both textbox values and proceed to checkout
//		WebElement cl_coupon=driver.findElement(By.xpath("//input[@id='input-coupon']"));
//		cl_coupon.click();
//		WebElement clear_coupon=driver.findElement(By.xpath("//*[@id='input-coupon']"));
//		clear_coupon.clear();
//		WebElement cl_gft_cer=driver.findElement(By.xpath("//input[@id='input-voucher']"));
//		cl_gft_cer.click();
//		WebElement clear_cer=driver.findElement(By.xpath("//*[@id='input-voucher']"));
//		clear_cer.clear();
		
		WebElement checkout1=driver.findElement(By.linkText("Checkout"));
		checkout1.click();
		}
		

		//14.Select register account option and enter all account and billing details, click continue
		//@Test(priority=6)
		public static void register() throws IOException {
			WebElement account=driver.findElement(By.linkText("My Account"));
			account.click();
			WebElement register=driver.findElement(By.linkText("Register"));
			register.click();
			WebElement first_name=driver.findElement(By.xpath("//input[@id='input-firstname']"));
			first_name.sendKeys("Reka");
			WebElement last_name=driver.findElement(By.xpath("//input[@id='input-lastname']"));
			last_name.sendKeys("NV");
			WebElement email=driver.findElement(By.xpath("//input[@id='input-email']"));
			email.sendKeys("abcd@gmail.com");
			WebElement telephone=driver.findElement(By.xpath("//input[@id='input-telephone']"));
			telephone.sendKeys("1234567890");
			WebElement pwd=driver.findElement(By.xpath("//input[@id='input-password']"));
			pwd.sendKeys("Numpy@22");
			WebElement con_pwd=driver.findElement(By.xpath("//input[@id='input-confirm']"));
			con_pwd.sendKeys("Numpy@22");
			WebElement pri_pol=driver.findElement(By.xpath("//*[@id=\"content\"]/form/div/div/input[1]"));
			pri_pol.click();
			WebElement cont=driver.findElement(By.xpath("//*[@id=\"content\"]/form/div/div/input[2]"));
			cont.click();
			TakesScreenshot screenshot =( TakesScreenshot) driver; 
			File sourceFile=screenshot.getScreenshotAs(OutputType.FILE);
			File destinationFile = new File("C://Users//Reka//eclipse-workspace//Reka_Selenium_NN//src//test//java//Selenium//register.png");  
			//FileUtils.copyFile(sourceFile, destinationFile); 
			FileHandler.copy(sourceFile, destinationFile);
			
			}
			
}
