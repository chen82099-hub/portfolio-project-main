/**
 * Kernel interface for the RacePredictor component.
 */
public interface RacePredictorKernel {
    /**
     * Adds a race finishing position to the stored history.
     *
     * @param position
     *            the finishing position of the driver
     */
    void enterResult(int position);

    /**
     * Returns the number of race results stored in the history.
     *
     * @return the total number of stored race results
     */
    int historySize();
}
