//     Author: Edwin Goh
//     Github: https://github.com/gohbiscuit/coding-interview-questions


// 		PROBLEM
//		Prints the word representation of an input number
//		Number range from 0-1000000 (for this problem, millions onwards not included)

//		Input: 4
//		Output: Four

//		Input: 23
//		Output: twenty three

//		Input: 3214
//		Output: Thirty two thousand and fourteen

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
    	    
    
    public static void main (String[] args)
    {
    	try
    	{
	        Scanner sc = new Scanner(System.in);
	        int number = sc.nextInt();
	        
	        sc.close();
	        
	        if(number >= 0 && number < 1000000)
	        {
	        	System.out.println(ConvertNumberToString(number));
	        }
	        else
	        {
	        	System.out.println("Please enter positive numbers, range 0-99999 only.");
	        }
    	}
    	catch(NumberFormatException nfe)
    	{
    		System.out.println("Invalid input, please try again.");
    	}
    }
    
    private static void PrintDebug(int digit, int ten, int hundred, int thousand, int ten_thousand, int hundred_thousand)
    {
    	System.out.println("digit: " + digit);
    	System.out.println("ten: " + ten);
        System.out.println("hundred: " + hundred);
        System.out.println("thousand: " + thousand);
        System.out.println("ten_thousand: " + ten_thousand);
        System.out.println("hundred_thousand: " + hundred_thousand);
    }
    
    private static String ConvertNumberToString(int input)
    {
    	int digit = input % 10;
    	int ten = GetValueAfterDivision(input, 10);
    	int hundred = GetValueAfterDivision(input, 100);
    	int thousand = GetValueAfterDivision(input, 1000);
    	int ten_thousand = GetValueAfterDivision(input, 10000);
    	int hundred_thousand = GetValueAfterDivision(input, 100000);
    	
        String digits_string = "", 
        	   ten_string = "", 
        	   hundred_string= "", 
        	   thousand_string = "", 
        	   ten_thousand_string = "", 
        	   hundred_thousand_string ="";
        
        
        /* For Debug Purpose */
        // PrintDebug(digit, ten, hundred, thousand, ten_thousand, hundred_thousand);
        
        
        /* One to Nine */
        if(input == 0)
        {
        	return "zero";
        }
        
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
      
        
        /* Thousand */
        if(thousand == 1)
        {
        	thousand_string = ten_single_digits[hundred-1];
        	thousand_string += " thousand ";
        }
        
        if(thousand > 1 && thousand < 10)
        {
            thousand_string = single_digits[thousand-1];
            thousand_string += " thousand ";
        }
        
        /* Ten Thousand  */
        if(ten_thousand == 1)
        {
        	ten_thousand_string = ten_single_digits[thousand-1];
        	ten_thousand_string += " thousand ";
        	
        	// clear 
        	thousand_string = "";
        }
        
        if(ten_thousand > 1 && ten_thousand < 10)
        {
        	ten_thousand_string = tens[ten_thousand-1];
        	ten_thousand_string += thousand == 0 ? " thousand " : " ";
        }
        
        /* Hundred Thousand  */
        if(hundred_thousand >= 1 && hundred_thousand < 10)
        {
            hundred_thousand_string = single_digits[hundred_thousand-1];
            hundred_thousand_string += " hundred ";
            hundred_thousand_string += ten_thousand == 0 ? "thousand " : "";
        }
        
        return hundred_thousand_string + ten_thousand_string + thousand_string + hundred_string + ten_string + digits_string;
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
