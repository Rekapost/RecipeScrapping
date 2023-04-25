package Selenium;
import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.Select;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

public class TutorialsNinjaNN {
	WebDriver driver;
	//JavascriptExecutor jse = (JavascriptExecutor)driver;
	
	@Test(priority=1)
	public void launch_Browser() {
		//1.Launch the application http://tutorialsninja.com/demo/index.php
		System.setProperty("webdriver.chrome.driver","C:\\Users\\Reka\\Drivers\\chromedriver.exe");
		driver=new ChromeDriver();	
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("http://tutorialsninja.com/demo/index.php");
	}

	// ********************  SELECTING CURRENCY TO EURO    **********************************	
	@Test(priority=2)
	public void searchCurrency() throws InterruptedException {
	 driver.findElement(By.xpath("//button[@class='btn btn-link dropdown-toggle']")).click();
//	 List<WebElement> from_drpDwn=driver.findElements(By.xpath("//ul[@class='dropdown-menu']/li"));
//	 System.out.println(from_drpDwn.size());
	 driver.findElement(By.xpath("//ul[@class='dropdown-menu']/li/button[@name='EUR']")).click();
	}
	
	@Test(priority=3)
	public void canaon() throws IOException, InterruptedException 
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
	
	@Test(priority=4)
	public void iphone() throws IOException, InterruptedException 
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
	
	@Test(priority=5)
	public void macBook() throws IOException, InterruptedException 
	{
	//10.Move to the home screen and click on Mac book, check the default quantity is 1 and click add to cart then verify success message
	//Thread.sleep(2000);

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
	
	@Test(priority=6)
	public void coupon() throws IOException, InterruptedException 
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
	
	Thread.sleep(500);
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
//	WebElement cl_coupon=driver.findElement(By.xpath("//input[@id='input-coupon']"));
//	cl_coupon.click();
//	WebElement clear_coupon=driver.findElement(By.xpath("//*[@id='input-coupon']"));
//	clear_coupon.clear();
//	WebElement cl_gft_cer=driver.findElement(By.xpath("//input[@id='input-voucher']"));
//	cl_gft_cer.click();
//	WebElement clear_cer=driver.findElement(By.xpath("//*[@id='input-voucher']"));
//	clear_cer.clear();
	
	WebElement checkout1=driver.findElement(By.linkText("Checkout"));
	checkout1.click();
	}

	//14.Select register account option and enter all account and billing details, click continue
	@Test(priority=7)
	public void register() throws IOException, InterruptedException {
	
	JavascriptExecutor jse = (JavascriptExecutor)driver;
	jse.executeScript("window.scrollBy(0,350)");

	Actions action=new Actions(driver);
	//Thread.sleep(1000);
	WebElement cont_option=driver.findElement(By.id("button-account"));
	action.moveToElement(cont_option);
	//action.scrollToElement(cont_option);
	//jse.executeScript("arguments[0].click()", cont_option);  
	cont_option.click();
	WebElement first_name=driver.findElement(By.id("input-payment-firstname"));
	//action.moveToElement(first_name);
	jse.executeScript("arguments[0].click()", first_name);
	first_name.sendKeys("Reka");
	WebElement last_name=driver.findElement(By.id("input-payment-lastname"));
	last_name.sendKeys("NV");
	WebElement email=driver.findElement(By.id("input-payment-email"));
	email.sendKeys("abcd_98@gmail.com");
	WebElement telephone=driver.findElement(By.id("input-payment-telephone"));
	telephone.sendKeys("1234567890");
	jse.executeScript("window.scrollBy(0,350)");
	WebElement pwd=driver.findElement(By.id("input-payment-password"));
	pwd.sendKeys("Numpy@22");
	WebElement con_pwd=driver.findElement(By.id("input-payment-confirm"));
	con_pwd.sendKeys("Numpy@22");
	WebElement company=driver.findElement(By.id("input-payment-company"));
	company.sendKeys("numpy");
	WebElement address1=driver.findElement(By.id("input-payment-address-1"));
	address1.sendKeys("dubai");
	WebElement address2=driver.findElement(By.id("input-payment-address-2"));
	address2.sendKeys("dubai side street");
	WebElement city=driver.findElement(By.id("input-payment-city"));
	city.sendKeys("dubaiiiiii");
	WebElement postcode=driver.findElement(By.id("input-payment-postcode"));
	postcode.sendKeys("402");
	WebElement country=driver.findElement(By.id("input-payment-country"));
	country.click();
	Select countryy=new Select(country);
	countryy.selectByVisibleText("Albania");
	WebElement region=driver.findElement(By.id("input-payment-zone"));
	region.click();
	Select state=new Select(region);
	state.selectByVisibleText("Devoll");
	WebElement pri_pol=driver.findElement(By.name("agree"));
	pri_pol.click();
	WebElement cont=driver.findElement(By.id("button-register"));
	action.moveToElement(cont);
	cont.click();
	
		TakesScreenshot screenshot3 =( TakesScreenshot) driver; 
		File sourceFile3=screenshot3.getScreenshotAs(OutputType.FILE);
		File destinationFile3 = new File("C://Users//Reka//eclipse-workspace//Reka_Selenium_NN//src//test//java//Selenium//registerNN.png");  
		//FileUtils.copyFile(sourceFile3, destinationFile3); 
		FileHandler.copy(sourceFile3, destinationFile3);
		
		}
	
	@Test(priority=8)
	public void commentPaymenet()
	{
	WebElement comment=	driver.findElement(By.name("comment"));
	comment.sendKeys("please handle with care ");
	
//	WebElement payment_msg=driver.findElement(By.xpath("//*[@id='collapse-payment-method']//text()[1]"));
//	String messagePay=payment_msg.getText();
//	System.out.println(" maeesage about payement: " + messagePay);
	
//	WebElement contactUs=driver.findElement(By.xpath("//*[@id='collapse-payment-method']//a"));
//	contactUs.click();
//	WebElement enquiry=driver.findElement(By.id("input-enquiry"));
//	enquiry.sendKeys("please contact me ASAP !!!!!!!!!!!!!!!!!!!!");
//	driver.findElement(By.xpath("//*[@id='content']/form//input[@value='Submit']")).click();
//	driver.findElement(By.linkText("Continue")).click();
	
	WebElement agreecom=driver.findElement(By.name("agree"));
	agreecom.click();
//	WebElement payment_msg=driver.findElement(By.xpath("//*[@id='collapse-payment-method']//text()[1]"));
//	String messagePay=payment_msg.getText();
//	System.out.println(" maeesage about payement: " + messagePay);
	WebElement cont_but=driver.findElement(By.id("button-payment-method"));
	cont_but.click();
	WebElement warning=driver.findElement(By.xpath("//*[@id='collapse-payment-method']/div/div[1]"));
	String warn_msg=warning.getText();
	System.out.println("warning message about payement: " +warn_msg );
	
	WebElement contactUs=driver.findElement(By.xpath("/html/body/footer/div/div/div[2]/ul/li[1]/a"));
	contactUs.click();
	WebElement enquiry=driver.findElement(By.id("input-enquiry"));
	enquiry.sendKeys("please contact me ASAP !!!!!!!!!!!!!!!!!!!!");
	driver.findElement(By.xpath("//*[@id='content']/form//input[@value='Submit']")).click();
	driver.findElement(By.linkText("Continue")).click();
	}
		
}
