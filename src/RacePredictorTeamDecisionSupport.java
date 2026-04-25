/**
 * Demonstrates using {@code RacePredictor} to compare two drivers.
 */
public final class RacePredictorTeamDecisionSupport {

    /**
     * Private constructor to prevent instantiation.
     */
    private RacePredictorTeamDecisionSupport() {
    }

    /**
     * Builds a predictor from the provided finishing positions.
     *
     * @param positions
     *            race positions to store
     * @return a populated predictor
     */
    private static RacePredictor buildPredictor(int... positions) {
        RacePredictor predictor = new RacePredictor1L();
        for (int position : positions) {
            predictor.enterResult(position);
        }
        return predictor;
    }

    /**
     * Compares two drivers for a simple team decision scenario.
     *
     * @param args
     *            command-line arguments, not used
     */
    public static void main(String[] args) {
        RacePredictor leadDriver = buildPredictor(1, 2, 2);
        RacePredictor supportDriver = buildPredictor(5, 6, 4);

        System.out.println("Lead driver podium probability: "
                + leadDriver.predictPodium());
        System.out.println("Support driver podium probability: "
                + supportDriver.predictPodium());

        if (leadDriver.predictPodium() > supportDriver.predictPodium()) {
            System.out.println("Recommendation: prioritize the lead driver.");
        } else {
            System.out.println("Recommendation: performance profiles are similar.");
        }
    }
}
