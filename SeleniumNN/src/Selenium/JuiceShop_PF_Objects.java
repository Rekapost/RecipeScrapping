package Selenium;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

//1.Please create a user in https://juice-shop.herokuapp.com/#/register 

//2. Please login to the website and Add one or two items to basket (from each page) – scroll, navigate and select product 

//3. Go to your basket and increase the quantity of all items by 2 

//4. Press checkout and add a new address (for all inputs generate random values using code and don’t hard code) 

//5. Select the address and any delivery speed 

//6. Add a new card, try to add a fake coupon (10 digits) and verify error message. Select card, review and place the order. 

//7. Verify in Account, order history -> print order, track order 

//8.Then logout from the application 


public class JuiceShop_PF_Objects {
	WebDriver driver;
	String URL="https://juice-shop.herokuapp.com/#/register";
	
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
//***********************
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
	
	@FindBy(xpath="//div[contains(text(),'Lemon Juice (500ml)')]/../../../div[2]//span[contains(text(),'Add to Basket')]")
	public static WebElement lemon;
	
	@FindBy(css="[aria-label='Next page']")
	public static WebElement nextPage;
	
	@FindBy(xpath="//div[contains(text(),' OWASP Juice Shop Hoodie ')]/../../../div[2]//span[contains(text(),'Add to Basket')]")
	public static WebElement oWASPJuiceShopHoodie;	
	
	@FindBy(css="[aria-label='Previous page']")
	public static WebElement previousPage;
	
	@FindBy(xpath="//div[contains(text(),' Green Smoothie ')]/../../../div[2]//span[contains(text(),'Add to Basket')]")
	public static WebElement greenSmoothie;
	
	
	@FindBy(xpath="//button[@aria-label='Show the shopping cart']")
	public static WebElement shoppingcart;
	
//	@FindBy(xpath="//mat-table//mat-row")
//	public static List<WebElement> rows;
	
	
	
	
	
	
	
	
	
	
}
