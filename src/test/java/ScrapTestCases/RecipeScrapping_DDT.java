package ScrapTestCases;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import commonUtilities.common_Utilities;

public class RecipeScrapping_DDT extends BaseClass {
	String filename;
	String RecpieIngredients;
	FileOutputStream outputStream;
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
	String prep_time;
	String cook_time;
	int i;
	String search_input;
	ExcelReadWrite reader;
	String search_data;
	String current_url;
	String current_url2;
	List<WebElement> nofrec;
	List<String> diabetic_eli_List;
	List<String> hypothyroid_eli_List;
	List<String> hypertension_eli_List;
	List<String> pcos_eli_List;
	List<String> receipeIDList = new ArrayList<>();
	int r;
	String title;
	int pgSize;
	int row_Size;
	boolean checkContainEliminatedItems;
	common_Utilities checkList = new common_Utilities();

// ****************** Using Data Provider to run single testcase with multiple set of data *******************
	@DataProvider(name = "inputData")
	public String[][] getExcelData() throws IOException {
		String path = "C:\\Users\\Reka\\eclipse-workspace\\webscrapping\\src\\test\\resources\\ScrapeData\\Recipes_Input.xlsx";
		reader = new ExcelReadWrite(path);
		noOfRows = reader.getRowCount("input_list");
		int noOfColumns = reader.getCellCount("input_list", 1); // 1 is rownumber

		String[][] dataTable = new String[noOfRows][noOfColumns];
		for (int m = 1; m <= noOfRows; m++) {
			// 0 row is header , so i=1
			for (int j = 0; j < noOfColumns; j++) {
				// j=0 ,< ; j=1 ,<=
				dataTable[m - 1][j] = reader.getCellData("input_list", m, j); // 0,0= 1,0
			}
		}
		return dataTable;
	}

// *********************** Launching browser and opening **********************
	@Test(dataProvider = "inputData") // (String searchinput)
	public void searchWithInput(String p, String searchinput) throws IOException, InterruptedException {
		List<String[]> scrapedData = new ArrayList<String[]>();
		List<String[]> rejectedscrapedData = new ArrayList<String[]>();
		diabetic_eli_List = new ArrayList<String>();
		hypertension_eli_List = new ArrayList<String>();
		hypothyroid_eli_List = new ArrayList<String>();
		pcos_eli_List = new ArrayList<String>();
		System.out.println("Opening tarladalal website ");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		Actions action = new Actions(driver);
//		System.out.println(" present  url : " + current_url);

//		WebElement search = driver.findElement(By.cssSelector("#ctl00_txtsearch"));
		JavascriptExecutor js = (JavascriptExecutor) driver;

// *********************** RETRIEVE SEARCH INPUT DATA FROM EXCEL

		String path = "C:\\Users\\Reka\\eclipse-workspace\\webscrapping\\src\\test\\resources\\ScrapeData\\Recipes_Input.xlsx";
		reader = new ExcelReadWrite(path);
		int rowSize = reader.getRowCount("input_list");
		System.out.println("Search input row size " + rowSize);
		Thread.sleep(1000);

//******************* Passing input and opening URL 
		System.out.println("search input " + searchinput);

		try {
			current_url = "https://www.tarladalal.com/RecipeSearch.aspx?rec=1&term" + "=" + searchinput;
			driver.get(current_url);
			System.out.println("inside  url : " + current_url);
		} catch (StaleElementReferenceException e) {
			System.out.println("exception loop " + searchinput);
			e.printStackTrace();
		}
		Thread.sleep(1000);

//************************  Geting page size 
		try {
			List<WebElement> pagelist = driver.findElements(By.xpath("//*[@id='cardholder']/div[3]/a"));
			pgSize = pagelist.size();
			System.out.println(" page size no:" + pagelist.size());
		} 
		catch (Exception e) {
			// TODO: handle exception
			List<WebElement> pagelist = driver.findElements(By.xpath("//*[@id='cardholder']/div[2]/a"));
			pgSize = pagelist.size();
			System.out.println(" page size no:" + pagelist.size());
		}

//****************** Navigation and working on pagination 
		for (int x = 1; x <= pgSize; x++) {
			System.out.println("Inside First Page ");

			if (x > 1) {
				System.out.println(" from second page onwards ");
				try {
					WebElement pageNum = driver.findElement(By.xpath("//*[@id='cardholder']/div[3]/a[" + x + "]"));
					String pagNumber = pageNum.getText();
					System.out.println("Page Number : " + pagNumber);
					pageNum.click();
				} catch (NoSuchElementException e) {
					WebElement pageNum = driver.findElement(By.xpath("//*[@id='cardholder']/div[2]/a[" + x + "]"));
					String pagNumber = pageNum.getText();
					System.out.println("Page Number : " + pagNumber);
					pageNum.click();
				} catch(Exception e) {
					System.out.println(" Page number not clickable");
				}
			}
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

// ********************* Iterating through each recipe  and getting Id, Name and ingredients 
			for (int r = 0; r < row_Size;) {
				//System.out.println("Inside each Recipe");
				System.out.println("Now In Recipe:" + r);
				// System.out.println("No.of Rows in a page:" + noOfRecipePerPage);

				Thread.sleep(2000);
				js.executeScript("window.scrollBy(0,250)", "");

				if (r == 8) {
					js.executeScript("window.scrollBy(0,250)", "");
					Thread.sleep(2000);
					r = 9;
				}

				if (receipeIDList.size() == 0)
					return;

				try {
					WebElement recipNam = driver
							.findElement(By.xpath("//*[@id='" + receipeIDList.get(r) + "']/div[3]/span[1]/a"));
					System.out.println("RecipeIDList : " + receipeIDList.get(r));
					// Receipe Name
					String recipeName = recipNam.getText();
					System.out.println("\t*******");
					System.out.println("Recipe Name : " + recipeName);
					// WaitForElement(recipeNam);
					recipNam.click();
				} catch (NoSuchElementException e) {
					WebElement recipNam = driver
							.findElement(By.xpath("//*[@id='" + receipeIDList.get(r) + "']/div[2]/span[1]/a"));
					System.out.println("RecipeIDList : " + receipeIDList.get(r));
					// Receipe Name
					String recipeName = recipNam.getText();
					System.out.println("Recipe Name : " + recipeName);
					WaitForElement(recipNam);
					// recipNam.click();
				} catch (Exception e) {
					System.out.println(" Recipe Id not found ");
				}
				Thread.sleep(1000);

				try {
					js.executeScript("window.scrollBy(0,2000)", "");
					WebElement ingredients = driver.findElement(By.xpath("//*[@id='rcpinglist']"));
					RecpieIngredients = ingredients.getText();
				} catch (Exception e) {
					System.out.println(" ingredients not found");
				}

				if (searchinput.equalsIgnoreCase("Diabetic Recipes")) {
					// reading diabetes eliminated list
					String diabetes = reader.getCellData("diabetic_eliminated", 0, 0);
					int diabrowSize = reader.getRowCount("diabetic_eliminated");
					System.out.println(" diabetic eliminated row size " + diabrowSize);
					for (int d = 1; d <= diabrowSize; d++) {
						String diabetes_data = reader.getCellData("diabetic_eliminated", d, 0);
						diabetic_eli_List.add(diabetes_data);
					}
					checkContainEliminatedItems = checkList.hasEliminatedLists(diabetic_eli_List, RecpieIngredients);
				}
				else if (searchinput.equalsIgnoreCase("Hypothyroid Recipes")) {
					String hypothyroid = reader.getCellData("hypothyroid_eliminated", 0, 0);
					int hypothyroid_rowSize = reader.getRowCount("hypothyroid_eliminated");
					System.out.println(" hypothyroid eliminated row size " + hypothyroid_rowSize);
					for (int th = 1; th <= hypothyroid_rowSize; th++) {
						String hypothyroid_data = reader.getCellData("hypothyroid_eliminated", th, 0);
						hypothyroid_eli_List.add(hypothyroid_data);
					}
					checkContainEliminatedItems = checkList.hasEliminatedLists(hypothyroid_eli_List, RecpieIngredients);
				} 
				else if (searchinput.equalsIgnoreCase("High Blood Pressure")) {
					String High_Blood_Pressure = reader.getCellData("hypertension_eliminated", 0, 0);
					int high_blood_Pressure_rowSize = reader.getRowCount("hypertension_eliminated");
					System.out.println(" High Blood Pressure eliminated row size " + high_blood_Pressure_rowSize);
					for (int bp = 1; bp <= high_blood_Pressure_rowSize; bp++) {
						String High_Blood_Pressure_data = reader.getCellData("hypertension_eliminated", bp, 0);
						hypertension_eli_List.add(High_Blood_Pressure_data);
					}
					checkContainEliminatedItems = checkList.hasEliminatedLists(hypertension_eli_List,
							RecpieIngredients);
				} 
				else if (searchinput.equalsIgnoreCase("PCOS Recipes")) {
					String pcos = reader.getCellData("pcos_eliminated", 0, 0);
					int pcos_rowSize = reader.getRowCount("pcos_eliminated");
					System.out.println(" pcos eliminated row size " + pcos_rowSize);
					for (int pc = 1; pc <= pcos_rowSize; pc++) {
						String pcos_data = reader.getCellData("pcos_eliminated", pc, 0);
						pcos_eli_List.add(pcos_data);
					}
					checkContainEliminatedItems = checkList.hasEliminatedLists(pcos_eli_List, RecpieIngredients);
				}

				if (checkContainEliminatedItems) {
					String url = driver.getCurrentUrl();
					String recipe_id = url.replaceAll("[^0-9]", "");
					System.out.println(
							recipe_id + " Recipe is not recomeneded as it has Ingredients from Eliminated List");
					driver.navigate().back();
					Thread.sleep(1000);
					r++;
				} else {
					System.out.println("recipe#" + r);
					System.out.println(" Recipe need to be printed");
					
					try {
					WebElement recipeTitlehead = driver
							.findElement(By.xpath("//div[@id='recipehead']//h1//span[@itemprop='name']"));
					title = recipeTitlehead.getText();
					}catch(Exception e) {
						System.out.println(" title head not present");
					}
					String url = driver.getCurrentUrl();
					// eachData.put("Recipe URL", url);

					String recipe_id = url.replaceAll("[^0-9]", "");
					System.out.println("Recipe #: " + recipe_id);
					System.out.println();
					// eachData.put("Recipe ID", recipe_id);

					String reciepename = null;
					List<WebElement> Recipename = driver.findElements(By.id("ctl00_cntrightpanel_lblRecipeName"));
					reciepename = Recipename.get(0).getText();

					String pageTiltle = driver.getTitle();
					// eachData.put("Page Title", pageTiltle);

					js.executeScript("window.scrollBy(0,2000)", "");
					try {
					WebElement cookTime = driver.findElement(By.xpath(
							"//div[@id='maincontent']//*[@id='ctl00_cntrightpanel_pnlRecipeScale']/section/p[2]/time[@itemprop='prepTime']"));
					cook_time = cookTime.getText();
					// eachData.put("RecipeCookTime", cook_time);
					}catch(Exception e) {
						System.out.println(" cook time not available");
					}
					
					try {
					WebElement prepTime = driver.findElement(By.xpath(
							"//div[@id='maincontent']//*[@id='ctl00_cntrightpanel_pnlRecipeScale']/section/p[2]/time[@itemprop='cookTime']"));
					prep_time = prepTime.getText();
					// eachData.put("RecipePrepTime", prep_time);
					} catch(Exception e) {
						System.out.println(" preparation time not available");
					}
					WebElement recipeMethod = driver
							.findElement(By.xpath("//*[@id='ctl00_cntrightpanel_pnlRcpMethod']"));
					String method = recipeMethod.getText();
					// eachData.put("Preparation Method", method);

					WebElement recipeNutrition = driver.findElement(By.xpath("//div[@id=\"accompaniments\"]"));
					String Nutritionlist = recipeNutrition.getText();
					// eachData.put("Nutrient values", Nutritionlist);

					driver.navigate().back();

//****************************  storing all data and printing in excel 
					String[] recipeData = { recipe_id, reciepename, title, RecpieIngredients, prep_time, cook_time,
							method, Nutritionlist, url };
					scrapedData.add(recipeData);

					if (searchinput.equalsIgnoreCase("Diabetic Recipes")) {
						try {
							checkList.writeDataToExcel(scrapedData, "Diabetic_recipes", searchinput);
						}  catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					else if (searchinput.equalsIgnoreCase("Hypothyroid Recipes")) {
						try {
							checkList.writeDataToExcel(scrapedData, "Hypothyroid_recipes", searchinput);
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					} else if (searchinput.equalsIgnoreCase("High Blood Pressure")) {
						try {
							checkList.writeDataToExcel(scrapedData, "High_Blood_Pressure_recipes", searchinput);
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					} else if (searchinput.equalsIgnoreCase("PCOS Recipes")) {
						try {
							checkList.writeDataToExcel(scrapedData, "PCOS_recipes", searchinput);
						}  catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					} else {
						System.out.println("No input located");
					}

					driver.navigate().refresh();
					r++;
				}
			}
		}
	}
}
