import java.io.PrintStream;

/**
 * Defines the contract for report generation.
 * Any class that generates a report must implement this interface.
 */
public interface Report {

    /**
     * Generates a report based on the given specifications and prints it
     * to the provided PrintStream.
     *
     * @param ps          The PrintStream to which the report will be written (e.g., System.out).
     * @param reportSpecs The specifications (filters, filename) for the report.
     */
    void generate(PrintStream ps, ReportSpecs reportSpecs);
}