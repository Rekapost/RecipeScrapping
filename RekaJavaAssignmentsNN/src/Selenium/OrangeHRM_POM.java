package Selenium;
import java.io.IOException;
import java.time.Duration;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class OrangeHRM_POM {
		WebDriver driver;
		String URL="https://opensource-demo.orangehrmlive.com/web/index.php/auth/login";	
		// 1.*************************************
		By userName=By.name("username");	
		By passWord=By.name("password");
		By logIn=By.xpath("//button[@type='submit']");
		// 2.************************************************************
		By info=By.xpath("//*[@id='app']//ul/li[6]/a");
		By addContact=By.linkText("Contact Details");
		By street1=By.xpath("//*[@id='app']//form/div[1]/div/div[1]/div/div[2]/input");
		By street2=	By.xpath("//*[@id='app']//form/div[1]/div/div[2]/div/div[2]/input");
		By city=By.xpath("//*[@id='app']//form/div[1]/div/div[3]/div/div[2]/input");
		By state=By.xpath("//*[@id='app']//form/div[1]/div/div[4]/div/div[2]/input");
		By zip=By.xpath("//*[@id='app']//form/div[1]/div/div[5]/div/div[2]/input");
		By telephone=By.xpath("//*[@id='app']//form/div[2]/div/div[1]/div/div[2]/input");
		By saveInfo=By.xpath("//*[@id='app']//form/div[4]/button");
		// 3.************************************************************
		By emergencyContact=By.linkText("Emergency Contacts");
		By addEmerCont=By.xpath("//*[@id='app']//div/div[2]/div[1]/div/button");
		By emerContName=By.xpath("//*[@id='app']//form/div[1]/div/div[1]/div/div[2]/input");
		By emerRelation=By.xpath("//*[@id='app']//form/div[1]/div/div[2]/div/div[2]/input");
		By emerPhone=By.xpath("//*[@id='app']//form/div[2]/div/div[1]/div/div[2]/input");
		By saveEmer=By.xpath("//*[@id='app']//form/div[3]/button[2]");
		// 4.*************************************************************
		By depenLink=By.linkText("Dependents");
		By addDepen=By.xpath("//*[@id='app']//div/div[2]/div[1]/div/button");
		By depenName=By.xpath("//*[@id='app']/div[1]//form/div[1]/div/div[1]/div/div[2]/input");
		By saveDepen=By.xpath("//*[@id='app']//form/div[3]/button[2]");
		// 5.*************************************************************
		By recruitLink=By.xpath("//*[@id='app']//ul/li[5]/a");
		By addCand=By.xpath("//*[@id='app']/div[1]/div[2]/div[2]/div/div[2]/div[1]/button");
		By candFirstName=By.xpath("//*[@id='app']//form/div[1]/div/div/div/div[2]/div[1]/div[2]/input");
		By candLastName=By.xpath("//*[@id='app']/div[1]//form/div[1]//div[2]/div[3]/div[2]/input");
		By candEmail=By.xpath("//*[@id='app']//form/div[3]/div/div[1]/div/div[2]/input");
		By saveCand=By.xpath("//*[@id='app']//form/div[8]/button[2]");
		// 6.*************************************************************
		By shortListCand=By.xpath("//*[@id='app']//form/div[2]/div[2]/button[2]");
		By saveShortListCand=By.xpath("//*[@id='app']//form/div[3]/button[2]");
		By schedInterview=By.xpath("//*[@id='app']//form/div[2]/div[2]/button[2]");
		By interviewTitle=By.xpath("//*[@id='app']//form/div[2]/div/div[1]/div/div[2]/input");
		By interviewer=By.xpath("//*[@id='app']//form/div[2]/div/div[2]/div/div/div[2]/div/div/input");
		By interviewDate=By.xpath("//*[@id='app']//form/div[2]/div/div[3]/div/div[2]/div/div/input");
		By saveInterview=By.xpath("//*[@id='app']//form/div[3]/button[2]");
		//7. *************************************************************************
		By markInterviewPassed=By.xpath("//*[@id='app']//form/div[2]/div[2]/button[3]");
		By saveIntPass=By.xpath("//*[@id='app']//form/div[3]/button[2]");
		By offerJob=By.xpath("//*[@id='app']//form/div[2]/div[2]/button[3]");
		By saveOffer=By.xpath("//*[@id='app']//form/div[3]/button[2]");
		//8. *************************************************************************
		By hire=By.xpath("//*[@id='app']//form/div[2]/div[2]/button[3]");
		By saveHire=By.xpath("//*[@id='app']//form/div[3]/button[2]");
		
		//9. **********************************************************************
		By adminLink=By.xpath("//*[@id='app']/div[1]/div[1]/aside/nav/div[2]/ul/li[1]/a");
		By addUser=By.xpath("//*[@id='app']/div[1]/div[2]/div[2]/div/div[2]/div[1]/button");	
		
// launching  browser   
		@Test(priority=1)
		public void launch_Browser() {
			//1.Launch the application http://tutorialsninja.com/demo/index.php
			System.setProperty("webdriver.chrome.driver","C:\\Users\\Reka\\Drivers\\chromedriver.exe");
			driver=new ChromeDriver();	
		}
		
// 1. Launch the below application https://opensource-demo.orangehrmlive.com/web/index.php/auth/login
//and login with give credentials 			
		@Test(priority=2)
		public void loginPage()  {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
			driver.get(URL);
			driver.findElement(userName).sendKeys("Admin");
			driver.findElement(passWord).sendKeys("admin123");
			driver.findElement(logIn).click();
		}
		
//2. Navigate to My info - > Contact Details, enter contact details and press save button 		
		@Test(priority=3)
		public void myInfo() throws InterruptedException  {	
		driver.findElement(info).click();
		driver.findElement(addContact).click();	
		//driver.findElement(street1).clear();
		driver.findElement(street1).sendKeys(Keys.CONTROL + "A");
		driver.findElement(street1).sendKeys(Keys.CONTROL + "X");
		driver.findElement(street1).sendKeys("independence");
		driver.findElement(street2).sendKeys(Keys.CONTROL + "A");
		driver.findElement(street2).sendKeys(Keys.CONTROL + "X");
		driver.findElement(street2).sendKeys("coit");	
		//driver.findElement(city).clear();
		driver.findElement(city).sendKeys(Keys.CONTROL + "A");
		driver.findElement(city).sendKeys(Keys.CONTROL + "X");
		driver.findElement(city).sendKeys("chennai");
		//driver.findElement(state).clear();
		driver.findElement(state).sendKeys(Keys.CONTROL + "A");
		driver.findElement(state).sendKeys(Keys.CONTROL + "X");
		driver.findElement(state).sendKeys("TN");
		driver.findElement(zip).sendKeys(Keys.CONTROL + "A");
		driver.findElement(zip).sendKeys(Keys.CONTROL + "X");
		//driver.findElement(zip).clear();
		driver.findElement(zip).sendKeys("60000");
		//driver.findElement(telephone).clear();
		driver.findElement(telephone).sendKeys(Keys.CONTROL + "A");
		driver.findElement(telephone).sendKeys(Keys.CONTROL + "X");
		driver.findElement(telephone).sendKeys("1234567890");			
		driver.findElement(saveInfo).click()	;		
		}
		
//3. Navigate to Emergency contacts and add details 		
		@Test(priority=4)
		public void emergencyContacts() throws InterruptedException {
		driver.findElement(emergencyContact).click();
		driver.findElement(addEmerCont).click();
		//driver.findElement(emerContName).clear();
		driver.findElement(emerContName).sendKeys(Keys.CONTROL + "A");
		driver.findElement(emerContName).sendKeys(Keys.CONTROL + "X");
		driver.findElement(emerContName).sendKeys("Roja");
		driver.findElement(emerRelation).sendKeys(Keys.CONTROL + "A");
		driver.findElement(emerRelation).sendKeys(Keys.CONTROL + "X");
		//driver.findElement(emerRelation).clear();
		driver.findElement(emerRelation).sendKeys("self-Strong ");		
		//driver.findElement(emerPhone).clear();
		driver.findElement(emerPhone).sendKeys(Keys.CONTROL + "A");
		driver.findElement(emerPhone).sendKeys(Keys.CONTROL + "X");
		driver.findElement(emerPhone).sendKeys("1023456344");
		driver.findElement(saveEmer).click()	;		
		}
	
//4. Navigate to Dependents and add dependents 		
		@Test(priority=5)
		public void dependents() throws InterruptedException
		{
			driver.findElement(depenLink).click();
			driver.findElement(addDepen).click();
			//driver.findElement(depenName).clear();
			driver.findElement(depenName).sendKeys(Keys.CONTROL + "A");
			driver.findElement(depenName).sendKeys(Keys.CONTROL + "X");
			driver.findElement(depenName).sendKeys("HariDarshana");	
			driver.findElement(By.className("oxd-select-text-input")).click();
			driver.findElement(By.xpath("//div[@role='listbox']//*[contains(text(),'Child')]")).click();///////hidden list NOTE
			driver.findElement(saveDepen).click();
		}
		
//5. Navigate to recruitment menu and add a candidate 
		@Test(priority=6)
		public void recruitment() throws InterruptedException {
		driver.findElement(recruitLink).click();
		driver.findElement(addCand).click();
		//JavascriptExecutor jse = (JavascriptExecutor)driver;
		driver.findElement(candFirstName).clear();
		driver.findElement(candFirstName).sendKeys("Rani");
		driver.findElement(candLastName).clear();
		driver.findElement(candLastName).sendKeys("NV");
		driver.findElement(By.className("oxd-select-text-input")).click();
		driver.findElement(By.xpath("//div[@role='listbox']//*[contains(text(),'Software Engineer')]")).click();////HIDDEN LIST NOTE IT
		driver.findElement(candEmail).clear();
		driver.findElement(candEmail).sendKeys("rhea@gmail.com");
		driver.findElement(saveCand).click();		
		}

//6. Shortlist the candidate and schedule the interview
		@Test(priority=7)
		public void shortlist() throws InterruptedException {
			driver.findElement(shortListCand).click();	
			driver.findElement(saveShortListCand).click();
			driver.findElement(schedInterview).click();
			Thread.sleep(2000);
			driver.findElement(interviewTitle).sendKeys("Testing ");							
			WebElement interviewer=driver.findElement(By.xpath("//input[@include-employees='onlyCurrent']"));
			interviewer.click();
			interviewer.sendKeys("odis" +Keys.DOWN);
			Thread.sleep(2000);
			driver.findElement(By.xpath("//div[@role='listbox']//*[contains(text(),'Odis  Adalwin')]")).click();		
			driver.findElement(interviewDate).sendKeys("2023-03-03");
			driver.findElement(saveInterview).click();
			Thread.sleep(2000);
		}
		
//7. Mark the interview passed and offer job 
		@Test(priority=8)
		public void passed() {
		driver.findElement(markInterviewPassed).click();
		driver.findElement(saveIntPass).click();
		driver.findElement(offerJob).click();
		driver.findElement(saveOffer).click();
		}
		
//8. Hire the candidate and print the candidate history(table) 
		@Test(priority=9)
		public void hire() throws InterruptedException {
		driver.findElement(hire).click();
		driver.findElement(saveHire).click();
		//Thread.sleep(1000);
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		//jse.executeScript("scroll(0, 7000)");
		WebElement table=driver.findElement(By.xpath("//div[@role='rowgroup']"));
		jse.executeScript("arguments[0].scrollIntoView()", table);
		//jse.executeScript("arguments[0].click()", table);
		int rows=driver.findElements(By.xpath("//div[@role='rowgroup']/div")).size();
		System.out.println(" rows count:"+ rows );
	
		for(int i=1;i<=rows-1;i++)
		{ 
			WebElement data=driver.findElement(By.xpath("//div[@role='rowgroup']/div["+i+"]"));
			String each=data.getText();
			System.out.println();
			System.out.println(each );
			}		
		}

//9. Navigate back to the Recruitment tab and search for the above candidate and verify the status 
		@Test(priority=10)
		public void verifyStatus() throws InterruptedException {
			driver.findElement(recruitLink).click();
			
			Thread.sleep(2000);	
			driver.findElement(By.xpath("//form/div[1]/div/div[2]//div[@class='oxd-select-text-input']")).click();
			driver.findElement(By.xpath("//div[@role='listbox']//*[contains(text(),'Software Engineer')]")).click();
			
			//driver.findElement(By.className("oxd-select-text-input")).click();
			WebElement CandidateNmae=driver.findElement(By.xpath("//input[@placeholder='Type for hints...']"));
			CandidateNmae.click();	
			CandidateNmae.sendKeys("Rani" +Keys.DOWN );
			//CandidateNmae.click();
			//Thread.sleep(2000);
			driver.findElement(By.xpath("//div[@role='listbox']//*[contains(text(),'Rani  NV')]")).click();
			//driver.findElement(By.xpath("//div[@role='listbox']")).click();
		    driver.findElement(By.xpath("//button[@type='submit']")).click();	
		}
//10. Navigate to the Admin tab and create a user with ESS role for the above employee 
		@Test(priority=11)
		public void ess() throws InterruptedException {
			driver.findElement(adminLink).click();
			driver.findElement(addUser).click();
			driver.findElement(By.xpath("//form//div[1]/div/div[2]//div[@class='oxd-select-text-input']")).click();
			driver.findElement(By.xpath("//div[@role='listbox']//*[contains(text(),'ESS')]")).click();
			
			WebElement EmployeeNmae=driver.findElement(By.xpath("//input[@placeholder='Type for hints...']"));
			EmployeeNmae.click();	
			Thread.sleep(2000);
			EmployeeNmae.sendKeys("odis" +Keys.DOWN );
			driver.findElement(By.xpath("//div[@role='listbox']//*[contains(text(),'Odis  Adalwin')]")).click();

			driver.findElement(By.xpath("//form//div[1]/div/div[3]//div[@class='oxd-select-text-input']")).click();
			driver.findElement(By.xpath("//div[@role='listbox']//*[contains(text(),'Enabled')]")).click();	
			
			driver.findElement(By.xpath("//div[1]/div/div[4]/div/div[2]/input")).sendKeys("demo&6789#");	
			Thread.sleep(1000);
			driver.findElement(By.xpath("//form/div[2]/div/div[1]//input[@type='password']")).sendKeys("Dub@NN@22");
			driver.findElement(By.xpath("//form/div[2]/div/div[2]//input[@type='password']")).sendKeys("Dub@NN@22");		
			driver.findElement(By.xpath("//button[@type='submit']")).click();
			Thread.sleep(2000);
		}
	
//11. Logout from the application and login with the above created username & password 
		
		@Test(priority=12)
		public void essCredential() throws InterruptedException {
			driver.findElement(By.xpath("//span/p[@class='oxd-userdropdown-name']")).click();
			driver.findElement(By.xpath("//a[contains(text(),'Logout')]")).click();	
			driver.findElement(By.name("username")).sendKeys("demo&6789#");
			driver.findElement(By.name("password")).sendKeys("Dub@NN@22");
			driver.findElement(By.xpath("//button[@type='submit']")).click();
		}
		
//12. Navigate to leave menu and apply personal leave, Logout from the application 	
		@Test(priority=13)
		public void leave() throws InterruptedException {
			//driver.findElement(By.xpath(""))
	
			driver.findElement(By.xpath("//*[@id='app']//ul/li[2]/a")).click();
			driver.findElement(By.xpath("//header/div[2]/nav/ul/li[1]/a")).click();
			
			try {
				driver.findElement(By.xpath("//div[@class='oxd-select-text-input']")).click();
				//driver.findElement(By.xpath("//div[@class='oxd-select-text-input']")).click();
				//driver.findElement(By.xpath("//form/div[1]/div/div[3]//div[@class='oxd-select-text-input']")).click();
				driver.findElement(By.xpath("//div[@role='listbox']//*[contains(text(),'US - Personal')]")).click();
				driver.findElement(By.xpath("//*[@id='app']//form/div[2]/div/div[1]//input")).sendKeys("2022-12-31");
				driver.findElement(By.xpath("//*[@id='app']//form/div[2]/div/div[2]//input")).sendKeys("2022-12-31");
				Thread.sleep(2000);
				driver.findElement(By.xpath("//*[@id='app']//form/div[4]//textarea")).sendKeys("Not Feeling Well....Need Full Day Leave.......Please approve my leave");
//				WebElement message=driver.findElement(By.xpath("//div[@class='orangehrm-card-container']/p"));
//				System.out.println(message.getText());
				driver.findElement(By.xpath("//button[@type='submit']"));
			} catch (StaleElementReferenceException e) {
				// TODO: handle exception
			}

//			WebElement message=driver.findElement(By.xpath("//div[@class='orangehrm-card-container']/p"));
//			System.out.println(message.getText());
			driver.findElement(By.xpath("//span/p[@class='oxd-userdropdown-name']")).click();
			driver.findElement(By.xpath("//a[contains(text(),'Logout')]")).click();	
		}
//13.Login with the Admin user again and navigate to Leave menu 
		@Test(priority=14)
		public void adminLaeve() {
			//driver.get(URL);
			driver.findElement(userName).sendKeys("Admin");
			driver.findElement(passWord).sendKeys("admin123");
			driver.findElement(logIn).click();
			driver.findElement(By.xpath("//*[@id='app']//ul/li[3]/a")).click();		
		}
//14. Approve the above applied leave under leave list and logout 
		@Test(priority=15)
		public void approve() throws InterruptedException {
			//driver.findElement(By.xpath("//header//ul/li[1]/a")).click();
			driver.findElement(By.xpath("//a[contains(text(),'Leave List')]")).click();	
			Thread.sleep(500);
			driver.findElement(By.xpath("//form/div[1]/div/div[1]//div[@class='oxd-date-input']")).sendKeys("2022-12-31");
			driver.findElement(By.xpath("//form/div[1]/div/div[2]//div[@class='oxd-date-input']")).sendKeys("2022-12-31");
			driver.findElement(By.xpath("//form//div[1]/div/div[3]//div[@class='oxd-select-text-input']")).click();
			driver.findElement(By.xpath("//div[@role='listbox']//*[contains(text(),'Pending Approval ')]")).click();
			WebElement noLeaveMessage=driver.findElement(By.xpath("//div[@id='app']//div[@id='oxd-toaster_1']"));
			System.out.println(noLeaveMessage.getText());
			
			
			driver.findElement(By.xpath("//form//div[1]/div/div[4]//div[@class='oxd-select-text-input']")).click();
			driver.findElement(By.xpath("//div[@role='listbox']//*[contains(text(),'US - Personal')]")).click();
			WebElement EmpleaveName=driver.findElement(By.xpath("//*[@id='app']//form/div[2]/div/div[1]//input"));
			EmpleaveName.click();	
			Thread.sleep(2000);
			EmpleaveName.sendKeys("odis" +Keys.DOWN );
			driver.findElement(By.xpath("//div[@role='listbox']//*[contains(text(),'Odis  Adalwin')]")).click();
			driver.findElement(By.xpath("//button[@type='submit']")).click();
			
			WebElement noLeaveMessage1=driver.findElement(By.xpath("//div[@id='app']//div[@id='oxd-toaster_1']"));
			System.out.println(noLeaveMessage1.getText());
			
			WebElement table=driver.findElement(By.xpath("//div[@role='rowgroup']"));
			JavascriptExecutor jse = (JavascriptExecutor)driver;
			jse.executeScript("arguments[0].scrollIntoView()", table);
			//jse.executeScript("arguments[0].click()", table);
			int rows=driver.findElements(By.xpath("//div[@role='rowgroup']/tr")).size();   //div[@role='rowgroup']/div
			System.out.println(" rows count:"+ rows );
			int columns=driver.findElements(By.xpath("//div[@role='rowgroup']/tr[1]//td")).size();
			System.out.println(" rows count:"+ columns );
			for(int i=1;i<=rows-1;i++)
				{ 
				for(int j=1;j<=columns-1;j++)
				{
				WebElement data=driver.findElement(By.xpath("//div[@role='rowgroup']/tr["+i+"]/td["+j+"]"));
				String each=data.getText();
				if(each=="Odis  Adalwin" )
				{
					driver.findElement(By.xpath("//div[@role='rowgroup']/tr["+i+"]/td[9]"));
					data.click();
				}
				System.out.println();
				System.out.println(each );
				}		
				}
		
			driver.findElement(By.xpath("//span/p[@class='oxd-userdropdown-name']")).click();
			driver.findElement(By.xpath("//a[contains(text(),'Logout')]")).click();
		
		}
}
