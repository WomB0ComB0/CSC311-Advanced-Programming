/**
 * Represents an item with a name and price.
 * This class does not implement any interfaces or inherit from other classes.
 */
class Item {
    private String name;
    private double price;

    /**
     * Default constructor for Item. Initializes name to "" and price to 0.0.
     */
    Item() {
        this.name = "";
        this.price = 0.0;
    }

    /**
     * Constructs an Item with a specified name and price.
     *
     * @param name  The name of the item.
     * @param price The price of the item.
     */
    Item(String name, double price) {
        this.name = name;
        this.price = price;
    }

    /**
     * Gets the name of the item.
     *
     * @return The item's name.
     */
    String getName() {
        return name;
    }

    /**
     * Gets the price of the item.
     *
     * @return The item's price.
     */
    double getPrice() {
        return price;
    }

    /**
     * Sets the name of the item.
     *
     * @param newName The new name for the item.
     */
    void setName(String newName) {
        this.name = newName;
    }

    /**
     * Sets the price of the item.
     *
     * @param newPrice The new price for the item.
     */
    void setPrice(double newPrice) {
        this.price = newPrice;
    }
}