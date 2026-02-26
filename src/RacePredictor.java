import java.util.ArrayList;
import java.util.List;

/**
 * Proof of Concept for the RacePredictor component. Demonstrates data storage,
 * kernel methods, and predictive logic.
 */
public class RacePredictor {

    // --- 1. Fields: Demonstrates state representation ---
    private List<Integer> history;
    private String driverName;

    /**
     * Constructor to initialize the predictor with a driver's name.
     * 
     * @param name
     *            the name of the driver
     */
    public RacePredictor(String name) {
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
        this.history.add(position); //
    }

    /**
     * Returns the total number of race results stored.
     * 
     * @return the size of the history list
     */
    public int historySize() {
        return this.history.size(); //
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

        // Logical threshold: If average rank is 3rd or better, high probability
        return average <= 3.0 ? 0.85 : 0.25;
    }

    // --- 4. Main Method: Demonstrates the component's value ---
    public static void main(String[] args) {
        // Create an instance for testing
        RacePredictor predictor = new RacePredictor("Max Verstappen");

        // Simulate adding race data
        predictor.enterResult(1);
        predictor.enterResult(2);
        predictor.enterResult(5);

        // Output results to console to prove functionality
        System.out.println("Driver: " + predictor.driverName);
        System.out.println("Total Races Tracked: " + predictor.historySize());
        System.out.println(
                "Predicted Podium Probability: " + predictor.predictPodium());
    }
}