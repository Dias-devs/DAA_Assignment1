package algos.dc.sort;

import algos.dc.metrics.Metrics;
import algos.dc.util.ArrayUtil;
import java.util.concurrent.ThreadLocalRandom;

public class QuickSort {
    private static final int INSERTION_CUTOFF = 16;

    public static void sort(int[] a, Metrics m) {
        m.resetAll();
        ArrayUtil.shuffle(a, m);
        quicksort(a, 0, a.length - 1, m);
    }

    private static void insertionSort(int[] a, int lo, int hi, Metrics m) {
        for (int i = lo + 1; i <= hi; ++i) {
            int key = a[i];
            int j = i - 1;
            while (j >= lo) {
                m.comps++;
                if (a[j] <= key) break;
                a[j + 1] = a[j]; m.swaps++;
                j--;
            }
            a[j + 1] = key; m.swaps++;
        }
    }

    private static void quicksort(int[] a, int lo, int hi, Metrics m) {
        while (lo < hi) {
            int n = hi - lo + 1;
            if (n <= INSERTION_CUTOFF) { insertionSort(a, lo, hi, m); return; }
            m.enter();
            try {
                int pivotIndex = ThreadLocalRandom.current().nextInt(lo, hi + 1);
                int pivot = a[pivotIndex];
                ArrayUtil.swap(a, pivotIndex, hi, m);
                int store = lo;
                for (int i = lo; i < hi; ++i) {
                    m.comps++;
                    if (a[i] < pivot) { ArrayUtil.swap(a, i, store, m); store++; }
                }
                ArrayUtil.swap(a, store, hi, m);
                int leftSize = store - lo;
                int rightSize = hi - store;
                if (leftSize < rightSize) {
                    quicksort(a, lo, store - 1, m);
                    lo = store + 1;
                } else {
                    quicksort(a, store + 1, hi, m);
                    hi = store - 1;
                }
            } finally { m.exit(); }
        }
    }
}