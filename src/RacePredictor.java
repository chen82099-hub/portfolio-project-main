/**
 * Enhanced interface for the {@code RacePredictor} component.
 */
public interface RacePredictor extends RacePredictorKernel {

    /**
     * Predicts the probability that the driver will finish on the podium (top 3
     * positions) based on historical race results.
     *
     * @return a probability value between 0.0 and 1.0 representing the
     *         likelihood of a podium finish
     * @ensures let p = predictPodium in 0.0 <= p and p <= 1.0 and this = #this
     *          and ((|#this| = 0 and p = 0.0) or (|#this| > 0 and p = (number
     *          of entries in #this less than or equal to 3) / |#this|))
     */
    double predictPodium();
}
