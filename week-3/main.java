import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Queue;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.LinkedHashSet;
import java.util.Collections;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * A demonstration class for working with Java Collections Framework and Generics.
 * This program showcases various collection types including Queue, PriorityQueue,
 * Map, and Set, along with their common operations such as iteration, insertion,
 * and removal.
 *
 * <p>The program performs the following operations:</p>
 * <ul>
 *   <li>Loads names from a text file and populates different queue types</li>
 *   <li>Demonstrates FIFO behavior with a regular queue</li>
 *   <li>Shows natural ordering with a priority queue</li>
 *   <li>Provides interactive region lookup using a map</li>
 *   <li>Illustrates duplicate handling with a set</li>
 * </ul>
 *
 * @author Mike Odnis
 * @version 1.0
 * @since 1.8
 */
public class GenericsCollectionsLab {

    /**
     * Main entry point for the collections demonstration program.
     * Orchestrates the execution of various collection operations including
     * file loading, queue processing, map searching, and set manipulation.
     *
     * @param args Command line arguments (not used in this implementation)
     */
    public static void main(String[] args) {
        String inputFile = "queue_data.txt";

        // Initialize different queue implementations
        Queue<String> normalQueue = new LinkedList<>();
        Queue<String> priorityQueue = new PriorityQueue<>();

        // Load data and populate queues
        List<String> names = loadNames(inputFile);
        normalQueue.addAll(names);
        priorityQueue.addAll(names);

        // Demonstrate queue iteration without removal
        System.out.println("=== Normal Queue (print using Iterator; does NOT remove) ===");
        printAllItemsQueue(normalQueue);

        // Demonstrate priority queue with removal
        System.out.println("\n=== Priority Queue (remove and print until empty) ===");
        printAndRemoveAllItemsPriorityQueue(priorityQueue);

        // Map operations
        Map<Integer, String> regions = buildRegionMap();
        Scanner sc = new Scanner(System.in);
        searchMap(regions, sc);

        // Set operations
        Set<String> nameSet = buildNameSet();
        System.out.println("\n=== Set (print using Iterator) ===");
        printAllItemsSet(nameSet);

        System.out.println("\nDone.");
    }

    /**
     * Loads names from a specified file path, filtering out empty lines and trimming whitespace.
     * Uses Java 8 Streams API for efficient file processing and data transformation.
     *
     * @param path The file path to read names from
     * @return A List containing all non-empty, trimmed lines from the file.
     *         Returns an empty list if the file cannot be read or doesn't exist.
     *
     * @throws IOException Handled internally - error message printed to stderr
     *
     * @see Files#readAllLines(java.nio.file.Path)
     * @see java.util.stream.Stream
     */
    private static List<String> loadNames(String path) {
        try {
            return Files.readAllLines(Paths.get(path))
                    .stream()
                    .map(String::trim)
                    .filter(s -> !s.isEmpty())
                    .collect(Collectors.toList());
        } catch (IOException e) {
            System.err.println("Could not read file: " + path + " (" + e.getMessage() + ")");
            return Collections.emptyList();
        }
    }

    /**
     * Prints all elements in a queue using an Iterator without removing them.
     * This method preserves the queue's contents while displaying all elements
     * in their current order. For LinkedList-based queues, this shows FIFO order.
     *
     * @param q The Queue to iterate through and print. Must not be null.
     * @param <T> The generic type of elements in the queue
     *
     * @throws NullPointerException if the queue parameter is null
     *
     * @see Iterator
     * @see Queue#iterator()
     */
    private static void printAllItemsQueue(Queue<String> q) {
        Iterator<String> it = q.iterator();
        while (it.hasNext()) {
            System.out.println(it.next());
        }
    }

    /**
     * Prints and removes all elements from a priority queue until it's empty.
     * Elements are removed and displayed in their natural ordering (alphabetical
     * for strings). This method modifies the queue by emptying it.
     *
     * <p><strong>Note:</strong> This method destructively consumes the queue contents.</p>
     *
     * @param q The PriorityQueue to process. Must not be null.
     * @param <T> The generic type of elements in the queue
     *
     * @throws NullPointerException if the queue parameter is null
     *
     * @see PriorityQueue#poll()
     * @see Queue#isEmpty()
     */
    private static void printAndRemoveAllItemsPriorityQueue(Queue<String> q) {
        while (!q.isEmpty()) {
            System.out.println(q.poll());
        }
    }

