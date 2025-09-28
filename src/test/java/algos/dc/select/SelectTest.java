package algos.dc.select;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SelectTest {

    @Test
    void testDeterministicSelect() {
        Random r = new Random(44);
        for (int trial = 0; trial < 100; trial++) {
            int n = 50 + r.nextInt(200);
            int[] a = r.ints(n, -500, 500).toArray();
            int k = r.nextInt(n);

            int kth = DeterministicSelect.select(Arrays.copyOf(a, a.length), k);

            Arrays.sort(a);
            assertEquals(a[k], kth, "Mismatch at trial=" + trial + " k=" + k);
        }
    }
}
