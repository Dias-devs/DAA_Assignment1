package algos.dc.geometry;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public class ClosestPair {

    /**
     * Simple immutable 2D point
     */
        public record Point(int x, int y) {
    }

    /** Public entrypoint: O(n log n) */
    public static double closest(Point[] pts) {
        Point[] ptsByX = Arrays.copyOf(pts, pts.length);
        Point[] ptsByY = Arrays.copyOf(pts, pts.length);

        Arrays.sort(ptsByX, Comparator.comparingInt(p -> p.x));
        Arrays.sort(ptsByY, Comparator.comparingInt(p -> p.y));

        return closest(ptsByX, ptsByY, 0, pts.length);
    }

    /** Recursive solver on [lo, hi) */
    private static double closest(Point[] ptsByX, Point[] ptsByY, int lo, int hi) {
        int n = hi - lo;
        if (n <= 3) {
            return bruteForce(ptsByX, lo, hi);
        }

        int mid = lo + n / 2;
        int midX = ptsByX[mid].x;

        // Split ptsByY into left and right halves
        ArrayList<Point> leftY = new ArrayList<>();
        ArrayList<Point> rightY = new ArrayList<>();
        for (Point p : ptsByY) {
            if (p.x < midX || (p.x == midX && belongsLeft(p, ptsByX, lo, mid))) {
                leftY.add(p);
            } else {
                rightY.add(p);
            }
        }

        double dl = closest(ptsByX, leftY.toArray(new Point[0]), lo, mid);
        double dr = closest(ptsByX, rightY.toArray(new Point[0]), mid, hi);
        double d = Math.min(dl, dr);

        // Build strip of candidates
        ArrayList<Point> strip = new ArrayList<>();
        for (Point p : ptsByY) {
            if (Math.abs(p.x - midX) < d) strip.add(p);
        }

        // Check at most 7 following neighbors in y-order
        for (int i = 0; i < strip.size(); i++) {
            for (int j = i + 1; j < strip.size() &&
                    (strip.get(j).y - strip.get(i).y) < d; j++) {
                d = Math.min(d, dist(strip.get(i), strip.get(j)));
            }
        }

        return d;
    }

    /** Decide tie-breaking if point lies exactly on mid-line */
    private static boolean belongsLeft(Point p, Point[] ptsByX, int lo, int mid) {
        for (int i = lo; i < mid; i++) {
            if (ptsByX[i] == p) return true;
        }
        return false;
    }

    /** Brute force for small n */
    private static double bruteForce(Point[] pts, int lo, int hi) {
        double d = Double.POSITIVE_INFINITY;
        for (int i = lo; i < hi; i++) {
            for (int j = i + 1; j < hi; j++) {
                d = Math.min(d, dist(pts[i], pts[j]));
            }
        }
        return d;
    }

    /** Euclidean distance */
    public static double dist(Point a, Point b) {
        long dx = (long) a.x - b.x;
        long dy = (long) a.y - b.y;
        return Math.sqrt(dx * dx + dy * dy);
    }
}