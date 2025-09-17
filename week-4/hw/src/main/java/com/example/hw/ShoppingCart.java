import java.util.ArrayList;
import java.util.List;

/**
 * Represents a shopping cart that holds a list of items for a specific customer.
 * Implements the Bean interface.
 */
class ShoppingCart implements Bean {
    private List<Item> items;
    private Customer customer;

    /**
     * Constructs a ShoppingCart for a given customer.
     *
     * @param customer The Customer associated with this shopping cart.
     */
    ShoppingCart(Customer customer) {
        this.items = new ArrayList<>();
        this.customer = customer;
    }

    /**
     * {@inheritDoc}
     * Returns "ShoppingCart" as the bean type.
     */
    @Override
    public String getBeanType() {
        return "ShoppingCart";
    }

    /**
     * {@inheritDoc}
     * Displays the customer's name and address, followed by the items in the cart
     * and the total cost.
     */
    @Override
    public void showInfo() {
        System.out.println("Customer Name: " + this.customer.getName());
        System.out.println("Customer Address: " + this.customer.getAddress());
        System.out.println("Items:");
        if (this.items.isEmpty()) {
            System.out.println("  No items in cart.");
        } else {
            for (Item item : this.items) {
                System.out.printf("  - %s: $%.2f%n", item.getName(), item.getPrice());
            }
        }
        System.out.printf("Total Cost: $%.2f%n", getTotalCost());
    }

    /**
     * Adds a given item to the shopping cart.
     *
     * @param item The Item to add to the cart.
     */
    public void add(Item item) {
        this.items.add(item);
    }

    /**
     * Calculates and returns the total cost of all items in the shopping cart.
     *
     * @return The total cost as a double.
     */
    public double getTotalCost() {
        double total = 0;
        for (Item item : this.items) {
            total += item.getPrice();
        }
        return total;
    }

    /**
     * Clears all items from the shopping cart.
     */
    public void clear() {
        this.items.clear();
    }

    /**
     * Gets the list of items in the shopping cart.
     *
     * @return The list of Items.
     */
    public List<Item> getItems() {
        return items;
    }

    /**
     * Gets the customer associated with the shopping cart.
     *
     * @return The Customer.
     */
    public Customer getCustomer() {
        return customer;
    }
}