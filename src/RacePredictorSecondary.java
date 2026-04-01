public abstract class RacePredictorSecondary implements RacePredictor {

    private static final double PODIUM_THRESHOLD = 3.0;
    private static final double HIGH_PROBABILITY = 0.85;
    private static final double LOW_PROBABILITY = 0.25;

    @Override
    public final double predictPodium() {
        if (this.historySize() == 0) {
            return 0.0;
        }

        int size = this.historySize();
        double sum = 0;

        for (int i = 0; i < size; i++) {
            assert this.historySize() > 0 : "Violation: history empty";

            int pos = this.removeAny();
            sum += pos;
            this.enterResult(pos); // restore
        }

        double avg = sum / size;

        if (avg <= PODIUM_THRESHOLD) {
            return HIGH_PROBABILITY;
        } else {
            return LOW_PROBABILITY;
        }
    }

    @Override
    public String toString() {
        String result = "";
        int size = this.historySize();

        for (int i = 0; i < size; i++) {
            int pos = this.removeAny();
            result += pos + " ";
            this.enterResult(pos);
        }

        return result.trim();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof RacePredictor)) {
            return false;
        }

        RacePredictor other = (RacePredictor) obj;

        if (this.historySize() != other.historySize()) {
            return false;
        }

        int size = this.historySize();

        for (int i = 0; i < size; i++) {
            int a = this.removeAny();
            int b = other.removeAny();

            if (a != b) {
                this.enterResult(a);
                other.enterResult(b);
                return false;
            }

            this.enterResult(a);
            other.enterResult(b);
        }

        return true;
    }
}