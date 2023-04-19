package utilities;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
// as u create object for the configReader class , constructor will be invoked and propertie file  will be loaded ,
// then u have read each  and every value by adding  method  for each and every variable  to read their value 
	
	Properties properties;    // creating object 
	//private final static String propertyFilePath = "./configuration/config.properties";    OR
	File propertyFilePath=new File("C:\\Users\\Reka\\eclipse-workspace\\webscrapping\\src\\test\\resources\\configuration\\config.properties");
	
	public ConfigReader()  {		
		try 
		{FileInputStream stream = new FileInputStream(propertyFilePath);  // to open file in read mode 
		properties = new Properties();    // creating object
		try {
			properties.load(stream);  // load config file at run time 
			stream.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(" Exception is :"+ e.getMessage());
		}
	} catch (FileNotFoundException e) {
		e.printStackTrace();
		throw new RuntimeException("Configuration.properties not found at " + propertyFilePath);
	}
	}
	
	
	public String getBrowserType() {
		String browser = properties.getProperty("BROWSER");
		Loggerload.info("Get property BrowserType");
		if (browser != null)		
			return browser;
		else
			throw new RuntimeException("browser not specified in the Configuration.properties file.");
	}

	public  String getApplicationUrl() {
		String url = properties.getProperty("APP_URL");
		if (url != null)
			return url;
		else
			throw new RuntimeException("url not specified in the Configuration.properties file.");
	}
	
	public  String getexcelfilepath() {
		String excelfilelpath = properties.getProperty("EliminatedExcelList");
		if (excelfilelpath != null)
			return excelfilelpath;
		else
			throw new RuntimeException("Excel file path not specified in the Configuration.properties file.");
	}
	
	public  String getJobType() {
		String url = properties.getProperty("typeOfJob");
		if (url != null)
			return url;
		else
			throw new RuntimeException("url not specified in the Configuration.properties file.");
	}
	
	public  String getJobLocation() {
		String url = properties.getProperty("location");
		if (url != null)
			return url;
		else
			throw new RuntimeException("url not specified in the Configuration.properties file.");
	}

	
	public  String getChromePath() {
		String chromePath = properties.getProperty("CHROME_DRIVER_LOCATION");	
		return chromePath;	
	}
	
	public  String getGeckoPath() {
		String geckoPath = properties.getProperty("FIREFOX_DRIVER_LOCATION");	
		return geckoPath;	
	}
	
	
	
}
	