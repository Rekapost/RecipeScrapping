package interview;
import static org.testng.Assert.assertTrue;
import java.io.File;
import org.testng.Assert;
import org.testng.annotations.Test;

public class FileSeperator {
	
	    @Test
	    public void slash() throws Exception {
	       File file = new File("C:\\Users\\Reka\\eclipse-workspace\\practice\\src\\test\\java\\interview\\FileSeperator.java");	
	       if(file.exists()== true)
	       {System.out.println(" // file found ");}
	       else {
	    	   System.out.println(" // file not found ");
	       }
	        //assertThat(file.exists(), is(true));
	    }

	    @Test
	    public void separator() throws Exception {
	        File file = new File("c:" + File.separator + "Users" + File.separator + "Reka"+ File.separator +
	        		"eclipse-workspace"+ File.separator + "practice"+ File.separator + "src"+ File.separator + "test"+ File.separator + "java" 
	        		+ File.separator + "interview" + File.separator + "FileSeperator.java");
	        if(file.exists()==true)
		       {System.out.println(" separator file found ");
	        //assertThat(file.exists(), is(true));
	        Assert.assertTrue(true);}
	        else {
		    	   System.out.println(" separator file not found ");
		    }
	}
}
