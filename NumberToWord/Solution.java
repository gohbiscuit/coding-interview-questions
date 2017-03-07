//     Author: Edwin Goh
//     Github: https://github.com/gohbiscuit/coding-interview-questions


// 		PROBLEM
//		Prints the word representation of an input number
//		Up to trillion

//		Input: 4
//		Output: Four

//		Input: 23
//		Output: twenty three

//		Input: 3214
//		Output: three thousand two hundred and fourteen

//		Input: 1234567
//		Output: one million two hundred and thirty four thousand five hundred and sixty seven

import java.util.Scanner;
	
public class Solution 
{
    private static String[] tens = new String[]
    {
        "ten", "twenty", "thirty", "fourty", "fifty", "sixty", "seventhy", "eighty", "ninety"
    };
    
    private static String[] single_digits = new String[]
    {
        "one", "two", "three", "four", "five", "six", "seven", "eight", "nine"
    };
    
    private static String[] ten_single_digits = new String[]
    {
        "eleventh", "twelve", "thirteen", "fourteen", "fiveteen", "sixteen", "seventeen", "eighteen", "ninteen"
    };
    
    private static String[] special_digits = new String[]
    {
    	"", " thousand", " million", " billion", " trillion"
    };
    	    
    
    public static void main (String[] args)
    {
    	try
    	{
	        Scanner sc = new Scanner(System.in);
	        int number = sc.nextInt();
	        
	        sc.close();
	        
        	if(number >= 0)
        	{
        		System.out.println(ConvertNumberToString(number));
        	}
        	else
        	{
        		System.out.println("negative " + ConvertNumberToString(number * -1));
        	}
    	}
    	catch(Exception e)
    	{
    		System.out.println("Invalid input, please try again.");
    	}
    }
    
    private static void PrintDebug(int digit, int ten, int hundred)
    {
    	System.out.println("digit: " + digit);
    	System.out.println("ten: " + ten);
        System.out.println("hundred: " + hundred);
    }
    
 
    private static String ConvertNumberToString(int input)
    {
      if(input == 0)
      {
         return "zero";
      }
    
    	int thousands_index = 0;
    	String word = "";
   
    	do
    	{
    		/* get the remainder less than a thousand */
    		int num = input % 1000;

    		/* convert less than a thousand */
    		if(num != 0)
    		{
    			String lessThanOneThousand = ConvertLessThanOneThousand(num);
   
    			word = lessThanOneThousand + special_digits[thousands_index] + word;
    		}
    		
    		thousands_index++;
    		input /= 1000;
    		
    	}while(input > 0);
    	
    	return word.trim();
    }
    
    private static String ConvertLessThanOneThousand(int num)
    {
    	int digit = num % 10;
    	int ten = GetValueAfterDivision(num, 10);
    	int hundred = GetValueAfterDivision(num, 100);
    	
    	String digits_string = "", ten_string = "", hundred_string= "";
    	
		 /* For Debug Purpose */
		 //PrintDebug(digit, ten, hundred);
		  
		 /* One to Nine */
		 if(digit > 0)
		 {
			 digits_string = single_digits[digit-1];
		 }
		  
		 /* Tenths */
		 if(ten == 1)
		 {
			 ten_string = digit == 0 ? tens[0] : ten_single_digits[digit-1]; 
			 ten_string += " ";
		 
			 digits_string = "";
		 }
		  
		 if(ten > 1 && ten < 10)
		 {
			 ten_string = tens[ten-1] + " ";
		 }
		
		 /* Hundreds */
		 if(hundred > 0 && hundred < 10)
		 {
			 hundred_string = single_digits[hundred-1];
			 hundred_string += (digit > 0 || ten > 0) ? " hundred and " : " hundred ";
		 }

		 return " " + hundred_string + ten_string + digits_string;
    }
 
    /* Gets the value after division
     * E.g. GetValueAfterDivision(1234, 10) --> it will extract out 34/10 and returns a value of 3
     * 		GetValueAfterDivision(1234, 100) --> it will extract out 234/100 and returns a value of 2
     *  */
    private static int GetValueAfterDivision(int number, int dividedValue)
    {
    	String numberString = String.valueOf(number);
    	
    	int stringLength = numberString.length();
    	int lengthOfDivisor = String.valueOf(dividedValue).length();	// e.g. 100, lengthOfDivisor (number of digits) = 3
    	
    	if(stringLength >= lengthOfDivisor)
    	{
    		numberString = numberString.substring(stringLength - lengthOfDivisor, stringLength);
    		
    		int value = Integer.parseInt(numberString);
    		return value / dividedValue;
    	}
    	
    	return 0;
    }
}