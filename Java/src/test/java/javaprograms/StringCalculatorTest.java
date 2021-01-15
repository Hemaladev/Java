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
	public void checkEmptyString() {
		assertEquals(stringCalculator.add(""), 0);
	}
	
	/**
	 * checkSingleNumberSum method is used to check result when one number is passed as string.
	 */
	@Test
	public void checkSingleNumberSum() {
		assertEquals(stringCalculator.add("1"), 1);
	}
	
	/**
	 * checkTwoNumbersSum method checks sum of two numbers with comma delimiter.
	 */
	@Test
	public void checkTwoNumbersSum() {
		assertEquals(stringCalculator.add("1,2"), 3);
	}
	
	/**
	 * testStringWithCommaAndNewline method checks sum of two numbers with comma and newline delimiter.
	 */
	@Test
	public void testStringWithCommaAndNewline() {
		assertEquals(stringCalculator.add("1,2\n3"), 6);
	}
	
	/**
	 * checkExceptionUsingDelimiters checks exception when both the delimiters are used together
	 */
	@Test(expected = NumberFormatException.class)
	public void checkExceptionUsingDelimiters() {
		stringCalculator.add("1,2,\n3");
	}

}
