package selenium;

import org.testng.annotations.Test;

public class OrderOfExecution {
	
	
	@Test
	public void method1(){
	        System.out.println("method1");
	}

	@Test(priority=0)
	public void method2(){
	        System.out.println("method2");
	}

	@Test
	public void method3(){
	        System.out.println("method3");
	}
}

/*
[RemoteTestNG] detected TestNG version 7.4.0
method1
method2
method3
PASSED: method1
PASSED: method3
PASSED: method2
*/
