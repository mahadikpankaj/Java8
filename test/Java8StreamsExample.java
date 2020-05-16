package test;

import java.util.Arrays;
import java.util.DoubleSummaryStatistics;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by aaa on 16/04/2017.
 */
public class Java8StreamsExample {

	public static void main(String[] args) {
		Person[] persons = { new Person(1, "Pavan", "Shinde", "Satara", 1000, 20),
				new Person(2, "Prakash", "Joshi", "Pune", 2000, 25), new Person(3, "Sanjay", "Doshi", "Pune", 3000, 25),
				new Person(4, "Santosh", "Shinde", "Pune", 4000, 20), new Person(5, "Sanjay", "Jain", "Pune", 3000, 25) };
		List<Person> personList = Arrays.<Person> asList(persons);
		Map<String, Long> firstNameCountMap = getMap(personList);
		System.out.println("First Names Count Map: " + firstNameCountMap);

		List<String> G7 = Arrays.asList("USA", "Japan", "France", "Germany", "Italy", "U.K.", "Canada");
		String G7Countries = G7.stream().map(x -> x.toUpperCase()).collect(Collectors.joining(", "));
		G7Countries = G7.stream().map(x -> x.toUpperCase()).reduce((a,b)->a+", "+ b).get();
		System.out.println(G7Countries);

		
		Map<Integer, List<Person>> personsByAge = personList
			    .stream()
			    .collect(Collectors.groupingBy(p -> p.age));

		System.out.println(personsByAge);
			personsByAge
			    .forEach((age, p) -> System.out.format("age %s: %s\n", age, p));
	}

	private static Map<String, Long> getMap(List<Person> personList) {
		Map<String, Long> resultMap = null;
		Map<String, DoubleSummaryStatistics> dMap = new HashMap<>();

		resultMap = personList.parallelStream()
				.collect(Collectors.toMap(t -> t.firstName, t -> Long.valueOf(t.id), (a, b) -> Long.valueOf(a)));
		System.out.println(resultMap);
		Map<String, String> results = personList.parallelStream()
				.collect(Collectors.toMap(t -> t.firstName, t -> String.valueOf(t.age), (a, b) -> a + "," + b));
		System.out.println("Results: "+results);
		resultMap = personList.parallelStream().collect(Collectors.groupingBy(t -> t.city, Collectors.counting()));
		System.out.println(resultMap);
		Map<Object, Map<Object, Long>> ob = personList.parallelStream().collect(Collectors.groupingBy(
				t -> t.city,
				Collectors.groupingBy(t->t.age, Collectors.counting())
				));
		System.out.println("OB: " +ob);
		dMap = personList.parallelStream()
				.collect(Collectors.groupingBy(t -> t.city, Collectors.summarizingDouble(t -> t.salary)));
		System.out.println(dMap);
		dMap.values().parallelStream().forEach(t -> System.out.println(t.getSum()));

		return resultMap;
	}

}

class Person {
	public int id;
	public String firstName;
	String lastName;
	String city;
	Double salary;
	int age;
	Person(int id, String firstName, String lastName, String city, double salary, int age) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.city = city;
		this.salary = salary;
		this.age = age;
	}

	public String getFirstName() {
		return firstName;
	}

	@Override
	public String toString() {
		return (id + " : " + firstName + " : " + lastName + " : " + city + " : " + salary);
	}
}
