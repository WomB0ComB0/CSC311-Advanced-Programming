import java.util.HashMap;
import java.util.Map;

/**
 * A container that manages and provides instances of Beans.
 * It handles the registration, retrieval, and management of beans.
 */
class IOCContainer {
    private Map<String, Bean> registeredBeans;

    /**
     * Constructs an IOCContainer and initializes the map for registered beans.
     */
    IOCContainer() {
        this.registeredBeans = new HashMap<>();
    }

    /**
     * Creates and registers a new ShoppingCart bean.
     * It injects a Customer bean with the specified customerId into the new ShoppingCart.
     *
     * @param shoppingCartId The unique ID for the shopping cart bean.
     * @param customerId     The ID of the Customer bean to inject.
     */
    void registerShoppingCart(String shoppingCartId, String customerId) {
        if (registeredBeans.containsKey(shoppingCartId)) {
            System.out.println("Error: Shopping cart ID '" + shoppingCartId + "' already exists.");
            return;
        }

        Bean customerBean = registeredBeans.get(customerId);
        if (customerBean == null || !(customerBean instanceof Customer)) {
            System.out.println("Error: Customer with ID '" + customerId + "' not found or is not a Customer bean.");
            return;
        }

        Customer customer = (Customer) customerBean;
        ShoppingCart shoppingCart = new ShoppingCart(customer);
        registeredBeans.put(shoppingCartId, shoppingCart);
        System.out.println("Shopping cart '" + shoppingCartId + "' registered successfully.");
    }

    /**
     * Creates and registers a new Customer bean.
     *
     * @param customerId        The unique ID for the customer bean.
     * @param customerName      The name of the customer.
     * @param customerAddress   The address of the customer.
     */
    void registerCustomer(String customerId, String customerName, String customerAddress) {
        if (registeredBeans.containsKey(customerId)) {
            System.out.println("ERROR: Customer ID '" + customerId + "' already exists. Choose a different customer id.");
            return;
        }

        Customer customer = new Customer(customerName, customerAddress);
        registeredBeans.put(customerId, customer);
        System.out.println("Customer '" + customerId + "' registered successfully.");
    }

    /**
     * Retrieves a bean instance by its ID.
     *
     * @param beanId The ID of the bean to retrieve.
     * @return The Bean instance if found, otherwise null.
     */
    Bean getBean(String beanId) {
        return registeredBeans.get(beanId);
    }

    /**
     * Displays the information of a bean by its ID.
     * If the bean is not found, an error message is printed.
     *
     * @param beanId The ID of the bean whose information to show.
     */
    void showBeanInfo(String beanId) {
        Bean bean = registeredBeans.get(beanId);
        if (bean == null) {
            System.out.println("Error: Bean with ID '" + beanId + "' does not exist.");
        } else {
            bean.showInfo();
        }
    }

    /**
     * Displays a list of all registered beans, including their IDs and types.
     * If the container is empty, a message indicating so is printed.
     */
    void showAllRegisteredBeans() {
        if (registeredBeans.isEmpty()) {
            System.out.println("Container is empty.");
            return;
        }
        System.out.println("\nBean Name\tBean Type");
        System.out.println("-------------------------");
        for (Map.Entry<String, Bean> entry : registeredBeans.entrySet()) {
            System.out.println(entry.getKey() + "\t\t" + entry.getValue().getBeanType());
        }
        System.out.println("-------------------------");
    }

    /**
     * Clears all registered beans from the container.
     */
    void clear() {
        registeredBeans.clear();
        System.out.println("All beans cleared from the container.");
    }
}