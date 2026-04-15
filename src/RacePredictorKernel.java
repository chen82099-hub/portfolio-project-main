import components.standard.Standard;

/**
 * Kernel interface for the RacePredictor component.
 */
public interface RacePredictorKernel extends Standard<RacePredictor> {

    /**
     * Adds a race finishing position to the stored history.
     *
     * @param position
     *            the finishing position of the driver
     * @updates this
     * @ensures this contains position
     */
    void enterResult(int position);

    /**
     * Returns the number of race results stored in the history.
     *
     * @return the total number of stored race results
     * @ensures historySize = |this|
     */
    int historySize();

    /**
     * Removes and returns one race result.
     *
     * @return a race position
     * @requires historySize() > 0
     * @updates this
     * @ensures <result> was in #this and this = #this \ {result}
     */
    int removeAny();
}
