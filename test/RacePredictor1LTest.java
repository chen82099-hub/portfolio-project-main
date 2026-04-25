import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.Test;

/**
 * JUnit tests for the {@link RacePredictor1L} kernel implementation and
 * Standard methods.
 */
public final class RacePredictor1LTest {

    /**
     * Creates a predictor populated with the given positions.
     *
     * @param positions
     *            race positions to add
     * @return a predictor containing the positions
     */
    private static RacePredictor1L createPredictor(int... positions) {
        RacePredictor1L predictor = new RacePredictor1L();
        for (int position : positions) {
            predictor.enterResult(position);
        }
        return predictor;
    }

    /**
     * Returns a snapshot of the predictor state without changing its contents.
     *
     * @param predictor
     *            predictor under test
     * @return all stored positions
     */
    private static List<Integer> snapshot(RacePredictor predictor) {
        List<Integer> values = new ArrayList<>();
        RacePredictor temp = predictor.newInstance();

        while (predictor.historySize() > 0) {
            int value = predictor.removeAny();
            values.add(value);
            temp.enterResult(value);
        }

        predictor.transferFrom(temp);
        return values;
    }

    /**
     * Asserts that a predictor contains exactly the expected positions.
     *
     * @param predictor
     *            predictor under test
     * @param expected
     *            expected positions
     */
    private static void assertStateEquals(RacePredictor predictor,
            int... expected) {
        List<Integer> actualValues = snapshot(predictor);
        List<Integer> expectedValues = new ArrayList<>();

        for (int value : expected) {
            expectedValues.add(value);
        }

        Collections.sort(actualValues);
        Collections.sort(expectedValues);

        assertEquals(expectedValues.size(), predictor.historySize());
        assertEquals(expectedValues, actualValues);
    }

    @Test
    public void testConstructorCreatesEmptyPredictor() {
        RacePredictor1L predictor = new RacePredictor1L();

        assertEquals(0, predictor.historySize());
    }

    @Test
    public void testEnterResultAddsValue() {
        RacePredictor1L predictor = new RacePredictor1L();

        predictor.enterResult(4);

        assertStateEquals(predictor, 4);
    }

    @Test
    public void testHistorySizeReportsStoredResults() {
        RacePredictor1L predictor = createPredictor(2, 5, 8);

        assertEquals(3, predictor.historySize());
        assertStateEquals(predictor, 2, 5, 8);
    }

    @Test
    public void testRemoveAnyRemovesOneStoredValue() {
        RacePredictor1L predictor = createPredictor(1, 3, 6);

        int removed = predictor.removeAny();

        assertTrue(removed == 1 || removed == 3 || removed == 6);
        assertEquals(2, predictor.historySize());
    }

    @Test
    public void testNewInstanceReturnsEmptySameDynamicType() {
        RacePredictor1L predictor = createPredictor(1, 3, 6);

        RacePredictor fresh = predictor.newInstance();

        assertTrue(fresh instanceof RacePredictor1L);
        assertEquals(0, fresh.historySize());
        assertStateEquals(predictor, 1, 3, 6);
    }

    @Test
    public void testClearEmptiesPredictor() {
        RacePredictor1L predictor = createPredictor(2, 4, 6);

        predictor.clear();

        assertStateEquals(predictor);
    }

    @Test
    public void testTransferFromMovesStateAndClearsSource() {
        RacePredictor1L source = createPredictor(1, 5, 9);
        RacePredictor1L destination = createPredictor(3, 7);

        destination.transferFrom(source);

        assertStateEquals(destination, 1, 5, 9);
        assertEquals(0, source.historySize());
    }

    @Test
    public void testEnterResultStoresDuplicateValues() {
        RacePredictor1L predictor = new RacePredictor1L();

        predictor.enterResult(2);
        predictor.enterResult(2);

        assertStateEquals(predictor, 2, 2);
    }
}
