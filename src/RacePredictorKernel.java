/**
 * Kernel interface for the RacePredictor component.
 */
public interface RacePredictorKernel {
    void enterResult(int position);

    int historySize();
}