import components.sequence.Sequence;
import components.sequence.Sequence1L;

/**
 * Kernel implementation of {@code RacePredictor}.
 */
public final class RacePredictor1L extends RacePredictorSecondary {

    /**
     * Representation of stored race results.
     */
    private Sequence<Integer> rep;

    /**
     * Creates a new representation.
     *
     * @ensures rep is initialized
     */
    private void createNewRep() {
        this.rep = new Sequence1L<>();
    }

    /**
     * No-argument constructor.
     *
     * @ensures this is empty
     */
    public RacePredictor1L() {
        this.createNewRep();
    }

    @Override
    public void enterResult(int position) {
        assert position > 0 : "Violation of: position > 0";
        this.rep.add(this.rep.length(), position);
    }

    @Override
    public int historySize() {
        return this.rep.length();
    }

    @Override
    public int removeAny() {
        assert this.historySize() > 0 : "Violation of: historySize() > 0";
        return this.rep.remove(this.rep.length() - 1);
    }

    @Override
    public RacePredictor newInstance() {
        return new RacePredictor1L();
    }

    @Override
    public void clear() {
        this.createNewRep();
    }

    @Override
    public void transferFrom(RacePredictor source) {
        assert source != null : "Violation of: source is not null";
        assert source != this : "Violation of: source is not this";
        assert source instanceof RacePredictor1L
                : "Violation of: source is of dynamic type RacePredictor1L";

        RacePredictor1L localSource = (RacePredictor1L) source;
        this.rep = localSource.rep;
        localSource.createNewRep();
    }
}
