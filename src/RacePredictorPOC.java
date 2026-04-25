import java.util.ArrayList;
import java.util.List;

/**
 * Proof of Concept for the RacePredictor component. Demonstrates data storage,
 * kernel methods, and predictive logic.
 */
public class RacePredictorPOC {

    // --- 1. Fields: Demonstrates state representation ---

    /**
     * Stores the history of race finishing positions.
     */
    private List<Integer> history;

    /**
     * The name of the driver being analyzed.
     */
    private String driverName;

    /**
     * Threshold average finishing position considered strong performance.
     */
    private static final double PODIUM_THRESHOLD = 3.0;

    /**
     * Probability returned when performance is strong.
     */
    private static final double HIGH_PROBABILITY = 0.85;

    /**
     * Probability returned when performance is weaker.
     */
    private static final double LOW_PROBABILITY = 0.25;

    /**
     * Example race result used in demonstration.
     */
    private static final int SAMPLE_RESULT_THREE = 5;

    /**
     * Constructor to initialize the predictor with a driver's name.
     *
     * @param name
     *            the name of the driver
     */
    public RacePredictorPOC(String name) {
        this.driverName = name;
        this.history = new ArrayList<>();
    }

    // --- 2. Kernel Methods: Core functionality from Part 1 design ---

    /**
     * Adds a race finish position to the driver's history.
     *
     * @param position
     *            the finishing rank (e.g., 1 for first place)
     */
    public void enterResult(int position) {
        this.history.add(position);
    }

    /**
     * Returns the total number of race results stored.
     *
     * @return the size of the history list
     */
    public int historySize() {
        return this.history.size();
    }

    // --- 3. Secondary Methods: Demonstrates high-level logic ---

    /**
     * Predicts the probability of the driver reaching the podium (Top 3).
     *
     * @return a double representing the probability (0.0 to 1.0)
     */
    public double predictPodium() {
        if (this.historySize() == 0) {
            return 0.0;
        }

        double sum = 0;

        for (int pos : this.history) {
            sum += pos;
        }

        double average = sum / this.historySize();

        // If average finishing position is within podium threshold,
        // return a higher probability of podium finish.
        if (average <= PODIUM_THRESHOLD) {
            return HIGH_PROBABILITY;
        } else {
            return LOW_PROBABILITY;
        }

    }

    // --- 4. Main Method: Demonstrates the component's value ---

    /**
     * Demonstrates the functionality of the RacePredictor component.
     *
     * @param args
     *            command-line arguments (not used)
     */
    public static void main(String[] args) {

        // Create an instance for testing
        RacePredictorPOC predictor = new RacePredictorPOC("Max Verstappen");

        // Simulate adding race data
        predictor.enterResult(1);
        predictor.enterResult(2);
        predictor.enterResult(SAMPLE_RESULT_THREE);

        // Output results to console to prove functionality
        System.out.println("Driver: " + predictor.driverName);
        System.out.println("Total Races Tracked: " + predictor.historySize());
        System.out.println(
                "Predicted Podium Probability: " + predictor.predictPodium());
    }
}
