package algos.dc.util;

import algos.dc.metrics.Metrics;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class ArrayUtil {
    public static void swap(int[] a, int i, int j, Metrics m) {
        if (i == j) return;
        int t = a[i]; a[i] = a[j]; a[j] = t;
        if (m != null) m.swaps++;
    }

    public static void shuffle(int[] a, Metrics m) {
        Random r = ThreadLocalRandom.current();
        for (int i = a.length - 1; i > 0; --i) {
            int j = r.nextInt(i + 1);
            swap(a, i, j, m);
        }
    }
}