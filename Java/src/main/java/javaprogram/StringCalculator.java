package javaprogram;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import org.springframework.util.CollectionUtils;

public class StringCalculator {

	/**
	 * add is used to add numbers extracted from string input.
	 * 
	 * @param numbers
	 * @return sum
	 * @throws Exception
	 */
	public int add(String numbers) throws Exception {
		return (numbers == null || numbers.isEmpty()) ? 0 : calculateSum(numbers);
	}

	/**
	 * calculateSum method is used to calculate sum.
	 * 
	 * @param numbers
	 * @return sum
	 * @throws Exception
	 */
	private int calculateSum(String numbers) throws Exception {
		AtomicInteger sum = new AtomicInteger();
		String delimiter = getDelimiter(numbers);
		if (numbers.startsWith("//")) {
			numbers = numbers.substring(numbers.indexOf("\n") + 1);
		}
		List<Integer> numberList = Arrays.asList(numbers.split(delimiter)).stream().map(Integer::parseInt)
				.collect(Collectors.toList());
		checkInvalidNumbers(sum, numberList);
		return sum.get();
	}

	/**
	 * checkInvalidNumbers checks invalid number and calculates sum.
	 * 
	 * @param sum
	 * @param numberList
	 * @throws Exception
	 */
	private void checkInvalidNumbers(AtomicInteger sum, List<Integer> numberList) throws Exception {
		List<Integer> negativeValues = new ArrayList<>();
		numberList.stream().filter(number -> number < 1000).forEach(number -> {
			if (number < 0) {
				negativeValues.add(number);
			} else {
				sum.addAndGet(number);
			}
		});
		if (!CollectionUtils.isEmpty(negativeValues)) {
			System.out.println("Negatives not allowed - " + negativeValues);
			throw new Exception("Negatives not allowed - " + negativeValues);
		}
	}

	/**
	 * getDelimiter method is used to get the delimiters.
	 * 
	 * @param numbers
	 * @return delimiter
	 */
	private String getDelimiter(String numbers) {
		int index = numbers.indexOf("\n");
		if (numbers.startsWith("//") && index != -1) {
			String delimiter = "";
			String extractedDelimiterString = numbers.substring(2, index);
			delimiter = extractedDelimiterString.contains("[")
					? getDelimiterListFromString(extractedDelimiterString, delimiter)
					: extractedDelimiterString;
			StringBuilder strBuilder = getSpecialCharacterTokenizer(delimiter);
			return strBuilder.toString();
		}
		return ",|\\\n";
	}

	/**
	 * getSpecialCharacterTokenizer method is used to append backslash for specila
	 * characters
	 * 
	 * @param delimiter
	 * @return strBuilder
	 */
	private StringBuilder getSpecialCharacterTokenizer(String delimiter) {
		StringBuilder strBuilder = new StringBuilder();
		for (int i = 0; i < delimiter.length(); i++) {
			if (delimiter.charAt(i) == '*') {
				strBuilder.append("\\*");
			} else if (delimiter.charAt(i) == '$') {
				strBuilder.append("\\$");
			} else if (delimiter.charAt(i) == '+') {
				strBuilder.append("\\+");
			} else if (delimiter.charAt(i) == '^') {
				strBuilder.append("\\^");
			} else {
				strBuilder.append(delimiter.charAt(i));
			}
		}
		return strBuilder;
	}

	/**
	 * getDelimiterListFromString method is used to get delimiters from input
	 * string.
	 * 
	 * @param delimiterString
	 * @param delimiter
	 * @return delimiter
	 */
	private String getDelimiterListFromString(String delimiterString, String delimiter) {
		if (delimiterString.contains("[") && !delimiter.isEmpty()) {
			delimiter += '|';
		}
		if (delimiterString.contains("[")) {
			delimiter += delimiterString.substring(delimiterString.indexOf("[") + 1, delimiterString.indexOf("]"));
			return getDelimiterListFromString(delimiterString.substring(delimiterString.indexOf("]") + 1), delimiter);
		}
		return delimiter;
	}

}
