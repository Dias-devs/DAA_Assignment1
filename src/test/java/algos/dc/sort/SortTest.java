package algos.dc.sort;

import algos.dc.metrics.Metrics;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class SortTest {

    private int[] sorted(int[] a) {
        int[] b = Arrays.copyOf(a, a.length);
        Arrays.sort(b);
        return b;
    }

    @Test
    void testMergeSortRandom() {
        Random r = new Random(42);
        for (int n = 1; n <= 1000; n += 113) {
            int[] a = r.ints(n, -1000, 1000).toArray();
            int[] b = Arrays.copyOf(a, a.length);
            MergeSort.sort(b, new Metrics());
            assertArrayEquals(sorted(a), b);
        }
    }

    @Test
    void testQuickSortRandom() {
        Random r = new Random(43);
        for (int n = 1; n <= 1000; n += 113) {
            int[] a = r.ints(n, -1000, 1000).toArray();
            int[] b = Arrays.copyOf(a, a.length);
            Metrics m = new Metrics();
            QuickSort.sort(b, m);
            assertArrayEquals(sorted(a), b);
            // recursion depth should be ≲ 2*log2(n) + O(1)
            int expected = (int) (2 * (Math.log(Math.max(1, n)) / Math.log(2)) + 10);
            assertTrue(m.maxDepth <= expected, "QuickSort depth too large");
        }
    }

    @Test
    void testAdversarialInput() {
        int[] a = new int[200];
        Arrays.fill(a, 7); // duplicates
        int[] b = Arrays.copyOf(a, a.length);
        QuickSort.sort(b, new Metrics());
        assertArrayEquals(sorted(a), b);

        int[] c = new int[200];
        for (int i = 0; i < 200; i++) c[i] = i; // sorted
        int[] d = Arrays.copyOf(c, c.length);
        QuickSort.sort(d, new Metrics());
        assertArrayEquals(sorted(c), d);
    }
}