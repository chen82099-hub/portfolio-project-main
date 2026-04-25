/**
 * Demonstrates using {@code RacePredictor} to summarize a driver's season.
 */
public final class RacePredictorSeasonReport {

    /**
     * Private constructor to prevent instantiation.
     */
    private RacePredictorSeasonReport() {
    }

    /**
     * Builds and prints a simple season report.
     *
     * @param args
     *            command-line arguments, not used
     */
    public static void main(String[] args) {
        RacePredictor predictor = new RacePredictor1L();

        predictor.enterResult(1);
        predictor.enterResult(2);
        predictor.enterResult(4);
        predictor.enterResult(3);

        double probability = predictor.predictPodium();

        System.out.println("=== Season Report ===");
        System.out.println("Stored race results: " + predictor);
        System.out
                .println("Number of races tracked: " + predictor.historySize());
        System.out.println("Predicted podium probability: " + probability);
    }
}
