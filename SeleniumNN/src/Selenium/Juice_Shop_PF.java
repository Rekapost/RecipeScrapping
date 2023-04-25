package Selenium;
import java.time.Duration;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class Juice_Shop_PF {
	private static final String URL = "https://juice-shop.herokuapp.com/#/register";
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
		}
	@FindBy(how=How.XPATH, using="//body/div[1]/div/a")
	public static WebElement meWantIt;	
	@FindBy(how=How.XPATH, using="//*[@id='mat-dialog-0']//button[2]/span[1]/span")
	public static WebElement dismiss;	
	@FindBy(how=How.ID, using="emailControl")
	public static WebElement emailuserName;	
	@FindBy(id = "passwordControl")
	public static WebElement passWord;	
	@FindBy(id="repeatPasswordControl")
	public static WebElement repeatPassword;
	@FindBy(id="mat-select-0")
	public static WebElement securityQuestionClick;	
	@FindBy(xpath="//div[@id='cdk-overlay-2']//div[@role='listbox']//mat-option[@id='mat-option-6']")
	public static WebElement securityQuestion;	
	@FindBy(id="securityAnswerControl")
	public static WebElement securityAnswer;	
	@FindBy(id="registerButton")
	public static WebElement register;
	
//***************************************
	@FindBy(id="navbarAccount")
	public static WebElement nav_Account;	
	@FindBy(id="navbarLoginButton")
	public static WebElement nav_LoginButton;	
	@FindBy(id="email")
	public static WebElement e_Mail;	
	@FindBy(id="password")
	public static WebElement pass_Word;	
	@FindBy(id="loginButton")
	public static WebElement login_Button;	
	@FindBy(xpath="//div[contains(text(),'Apple Juice (1000ml)')]/../../../div[2]//span[contains(text(),'Add to Basket')]")
	public static WebElement apple;	
	@FindBy(xpath="//span[contains(text(),'X')]")
	public static WebElement appleMessage;	
	@FindBy(xpath="//div[contains(text(),'Lemon Juice (500ml)')]/../../../div[2]//span[contains(text(),'Add to Basket')]")
	public static WebElement lemon;	
	@FindBy(css="[aria-label='Next page']")
	public static WebElement nextPage;	
	@FindBy(xpath="//div[contains(text(),' OWASP Juice Shop Hoodie ')]/../../../div[2]//span[contains(text(),'Add to Basket')]")
	public static WebElement oWASPJuiceShopHoodie;		
	@FindBy(xpath="//span[contains(text(),'X')]")
	public static WebElement JuiceShopHoodieMessage;	
	@FindBy(css="[aria-label='Previous page']")
	public static WebElement previousPage;	
	@FindBy(xpath="//div[contains(text(),' Green Smoothie ')]/../../../div[2]//span[contains(text(),'Add to Basket')]")
	public static WebElement greenSmoothie;	
	@FindBy(xpath="//span[contains(text(),'X')]")
	public static WebElement greenSmoothieMessage;
	
//******************************************************	
	@FindBy(xpath="//button[@aria-label='Show the shopping cart']")
	public static WebElement shoppingcart;	
	@FindBy(id="checkoutButton")
	public static WebElement shoppingcartCheckOut;
	
//***************************************************
	@FindBy(xpath="//button[@aria-label='Add a new address']")
	public static WebElement addNewAddress;
	@FindBy(xpath="//mat-card//input[@placeholder='Please provide a country.']")
	public static WebElement country;
	@FindBy(xpath="//mat-card//input[@placeholder='Please provide a name.']")
	public static WebElement name;
	@FindBy(xpath="//mat-card//input[@placeholder='Please provide a mobile number.']")
	public static WebElement mobile;
	@FindBy(xpath="//mat-card//input[@placeholder='Please provide a ZIP code.']")
	public static WebElement zip;
	@FindBy(xpath="//mat-card//textarea[@placeholder='Please provide an address.']")
	public static WebElement address;
	@FindBy(xpath="//mat-card//input[@placeholder='Please provide a city.']")
	public static WebElement city;
	@FindBy(xpath="//mat-card//input[@placeholder='Please provide a state.']")
	public static WebElement state;
	
