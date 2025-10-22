import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Lambda {
  // A simple class to use
  static class Person {
    private String name;
    private int age;

    public Person(String name, int age) {
      this.name = name;
      this.age = age;
    }

    public String getName() {
      return name;
    }

    public int getAge() {
      return age;
    }

    @Override
    public String toString() {
      return "Person{" + "name='" + name + '\'' + ", age=" + age + '}';
    }
  }

  // A functional interface
  @FunctionalInterface
  interface MathOperation {
    int operate(int a, int b);
  }

  public static void main(String[] args) {
    System.out.println("--- Lambda Expressions  ---");
    // Assigning lambdas to variables of a functional interface type
    MathOperation addition = (a, b) -> a + b;
    MathOperation subtraction = (a, b) -> a - b;

    System.out.println("10 + 5 = " + addition.operate(10, 5));
    System.out.println("10 - 5 = " + subtraction.operate(10, 5));

    System.out.println("\n--- Streams ---");
    List<Person> people =
        Arrays.asList(
            new Person("Arthur", 35),
            new Person("Rose", 22),
            new Person("Mateo", 41),
            new Person("Wei", 19),
            new Person("Zoe", 32));

    // Exmaple 1: filter() and forEach()
    System.out.println("People older than 30:");
    people.stream().filter(p -> p.getAge() > 30).forEach(p -> System.out.println(p.getName()));

    // Example 2: map() and collect()
    System.out.println("\nList of all names:");
    List<String> names =
        people.stream()
            .map(Person::getName) // Transform each Person object into their name (String)
            .collect(Collectors.toList());

    System.out.println(names);

    // Example 3: sorted()
    Sytem.out.println("\nPeople sorted by age:");
    people.stream()
        .sorted(
            (p1, p2) -> Integer.compare(p1.getAge(), p2.getAge())) // Sort using a lambda comaprator
        .forEach(System.out::println); // Print the sorted Person objects

    // Example 4 Chaining multiple operations
    System.out.println("\nNames of people older than 20, sorted alphabetically:");
    List<String> sortedNames =
        people.stream()
            .filter(p -> p.getAge() > 20) // Find people older than 20
            .map(Person::getName) // Get their names
            .sorted() // Sort the names
            .collect(Collectors.toList()); // Collect into a list
    System.out.println(sortedNames);

    // Example 5: Reduction operations - average()
    double averageAge =
        people.stream()
            .mapToInt(Person::getAge) // Convert stream of Person to a stream of int (ages)
            .average() // Calculate the average
            .orElse(0.0); // Provide a default value if the stream is empty
    System.out.println("\nAverage age: " + averageAge);
  }
}
