package algos.dc.sort;

import algos.dc.metrics.Metrics;

public class MergeSort {
    private static final int INSERTION_CUTOFF = 16;

    public static void sort(int[] a, Metrics m) {
        m.resetAll();
        int[] buf = new int[a.length];
        m.allocs++;
        mergesort(a, 0, a.length, buf, m);
    }

    private static void insertionSort(int[] a, int lo, int hi, Metrics m) {
        for (int i = lo + 1; i < hi; ++i) {
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

    private static void mergesort(int[] a, int lo, int hi, int[] buf, Metrics m) {
        m.enter();
        try {
            int n = hi - lo;
            if (n <= INSERTION_CUTOFF) {
                insertionSort(a, lo, hi, m);
                return;
            }
            int mid = lo + (n >> 1);
            mergesort(a, lo, mid, buf, m);
            mergesort(a, mid, hi, buf, m);
            int i = lo, j = mid, k = lo;
            while (i < mid || j < hi) {
                if (i < mid && j < hi) {
                    m.comps++;
                    if (a[i] <= a[j]) buf[k++] = a[i++];
                    else buf[k++] = a[j++];
                    m.swaps++;
                } else if (i < mid) buf[k++] = a[i++];
                else buf[k++] = a[j++];
                m.swaps++;
            }
            for (k = lo; k < hi; ++k) { a[k] = buf[k]; m.swaps++; }
        } finally { m.exit(); }
    }
}