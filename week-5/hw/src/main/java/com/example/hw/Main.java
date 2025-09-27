import java.io.PrintStream;

/**
 * The main driver class for the report generation application.
 * This class demonstrates the use of the ReportFactory and ReportSpecs builder
 * to generate multiple reports with different criteria.
 */
public class Main {

    public static void main(String[] args) {
        // Use System.out as the PrintStream for console output
        PrintStream console = System.out;
        String dataFile = "products.csv";

        // --- DEMONSTRATION 1: Report with a low max rating and a specific name pattern ---
        console.println("====== TEST 1: Max Rating 3.0, Name starts with 'S' ======\n");

        // Use the builder to create the specifications
        ReportSpecs specs1 = new ReportSpecs.Builder(dataFile)
                .maximumRating(3.0)
                .nameRegex("S.*")
                .build();

        // Use the factory to create the report objects
        Report detailReport1 = ReportFactory.getReport("DETAIL");
        Report summaryReport1 = ReportFactory.getReport("SUMMARY");

        // Generate the reports
        detailReport1.generate(console, specs1);
        summaryReport1.generate(console, specs1);


        // --- DEMONSTRATION 2: Report with a specific price range ---
        console.println("====== TEST 2: Price between $50 and $150 ======\n");

        ReportSpecs specs2 = new ReportSpecs.Builder(dataFile)
                .minimumPrice(50.0)
                .maximumPrice(150.0)
                .build();

        Report detailReport2 = ReportFactory.getReport("DETAIL");
        Report summaryReport2 = ReportFactory.getReport("SUMMARY");

        detailReport2.generate(console, specs2);
        summaryReport2.generate(console, specs2);

        // --- DEMONSTRATION 3: Report with all default settings (only filename provided) ---
        console.println("====== TEST 3: All Default Specifications ======\n");

        ReportSpecs specs3 = new ReportSpecs.Builder(dataFile).build();

        Report detailReport3 = ReportFactory.getReport("DETAIL");
        Report summaryReport3 = ReportFactory.getReport("SUMMARY");

        detailReport3.generate(console, specs3);
        summaryReport3.generate(console, specs3);
    }
}