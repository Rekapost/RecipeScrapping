package WithoutFindByAndFindElementBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class PageObject {
	
	public static WebElement userName(WebDriver driver) {
		WebElement UsernameBox=driver.findElement(By.xpath("//input[@id='Email']"));
		return UsernameBox;}
	
	
		@FindBy(how=How.XPATH,using="//button[normalize-space()='Log in']")
		public static WebElement clicklogin;	
		
		
	
}