//***************************************************
	@FindBy(xpath="//span[@class='mat-radio-container']")
	public static WebElement selectAddress;
	@FindBy(xpath="//button[@aria-label='Proceed to payment selection']")
	public static WebElement continue1;
	@FindBy(xpath="//mat-radio-button[@id='mat-radio-42' or @id='mat-radio-44'  or @id='mat-radio-46']")
	public static WebElement speedSelect;
	@FindBy(xpath="//button[@aria-label='Proceed to delivery method selection']//span/mat-icon")
	public static WebElement continueSpeed;
	
//***************************************************************************
	@FindBy(xpath="//mat-panel-title[contains(text(),' Add new card ')]")
	public static WebElement newcard;
	@FindBy(xpath="//input[@id='mat-input-14']")
	public static WebElement cardname;
	@FindBy(xpath="//input[@id='mat-input-15']")
	public static WebElement cardNumber;	
	@FindBy(xpath="//select[@id='mat-input-16']")
	public static WebElement month;	
	@FindBy(xpath="//select[@id='mat-input-17']")
	public static WebElement year;	
	@FindBy(xpath="//button[@id='submitButton']")
	public static WebElement submit;
	@FindBy(xpath="//mat-panel-title[contains(text(),' Add a coupon ')]")
	public static WebElement addcoupon;
	@FindBy(xpath="//div[@id='cdk-accordion-child-1']//input[@placeholder='Please enter your coupon code']")
	public static WebElement entercoupon;
	@FindBy(xpath="//button[@id='applyCouponButton']")
	public static WebElement redeem;
	@FindBy(xpath="//div[@class='error ng-star-inserted']")
	public static WebElement couponMessage;	
	@FindBy(xpath="//span[@class='mat-radio-outer-circle']")
	public static WebElement selectCard;	
	@FindBy(xpath="//button/span/mat-icon[contains(text(),' navigate_next ')]")
	public static WebElement proceedtoreviw;
	
	@FindBy(xpath="//button[@id='checkoutButton']")
	public static WebElement newCardCheckout;
	@FindBy(xpath="//mat-card[@class='mat-card mat-focus-indicator mat-elevation-z6 mat-own-card']")
	public static WebElement printCard;
	@FindBy(xpath="//div[@id='cdk-overlay-3']//button[2]//span[contains(text(),' Orders & Payment ')]")
	public static WebElement orderPaymenet;
	@FindBy(xpath="//*[@id='mat-menu-panel-3']//span[contains(text(),' Order History ')]")
	public static WebElement orderHistory;

	@FindBy(id="navbarLogoutButton")
	public static WebElement logOut;
	
//1. Please create a user in https://juice-shop.herokuapp.com/#/register 	
		@Test(priority=1)    
		public void userRegistration() throws InterruptedException  {
			PageFactory.initElements(driver, Juice_Shop_PF.class);  // for null exception 
			driver.get(URL);
			meWantIt.click();
			dismiss.click();
			emailuserName.sendKeys("admin069@gmail.com");
			
//			emailuserName.click();			
//			Random randomGenerator=new Random();
//			int randomInt=randomGenerator.nextInt(1000);
//			//emailuserName.sendKeys("reka"+randomInt+"@gmail.com");
//			Object String = ("reka"+randomInt+"@gmail.com");
//			emailuserName.sendKeys((CharSequence[]) String);
			
			passWord.sendKeys("admin1234");
			repeatPassword.sendKeys("admin1234");
			securityQuestionClick.click();
			securityQuestion.click();
			securityAnswer.sendKeys("96");			
			register.click();
			Thread.sleep(2000);
			System.out.println("1. registration done");
		}
				
