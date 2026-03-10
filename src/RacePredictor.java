/**
 * Enhanced interface for the RacePredictor component.
 */
public interface RacePredictor extends RacePredictorKernel {
    /**
     * Predicts the probability that the driver will finish on the podium (top 3
     * positions) based on historical race results.
     *
     * @return a probability value between 0.0 and 1.0 representing the
     *         likelihood of a podium finish
     */
    double predictPodium();
}
