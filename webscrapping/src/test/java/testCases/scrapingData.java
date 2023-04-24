package testCases;

import java.awt.AWTException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Reader;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.compress.archivers.dump.InvalidFormatException;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

//import utilities.Loggerload;

//import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class scrapingData {
	
	  static ExcelReadWrite Reader = new ExcelReadWrite();
	public static WebDriver driver;
	
	
	public static void main(String[] args) throws InterruptedException, IOException {
 
		driver = new ChromeDriver();
		driver.manage().window().maximize();
//		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		
		

		   		
		driver.get("https://www.tarladalal.com/");
		WebElement search=driver.findElement(By.id("ctl00_txtsearch"));
		search.sendKeys("High Blood Pressure recipes");
		WebElement submit=driver.findElement(By.id("ctl00_imgsearch"));
		submit.click();
		jse.executeScript("window.scrollBy(0,500)");


	
		List<WebElement> pagenum = driver.findElements(By.xpath("//div[@id='maincontent']/div/div[@id='cardholder']/div[3]//a"));
 		int pagination=pagenum.size(); 
		System.out.println("PageSize: " + pagination);
		
//		int noofRecipePerPage= driver.findElements(By.xpath("//div[@class='rcc_recipecard']")).size();
//		System.out.println("Number of rows in a page: " + noofRecipePerPage);
		
		int totalpage=0;
		
		if (pagenum.size() > 0) {
			System.out.println("pagination exists");
			totalpage = Integer.parseInt(pagenum.get(pagination - 1).getText());
		}

		else {
			System.out.println("pagination not exists");
		}

		System.out.println(totalpage);

		List<Object[]> scrapedData = new ArrayList<Object[]>();
		
		for( int i=1;i<=totalpage;i++)
		{
			System.out.println("currentPge" + i);

			List<WebElement> recipelist = driver.findElements(By.xpath("//div[@class='rcc_recipecard']"));
			
			int pagesize = recipelist.size();
			
			System.out.println("total recipes in page:" + pagesize);

			Thread.sleep(500);
try {
			driver.findElement(By.xpath("//div[@id='maincontent']/div/div[@id='cardholder']/div[3]//a[" + i + "]"))
					.click();
}catch(Exception e)
{
	System.out.println("Exception occcured ::"+e);	
					}
//			Thread.sleep(500);
//			WebElement page=driver.findElement(By.xpath("//div[@id='maincontent']/div/div[@id='cardholder']/div[3]//a["+i+"]"));
// 			page.click();
//			
//			jse.executeScript("window.scrollBy(250,2000)");	
//			
//			  List<String[]> scrapedData = new ArrayList<String[]>();
//			  
			                  for(int j=1;j<=pagesize;j++)
			                  {
			                	  	try {		        	
			                	   
						              
						              List<WebElement> RecipeID = driver.findElements(By.xpath("//div[@class='rcc_recipecard']["+j+"]/div[2]/span[1]"));
							             String receipeId = null;
							        
							              if (RecipeID.isEmpty() || RecipeID.size()==0 || RecipeID ==null) {
							        	
							           
							        	   List<WebElement> altRecipeID = driver.findElements(By.xpath("//div[@id='maincontent']/div/div[2]/div["+j+"]/div[2]/span"));
							        	if (!altRecipeID.isEmpty()) {
							        	receipeId = altRecipeID.get(0).getText();
							        	System.out.println("Recipe id:"+altRecipeID.get(0).getText());
							        	}
							             }else {
							        	receipeId = RecipeID.get(0).getText();
							        	System.out.println("receipeID"+RecipeID.get(0).getText());
							        	
							             }

//							              WebElement firstrecipeTitle = driver.findElement(By.xpath("//div[@class='rcc_recipecard']["+j+"]/div[3]/span[1]/a"));
//										     firstrecipeTitle.click(); 
//										     
//							      String Recipename = driver.findElement(By.id("ctl00_cntrightpanel_lblRecipeName")).getText();
//							       
//							      System.out.println("Receipe name "+Recipename);
//							      
										     List<WebElement> Recipename = driver.findElements(By.id("ctl00_cntrightpanel_lblRecipeName"));
										     
//										     System.out.println("Receipe name "+Recipename);
//											    
										     String reciepename=null; 
									
										     
							              if (Recipename.isEmpty() || Recipename.size() == 0 || Recipename == null) {

												List<WebElement> altRecipename = driver.findElements(
														By.xpath("//div[@id='maincontent']/div/div[2]/div[" + j + "]/div[3]/span[1]/a"));
												if (!altRecipename.isEmpty()) {
													reciepename = altRecipename.get(0).getText();
													System.out.println("Recipe Name:" + altRecipename.get(0).getText());
													altRecipename.get(0).click();
												}
											} else {
												reciepename = Recipename.get(0).getText();
												System.out.println("receipe Name" + Recipename.get(0).getText());
												Recipename.get(0).click();

											}
 						       
						       String url=driver.getCurrentUrl();
						       System.out.println(driver.getCurrentUrl());
						       
						       WebElement ingredients = driver.findElement(By.xpath("//div[@id='rcpinglist']"));
								System.out.println("ingredients name " + ingredients.getText());
								String ingredientsName = ingredients.getText();

								WebElement method = driver.findElement(By.xpath("//div[@id='recipe_small_steps']"));
								System.out.println("ingredientmethod " + method.getText());
								String methodName = method.getText();
								
								String nutrientValue = null;
								String preparationTimeSt = null;
								String cookTime = null;
								try {
									 WebElement NutrientValue = driver.findElement(By.id("rcpnutrients"));
								       
								      	    System.out.println("\n Nutrient Values :"+ NutrientValue.getText());
								       
								      	  nutrientValue=NutrientValue.getText();
									
							     WebElement preparationTime = driver.findElement(By.xpath("//time[@itemprop='prepTime']"));
							       System.out.println("\n Preparation Time "+preparationTime.getText());
							       preparationTimeSt=preparationTime.getText();
							       
							       WebElement cookingTime = driver.findElement(By.xpath("//time[@itemprop='cookTime']"));
							       System.out.println("\n Cooking Time :"+cookingTime.getText());
							       cookTime=cookingTime.getText();
								}
								catch(Exception e)
								{
									System.out.println("Exception occured ::"+e);
								}
 					       
						          
						       driver.navigate().back();
			                  
			                  
			Object[] recipeData= {receipeId, reciepename, ingredientsName,url,nutrientValue, methodName,      
					preparationTimeSt, cookTime};
			scrapedData.add(recipeData);
			Reader.writeToExcel(scrapedData);
			                  }
			catch(Exception e)
			{
				System.out.println("Exception occured ::"+e);
			}
			                  
			                  }
//			                  driver.close();                 
		}}}