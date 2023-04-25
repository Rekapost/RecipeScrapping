package westBengalCovid;
import java.time.Duration;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

public class Covid {
	WebDriver driver;
	@Test
	public void covidSafe() throws InterruptedException {
		System.setProperty("webdriver.chrome.driver","C:\\Users\\Reka\\Drivers\\chromedriver.exe");
		driver=new ChromeDriver();	
		//driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));  
		driver.get("https://westbengal.covidsafe.in/");
		driver.manage().window().maximize()	;
		//		Please write the number of beds, O2 and Ventilator availability in console likebelow for all hospitals from the below link . 
		//		(create code for one hospital and use ‘for’ loop to get all hospitals in the zone -use dynamic xpath)
		//		• Name of the hospital :
		//			• Total available beds :
		//				• O2 beds availability :
		//					• Ventialtor(s) availability :
		//						• Contact number :

		// **************  FINDING NO OF ROWS   ****************************************
		//List<WebElement> hospitals=driver.findElements(By.xpath("//strong[contains(text(),'Hospital')]"));
		List<WebElement> rows=driver.findElements(By.xpath("//tbody/tr"));
		//List<WebElement> rows=driver.findElements(By.xpath("//tr"));
		int rows_count=rows.size();
		System.out.println("No Of Hospitals rows :" + rows_count);

		// *************   FINDING NO OF COLUMNS  ***************************************		
		List<WebElement> columns=driver.findElements(By.xpath("//tr[1]//td"));
		int column_count=columns.size();
		System.out.println("No Of Hospitals columns:" + column_count);

		// *************   FINDING THE NAME OF THE HOSPITAL IN  FIRST ROW FIRST COLUMN [1][1]  **********************
		WebElement firstHospital=driver.findElement(By.xpath("//tr[1]//strong"));
		String hospitalNmae=firstHospital.getText();
		System.out.println(" first hospital name:" + hospitalNmae);

		
		//**************    FINDING THE ALL HOSPITAL NAMES IN FIRST COLUMN     *********************
		/*	for(int i=1;i<=rows_count;i++)
		{
			WebElement eachHospital=driver.findElement(By.xpath("//tr["+i+"]//strong"));
			String hospitalName=eachHospital.getText();
			System.out.println("Names of the Hospital : " +hospitalName);
		}
		 */	
		
		//****************       GETTING DETAILS OF EACH HOSPITAL  finding all rows and column values       *****************************
		/*	for(int i=1;i<=rows_count;i++)
		{ 
			for(int j=1;j<=column_count;j++)
			{
			WebElement eachHospitalDetails=driver.findElement(By.xpath("//tr["+i+"]/td["+j+"]"));
			String hospitalDetails=eachHospitalDetails.getText();
			System.out.println();
			System.out.println(hospitalDetails );
			}
		}*/
		
		// ***************************   finding Phone NO in all rows and only column 1     ********************************
/*		for(int i=1;i<=rows_count;i++)
		{ 		
			//Actions action=new Actions(driver);  NW
			WebElement eachHospital=driver.findElement(By.xpath("//tr["+i+"]//strong"));
			//action.moveToElement(eachHospital);   NW
			JavascriptExecutor jse = (JavascriptExecutor)driver;
			//jse.executeScript("arguments[0].scrollIntoView()", eachHospital);  W1
			jse.executeScript("arguments[0].click()", eachHospital);      //W2
			String hospitalName=eachHospital.getText();
			System.out.println("Names of the Hospital : " +hospitalName);
			Thread.sleep(1000);
			//eachHospital.click();     W1
			WebElement address=driver.findElement(By.xpath("//tr["+(i+1)+"]//span[contains(text(),'Phone')]"));
			String hospital_Details=address.getText();
			System.out.println("phone number:" +hospital_Details );	 
			//eachHospital.click();         W1
			//jse.executeScript("arguments[0].scrollIntoView()", eachHospital);       W1
			jse.executeScript("arguments[0].click()", eachHospital);     //W2
			
			//JavascriptExecutor eachHospital = (JavascriptExecutor)driver;
			//jse.executeScript("scroll(0, 250)"); // if the element is on bottom.		
		}
		// **********   not working ***************************
		//WebElement next=driver.findElement(By.linkText("Load next 20"));
		//next.click();          */
		
		int rowsv=driver.findElements(By.tagName("tr")).size();
		System.out.println("No.of Rows in a page:"+rowsv);
	
		for(int r=1;r<=rowsv;r++) {
			
		
	    WebElement hosp_name=driver.findElement(By.xpath("//tbody/tr["+r+"]/td//div[@class='p-1']"));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		//js.executeScript("window.scrollBy(0,100)", "");
		js.executeScript("arguments[0].click()", hosp_name);
								
		System.out.println("Name of Hospital:" +hosp_name.getText());
		WebElement oxygenbed=driver.findElement(By.xpath("(//tr[1]/td[@class='text-center'])[2]"));
        System.out.println("The no.of Oxygen bed:" +oxygenbed.getText());
        WebElement ICU_vent = driver.findElement(By.xpath("(//td[@class='text-center'])[4]"));
        System.out.println("The no.of ICU bed with Ventilator:" +ICU_vent.getText());
        WebElement phone_num = driver.findElement(By.xpath("//td/p/span[contains(text(),'Phone')]"));
		System.out.println("Phone Number:" +phone_num.getText());
       	WebElement Address=	driver.findElement(By.xpath("//td/p/span[contains(text(),'Address')]"));
		System.out.println("Address:" +Address.getText());
      	WebElement last_updated = driver.findElement(By.cssSelector("span[class='text-muted font-weight-bold']"));
      	System.out.println("Last updated:" +last_updated.getText());

		js.executeScript("arguments[0].click()", hosp_name);
		}
	}
}






