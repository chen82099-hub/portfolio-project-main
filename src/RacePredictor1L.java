import components.set.Set;
import components.set.Set1L;

/**
 * Kernel implementation of RacePredictor.
 */
public class RacePredictor1L extends RacePredictorSecondary {

    /**
     * Representation of race results.
     */
    private Set<Integer> rep;

    /*
     * Creates a new representation.
     */
    /**
     * @ensures rep is initialzed.
     */
    private void createNewRep() {
        this.rep = new Set1L<>();
    }

    /*
     * Constructor
     *
     * @ensures this is empty.
     */
    /**
     * @ensures this is empty.
     */
    public RacePredictor1L() {
        this.createNewRep();
    }

    @Override
    public final void enterResult(int position) {
        this.rep.add(position);
    }

    @Override
    public final int historySize() {
        return this.rep.size();
    }

    @Override
    public final int removeAny() {
        return this.rep.removeAny();
    }

    /**
     * Returns a new instance of RacePredictor with an empty state.
     *
     * @return a new empty RacePredictor
     * @ensures newInstance is empty
     */
    public final RacePredictor newInstance() {
        try {
            return this.getClass().getConstructor().newInstance();
        } catch (Exception e) {
            throw new AssertionError("Cannot construct new instance");
        }
    }

    /**
     * Clears all stored race results.
     *
     * @updates this
     * @ensures this is empty
     */
    public final void clear() {
        this.createNewRep();
    }

    /**
     * Transfers the state from source to this.
     *
     * @param source
     *            the source RacePredictor
     * @updates this
     * @clears source
     * @requires source is not null and source is not this
     * @ensures this = #source and source is empty
     */
    public final void transferFrom(RacePredictor source) {
        assert source != null : "Violation of: source is not null";
        assert source != this : "Violation of: source is not this";

        RacePredictor1L localSource = (RacePredictor1L) source;

        this.rep = localSource.rep;
        localSource.createNewRep();
    }
}
