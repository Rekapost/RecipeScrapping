package ScrapTestCases;
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
import commonUtilities.common_Utilities;

public class PCOS_TestCase extends BaseClass{
	List<WebElement> recipeTitle;
	String eachRecipeNutri;
	List<WebElement> recipeCookingTime;
	String eachRecipecookTime;
	List<WebElement> recipeID;
	String eachRecipeID;
	List<WebElement> recipeMethod;
	String eachRecipeMethod;
	List<WebElement> recipeNutrition;
	String eachRecipeNutrition;
	List<WebElement> recipeIngredients;
	WebElement recipe_Title;
	String eachRecipeIng;
	WebElement pagei;
	String nutri;
	int noOfRows;
	int i;
//	int p;
	String search_input;
	ExcelReadWrite reader;
	String search_data;
	String current_url;
	String current_url2;
	List<WebElement> nofrec;
	List<String> PCOS_eli_List;
	List<String> receipeIDList = new ArrayList<>();
	int r;
	int pgSize;
	int row_Size;
	common_Utilities common = new common_Utilities();

	@DataProvider(name = "inputData")
	public String[][] getExcelData() throws IOException {
		String path = "C:\\Users\\Reka\\eclipse-workspace\\webscrapping\\src\\test\\resources\\ScrapeData\\PCOS_Input.xlsx";
		reader = new ExcelReadWrite(path);
		noOfRows = reader.getRowCount("PCOS_list");
		int noOfColumns = reader.getCellCount("PCOS_list", 1); // 1 is rownumber

		String[][] dataTable = new String[noOfRows][noOfColumns];
		for (int m = 1; m <= noOfRows; m++) {
			// 0 row is header , so i=1
			for (int j = 0; j < noOfColumns; j++) {
				// j=0 ,< ; j=1 ,<=
				dataTable[m - 1][j] = reader.getCellData("PCOS_list", m, j); // 0,0= 1,0
			}
		}
		return dataTable;
	}

