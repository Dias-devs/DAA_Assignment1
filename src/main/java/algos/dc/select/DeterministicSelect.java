package algos.dc.select;

import algos.dc.metrics.Metrics;
import algos.dc.util.ArrayUtil;

import java.util.Arrays;

public class DeterministicSelect {

    // Public entry point with Metrics
    public static int selectKth(int[] a, int k, Metrics m) {
        m.resetAll();
        return select(a, 0, a.length - 1, k, m);
    }

    // Convenience wrapper without Metrics
    public static int select(int[] a, int k) {
        return selectKth(a, k, new Metrics());
    }

    // Core selection
    private static int select(int[] a, int lo, int hi, int k, Metrics m) {
        m.enter();
        try {
            while (true) {
                if (lo == hi) return a[lo];

                int pivot = pivotMedianOfMedians(a, lo, hi, m);
                int pivotIndex = partitionAroundPivot(a, lo, hi, pivot, m);

                if (k == pivotIndex) {
                    return a[pivotIndex];
                } else if (k < pivotIndex) {
                    hi = pivotIndex - 1;
                } else {
                    lo = pivotIndex + 1;
                }
            }
        } finally {
            m.exit();
        }
    }
    private static int partitionAroundPivot(int[] a, int lo, int hi, int pivot, Metrics m) {
        int i = lo, j = lo;
        for (; i <= hi; i++) {
            m.comps++;
            if (a[i] < pivot) {
                ArrayUtil.swap(a, i, j, m);
                j++;
            }
        }

        // move equals next to "<"
        int k = j;
        for (i = j; i <= hi; i++) {
            if (a[i] == pivot) {
                ArrayUtil.swap(a, i, k, m);
                k++;
            }
        }

        // return an index in the "== pivot" block
        return j;
    }

    // Median-of-medians pivot selection
    private static int pivotMedianOfMedians(int[] a, int lo, int hi, Metrics m) {
        int n = hi - lo + 1;
        if (n <= 5) {
            Arrays.sort(a, lo, hi + 1);
            m.allocs++;
            return a[lo + n / 2];
        }

        int numMedians = (n + 4) / 5;
        for (int i = 0; i < numMedians; i++) {
            int subLo = lo + i * 5;
            int subHi = Math.min(subLo + 4, hi);
            Arrays.sort(a, subLo, subHi + 1);
            m.allocs++;
            int median = a[subLo + (subHi - subLo) / 2];
            a[lo + i] = median;
            m.swaps++;
        }

        int medianIndex = lo + numMedians / 2;
        return select(a, lo, lo + numMedians - 1, medianIndex, m);
    }
}