//2.  Please login to the website and Add one or two items to basket (from each page)– scroll, navigate and select product 
		@Test(priority=2)
		public void addToBasket() throws InterruptedException {
			nav_Account.click();
			nav_LoginButton.click();			
			e_Mail.sendKeys("admin069@gmail.com");
			pass_Word.sendKeys("admin1234");
			//driver.findElement(By.id("rememberMe")).click();
			login_Button.click();
			System.out.println("2. logged in");
			Thread.sleep(500);
			apple.click();	
			appleMessage.click();
			System.out.println("2. ordered apple");
			//driver.findElement(By.xpath("Lemon")).click();
			JavascriptExecutor jse= (JavascriptExecutor) driver;
			jse.executeScript("scroll(0, 400)");
			Thread.sleep(500);		
			nextPage.click();		
			Thread.sleep(1000);
			oWASPJuiceShopHoodie.click();
			JuiceShopHoodieMessage.click();
			System.out.println("2. ordered JuiceShopHoodie");
			previousPage.click();
			Thread.sleep(1000);
			greenSmoothie.click();
			greenSmoothieMessage.click();		
		}
		
//3. Go to your basket and increase the quantity of all items by 2 
		@Test(priority=3)
		public void increaseQuantity() throws InterruptedException {
			shoppingcart.click();
			Thread.sleep(1000);
			driver.manage().window().maximize();		
			List<WebElement> rows=driver.findElements(By.xpath("//mat-table//mat-row"));
			int rows_count=rows.size();
			System.out.println(" Rows count"+ rows_count);		
			for(int i=1;i<=rows_count;i++)
			{ 
			driver.findElement(By.xpath("//mat-table//mat-row["+i+"]/mat-cell[3]/button[2]")).click();
			Thread.sleep(500);
			}	
			shoppingcartCheckOut.click();		
			System.out.println("3. checkout done");
		}
		
//4. Press checkout and add a new address (for all inputs generate random values using code and don’t hard code) 
		@Test(priority=4)
		public void checkout() throws InterruptedException {			
			addNewAddress.click();
			Thread.sleep(500);			
			country.sendKeys("India");			
			name.sendKeys("Reenu");			
			mobile.sendKeys("123456789");			
			zip.sendKeys("60000");			
			address.sendKeys("Dubai street");			
			city.sendKeys("Dunbun");			
			state.sendKeys("bundun	");
			System.out.println(" 4. entered all details ");		
			//driver.findElement(By.xpath("//div/button[@id='submitButton']")).click();
			WebElement checkoutSubmitButton= new WebDriverWait(driver,  Duration.ofSeconds(10)).
				       until(ExpectedConditions. elementToBeClickable(driver.findElement(By.xpath("//div/button[@id='submitButton']"))));
			checkoutSubmitButton.click();			
		}
		
//5. Select the address and any delivery speed 
		@Test(priority=5)
		public void address() {			
			selectAddress.click();			
			continue1.click();			
			speedSelect.click();
			System.out.println(" 5. selected speed");
			continueSpeed.click();
		}

//6. Add a new card, try to add a fake coupon (10 digits) and verify error message. Select card, review and place the order. 
		@Test(priority=6)
		public void newCard() {			
			newcard.click();			
			cardname.sendKeys("Reka");			
			cardNumber.sendKeys("1122334455667788");			
			month.sendKeys("8");			
			year.sendKeys("2080");
			System.out.println("6. added card details ");
			submit.click();
			JavascriptExecutor jse= (JavascriptExecutor) driver;	
			jse.executeScript("scroll(0, 3050)");					
			addcoupon.click();				
			entercoupon.sendKeys("1234567890");
			System.out.println(" 6.added coupon ");			
			redeem.click();			
			System.out.println(couponMessage.getText());
			selectCard.click();
			System.out.println("6. selected card ");			
			proceedtoreviw.click();
			newCardCheckout.click();		
			System.out.println(printCard.getText());
			System.out.println(" print ");			
		}
		
	//7. Verify in Account, order history -> print order, track order 
		@Test(priority=7)
		public void verify() throws InterruptedException {
		nav_Account.click();	
		orderPaymenet.click();		
		orderHistory.click();
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
			nav_Account.click();
			logOut.click();
		}
}