	@Test(dataProvider = "inputData") // (String searchinput)
	public void searchWithInput(String p, String searchinput) throws IOException, InterruptedException {
		List<String[]> scrapedData = new ArrayList<String[]>();

		PCOS_eli_List = new ArrayList<String>();
		System.out.println("Opening tarladalal website ");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		Actions action = new Actions(driver);
		System.out.println(" present  url : " + current_url);

//		WebElement search = driver.findElement(By.cssSelector("#ctl00_txtsearch"));
		JavascriptExecutor js = (JavascriptExecutor) driver;

		// *********************** RETRIEVE SEARCH INPUT DATA FROM EXCEL

		String path = "C:\\Users\\Reka\\eclipse-workspace\\webscrapping\\src\\test\\resources\\ScrapeData\\PCOS_Input.xlsx";
		reader = new ExcelReadWrite(path);
		int rowSize = reader.getRowCount("PCOS_list");
		System.out.println(" row size " + rowSize);
		System.out.println("Reading excel ");
		Thread.sleep(1000);
		
// reading diabetes eliminated list		
		String PCOS = reader.getCellData("PCOS_eliminated", 0, 0);
		int pcoRowSize = reader.getRowCount("PCOS_eliminated");
		System.out.println(" row size " + pcoRowSize);
		System.out.println("Reading excel ");

		for (int d = 1; d <= pcoRowSize; d++) {
			String PCOS_data = reader.getCellData("PCOS_eliminated", d, 0);
			
			PCOS_eli_List.add(PCOS_data);
			
/// *******************
		}
		System.out.println("search input " + searchinput);
//*******************
		try {
			current_url = "https://www.tarladalal.com/RecipeSearch.aspx?rec=1&term" + "=" + searchinput;
			driver.get(current_url);

			System.out.println(" inside  url : " + current_url);
		} catch (StaleElementReferenceException e) {
			System.out.println("exception loop " + searchinput);
			e.printStackTrace();
		}
		Thread.sleep(1000);
		if (searchinput.equalsIgnoreCase("PCOS Indian")) {
			List<WebElement> pagelist = driver.findElements(By.xpath("//*[@id='cardholder']/div[4]/a"));
			pgSize = pagelist.size();
			System.out.println(" page size no:" + pagelist.size());
		}

		else {
			List<WebElement> pagelist = driver.findElements(By.xpath("//*[@id='cardholder']/div[3]/a"));
			pgSize = pagelist.size();
			System.out.println(" page size no:" + pagelist.size());
		}


		int noOfRecipePerPage1 = driver.findElements(By.xpath("//div[@id='maincontent']//div[@class='rcc_recipecard']"))
				.size();
		System.out.println("No.of Rows in a page:" + noOfRecipePerPage1);

		int noOfRecipePerPage = driver.findElements(By.xpath("//div[@id='maincontent']//div[@class='rcc_recipecard']"))
				.size();

		nofrec = driver.findElements(By.xpath("//div[@class='rcc_recipecard']"));
		List<WebElement> lstReceipes = driver.findElements(By.xpath("html/body//div[@class='rcc_recipecard']"));
		row_Size = lstReceipes.size();
		receipeIDList = new ArrayList<>();
		for (WebElement w : lstReceipes) {
			// System.out.println("w ell :" +w.getText());
			String recipe = "rcp" + w.getText().substring(8, 13);
			// System.out.println("receipe id :" +recipe);
			receipeIDList.add(recipe);
		}
//						 r=1;	i=1;
//						 for ( r = 1; r <= noOfRecipePerPage; r++)  {
		for (int x = 1; x <= pgSize; x++) {
			System.out.println("test1first");
			if (x > 1) {
				if (searchinput.equalsIgnoreCase("PCOS Indian")) {

					WebElement pageNum = driver.findElement(By.xpath("//*[@id='cardholder']/div[4]/a[" + x + "]"));
					String pagNumber = pageNum.getText();
					pageNum.click();
					System.out.println("Page Number : " + pagNumber);
					lstReceipes = driver.findElements(By.xpath("html/body//div[@class='rcc_recipecard']"));
					row_Size = lstReceipes.size();
					receipeIDList = new ArrayList<>();
					for (WebElement w : lstReceipes) {
						String recipe = "rcp" + w.getText().substring(8, 13);
						receipeIDList.add(recipe);
					}

					nofrec = driver.findElements(By.xpath("//div[@class='rcc_recipecard']"));
					// div[@id='cardholder']//div[@style='text-align:right;padding-bottom:15px;']/a[@class='respglink']
					// and text()='"+x+"']
				} else {
					WebElement pageNum = driver.findElement(By.xpath("//*[@id='cardholder']/div[3]/a[" + x + "]"));
					String pagNumber = pageNum.getText();
					pageNum.click();
					System.out.println("Page Number : " + pagNumber);
					lstReceipes = driver.findElements(By.xpath("html/body//div[@class='rcc_recipecard']"));
					row_Size = lstReceipes.size();
					receipeIDList = new ArrayList<>();
					for (WebElement w : lstReceipes) {
						String recipe = "rcp" + w.getText().substring(8, 13);
						receipeIDList.add(recipe);
						nofrec = driver.findElements(By.xpath("//div[@class='rcc_recipecard']"));
					}
				}
			}

//			r = 1;
			for (int r = 0; r < row_Size;) {
				System.out.println("test3first");
				System.out.println("r value:" + r);
				System.out.println("No.of Rows in a page:" + noOfRecipePerPage);

				Thread.sleep(2000);
				js.executeScript("window.scrollBy(0,250)", "");
//							we.getTagName()
				if (r == 8) {
					js.executeScript("window.scrollBy(0,250)", "");
					Thread.sleep(2000);
					r = 9;
				}

//				recipe_Title = driver.findElement(By.xpath(
//						"/html/body/div[2]/form/div[3]/div[2]/div/div[1]/div[2]/div["+r+"]/div[3]/span[1]/a"));

				// if(searchinput.equalsIgnoreCase("Vegan Diabetic")) {
				if (receipeIDList.size() == 0)
					return;
				WebElement recipNam = driver
						.findElement(By.xpath("//*[@id='" + receipeIDList.get(r) + "']/div[3]/span[1]/a"));
				System.out.println("RecipeIDList : " + receipeIDList.get(r));

				// Receipe Name
				String recipeName = recipNam.getText();
				System.out.println("\t*******");
				System.out.println("Recipe Name : " + recipeName);
				//recipNam.click();
                WaitForElement(recipNam);
				Thread.sleep(1000);
//				js.executeScript("window.scrollBy(0,250)", "");
//				Thread.sleep(1000);

				WebElement ingredients = driver.findElement(By.xpath("//*[@id='rcpinglist']"));
				String RecpieIngredients = ingredients.getText();

				boolean isContainEliminatedItems = common.hasEliminatedLists(PCOS_eli_List, RecpieIngredients);

				if (isContainEliminatedItems) {
					System.out.println(" Recipe is not recomeneded as it has Ingredients from Eliminated List");
					driver.navigate().back();
//					driver.navigate().refresh();
					r++;
				} else {
					System.out.println("recipe#" + r);

					System.out.println(" Recipe need to be printed");

					WebElement recipeTitlehead = driver
							.findElement(By.xpath("//div[@id='recipehead']//h1//span[@itemprop='name']"));
					String title = recipeTitlehead.getText();

					String url = driver.getCurrentUrl();
//				System.out.println(driver.getCurrentUrl());
					// eachData.put("Recipe URL", url);

					String recipe_id = url.replaceAll("[^0-9]", "");
					System.out.println("Recipe #: " + recipe_id);
					System.out.println();
					// eachData.put("Recipe ID", recipe_id);

					String reciepename = null;
					List<WebElement> Recipename = driver.findElements(By.id("ctl00_cntrightpanel_lblRecipeName"));
					reciepename = Recipename.get(0).getText();

					String pageTiltle = driver.getTitle();
//								System.out.println(driver.getTitle());
					// eachData.put("Page Title", pageTiltle);

					js.executeScript("window.scrollBy(0,2000)", "");
					WebElement cookTime = driver.findElement(By.xpath(
							"//div[@id='maincontent']//*[@id='ctl00_cntrightpanel_pnlRecipeScale']/section/p[2]/time[@itemprop='prepTime']"));
					String cook_time = cookTime.getText();
					// eachData.put("RecipeCookTime", cook_time);

					WebElement prepTime = driver.findElement(By.xpath(
							"//div[@id='maincontent']//*[@id='ctl00_cntrightpanel_pnlRecipeScale']/section/p[2]/time[@itemprop='cookTime']"));
					String prep_time = prepTime.getText();
					// eachData.put("RecipePrepTime", prep_time);

//				WebElement morbidCondition  = driver.findElement(By.xpath("//img[@src='images/recipe/diabetic.gif']"));
//				String morbid_condition=morbidCondition.getAttribute("src");

					WebElement recipeMethod = driver
							.findElement(By.xpath("//*[@id='ctl00_cntrightpanel_pnlRcpMethod']"));
					String method = recipeMethod.getText();
					// eachData.put("Preparation Method", method);

					WebElement recipeNutrition = driver.findElement(By.xpath("//div[@id=\"accompaniments\"]"));
					String Nutritionlist = recipeNutrition.getText();
					// eachData.put("Nutrient values", Nutritionlist);

//				WebElement ingredients = driver.findElement(By.xpath("//*[@id='rcpinglist']"));
//				String RecpieIngredients = ingredients.getText();

					driver.navigate().back();
//				// Thread.sleep(1000);

					String[] recipeData = { recipe_id, reciepename, title, RecpieIngredients, prep_time, cook_time,
							method, Nutritionlist, url };
					scrapedData.add(recipeData);
					if (searchinput.equalsIgnoreCase("PCOS Indian")) {
						try {
							common.writeDataToExcel(scrapedData, "PCOS Indian", searchinput);
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}

					else if (searchinput.equalsIgnoreCase("PCOS breakfast")) {
						try {
							common.writeDataToExcel(scrapedData, "PCOS breakfast", searchinput);
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					} 
					else if (searchinput.equalsIgnoreCase("PCOS Weight Loss")) {
						try {
							common.writeDataToExcel(scrapedData, "PCOS Weight Loss", searchinput);
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					} 
									
					else {
						System.out.println("No input located");
					}
					driver.navigate().refresh();
					r++;

				}

			}
		}

	}
}
