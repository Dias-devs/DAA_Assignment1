package algos.dc.sort;

import algos.dc.metrics.Metrics;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import static org.junit.jupiter.api.Assertions.*;

class MergeSortTest {

    @Test
    void testSmallArraySortedCorrectly() {
        int[] arr = {5, 2, 9, 1, 5, 6};
        int[] copy = Arrays.copyOf(arr, arr.length);
        Arrays.sort(copy);

        Metrics m = new Metrics();
        MergeSort.sort(arr, m);
        assertArrayEquals(copy, arr);
    }

    @Test
    void testRandomArrays() {
        Random r = ThreadLocalRandom.current();
        for (int n = 1; n <= 200; n++) {
            int[] arr = r.ints(n).toArray();
            int[] copy = Arrays.copyOf(arr, arr.length);
            Arrays.sort(copy);

            Metrics m = new Metrics();
            MergeSort.sort(arr, m);
            assertArrayEquals(copy, arr);
        }
    }
}