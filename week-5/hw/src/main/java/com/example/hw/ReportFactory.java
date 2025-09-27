/**
 * A factory for creating different types of Report objects.
 * This class uses a static factory method to decouple the client
 * from the concrete report implementations (DetailReport, SummaryReport).
 */
public class ReportFactory {

    /**
     * Private constructor to prevent instantiation of the factory itself.
     */
    private ReportFactory() {}

    /**
     * Creates and returns a Report object based on the specified type.
     *
     * @param reportType A string indicating the type of report to create.
     *                   Valid options are "DETAIL" and "SUMMARY". Case-insensitive.
     * @return An object that implements the Report interface.
     * @throws IllegalArgumentException if the reportType is unknown.
     */
    public static Report getReport(String reportType) {
        if (reportType == null) {
            return null;
        }
        if (reportType.equalsIgnoreCase("DETAIL")) {
            return new DetailReport();
        } else if (reportType.equalsIgnoreCase("SUMMARY")) {
            return new SummaryReport();
        }

        throw new IllegalArgumentException("Unknown report type: " + reportType);
    }
}