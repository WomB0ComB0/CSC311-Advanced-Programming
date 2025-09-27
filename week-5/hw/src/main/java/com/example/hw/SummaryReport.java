import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Generates a summary report of product data.
 * This class implements the Report interface.
 */
public class SummaryReport implements Report {

    /**
     * Generates and prints a summary report.
     * It filters products based on the report specifications and then uses
     * a Java Stream to calculate the average price and rating of the
     * included products.
     *
     * @param ps          The PrintStream to write the report to.
     * @param reportSpecs The specifications for the report.
     */
    @Override
    public void generate(PrintStream ps, ReportSpecs reportSpecs) {
        printHeader(ps, reportSpecs);
        List<Product> products = readProductsFromFile(reportSpecs.getFilename());

        // Use a Java Stream to filter products first.
        List<Product> filteredProducts = products.stream()
                .filter(p -> p.getPrice() <= reportSpecs.getMaximumPrice())
                .filter(p -> p.getPrice() >= reportSpecs.getMinimumPrice())
                .filter(p -> p.getRating() <= reportSpecs.getMaximumRating())
                .filter(p -> p.getRating() >= reportSpecs.getMinimumRating())
                .filter(p -> p.getName().matches(reportSpecs.getNameRegex()))
                .collect(Collectors.toList());

        // Use Java Streams on the filtered list to calculate averages.
        double averagePrice = filteredProducts.stream()
                .mapToDouble(Product::getPrice)
                .average()
                .orElse(0.0);

        double averageRating = filteredProducts.stream()
                .mapToDouble(Product::getRating)
                .average()
                .orElse(0.0);

        ps.printf("Average Price  = %.2f%n", averagePrice);
        ps.printf("Average Rating = %.2f%n", averageRating);
        ps.println("------------------------------------------\n\n");
    }

    /**
     * Prints the header information for the report.
     * @param ps The PrintStream to write to.
     * @param spec The report specifications.
     */
    private void printHeader(PrintStream ps, ReportSpecs spec) {
        ps.println("Summary Report");
        ps.println("------------------------------------------");
        ps.printf("Filename    : %s%n", spec.getFilename());
        ps.printf("Price Max   : %.2f%n", spec.getMaximumPrice());
        ps.printf("Price Min   : %.2f%n", spec.getMinimumPrice());
        ps.printf("Rating Max  : %.2f%n", spec.getMaximumRating());
        ps.printf("Rating Min  : %.2f%n", spec.getMinimumRating());
        ps.printf("Name Regex  : %s%n", spec.getNameRegex());
        ps.println("------------------------------------------");
    }

    /**
     * Reads product data from a CSV file.
     * Note: In a larger application, this might be moved to a shared utility class.
     *
     * @param filename The path to the CSV file.
     * @return A list of Product objects.
     */
    private List<Product> readProductsFromFile(String filename) {
        List<Product> products = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            br.readLine(); // Skip header
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                // *** THIS IS THE CORRECTED LINE ***
                Product product = new Product(
                        values[0],                      // id (String)
                        values[1],                      // name (String)
                        Double.parseDouble(values[2]),  // price (double)
                        values[3],                      // stockStatus (String)
                        Double.parseDouble(values[4]),  // rating (double)
                        values[5],                      // description (String)
                        values[6]                       // customerReview (String)
                );
                products.add(product);
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
        return products;
    }
}