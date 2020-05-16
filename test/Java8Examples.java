package test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Java8Examples {
	public static void main(String[] args) {
//		mapExamples();
		try {
			Stream<String> stream = Files.lines(Paths.get("f:\\study\\machine_learning_notes.txt"));
			// "f:\\study\\sorting_algorithms.txt"
			stream.forEach(System.out::println);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void mapExamples(){
		String name = "Pankaj";

		List<Character> charList = name.chars().mapToObj(c -> (char) c).collect(LinkedList::new, (a, b) -> a.add(b),
				(a, b) -> a.addAll(b));
		
		Map<Character, Long> charCountMap = name.chars().mapToObj(c -> (char) c)
				.collect(Collectors.groupingBy(x -> (char) x, LinkedHashMap::new, Collectors.counting()));
		
		Map<Character, Long> charCountSortedMap = name.chars().mapToObj(c -> (char) c)
				.collect(
						Collectors.groupingBy(x -> (char) x,
								() -> new TreeMap<Character, Long>(
										(a, b) -> a.toString().compareToIgnoreCase(b.toString())),
								Collectors.counting()));
		
		Map<Character, Long> charCountSortedByCountDescMap = name.chars().mapToObj(c -> (char) c)
				.collect(Collectors.groupingBy(x -> (char) x, Collectors.counting())).entrySet().parallelStream()
				.sorted((a, b) -> {
					int diff = (int) (b.getValue() - a.getValue());
					if (diff == 0) {
						diff = a.getKey().toString().compareToIgnoreCase(b.getKey().toString());
					}
					return diff;
				}).collect(LinkedHashMap::new, (a, b) -> a.put(b.getKey(), b.getValue()), (a, b) -> a.putAll(b));

		Comparator<Map.Entry<Character, Long>> keyComparator = Map.Entry
				.comparingByKey((a, b) -> a.toString().compareToIgnoreCase(b.toString()));
		Comparator<Map.Entry<Character, Long>> valueComparator = Map.Entry.comparingByValue(Comparator.reverseOrder());

		charCountSortedByCountDescMap = name.chars().mapToObj(c -> (char) c)
				.collect(Collectors.groupingBy(x -> (char) x, Collectors.counting())).entrySet().stream()
				.sorted(valueComparator.thenComparing(keyComparator))
				.collect(LinkedHashMap::new, (a, b) -> a.put(b.getKey(), b.getValue()), (a, b) -> a.putAll(b));

		System.out.println("charList: " + charList);
		System.out.println("charCountMap:" + charCountMap);
		System.out.println("charCountSortedMap:" + charCountSortedMap);
		System.out.println("charCountSortedByCountDescMap: " + charCountSortedByCountDescMap);
		// System.out.println(charCountMap.getClass().toString());

	}
}
