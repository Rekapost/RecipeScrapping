package Eli;

import java.awt.AWTException;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;
import testCases.ExcelReadWrite;
import utilities.ConfigReader;

public class Sample extends BaseClass {
	// *************************************** CLASS LEVEL VARIABLES
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
	String eachRecipeIng;
	String nutri;
	WebElement prepTime;
	int i;
	String search_input;
	ExcelReadWrite reader;
	String search_data;
	String current_url;
	String current_url2;
	int r;
	WebElement recipe_Title;

	// ********************************** LAUNCH BROWSER AND OPEN PAGE
	@Test(priority = 1)
	public void jobSearchTestCase() {
		System.out.println("Opening tarladalal website ");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	}

	// ******************* READING AND WRITING DATA INTO EXCEL
	@Test(priority = 2)
	public void readFromExcel() throws InvalidFormatException, IOException, InterruptedException, AWTException {
		List<String> recipeIDList = new ArrayList<String>();
		List<String> recipeIngredientList = new ArrayList<String>();
		List<String> diabetic_eli_List = new ArrayList<String>();
		List<String> diabetic_allergy_List = new ArrayList<String>();
		List<String> diabetic_toadd_List = new ArrayList<String>();
		Actions action = new Actions(driver);
		ConfigReader readConfig = new ConfigReader();
		// String Excelpath = readConfig.getexcelfilepath();
		String Excelpath = "C:\\Users\\Reka\\eclipse-workspace\\webscrapping\\src\\test\\resources\\Morbidity.xlsx";
		// ExcelReader reader = new ExcelReader( );
		reader = new ExcelReadWrite(Excelpath);

		WebElement search = driver.findElement(By.cssSelector("#ctl00_txtsearch"));
		int rowSize = reader.getRowCount("Sheet1");
		System.out.println(" row size " + rowSize);
		System.out.println("Reading excel ");
		for (int i = 1; i <= rowSize; i++) {
			Thread.sleep(1000);
			search_data = reader.getCellData("Sheet1", i, 4);
			System.out.println("search input " + search_data);
//					searchInputArray.add(search_input);  ///*******************
			try {
				current_url = "https://www.tarladalal.com/RecipeSearch.aspx?rec=1&term" + "=" + search_data;
				driver.get(current_url);

				System.out.println(" inside  url : " + current_url);
			} catch (StaleElementReferenceException e) {
				System.out.println("exception loop " + search_data);
				e.printStackTrace();
			}
			Thread.sleep(1000);

			// **************************** READ DIABETES DATA
			String diabetes = reader.getCellData("Sheet1", 0, 0);
			int diabrowSize = reader.getRowCount("Sheet1");
			System.out.println(" row size " + diabrowSize);
			System.out.println("Reading excel ");

			for (int d = 1; d <= diabrowSize; d++) {
				String diabetes_data = reader.getCellData("Sheet1", d, 0);
				System.out.println(diabetes_data);
				diabetic_eli_List.add(diabetes_data); /// *******************
			}

			// ************************ READ ALLERGY DATA ***************************
			String allergy = reader.getCellData("Sheet1", 0, 6);
			int allergySize = reader.getRowCount("Sheet1");
			System.out.println(" row size " + allergySize);
			System.out.println("Reading excel ");
			JavascriptExecutor js = (JavascriptExecutor) driver;
			for (int a = 1; a <= allergySize; a++) {
				String allergy_data = reader.getCellData("Sheet1", a, 6);
				System.out.println(allergy_data);
				diabetic_allergy_List.add(allergy_data); /// *******************
			}

			// **************************** READ TO ADD DATA
			String toAdd = reader.getCellData("Sheet1", 0, 5);
			int toaddbrowSize = reader.getRowCount("Sheet1");
			System.out.println(" row size " + toaddbrowSize);
			System.out.println("Reading excel ");

			for (int d = 1; d <= toaddbrowSize; d++) {
				String to_ADD = reader.getCellData("Sheet1", d, 5);
				System.out.println(to_ADD);
				diabetic_toadd_List.add(to_ADD); /// *******************
			}

			// ******************* PAGINATION SIZE AND ROW SIZE ****************************
//			List<WebElement> pagination = driver.findElements(By.xpath("//div[@id='maincontent']//div[2]//a[@class='respglink']"));
//			int pgSize = pagination.size();
//			System.out.println(" pagination size = " + pgSize);
			List<WebElement> pagelist = driver.findElements(By.xpath("//*[@id='cardholder']/div[2]/a"));
			int pgSize = pagelist.size();
			System.out.println(" page size no:" + pagelist.size());

			List<WebElement> pagelist1 = driver.findElements(By.xpath("//*[@id='cardholder']/div[2]/a"));
			System.out.println(" page size no:" + pagelist1.size());
			int j = 2;
			for (WebElement eachpage : pagelist) {

				WebElement pagei = driver.findElement(
						By.xpath("//div[@id='maincontent']//div[2]//a[@class='respglink' and text()='" + j + "']")); /// html/body/div[2]/form/div[3]/div[2]/div/div[1]/div[1]/div[2]/a['"+j+"']
				Thread.sleep(1000);
				pagei.click();
				// >>>>>> j++;

				int noOfRecipePerPage = driver
						.findElements(By.xpath("//div[@id='maincontent']//div[@class='rcc_recipecard']")).size();
				System.out.println("No.of Rows in a page:" + noOfRecipePerPage);
				List<WebElement> nofrec = driver.findElements(By.xpath("//div[@class='rcc_recipecard']"));

				r = 1;
				for (WebElement we : nofrec) {
//						for(int r=1;r<=noOfRecipePerPage;r++) { 

					WebElement printrecipeID = driver
							.findElement(By.xpath("//div[@id='maincontent']//div[@class='rcc_recipecard']"));
					String recpid = printrecipeID.getAttribute("id");

					Thread.sleep(2000);
					js.executeScript("window.scrollBy(0,250)", "");
//						we.getTagName()
					if (r == 8) {
						js.executeScript("window.scrollBy(0,250)", "");
						Thread.sleep(2000);
						r = 9;
					}

					recipe_Title = driver.findElement(By.xpath(
							"/html/body/div[2]/form/div[3]/div[2]/div/div[1]/div[2]/div[" + r + "]/div[3]/span[1]/a"));

					action.moveToElement(recipe_Title).click().perform();
					Thread.sleep(500);

					recipeID = driver
							.findElements(By.xpath("//div[@id='maincontent']//div[@class='rcc_recipecard']['+j+']"));
					for (WebElement eachRecipeID : recipeID) {
						recipeIDList.add(eachRecipeID.getAttribute("id"));
					}

//)))))))))))))))))))))))))))))))					

					WebElement ingredients = driver.findElement(By.xpath("//*[@id='rcpinglist']"));
					String RecpieIngredients = ingredients.getText();

					int Ingredientssize = driver.findElements(By.xpath("//*[@id='rcpinglist']/div/span")).size();
					System.out.println(Ingredientssize);
					for (int l = 1; l <= Ingredientssize; l++) {
						recipeIngredients = driver.findElements(By.xpath("//*[@id='rcpinglist']/div/span['+i+']"));
						for (WebElement eachIngredient : recipeIngredients) {
							recipeIngredientList.add(eachIngredient.getText());
						}
					}

					// if(recipeIngredientList != eliminatedArray)
					for (String ingredient : recipeIngredientList) {
						if (diabetic_eli_List.contains(ingredient)) {
							System.out.println("Cannot make recipe due to eliminated ingredients");
							return;
						} else {
							
							
							
							
							
							
//)))))))))))))))))))))))))))))))))					
							// *********************** RETRIEVE DATA FOR EACH RECIPE IN EACH PAGE

							// js.executeScript("arguments[0].click()", recipeTitle);
							WebElement recipeTitlehead = driver
									.findElement(By.xpath("//div[@id='recipehead']//h1//span[@itemprop='name']"));
							String title = recipeTitlehead.getText();

							String url = driver.getCurrentUrl();
							System.out.println(driver.getCurrentUrl());

							String pageTiltle = driver.getTitle();
							System.out.println(driver.getTitle());

							js.executeScript("window.scrollBy(0,2000)", "");
							WebElement cookTime = driver.findElement(By.xpath(
									"//div[@id='maincontent']//*[@id='ctl00_cntrightpanel_pnlRecipeScale']/section/p[2]/time[@itemprop='prepTime']"));
							String cook_time = cookTime.getText();

							prepTime = driver.findElement(By.xpath(
									"//div[@id='maincontent']//*[@id='ctl00_cntrightpanel_pnlRecipeScale']/section/p[2]/time[@itemprop='cookTime']"));
							String prep_time = prepTime.getText();

							WebElement morbidCondition = driver
									.findElement(By.xpath("//img[@src='images/recipe/diabetic.gif']"));
							String morbid_condition = morbidCondition.getAttribute("src");

							WebElement recipeMethod = driver
									.findElement(By.xpath("//*[@id='ctl00_cntrightpanel_pnlRcpMethod']"));
							String method = recipeMethod.getText();

//						WebElement recipeCategory = driver.findElement(By.xpath("//*[@id='recipe_tags']/a[5]/span"));
//						String RecpieCategory=recipeCategory.getText();

							WebElement recipeNutrition = driver.findElement(By.xpath("//div[@id=\"accompaniments\"]"));
							String Nutritionlist = recipeNutrition.getText();

							driver.navigate().back();
							// Thread.sleep(1000);

//	    *************** WRITING DATA INTO EXCEL ***********************************		
//						for(int i=1;i<=noOfRows;i++) {			
							reader.setCellData("Sheet" + i + 1, 0, 0, "Recipe ID");
							reader.setCellData("Sheet" + i + 1, 0, 1, "Recipe Name");
							reader.setCellData("Sheet" + i + 1, 0, 2, "Page Title");
							reader.setCellData("Sheet" + i + 1, 0, 3, "Vegan Diabetic Food Category");
							reader.setCellData("Sheet" + i + 1, 0, 4, "Ingredients");
							reader.setCellData("Sheet" + i + 1, 0, 5, "RecipePrepTime");
							reader.setCellData("Sheet" + i + 1, 0, 6, "RecipeCookTime");
							reader.setCellData("Sheet" + i + 1, 0, 7, "Preparation Method");
							reader.setCellData("Sheet" + i + 1, 0, 8, "Nutrient values");
							reader.setCellData("Sheet" + i + 1, 0, 9,
									"Targetted morbid conditions (Diabeties/Hypertension/Hypothyroidism)");
							reader.setCellData("Sheet" + i, 0, 10, "Recipe URL");

							reader.setCellData("Sheet" + i + 1, r, 1, title);
							reader.setCellData("Sheet" + i + 1, r, 2, pageTiltle);
							reader.setCellData("Sheet" + i + 1, r, 4, RecpieIngredients);
							reader.setCellData("Sheet" + i + 1, r, 5, prep_time);
							reader.setCellData("Sheet" + i + 1, r, 6, cook_time);
							reader.setCellData("Sheet" + i + 1, r, 7, method);
							reader.setCellData("Sheet" + i + 1, r, 8, Nutritionlist);
//					reader.setCellData("Sheet"+i, r, 9, morbid_condition);
							reader.setCellData("Sheet" + i + 1, r, 10, url);
							reader.setCellData("Sheet" + i + 1, r, 10, recpid);

							int k = 1;
							for (String eachIDList : recipeIDList) {
								eachRecipeID = eachIDList;
								// writing the data in excel sheet
								reader.setCellData("Sheet" + i + 1, k, 0, eachRecipeID);
								k++;
								System.out.println(eachIDList);
								eachRecipeID = null;
							}
							driver.navigate().refresh();
							r++;
						}
						j++;
						// js.executeScript(window.scrollTo(0,document.body.scrollTop));
//			js.executeScript("window.scroll(0,0)", " " );
//			Thread.sleep(2000);
//			driver.navigate().refresh();

					} // ))))))))))
				} // ))))))))))
			}
		}
	}
}
