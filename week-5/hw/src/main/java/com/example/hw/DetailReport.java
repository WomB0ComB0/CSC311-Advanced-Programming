import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Generates a detailed report of products that match specific criteria.
 * This class implements the Report interface.
 */
public class DetailReport implements Report {

    /**
     * Generates and prints a detailed report.
     * It reads product data, then uses a Java Stream to filter the products
     * based on the criteria in reportSpecs, and finally prints the formatted
     * details of the filtered products.
     *
     * @param ps          The PrintStream to write the report to.
     * @param reportSpecs The specifications for the report.
     */
    @Override
    public void generate(PrintStream ps, ReportSpecs reportSpecs) {
        printHeader(ps, reportSpecs);
        List<Product> products = readProductsFromFile(reportSpecs.getFilename());

        // Use a Java Stream to filter products based on the report specifications.
        List<Product> filteredProducts = products.stream()
                .filter(p -> p.getPrice() <= reportSpecs.getMaximumPrice())
                .filter(p -> p.getPrice() >= reportSpecs.getMinimumPrice())
                .filter(p -> p.getRating() <= reportSpecs.getMaximumRating())
                .filter(p -> p.getRating() >= reportSpecs.getMinimumRating())
                .filter(p -> p.getName().matches(reportSpecs.getNameRegex()))
                .collect(Collectors.toList());

        // Print the table header
        ps.printf("%-5s %-32s %-10s %-12s %-8s %-35s %s%n",
                "Id", "Name", "Price", "Status", "Rating", "Description", "Review");
        ps.println("-".repeat(150));

        // Print each filtered product
        filteredProducts.forEach(p -> ps.printf("%-5s %-32s %-10.2f %-12s %-8.2f %-35s %s%n",
                p.getId(),
                p.getName(),
                p.getPrice(),
                p.getStockStatus(),
                p.getRating(),
                p.getDescription(),
                p.getCustomerReview()));
        ps.println("\n");
    }

    /**
     * Prints the header information for the report.
     * @param ps The PrintStream to write to.
     * @param spec The report specifications.
     */
    private void printHeader(PrintStream ps, ReportSpecs spec) {
        ps.println("Detail Report");
        ps.println("------------------------------------------");
        ps.printf("Filename    : %s%n", spec.getFilename());
        ps.printf("Price Max   : %.2f%n", spec.getMaximumPrice());
        ps.printf("Price Min   : %.2f%n", spec.getMinimumPrice());
        ps.printf("Rating Max  : %.2f%n", spec.getMaximumRating());
        ps.printf("Rating Min  : %.2f%n", spec.getMinimumRating());
        ps.printf("Name Regex  : %s%n", spec.getNameRegex());
        ps.println("------------------------------------------\n");
    }

    /**
     * Reads product data from a CSV file.
     * Assumes a comma-separated format with a header line to be skipped.
     *
     * @param filename The path to the CSV file.
     * @return A list of Product objects.
     */
    private List<Product> readProductsFromFile(String filename) {
        List<Product> products = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            // Skip the header line
            br.readLine();
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                Product product = new Product(
                        values[0],                      // id
                        values[1],                      // name
                        Double.parseDouble(values[2]),  // price
                        values[3],                      // stockStatus
                        Double.parseDouble(values[4]),  // rating
                        values[5],                      // description
                        values[6]                       // customerReview
                );
                products.add(product);
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
        return products;
    }
}