package javaprogram;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class StringCalculator {
	
	public int add(String numbers) {
		if(numbers == null || numbers.isEmpty()) {
			return 0;
		}
		return calculateSum(numbers);
	}

	private int calculateSum(String numbers) {
		AtomicInteger sum = new AtomicInteger();
		List<String> numberList = Arrays.asList(numbers.split(",|\\\n"));
		System.out.println(numberList);
		numberList.stream().forEach(number -> sum.addAndGet(Integer.parseInt(number)));
		return sum.get();
	}

}
