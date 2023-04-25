package testRunner;
import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(	
		features="src/test/resources/features",
		glue="stepDefinitions",
		dryRun=false,
//		dryRun=true, //to check every steps have corresponding method or not
//		plugin= {"pretty","html:test-output"},
		monochrome=true
//		tags= {"@sanity"}
//		tags= {"@sanity,@regression"}  OR
//		tags= {"@sanity","@regression"}  AND
		)
public class TestRunner {

}
