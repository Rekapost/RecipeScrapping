package stepDefinitions;
import java.util.Properties;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import pageObjects.LoginPage;

public class Common_Step_Definition {
	public  WebDriver driver;
	public  LoginPage lp;
//	public AddcustomerPage addCust;
//	public SearchCustomerpage searchCust;
	public static Logger logger;
	public Properties configProperties;

}
