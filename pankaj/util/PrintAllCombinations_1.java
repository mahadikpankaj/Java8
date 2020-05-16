package pankaj.util;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

public class PrintAllCombinations_1 {
	public static void main(String[] args) {
		String sourceString = "ABC";
		getPermutationsCombinations(sourceString);
	}

	private static void getPermutationsCombinations(String sourceString) {
		Set<String> results = new TreeSet<String>((a, b) -> {
			int ret = a.length()-b.length();
			if(ret==0){
				ret = a.compareToIgnoreCase(b);
			}
			return ret;
		});
		Set<String> set = new HashSet<String>();
		permute(sourceString, 0, sourceString.length() - 1, set);
		results.addAll(set);
		set.stream().forEach(x -> results.addAll(getCombinations(x)));

		results.forEach(System.out::println);
	}

	private static void permute(String str, int l, int r, Set<String> values) {
		if (l == r || l == str.length()) {
			values.add(str);
			// System.out.println(str);
		}

		else {
			for (int i = l; i <= r; i++) {
				str = swap(str, l, i);
				permute(str, l + 1, r, values);
				str = swap(str, l, i);
			}
		}
	}

	public static String swap(String a, int i, int j) {
		char temp;
		char[] charArray = a.toCharArray();
		temp = charArray[i];
		charArray[i] = charArray[j];
		charArray[j] = temp;
		return String.valueOf(charArray);
	}

	private static Set<String> getCombinations(String sourceString) {
		Set<String> values = new HashSet<String>();
		getCombinations(sourceString.toCharArray(), 0, values, "");
		return values;
	}

	private static void getCombinations(char[] input, int index, Set<String> values, String... combinedString) {
		String combination = combinedString != null && combinedString.length > 0 ? combinedString[0] : "";

		if (index == input.length) {
//			System.out.println(combination);
			if(combination!=null && combination.length()>0)
			values.add(combination);
			return;
		}

		getCombinations(input, index + 1, values, combination);
		getCombinations(input, index + 1, values, combination + input[index]);
	}
}
