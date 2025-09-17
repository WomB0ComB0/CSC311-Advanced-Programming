import java.util.Scanner;

/**
 * The main class that runs the Inversion of Control (IoC) container application.
 * It provides a menu-driven interface for interacting with the container.
 */
public class Main {

    /**
     * The entry point of the application.
     * Initializes the IOCContainer and handles the user interaction loop.
     *
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        IOCContainer container = new IOCContainer(); // IOCContainer is no longer an inner class
        Scanner scanner = new Scanner(System.in);
        int choice = 0;

        do {
            printMenu();
            choice = getUserChoice(scanner);

            switch (choice) {
                case 1:
                    registerNewCustomer(container, scanner);
                    break;
                case 2:
                    registerNewShoppingCart(container, scanner);
                    break;
                case 3:
                    loadShoppingCartFromFile(container, scanner);
                    break;
                case 4:
                    addItemToShoppingCart(container, scanner);
                    break;
                case 5:
                    showBeanInfo(container, scanner);
                    break;
                case 6:
                    container.showAllRegisteredBeans();
                    break;
                case 7:
                    container.clear();
                    break;
                case 8:
                    System.out.println("Exiting program.");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
            System.out.println(); // Add a blank line for readability
        } while (choice != 8);

        scanner.close();
    }

    /**
     * Prints the main menu options to the console.
     */
    private static void printMenu() {
        System.out.println("-------------------------");
        System.out.println("Container Menu");
        System.out.println("-------------------------");
        System.out.println("1 - Register new customer bean in container.");
        System.out.println("2 - Register new shopping cart bean in container.");
        System.out.println("3 - Load shopping cart from file.");
        System.out.println("4 - Add an item to a shopping cart.");
        System.out.println("5 - Show bean info.");
        System.out.println("6 - Show all registered beans.");
        System.out.println("7 - Clear container.");
        System.out.println("8 - Exit.");
        System.out.println("-------------------------");
    }

    /**
     * Prompts the user for their menu choice and validates the input.
     *
     * @param scanner The Scanner object to read user input.
     * @return The user's valid integer choice.
     */
    private static int getUserChoice(Scanner scanner) {
        System.out.print("Enter Choice: ");
        while (!scanner.hasNextInt()) {
            System.out.println("Invalid input. Please enter a number.");
            scanner.next(); // consume the non-integer input
            System.out.print("Enter Choice: ");
        }
        return scanner.nextInt();
    }

    /**
     * Handles the registration of a new customer bean.
     * Prompts the user for customer details and registers them with the container.
     *
     * @param container The IOCContainer instance.
     * @param scanner   The Scanner object to read user input.
     */
    private static void registerNewCustomer(IOCContainer container, Scanner scanner) {
        System.out.print("Enter Customer Bean Id: ");
        String customerId = scanner.next();
        System.out.print("Enter customer name: ");
        scanner.nextLine(); // Consume newline left-over
        String customerName = scanner.nextLine();
        System.out.print("Enter customer address: ");
        String customerAddress = scanner.nextLine();
        container.registerCustomer(customerId, customerName, customerAddress);
    }

    /**
     * Handles the registration of a new shopping cart bean.
     * Prompts the user for shopping cart and customer IDs and registers the cart.
     *
     * @param container The IOCContainer instance.
     * @param scanner   The Scanner object to read user input.
     */
    private static void registerNewShoppingCart(IOCContainer container, Scanner scanner) {
        System.out.print("Enter ShoppingCart Bean Id: ");
        String shoppingCartId = scanner.next();
        System.out.print("Enter Customer Bean Id: ");
        String customerId = scanner.next();
        container.registerShoppingCart(shoppingCartId, customerId);
    }

    /**
     * Loads items from a file into an existing shopping cart.
     * Prompts the user for the shopping cart ID and the filename.
     *
     * @param container The IOCContainer instance.
     * @param scanner   The Scanner object to read user input.
     */
    private static void loadShoppingCartFromFile(IOCContainer container, Scanner scanner) {
        System.out.print("Enter ShoppingCart Bean Id: ");
        String shoppingCartId = scanner.next();
        System.out.print("Enter filename: ");
        String filename = scanner.next();

        Bean bean = container.getBean(shoppingCartId);
        if (bean == null || !(bean instanceof ShoppingCart)) {
            System.out.println("Error: Shopping cart with ID '" + shoppingCartId + "' not found or is not a ShoppingCart bean.");
            return;
        }

        ShoppingCart cart = (ShoppingCart) bean;
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String itemName = line;
                if ((line = reader.readLine()) != null) {
                    try {
                        double itemPrice = Double.parseDouble(line);
                        // Item is now a top-level class, so no 'this' needed.
                        Item item = new Item(itemName, itemPrice);
                        cart.add(item);
                    } catch (NumberFormatException e) {
                        System.err.println("Error parsing price for item '" + itemName + "': " + line);
                    }
                } else {
                    System.err.println("Incomplete item data for '" + itemName + "' in file.");
                    break;
                }
            }
            System.out.println("Shopping cart '" + shoppingCartId + "' loaded from '" + filename + "'.");
        } catch (IOException e) {
            System.err.println("Error reading file '" + filename + "': " + e.getMessage());
        }
    }

    /**
     * Adds an item to a specified shopping cart.
     * Prompts the user for the shopping cart ID, item name, and item price.
     *
     * @param container The IOCContainer instance.
     * @param scanner   The Scanner object to read user input.
     */
    private static void addItemToShoppingCart(IOCContainer container, Scanner scanner) {
        System.out.print("Enter ShoppingCart Bean Id: ");
        String shoppingCartId = scanner.next();
        System.out.print("Enter Item Name: ");
        String itemName = scanner.next();
        System.out.print("Enter Item Price: ");
        while (!scanner.hasNextDouble()) {
            System.out.println("Invalid input. Please enter a valid price.");
            scanner.next(); // consume the non-double input
            System.out.print("Enter Item Price: ");
        }
        double itemPrice = scanner.nextDouble();

        Bean bean = container.getBean(shoppingCartId);
        if (bean == null || !(bean instanceof ShoppingCart)) {
            System.out.println("Error: Shopping cart with ID '" + shoppingCartId + "' not found or is not a ShoppingCart bean.");
            return;
        }

        ShoppingCart cart = (ShoppingCart) bean;
        // Item is now a top-level class, so no 'this' needed.
        Item item = new Item(itemName, itemPrice);
        cart.add(item);
        System.out.println("Item '" + itemName + "' added to shopping cart '" + shoppingCartId + "'.");
    }

    /**
     * Displays the information of a specific bean from the container.
     * Prompts the user for the bean ID.
     *
     * @param container The IOCContainer instance.
     * @param scanner   The Scanner object to read user input.
     */
    private static void showBeanInfo(IOCContainer container, Scanner scanner) {
        System.out.print("Enter Bean Id: ");
        String beanId = scanner.next();
        container.showBeanInfo(beanId);
    }
}