import java.util.Objects;

/**
 * Represents a product with its various attributes.
 * This class stores information such as id, name, price, stock status,
 * rating, description, and customer review.
 */
public class Product {

    private String id;
    private String name;
    private double price;
    private String stockStatus;
    private double rating;
    private String description;
    private String customerReview;

    /**
     * Constructs a new Product with specified details.
     *
     * @param id             The unique identifier for the product.
     * @param name           The name of the product.
     * @param price          The price of the product.
     * @param stockStatus    The stock status (e.g., "In Stock", "Out of Stock").
     * @param rating         The customer rating of the product (e.g., out of 5).
     * @param description    A brief description of the product.
     * @param customerReview A customer's review of the product.
     */
    public Product(String id, String name, double price, String stockStatus, double rating, String description, String customerReview) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stockStatus = stockStatus;
        this.rating = rating;
        this.description = description;
        this.customerReview = customerReview;
    }

    // Getters and Setters with JavaDoc

    /** @return The product's unique ID. */
    public String getId() { return id; }
    /** @param id The product's new ID. */
    public void setId(String id) { this.id = id; }

    /** @return The product's name. */
    public String getName() { return name; }
    /** @param name The product's new name. */
    public void setName(String name) { this.name = name; }

    /** @return The product's price. */
    public double getPrice() { return price; }
    /** @param price The product's new price. */
    public void setPrice(double price) { this.price = price; }

    /** @return The product's stock status. */
    public String getStockStatus() { return stockStatus; }
    /** @param stockStatus The product's new stock status. */
    public void setStockStatus(String stockStatus) { this.stockStatus = stockStatus; }

    /** @return The product's rating. */
    public double getRating() { return rating; }
    /** @param rating The product's new rating. */
    public void setRating(double rating) { this.rating = rating; }

    /** @return The product's description. */
    public String getDescription() { return description; }
    /** @param description The product's new description. */
    public void setDescription(String description) { this.description = description; }

    /** @return The product's customer review. */
    public String getCustomerReview() { return customerReview; }
    /** @param customerReview The product's new customer review. */
    public void setCustomerReview(String customerReview) { this.customerReview = customerReview; }

    @Override
    public String toString() {
        return "Product{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}