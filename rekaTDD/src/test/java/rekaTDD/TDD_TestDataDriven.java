package rekaTDD;

import org.testng.Assert;
import org.testng.annotations.Test;

public class TDD_TestDataDriven {

			@Test

			public void testAdd() {
			Calculator calculator = new Calculator();
			int result = calculator.add(2, 3);
			System.out.println("addition answer : " + result);
			Assert.assertEquals(result, 5);

			}

			@Test

			public void testSubtract() {
			Calculator calculator = new Calculator();
			int result = calculator.subtract(5, 2);
			System.out.println("subtract answer : " + result);
			Assert.assertEquals(result, 3);

			}

			@Test

			public void testMultiply() {
			Calculator calculator = new Calculator();
			int result = calculator.multiply(2, 3);
			System.out.println("multiply answer : " + result);
			Assert.assertEquals(result, 6);

			}

			@Test

			public void testDivide() {
			Calculator calculator = new Calculator();
			int result = calculator.divide(6, 3);
			System.out.println("divide answer : " + result);
			Assert.assertEquals(result, 2);

			}

			@Test(expectedExceptions = IllegalArgumentException.class)

			public void testDivideByZero() {
			Calculator calculator = new Calculator();

			calculator.divide(6, 0);

			}

			}