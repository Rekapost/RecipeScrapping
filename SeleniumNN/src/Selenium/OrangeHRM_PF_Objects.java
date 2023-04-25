package Selenium;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class OrangeHRM_PF_Objects {
		WebDriver driver;
		String URL="https://opensource-demo.orangehrmlive.com/web/index.php/auth/login";
		
		@FindBy(how=How.NAME, using="username")
		public static WebElement userName;
		
		@FindBy(name="password")
		public static WebElement passWord;
		
		@FindBy(xpath="//button[@type='submit']")
		public static WebElement logIn;
		 
}
