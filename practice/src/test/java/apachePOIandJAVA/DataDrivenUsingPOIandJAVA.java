package apachePOIandJAVA;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.List;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
//**********************************************JAVA PROGRAM ***********************************//
public class DataDrivenUsingPOIandJAVA {
	static List<String> userNameList=new ArrayList<String>();
	static List<String> passWordList=new ArrayList<String>();
	public void readExcel() throws  IOException	{
		FileInputStream excel=new FileInputStream("C:\\Users\\Reka\\Desktop\\New\\Eclipse\\loginData1.xlsx");
		XSSFWorkbook workbook=new XSSFWorkbook(excel);
		//workBook is interface
		Sheet firstSheet= workbook.getSheetAt(0);
		//Sheet sheet= workbook.getSheet("Sheet1");
		Iterator<Row> rowIterator=firstSheet.iterator();
		while(rowIterator.hasNext())		{
			Row nextRow=rowIterator.next();
			Iterator<Cell> columnIterator=nextRow.iterator();
			int lastRow=firstSheet.getLastRowNum();
			/*  R  int i;
			for (i=1; i<lastRow;i++) {
				if(columnIterator.hasNext())
					//Cell cellValue=columnIterator.next();;
					//System.out.println(cellValue);*/
			/* LAO int i=1;
			while (columnIterator.hasNext())				{
				//Cell cellValue=columnIterator.next();
				//System.out.println(cellValue);
				if(i%2==0) 				{
					//Cell userNameList=columnIterator.next();
					//* OR userNameList.add(columnIterator.next().toString());	
					userNameList.add(columnIterator.next().getStringCellValue());
				}
				else				{
					//Cell passWordList=columnIterator.next();
					// OR  passWordList.add(columnIterator.next().toString());	
					passWordList.add(columnIterator.next().getStringCellValue());	
				}
				i++;
				}*/
		}
	}
	
	public void executeTest()	{	
		for (int i=0; i<userNameList.size();i++){
				loginWithCredentials(userNameList.get(i),passWordList.get(i));
			}
	}
	
	public void loginWithCredentials(String userName, String passWord) {
		System.setProperty("webdriver.chrome.driver","C:\\Users\\Reka\\Drivers\\chromedriver.exe");
		WebDriver driver=new ChromeDriver();
		driver.get("https://practice.automationtesting.in/my-account/");
		WebElement UsernameBox=driver.findElement(By.id("username"));
		UsernameBox.sendKeys(userName);
		WebElement passwordBox=driver.findElement(By.id("password"));
		passwordBox.sendKeys(passWord);
		WebElement login=driver.findElement(By.xpath("//*[@id=\'customer_login\']/div[1]/form/p[3]/input[3]"));
		login.click();  
		driver.quit();
	}

	public static void main(String[] args)  throws IOException	{
		// TODO Auto-generated method stub	
		DataDrivenUsingPOIandJAVA usingPOI =new DataDrivenUsingPOIandJAVA();
		usingPOI.readExcel();
		System.out.println("user name list"+userNameList);
		System.out.println("password list"+passWordList);
		usingPOI.executeTest();	
	}
}
