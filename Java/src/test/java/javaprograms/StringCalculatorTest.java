package javaprograms;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import javaprogram.StringCalculator;

public class StringCalculatorTest {
	
	static StringCalculator stringCalculator;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		stringCalculator = new StringCalculator();
	}

	/**
	 * checkEmptyString method is used to check empty string input
	 */
	@Test
	public void checkEmptyString() throws Exception  {
		assertEquals(stringCalculator.add(""), 0);
	}
	
	/**
	 * checkSingleNumberSum method is used to check result when one number is passed as string.
	 */
	@Test
	public void checkSingleNumberSum() throws Exception  {
		assertEquals(stringCalculator.add("1"), 1);
	}
	
	/**
	 * checkTwoNumbersSum method checks sum of two numbers with comma delimiter.
	 * @throws Exception 
	 */
	@Test
	public void checkTwoNumbersSum() throws Exception {
		assertEquals(stringCalculator.add("1,2"), 3);
	}
	
	/**
	 * testStringWithCommaAndNewline method checks sum of two numbers with comma and newline delimiter.
	 */
	@Test
	public void testStringWithCommaAndNewline() throws Exception  {
		assertEquals(stringCalculator.add("1,2\n3"), 6);
	}
	
	/**
	 * checkExceptionUsingDelimiters checks exception when both the delimiters are used together
	 */
	@Test(expected = NumberFormatException.class)
	public void checkExceptionUsingDelimiters() throws Exception  {
		stringCalculator.add("1,2,\n3");
	}
	
	/**
	 * testDefaultDelimiterChange method checks sum of two numbers with comma and newline delimiter.
	 */
	@Test
	public void testDefaultDelimiterChange() throws Exception  {
		assertEquals(stringCalculator.add("//;\n1;2"), 3);
	}
	
	/**
	 * checkForNegativeNumbers checks negative value and throws exception.
	 */
	@Test(expected = Exception.class)
	public void checkForNegativeNumbers() throws Exception  {
		stringCalculator.add("//;\n1;-2;3;-1;6");
	}
	
	/**
	 * ignoreNumbersGreaterThan1000 method is to check numbers greater than 1000 are ignored or not.
	 */
	@Test
	public void ignoreNumbersGreaterThan1000() throws Exception  {
		assertEquals(stringCalculator.add("1,1000,4"), 5);
	}

}
