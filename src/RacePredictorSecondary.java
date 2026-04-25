import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import components.sequence.Sequence;
import components.sequence.Sequence1L;

/**
 * Secondary methods for the {@code RacePredictor} component.
 */
public abstract class RacePredictorSecondary implements RacePredictor {

    /**
     * Largest finishing position that still counts as a podium result.
     */
    private static final int PODIUM_POSITION = 3;

    /**
     * Returns a snapshot of the stored results and restores the component
     * state before returning.
     *
     * @return a sequence containing the stored race positions
     * @updates this
     * @ensures this = #this
     */
    private Sequence<Integer> snapshot() {
        Sequence<Integer> values = new Sequence1L<>();
        RacePredictor temp = this.newInstance();

        while (this.historySize() > 0) {
            int position = this.removeAny();
            values.add(values.length(), position);
            temp.enterResult(position);
        }

        this.transferFrom(temp);
        return values;
    }

    @Override
    public final double predictPodium() {
        Sequence<Integer> values = this.snapshot();

        if (values.length() == 0) {
            return 0.0;
        }

        int podiumCount = 0;

        for (int position : values) {
            if (position <= PODIUM_POSITION) {
                podiumCount++;
            }
        }

        return (double) podiumCount / values.length();
    }

    @Override
    public final String toString() {
        StringBuilder result = new StringBuilder();
        Sequence<Integer> values = this.snapshot();

        for (int position : values) {
            if (result.length() > 0) {
                result.append(' ');
            }
            result.append(position);
        }

        return result.toString();
    }

    @Override
    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof RacePredictor)) {
            return false;
        }

        RacePredictor other = (RacePredictor) obj;
        List<Integer> thisValues = sortedSnapshot(this);
        List<Integer> otherValues = sortedSnapshot(other);
        return thisValues.equals(otherValues);
    }

    /**
     * Returns a snapshot of the given predictor and restores its state before
     * returning.
     *
     * @param predictor
     *            predictor under inspection
     * @return a sequence containing the stored race positions
     * @updates predictor
     * @ensures predictor = #predictor
     */
    private static Sequence<Integer> snapshotOf(RacePredictor predictor) {
        Sequence<Integer> values = new Sequence1L<>();
        RacePredictor temp = predictor.newInstance();

        while (predictor.historySize() > 0) {
            int position = predictor.removeAny();
            values.add(values.length(), position);
            temp.enterResult(position);
        }

        predictor.transferFrom(temp);
        return values;
    }

    /**
     * Returns a sorted list view of the predictor contents.
     *
     * @param predictor
     *            predictor under inspection
     * @return sorted stored race positions
     * @updates predictor
     * @ensures predictor = #predictor
     */
    private static List<Integer> sortedSnapshot(RacePredictor predictor) {
        Sequence<Integer> values = snapshotOf(predictor);
        List<Integer> result = new ArrayList<>();

        for (int value : values) {
            result.add(value);
        }

        Collections.sort(result);
        return result;
    }
}
