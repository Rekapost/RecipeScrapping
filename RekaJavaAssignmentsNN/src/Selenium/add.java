package Selenium;

public class add {
	 
public static void main(String[] args) {
	
// METHOD 1
//	long number =2345;
//	long sum;
//	//executes until the condition number!=0 becomes false  
//	for( sum=0; number!=0; number=number/10)  
//	{  
//	//finds the last digit and add it to the variable sum      
//	sum =  sum + number % 10;  
//	//prints the result  
//	 
//	}
//	System.out.println("Sum of digits: "+sum); 
	
// METHOD 2
int number=1234;
int digit, sum=0;
while(number>0) {	
	// find the last digit of the given number
	digit=number%10;   // 4    3   2   1
	// add last digit to the variable sum
	sum=sum+digit;    //4+0=4  ->  4+3=7  ->  7+2=9  ->  9+1=10
	// remove the last digit from the number 
	number=number/10;   // 123    12     1    -
}
System.out.println("Sum of digits: "+sum);	   
}
} 

