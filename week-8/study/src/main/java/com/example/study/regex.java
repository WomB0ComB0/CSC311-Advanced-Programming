
public class Regex {
  public static void main(String[] args) {
    System.out.println("--- Core Concepts & Character Classes ---");
    // Example: validate if a string is a single lowercase word
    System.out.println("'hello'.matches(\"[a-z]+\") -> " + "hello".matches("[a-z]+")); // true
    System.out.println("'hello'.matches(\"[a-z]+\") -> " + "Hello".matches("[a-z]+")); // false
    System.out.println(
        "'ab12'.matches(\"[a-zA-Z0-9]+\") -> " + "ab12".matches("[a-zA-Z0-9]+")); // true

    System.out.println("\n--- Predefined Character Symbols ---");
    // Example: Validate a US phone number format (e.g., 123-456-7890)
    // \\d matches a digit. We need \\ because \ is an escape character in Java strings.
    String phoneRegex = "\\d{3}-\\d{3}-\\d{4}";
    System.out.println(
        "'123-456-7890'.matches(phoneRegex) -> " + "123-456-7890".matches(phoneRegex)); // true
    System.out.println(
        "'123-ABC-7890'.matches(phoneRegex) -> " + "123-ABC-7890".matches(phoneRegex)); // false

    System.out.println("\n--- Quantifiers ---");
    // Example: Validate a username that must be 6-16 alphanumeric characters
    String usernameRegex = "[a-zA-Z0-9]{6,16}";
    System.out.println(
        "'user123'.matches(usernameRegex) -> " + "user123".matches(usernameRegex)); // true
    System.out.println(
        "'user'.matches(usernameRegex) -> " + "user".matches(usernameRegex)); // false (too short)
    System.out.println(
        "'user!'.matches(usernameRegex) -> "
            + "user!".matches(usernameRegex)); // false (contains '!')

    System.out.println("\n--- Capturing Groups & Substitution ---");
    // Example: Reformat a data from MM/DD/YYYY to YYYY-MM-DD
    String date = "10/21/2025";
    // The parentheses () create capturing groups. Group 1 : MM, Group 2: DD, Group 3: YYYY
    String dateReformatRegex = "(\\d{2})/(\\d{2})/(\\d{4})";
    // We can refer to the captured groups using $1, $2, $3
    String reformattedDate = date.replaceAll(dateReformatRegex, "$3-$1-$2");
    System.out.println("Original date: " + date);
    System.out.println("Reformatted date: " + reformattedDate);
  }
}
;
