import components.standard.Standard;

/**
 * Kernel interface for the {@code RacePredictor} component.
 *
 * <p>
 * This component models a mutable sequence of race finishing positions that can
 * be updated over time and inspected through kernel operations.
 * </p>
 */
public interface RacePredictorKernel extends Standard<RacePredictor> {

    /**
     * Adds a race finishing position to the stored history.
     *
     * @param position
     *            the finishing position of the driver (restores mode)
     * @updates this
     * @requires position > 0
     * @ensures this = #this o <position>
     */
    void enterResult(int position);

    /**
     * Returns the number of race results stored in the history.
     *
     * @return the total number of stored race results
     * @ensures result = |this|
     */
    int historySize();

    /**
     * Removes and returns one race result.
     *
     * @return a race position
     * @requires historySize() > 0
     * @updates this
     * @ensures removeAny is in #this and
     *          this = #this without one occurrence of removeAny
     */
    int removeAny();
}
