package testCases;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class DDT extends BaseClass{
	List<WebElement> recipeTitle ;
	String eachRecipeNutri;
	List<WebElement> recipeCookingTime ;
	String eachRecipecookTime;
	List<WebElement> recipeID ;
	String eachRecipeID;
	List<WebElement> recipeMethod ;
	String eachRecipeMethod;
	List<WebElement> recipeNutrition ;
	String eachRecipeNutrition;
	List<WebElement> recipeIngredients ;
	WebElement recipe_Title;
	String eachRecipeIng;
	String nutri;
	int noOfRows;
	int i;
//	int p;
	String search_input;
	ExcelReadWrite reader;
	String search_data;
	String current_url;
	String current_url2;
	int r;

	@DataProvider(name="loginData")
	public String[][] getExcelData() throws IOException
	{		
		String path="C:\\Users\\Reka\\eclipse-workspace\\webscrapping\\src\\test\\resources\\Eliminated_List2.xlsx";
		 reader = new ExcelReadWrite(path );
		noOfRows = reader.getRowCount("Sheet5");
	    int noOfColumns = reader.getCellCount("Sheet5", 1); //1 is rownumber
	    
	    String[][] dataTable = new String[noOfRows][noOfColumns];
	    for (int m = 1; m <=noOfRows; m++) {  
	    	// 0 row is header , so i=1
	        for (int j = 0; j < noOfColumns; j++) {   
	        	  //j=0 ,<  ; j=1 ,<=
	            dataTable[m-1][j] = reader.getCellData("Sheet5",m,j);   //0,0= 1,0
	        }      
	    }
	    return dataTable;	   
	}	
	
	@Test (dataProvider = "loginData")      // (String searchinput)
	public void loginWithInput( String p,String searchinput) throws IOException, InterruptedException
	{
		List<String> recipeIDList = new ArrayList<String>();
		System.out.println("Opening tarladalal website ");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		Actions action =new Actions(driver);		
	    System.out.println(" present  url : "+ current_url);		
		
		WebElement search= driver.findElement(By.cssSelector("#ctl00_txtsearch"));
		JavascriptExecutor js = (JavascriptExecutor) driver;
				
		// *********************** RETRIEVE SEARCH INPUT DATA FROM EXCEL ************************************					
				String path="C:\\Users\\Reka\\eclipse-workspace\\webscrapping\\src\\test\\resources\\Eliminated_List2.xlsx";
				reader = new ExcelReadWrite(path );
				int rowSize=reader.getRowCount("Sheet5");
				System.out.println(" row size "+ rowSize);
				System.out.println("Reading excel ");  
				Thread.sleep(1000);
					 //&&   search_data=reader.getCellData("Sheet5", i, 0);
						System.out.println("search input "+searchinput);
//						searchInputArray.add(search_input);  ///*******************
						try {
							 current_url= "https://www.tarladalal.com/RecipeSearch.aspx?rec=1&term"+"="+searchinput;
							 driver.get(current_url);

							  System.out.println(" inside  url : "+ current_url);
						}catch(StaleElementReferenceException e)	{
							System.out.println("exception loop "+searchinput);
						e.printStackTrace()	;
						}
						Thread.sleep(1000);

						List<WebElement> pagelist=driver.findElements(By.xpath("//*[@id='cardholder']/div[2]/a"));
						int pgSize = pagelist.size(); 
						System.out.println(" page size no:"+ pagelist.size());
						   
						int noOfRecipePerPage1=driver.findElements(By.xpath("//div[@id='maincontent']//div[@class='rcc_recipecard']")).size();
						System.out.println("No.of Rows in a page:"+noOfRecipePerPage1);

						int noOfRecipePerPage=driver.findElements(By.xpath("//div[@id='maincontent']//div[@class='rcc_recipecard']")).size();
						List<WebElement> nofrec = driver.findElements(By.xpath("//div[@class='rcc_recipecard']"));
						System.out.println("No.of Rows in a page:"+noOfRecipePerPage);
						
//						 r=1;	i=1;
//						 for ( r = 1; r <= noOfRecipePerPage; r++)  {
							 r=1;
								for (WebElement we: nofrec) {
									if(r==5) {
										break;
									}
									
									
//									for(int r=1;r<=noOfRecipePerPage;r++) { 
															
									Thread.sleep(2000);
									js.executeScript("window.scrollBy(0,250)", "");
//									we.getTagName()
									if( r==8){
										js.executeScript("window.scrollBy(0,250)", "");
										Thread.sleep(2000);
										r=9;
									}
										
									recipe_Title=driver.findElement(By.xpath("/html/body/div[2]/form/div[3]/div[2]/div/div[1]/div[2]/div["+r+"]/div[3]/span[1]/a"));
																
								action.moveToElement(recipe_Title).click().perform();
								Thread.sleep(500);
								WebElement recipeTitlehead = driver.findElement(By.xpath("//div[@id='recipehead']//h1//span[@itemprop='name']"));
								String title=recipeTitlehead.getText();
								
								String url=driver.getCurrentUrl();			
								System.out.println(driver.getCurrentUrl());
								
								String pageTiltle=driver.getTitle();
								System.out.println(driver.getTitle());
								
								js.executeScript("window.scrollBy(0,2000)", "");
								WebElement cookTime = driver.findElement(By.xpath("//div[@id='maincontent']//*[@id='ctl00_cntrightpanel_pnlRecipeScale']/section/p[2]/time[@itemprop='prepTime']"));			
								String cook_time=cookTime.getText();
								
								WebElement prepTime = driver.findElement(By.xpath("//div[@id='maincontent']//*[@id='ctl00_cntrightpanel_pnlRecipeScale']/section/p[2]/time[@itemprop='cookTime']"));			
								String prep_time=prepTime.getText();
								
//								WebElement morbidCondition  = driver.findElement(By.xpath("//img[@src='images/recipe/diabetic.gif']"));
//								String morbid_condition=morbidCondition.getAttribute("src");
								
								WebElement recipeMethod = driver.findElement(By.xpath("//*[@id='ctl00_cntrightpanel_pnlRcpMethod']")); 								
								String method=recipeMethod.getText();
										
								WebElement recipeNutrition = driver.findElement(By.xpath("//div[@id=\"accompaniments\"]"));
								String Nutritionlist=recipeNutrition.getText();
														
								WebElement ingredients= driver.findElement(By.xpath("//*[@id='rcpinglist']"));
								String RecpieIngredients=ingredients.getText(); 
								
								driver.navigate().back();
								//Thread.sleep(1000);
								
//		   **************************** WRITING DATA INTO EXCEL ***********************************								
							//	for(int i=1;i<=noOfRows;i++) {			
									 reader.setCellData("Sheet"+p, 0, 0, "Recipe ID"); 
									 reader.setCellData("Sheet"+p, 0, 1, "Recipe Name");
									 reader.setCellData("Sheet"+p, 0, 2, "Page Title");
									 reader.setCellData("Sheet"+p, 0, 3, "Vegan Diabetic Food Category");
									 reader.setCellData("Sheet"+p, 0, 4, "Ingredients");
									 reader.setCellData("Sheet"+p, 0, 5, "RecipePrepTime");
									 reader.setCellData("Sheet"+p, 0, 6, "RecipeCookTime");
									 reader.setCellData("Sheet"+p, 0, 7, "Preparation Method");
									 reader.setCellData("Sheet"+p, 0, 8, "Nutrient values");
									 reader.setCellData("Sheet"+p, 0, 9, "Targetted morbid conditions (Diabeties/Hypertension/Hypothyroidism)");
									 reader.setCellData("Sheet"+p, 0, 10, "Recipe URL");
									
								//js.executeScript("arguments[0].click()", recipeTitle);			
								reader.setCellData("Sheet"+p, r, 1, title);
								reader.setCellData("Sheet"+p, r, 2, pageTiltle);				
								reader.setCellData("Sheet"+p, r, 4,RecpieIngredients );
								reader.setCellData("Sheet"+p, r, 5, prep_time);
								reader.setCellData("Sheet"+p, r, 6, cook_time);
								reader.setCellData("Sheet"+p, r, 7, method);
								reader.setCellData("Sheet"+p, r, 8,Nutritionlist);
//								reader.setCellData("Sheet"+p, r, 9, morbid_condition);
								reader.setCellData("Sheet"+p, r, 10, url);
						 
						 int k=1;
						 for (String eachIDList : recipeIDList ) {
							 eachRecipeID=eachIDList;
							 // writing the data in excel sheet	
							reader.setCellData("Sheet"+p, k, 0, eachRecipeID);
							k++;
							System.out.println(eachIDList);
							eachRecipeID=null;
						 }					 
					  driver.navigate().refresh();
						 r++;
							}								
					}
				}
			//}
//		 }
