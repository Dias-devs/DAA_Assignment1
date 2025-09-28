package algos.dc;

import algos.dc.metrics.Metrics;
import algos.dc.sort.MergeSort;
import algos.dc.sort.QuickSort;
import algos.dc.select.DeterministicSelect;
import algos.dc.geometry.ClosestPair;
import algos.dc.geometry.ClosestPair.Point;

import java.io.FileWriter;
import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class App {
    public static void main(String[] args) throws Exception {
        Metrics m = new Metrics();
        int[] a = {5,3,8,1,2,9,4,7,6,0};

        // ---- MergeSort ----
        int[] b = Arrays.copyOf(a,a.length);
        MergeSort.sort(b, m);
        System.out.println("MergeSort OK: " + Arrays.equals(b, sorted(a)));

        // ---- QuickSort ----
        b = Arrays.copyOf(a,a.length);
        QuickSort.sort(b, m);
        System.out.println("QuickSort OK: " + Arrays.equals(b, sorted(a)));

        // ---- Deterministic Select ----
        int[] c = {9,2,7,6,3,1,8,4,5};
        int k = 4;
        int kth = DeterministicSelect.selectKth(Arrays.copyOf(c,c.length), k, m);
        Arrays.sort(c);
        System.out.println("Select OK: " + (kth == c[k]));

        // ---- Closest Pair of Points ----
        Point[] pts = {
                new Point(0, 0),
                new Point(3, 4),
                new Point(7, 1),
                new Point(2, 2),
                new Point(5, 5)
        };
        double d = ClosestPair.closest(pts);
        System.out.println("ClosestPair distance = " + d);

        // ---- Example CSV export ----
        try (FileWriter fw = new FileWriter("metrics_example.csv")) {
            fw.write("algo,n,nanos,comps,swaps,allocs,maxDepth\n");
            for (int n = 1000; n <= 5000; n += 1000) {
                int[] arr = randomArray(n);
                Metrics mm = new Metrics();
                long t0 = System.nanoTime();
                MergeSort.sort(arr, mm);
                long t1 = System.nanoTime();
                fw.write(mm.toCSV(n, t1 - t0, "mergesort") + "\n");
            }
        }
    }

    private static int[] randomArray(int n) {
        int[] arr = new int[n];
        Random r = ThreadLocalRandom.current();
        for (int i = 0; i < n; i++) arr[i] = r.nextInt();
        return arr;
    }

    private static int[] sorted(int[] a) {
        int[] b = Arrays.copyOf(a,a.length);
        Arrays.sort(b);
        return b;
    }
}