    /**
     * Creates and populates a HashMap with sample region data.
     * The map associates integer keys with directional region names.
     *
     * <p>Sample data mapping:</p>
     * <ul>
     *   <li>1 → North</li>
     *   <li>2 → South</li>
     *   <li>3 → East</li>
     *   <li>4 → West</li>
     * </ul>
     *
     * @return A new HashMap containing the predefined region mappings
     *
     * @see HashMap
     */
    private static Map<Integer, String> buildRegionMap() {
        Map<Integer, String> m = new HashMap<>();
        // Sample Data (Map)
        // 3 -> East, 1 -> North, 2 -> South, 4 -> West
        m.put(3, "East");
        m.put(1, "North");
        m.put(2, "South");
        m.put(4, "West");
        return m;
    }

    /**
     * Provides an interactive search interface for the regions map.
     * Prompts the user to enter a region number and displays the corresponding
     * region name if found. Handles invalid input gracefully with appropriate
     * error messages.
     *
     * <p>Input validation includes:</p>
     * <ul>
     *   <li>Checking for valid integer format</li>
     *   <li>Verifying key exists in the map</li>
     *   <li>Providing descriptive error messages for invalid inputs</li>
     * </ul>
     *
     * @param regions The Map containing region number to name mappings. Must not be null.
     * @param sc The Scanner instance for reading user input. Must not be null.
     *
     * @throws NullPointerException if either parameter is null
     * @throws NumberFormatException Handled internally when parsing invalid integers
     *
     * @see Map#containsKey(Object)
     * @see Map#get(Object)
     * @see Scanner#nextLine()
     */
    private static void searchMap(Map<Integer, String> regions, Scanner sc) {
        System.out.print("\nEnter a region number (e.g., 1-4): ");
        String input = sc.nextLine().trim();
        try {
            int key = Integer.parseInt(input);
            if (regions.containsKey(key)) {
                System.out.println("Region " + key + " -> " + regions.get(key));
            } else {
                System.out.println("That key (" + key + ") is not valid.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input: please enter an integer key.");
        }
    }

    /**
     * Creates and populates a LinkedHashSet with sample name data, including duplicates.
     * Demonstrates the Set's behavior of maintaining insertion order (LinkedHashSet)
     * while automatically filtering out duplicate entries.
     *
     * <p>The method adds each name twice to show duplicate handling:</p>
     * <ul>
     *   <li>Mateo (added twice, stored once)</li>
     *   <li>Kenji (added twice, stored once)</li>
     *   <li>Rose (added twice, stored once)</li>
     * </ul>
     *
     * @return A new LinkedHashSet containing unique names in insertion order
     *
     * @see LinkedHashSet
     * @see Set#add(Object)
     */
    private static Set<String> buildNameSet() {
        Set<String> s = new LinkedHashSet<>();

        s.add("Mateo");
        s.add("Kenji");
        s.add("Rose");
        s.add("Mateo");  // Duplicate - will not be added
        s.add("Kenji");  // Duplicate - will not be added
        s.add("Rose");   // Duplicate - will not be added
        return s;
    }

    /**
     * Prints all elements in a Set using an Iterator.
     * For LinkedHashSet, elements are displayed in their insertion order.
     * This method does not modify the set contents.
     *
     * @param set The Set to iterate through and print. Must not be null.
     * @param <T> The generic type of elements in the set
     *
     * @throws NullPointerException if the set parameter is null
     *
     * @see Set#iterator()
     * @see Iterator#hasNext()
     * @see Iterator#next()
     */
    private static void printAllItemsSet(Set<String> set) {
        Iterator<String> it = set.iterator();
        while (it.hasNext()) {
            System.out.println(it.next());
        }
    }
}
