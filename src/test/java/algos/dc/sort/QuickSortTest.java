package algos.dc.sort;

import algos.dc.metrics.Metrics;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

import static org.junit.jupiter.api.Assertions.*;

class QuickSortTest {

    @Test
    void testSmallArraySortedCorrectly() {
        int[] arr = {3, 7, 2, 1, 9, 5};
        int[] copy = Arrays.copyOf(arr, arr.length);
        Arrays.sort(copy);

        Metrics m = new Metrics();
        QuickSort.sort(arr, m);
        assertArrayEquals(copy, arr);
    }

    @Test
    void testRecursionDepthBounded() {
        int n = 1_000;
        int[] arr = ThreadLocalRandom.current().ints(n).toArray();

        Metrics m = new Metrics();
        QuickSort.sort(arr, m);

        int log2n = (int) (Math.log(n) / Math.log(2));
        assertTrue(m.maxDepth <= 2 * log2n + 10,
                "Recursion depth too large: " + m.maxDepth);
    }
}
