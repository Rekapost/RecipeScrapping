package dataDrivenJXL;
import java.io.FileInputStream;
import java.io.IOException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import io.github.bonigarcia.wdm.WebDriverManager;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class UsingJXL {
	WebDriver driver;
	String [][]value =null;
	
	public String[][] excelData() throws BiffException, IOException {
		FileInputStream excel=new FileInputStream("C:\\Users\\Reka\\Desktop\\New\\Eclipse\\loginDatanNOP.xls");
		Workbook workbook=Workbook.getWorkbook(excel);
		Sheet excelSheet=workbook.getSheet(0);
		//int rowCount=excelSheet.getRows();
		int rowCount = 4;
		int columnCount = 2;
		//int columnCount=excelSheet.getColumns();
		//System.out.println(rowCount);
		//System.out.println(columnCount);
		String[][] value=new  String [rowCount][columnCount];
		for(int i = 1;i<=rowCount;i++) {
			for (int j = 0;j<columnCount;j++) {
				value[i-1][j]=excelSheet.getCell(j, i).getContents()	;
				System.out.println(value[i-1][j]);
			}	
		}
		return value;
	}
	
	@DataProvider(name="loginData")
	public String[][] input() throws BiffException, IOException {
		value=excelData();
		return value;	
	}
	
	@BeforeTest
	public void executeBefore() {
	System.setProperty("driver.webdriver.chrome","C:\\Users\\Reka\\Drivers\\chromedriver.exe");
	WebDriverManager.chromedriver().setup();
	driver=new ChromeDriver();
	}
	@AfterTest
	public void executeAfter() {
		driver.quit();
	}
	@Test(dataProvider ="loginData" )
	public void loginTestCase(String userName,String passWord) {
		driver.get("https://admin-demo.nopcommerce.com");
		WebElement UsernameBox=driver.findElement(By.xpath("//input[@id='Email']"));
		UsernameBox.clear();
		UsernameBox.sendKeys(userName);
		WebElement passwordBox=driver.findElement(By.xpath("//input[@id='Password']"));
		passwordBox.clear();
		passwordBox.sendKeys(passWord);
		WebElement login=driver.findElement(By.xpath("//button[normalize-space()='Log in']"));
		login.click();
	}	
		

}
