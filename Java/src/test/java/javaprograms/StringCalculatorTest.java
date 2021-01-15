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

	@Test
	public void checkEmptyString() {
		assertEquals(stringCalculator.add(""), 0);
	}
	
	@Test
	public void checkSingleNumberSum() {
		assertEquals(stringCalculator.add("1"), 1);
	}
	
	@Test
	public void checkTwoNumbersSum() {
		assertEquals(stringCalculator.add("1,2"), 3);
	}

}
