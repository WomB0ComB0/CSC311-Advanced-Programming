package com.example.lab;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    private static void testRegex(String name, String regex, String trueStr, String falseStr) {
        System.out.println("--- " + name + " ---");
        System.out.println("Regex: \"" + regex + "\"");

        boolean resultTrue = Pattern.matches(regex, trueStr);
        System.out.printf("Test String: \"%s\" -> Result: %s%n", trueStr, resultTrue);

        boolean resultFalse = Pattern.matches(regex, falseStr);
        System.out.printf("Test String: \"%s\" -> Result: %s%n", falseStr, resultFalse);
        System.out.println();
    }

    private static void testInputRegex(String description, String input, String regex) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);

        System.out.println("Description: " + description);
        System.out.println("Input String: \"" + input + "\"");
        System.out.println("Regex: \"" + regex + "\"");
        System.out.printf("Match Found: %s%n%n", matcher.matches());
    }

    private static void checkPassword(String password) {
        String passwordRegex = "^[A-Z]\\d+\\W{3,}$";

        boolean isValid = password.matches(passwordRegex);

        System.out.printf("Checking password: \"%s\"%n", password);
        System.out.printf("Regex: \"%s\"%n", passwordRegex);

        if (isValid) {
            System.out.println("Result: VALID Password.");
        } else {
            System.out.println("Result: INVALID Password.");
        }
        System.out.println();
    }

    public static void main(String[] args) {

        System.out.println("==========================================");
        System.out.println("       Simple Regular Expressions         ");
        System.out.println("==========================================");

        testRegex(
                "Match d, e, f, g, or h",
                "[d-h]",
                "f",
                "a"
        );

        testRegex(
                "Match any one digit",
                "\\d",
                "5",
                "a"
        );

        testRegex(
                "Match lowercase letter followed by a digit",
                "[a-z]\\d",
                "a9",
                "9a"
        );


        System.out.println("\n==========================================");
        System.out.println("  Regular Expressions with Quantifiers    ");
        System.out.println("==========================================");

        testRegex(
                "Match any number of any characters",
                ".*",
                "This can be anything 123.",
                ""
        );

        testRegex(
                "Match any number of digits (0 or more)",
                "\\d*",
                "12345",
                "abc"
        );

        testRegex(
                "Match at least one digit (1 or more)",
                "\\d+",
                "9876",
                "hello"
        );

        testRegex(
                "Match at least 1 digit, 1 lowercase letter, and then anything",
                "\\d+[a-z].*",
                "123aXYZ",
                "a123"
        );

        testRegex(
                "Match string ending in 'q'",
                ".*q$",
                "This ends in q",
                "This ends in r"
        );

        System.out.println("\n==========================================");
        System.out.println("       Write Regular Expressions          ");
        System.out.println("==========================================");

        final String INPUT_STRING = "77cd22";

        testInputRegex(
                "Check that '77' has any number of characters following it.",
                INPUT_STRING,
                "^77.*"
        );

        testInputRegex(
                "Check for .* + \\d+ + cd + \\d* + .*",
                INPUT_STRING,
                ".*\\d+cd\\d*.*"
        );

        testInputRegex(
                "Check for at least 2 consecutive digits before '22' and anything after '22'.",
                INPUT_STRING,
                ".*\\d{2}.*22.*"
        );

        System.out.println("\n==========================================");
        System.out.println("      Password Checker Method Tests       ");
        System.out.println("==========================================");

        checkPassword("A123@#$");
        checkPassword("Z999!!!pass");

        checkPassword("a123@#$");
        checkPassword("B45%^");
        checkPassword("C@#$");
        checkPassword("D123@#$pass");
    }
}