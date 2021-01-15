package javaprogram;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import org.springframework.util.CollectionUtils;

public class StringCalculator {

	public int add(String numbers) throws Exception {
		if (numbers == null || numbers.isEmpty()) {
			return 0;
		}
		return calculateSum(numbers);
	}

	private int calculateSum(String numbers) throws Exception {
		AtomicInteger sum = new AtomicInteger();
		String delimiter = ",|\\\n";
		if (numbers.startsWith("//")) {
			int index = numbers.indexOf("\n");
			if (index != -1) {
				delimiter = numbers.substring(2, index);
				numbers = numbers.substring(index + 1);
			}
		}
		List<Integer> numberList = Arrays.asList(numbers.split(delimiter)).stream().map(Integer::parseInt)
				.collect(Collectors.toList());
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
		return sum.get();
	}

}
