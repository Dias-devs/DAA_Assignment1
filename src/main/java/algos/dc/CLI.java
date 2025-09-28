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

public class CLI {

    public static void main(String[] args) throws Exception {
        String algo = null;
        int n = 1000;
        String out = "metrics.csv";
        int k = -1;

        // --- Simple argument parsing ---
        for (int i = 0; i < args.length; i++) {
            switch (args[i]) {
                case "--algo": algo = args[++i]; break;
                case "--n": n = Integer.parseInt(args[++i]); break;
                case "--out": out = args[++i]; break;
                case "--k": k = Integer.parseInt(args[++i]); break;
                default:
                    System.err.println("Unknown arg: " + args[i]);
                    return;
            }
        }

        if (algo == null) {
            System.err.println("Usage: --algo mergesort|quicksort|select|closest --n N [--k K] --out file.csv");
            return;
        }

        // --- Run algorithm ---
        Metrics m = new Metrics();
        long t0 = System.nanoTime();

        switch (algo) {
            case "mergesort":
                int[] arr = randomArray(n);
                MergeSort.sort(arr, m);
                break;

            case "quicksort":
                arr = randomArray(n);
                QuickSort.sort(arr, m);
                break;

            case "select":
                if (k < 0 || k >= n) {
                    System.err.println("For select, must provide --k between 0 and n-1");
                    return;
                }
                arr = randomArray(n);
                int kth = DeterministicSelect.selectKth(arr, k, m);
                Arrays.sort(arr);
                assert kth == arr[k] : "Select mismatch!";
                break;

            case "closest":
                Point[] pts = randomPoints(n);
                double d = ClosestPair.closest(pts);
                if (n <= 2000) {
                    double brute = bruteForce(pts);
                    assert Math.abs(d - brute) < 1e-9 : "Closest mismatch!";
                }
                break;

            default:
                System.err.println("Unknown algorithm: " + algo);
                return;
        }

        long t1 = System.nanoTime();

        // --- Write metrics ---
        try (FileWriter fw = new FileWriter(out, true)) {
            fw.write(m.toCSV(n, t1 - t0, algo) + "\n");
        }
    }

    private static int[] randomArray(int n) {
        int[] arr = new int[n];
        Random r = ThreadLocalRandom.current();
        for (int i = 0; i < n; i++) arr[i] = r.nextInt();
        return arr;
    }

    private static Point[] randomPoints(int n) {
        Random r = ThreadLocalRandom.current();
        Point[] pts = new Point[n];
        for (int i = 0; i < n; i++) {
            pts[i] = new Point(r.nextInt(1_000_000), r.nextInt(1_000_000));
        }
        return pts;
    }

    private static double bruteForce(Point[] pts) {
        double d = Double.POSITIVE_INFINITY;
        for (int i = 0; i < pts.length; i++) {
            for (int j = i + 1; j < pts.length; j++) {
                long dx = (long) pts[i].x() - pts[j].x();
                long dy = (long) pts[i].y() - pts[j].y();
                d = Math.min(d, Math.sqrt(dx * dx + dy * dy));
            }
        }
        return d;
    }
}