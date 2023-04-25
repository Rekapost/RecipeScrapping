package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import Utilities.WaitHelper;

public class LoginPage {
	//public WebDriver driver;
	
//using constructor to initialze webelements in pagefactory	
 public static WebDriver ldriver;	
 WaitHelper waithelper;
	public LoginPage(WebDriver rdriver)
	{
		ldriver=rdriver;
		PageFactory.initElements(ldriver,this);
		waithelper=new WaitHelper(ldriver);
	}
	
//What is CacheLookup in Selenium?
//	@CacheLookup, as the name suggests helps us control when to cache a WebElement and when not to. 
//	This annotation, when applied over a WebElement, instructs Selenium to keep a cache of the WebElement 
//	instead of searching for the WebElement every time from the WebPage. This helps us save a lot of time.
	
	@FindBy(how=How.ID,using="Email")
	@CacheLookup
	public static WebElement username;
	
	@FindBy(how=How.ID,using="Password")
	@CacheLookup
	public static WebElement password;
	
	@FindBy(how=How.XPATH,using="//button[normalize-space()='Log in']")
	@CacheLookup
	public static WebElement loginButton;
	
// if u r collecting multiple row elements from a table //tbody/tr
// List<WebElement> tableRows;
// if u r collecting multiple column elements from a table //tbody/tr/td
// List<WebElement> tableColumns;
	
//	public void username(String username)
//	{
//		waithelper.WaitForElement(USERNAME,30);
//		USERNAME.clear();
//		USERNAME.sendKeys(username);
//	}
	
//	public void password(String password)
//		{
//		waithelper.WaitForElement(PASSWORD,30);
//		PASSWORD.clear();
//		PASSWORD.sendKeys(password);
//		}
//	comparing one value with multiple value ,
//	if matched , say record found  pass, else fail
//	public void clickSearch()
//	{
//		btnSearch.click();
//		waithelper.WaitForElement(btnSearch,30);	
//	}
//	public int getNoOfRows() {
//		return(tableRows.size());
//	}
//	public int getNoOfColumns() {
//		return(tableColumns.size());
//	}
	
//	public boolean searchCustomerByEmail(String email)
//	{
//		boolean flag=false;
//		for(int i=1;i<=getNoOfRows();i++)
//		{
//			String emailid=table.findElement(****/tbody/tr["+i+"]/td[2].getText******)
//			System.out.println(emailid));
//			if (emailid.equals(email))
//			{
//				flag=true;
//			}
//		}
//		return flag;
//	}
	
//	public boolean searchCustomerByName(String Name)
//	{
//		boolean flag=false;
//		for(int =1;i<=getNoofRows();i++)
//		{
//			String name=table.findElement(****/tbody/tr["+i+"]/td[3]**);
//			String names[]=name.split(" "); //seperating fname & lname
//			if(names[0].equals("Reka")&& names[1].equals("NV"))
//			{
//				flag=true;
//			}
//		}
//		return false;
//	}
	
	
	
}
