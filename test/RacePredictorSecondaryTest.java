import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.Test;

/**
 * JUnit tests for the secondary methods of {@link RacePredictor}.
 */
public final class RacePredictorSecondaryTest {

    /**
     * Tolerance for floating-point comparisons.
     */
    private static final double DELTA = 0.000001;

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

    /**
     * Asserts that the string contains exactly the expected values, regardless
     * of order.
     *
     * @param actual
     *            string returned by toString
     * @param expected
     *            expected stored positions
     */
    private static void assertStringContainsValues(String actual,
            int... expected) {
        List<Integer> actualValues = new ArrayList<>();
        if (!actual.isBlank()) {
            String[] tokens = actual.split("\\s+");
            for (String token : tokens) {
                actualValues.add(Integer.parseInt(token));
            }
        }

        List<Integer> expectedValues = new ArrayList<>();
        for (int value : expected) {
            expectedValues.add(value);
        }

        Collections.sort(actualValues);
        Collections.sort(expectedValues);
        assertEquals(expectedValues, actualValues);
    }

    @Test
    public void testPredictPodiumEmptyHistoryReturnsZero() {
        RacePredictor1L predictor = new RacePredictor1L();

        assertEquals(0.0, predictor.predictPodium(), DELTA);
        assertEquals(0, predictor.historySize());
    }

    @Test
    public void testPredictPodiumAllPodiumFinishesStateRestored() {
        RacePredictor1L predictor = createPredictor(1, 2, 3);

        double probability = predictor.predictPodium();

        assertEquals(1.0, probability, DELTA);
        assertStateEquals(predictor, 1, 2, 3);
    }

    @Test
    public void testPredictPodiumMixedResultsStateRestored() {
        RacePredictor1L predictor = createPredictor(1, 4, 3, 8);

        double probability = predictor.predictPodium();

        assertEquals(0.5, probability, DELTA);
        assertStateEquals(predictor, 1, 4, 3, 8);
    }

    @Test
    public void testPredictPodiumNoPodiumFinishesReturnsZero() {
        RacePredictor1L predictor = createPredictor(4, 6, 8);

        double probability = predictor.predictPodium();

        assertEquals(0.0, probability, DELTA);
        assertStateEquals(predictor, 4, 6, 8);
    }

    @Test
    public void testToStringListsStoredResultsWithoutChangingState() {
        RacePredictor1L predictor = createPredictor(2, 4, 6);

        String text = predictor.toString();

        assertStringContainsValues(text, 2, 4, 6);
        assertStateEquals(predictor, 2, 4, 6);
    }

    @Test
    public void testToStringEmptyPredictorReturnsEmptyString() {
        RacePredictor1L predictor = new RacePredictor1L();

        String text = predictor.toString();

        assertEquals("", text);
        assertStateEquals(predictor);
    }

    @Test
    public void testEqualsSameObjectIsTrue() {
        RacePredictor1L predictor = createPredictor(1, 3, 5);

        assertTrue(predictor.equals(predictor));
        assertStateEquals(predictor, 1, 3, 5);
    }

    @Test
    public void testEqualsNullAndOtherTypeAreFalse() {
        RacePredictor1L predictor = createPredictor(1, 3, 5);

        assertFalse(predictor.equals(null));
        assertFalse(predictor.equals("not a predictor"));
        assertStateEquals(predictor, 1, 3, 5);
    }

    @Test
    public void testEqualsSameContentsDifferentInstancesIsTrue() {
        RacePredictor1L first = createPredictor(1, 2, 5);
        RacePredictor1L second = createPredictor(1, 2, 5);

        assertTrue(first.equals(second));
        assertStateEquals(first, 1, 2, 5);
        assertStateEquals(second, 1, 2, 5);
    }

    @Test
    public void testEqualsDifferentContentsIsFalse() {
        RacePredictor1L first = createPredictor(1, 2, 5);
        RacePredictor1L second = createPredictor(1, 3, 5);

        assertFalse(first.equals(second));
        assertStateEquals(first, 1, 2, 5);
        assertStateEquals(second, 1, 3, 5);
    }

    @Test
    public void testEqualsDifferentSizesIsFalse() {
        RacePredictor1L first = createPredictor(1, 2);
        RacePredictor1L second = createPredictor(1, 2, 3);

        assertFalse(first.equals(second));
        assertStateEquals(first, 1, 2);
        assertStateEquals(second, 1, 2, 3);
    }
}
