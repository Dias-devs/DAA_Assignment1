package algos.dc.select;

import algos.dc.metrics.Metrics;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import static org.junit.jupiter.api.Assertions.*;

class DeterministicSelectTest {

    @Test
    void testSelectMatchesSortedArray() {
        Random r = ThreadLocalRandom.current();
        for (int n = 1; n <= 200; n++) {
            int[] arr = r.ints(n).toArray();
            int[] sorted = Arrays.copyOf(arr, arr.length);
            Arrays.sort(sorted);

            Metrics m = new Metrics();
            for (int k = 0; k < n; k++) {
                int res = DeterministicSelect.selectKth(Arrays.copyOf(arr, arr.length), k, m);
                assertEquals(sorted[k], res);
            }
        }
    }
}
