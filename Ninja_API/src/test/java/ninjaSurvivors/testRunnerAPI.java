package ninjaSurvivors;
import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
	
		features="src/test/resources/Features/one.feature",
		glue={"stepDefinitions"},				
		dryRun=false,
		monochrome=true,
				plugin= 
//		{"pretty","html:target/cucumber-reports/cucumber.html",				
//        "json:target/cucumber-reports/cucumber.json"}
					  {
							 
							"pretty","html:target/cucumber-reports/batch.html",				
					        "json:target/cucumber-reports/batch.json"
					       
					        }		
		) //  "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:",
// "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm"

public class testRunnerAPI {

}
