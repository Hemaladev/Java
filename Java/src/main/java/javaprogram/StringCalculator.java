package javaprogram;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class StringCalculator {

	public int add(String numbers) {
		if (numbers == null || numbers.isEmpty()) {
			return 0;
		}
		return calculateSum(numbers);
	}

	private int calculateSum(String numbers) {
		AtomicInteger sum = new AtomicInteger();
		String delimiter = ",|\\\n";
		if (numbers.startsWith("//")) {
			int index = numbers.indexOf("\n");
			if(index != -1) {
				delimiter = numbers.substring(2, index);
				numbers = numbers.substring(index + 1);
			}
		}
		List<String> numberList = Arrays.asList(numbers.split(delimiter));
		numberList.stream().forEach(number -> sum.addAndGet(Integer.parseInt(number)));
		return sum.get();
	}

}
