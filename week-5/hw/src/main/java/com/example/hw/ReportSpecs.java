/**
 * Stores the specifications for generating a report.
 * This class is immutable and is constructed using the Builder pattern.
 */
public class ReportSpecs {

    private final String filename;
    private final double maximumPrice;
    private final double minimumPrice;
    private final double maximumRating;
    private final double minimumRating;
    private final String nameRegex;

    /**
     * Private constructor to be used by the Builder.
     * @param builder The builder instance containing all the specifications.
     */
    private ReportSpecs(Builder builder) {
        this.filename = builder.filename;
        this.maximumPrice = builder.maximumPrice;
        this.minimumPrice = builder.minimumPrice;
        this.maximumRating = builder.maximumRating;
        this.minimumRating = builder.minimumRating;
        this.nameRegex = builder.nameRegex;
    }

    // Getters for all specification fields
    public String getFilename() { return filename; }
    public double getMaximumPrice() { return maximumPrice; }
    public double getMinimumPrice() { return minimumPrice; }
    public double getMaximumRating() { return maximumRating; }
    public double getMinimumRating() { return minimumRating; }
    public String getNameRegex() { return nameRegex; }

    /**
     * The Builder class for creating ReportSpecs instances.
     * Follows the Builder design pattern to allow for flexible object creation.
     */
    public static class Builder {
        // Required parameter
        private final String filename;

        // Optional parameters with default values
        private double maximumPrice = 1000.0;
        private double minimumPrice = 0.0;
        private double maximumRating = 5.0;
        private double minimumRating = 0.0;
        private String nameRegex = ".*"; // Matches any string

        /**
         * Constructs a new Builder with the required filename.
         * @param filename The name of the data file (e.g., "products.csv"). Must not be null.
         */
        public Builder(String filename) {
            if (filename == null || filename.trim().isEmpty()) {
                throw new IllegalArgumentException("Filename cannot be null or empty.");
            }
            this.filename = filename;
        }

        /**
         * Sets the maximum price for the report.
         * @param maxPrice The highest price to include.
         * @return The builder instance for chaining.
         */
        public Builder maximumPrice(double maxPrice) {
            this.maximumPrice = maxPrice;
            return this;
        }

        /**
         * Sets the minimum price for the report.
         * @param minPrice The lowest price to include.
         * @return The builder instance for chaining.
         */
        public Builder minimumPrice(double minPrice) {
            this.minimumPrice = minPrice;
            return this;
        }

        /**
         * Sets the maximum rating for the report.
         * @param maxRating The highest rating to include.
         * @return The builder instance for chaining.
         */
        public Builder maximumRating(double maxRating) {
            this.maximumRating = maxRating;
            return this;
        }

        /**
         * Sets the minimum rating for the report.
         * @param minRating The lowest rating to include.
         * @return The builder instance for chaining.
         */
        public Builder minimumRating(double minRating) {
            this.minimumRating = minRating;
            return this;
        }

        /**
         * Sets the regular expression to filter product names.
         * @param regex The regular expression for matching product names.
         * @return The builder instance for chaining.
         */
        public Builder nameRegex(String regex) {
            this.nameRegex = regex;
            return this;
        }

        /**
         * Builds and returns an immutable ReportSpecs object.
         * @return A new instance of ReportSpecs.
         */
        public ReportSpecs build() {
            return new ReportSpecs(this);
        }
    }
}