package algos.dc.geometry;

import algos.dc.geometry.ClosestPair.Point;
import org.junit.jupiter.api.Test;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import static org.junit.jupiter.api.Assertions.*;

class ClosestPairTest {

    @Test
    void testSimplePoints() {
        Point[] pts = {
                new Point(0, 0),
                new Point(3, 4),
                new Point(7, 1),
                new Point(2, 2),
                new Point(5, 5)
        };
        double d = ClosestPair.closest(pts);
        assertEquals(Math.sqrt(2), d, 1e-9);
    }

    @Test
    void testRandomAgainstBruteForce() {
        Random r = ThreadLocalRandom.current();
        for (int n = 2; n <= 200; n += 20) {
            Point[] pts = new Point[n];
            for (int i = 0; i < n; i++) {
                pts[i] = new Point(r.nextInt(1000), r.nextInt(1000));
            }
            double fast = ClosestPair.closest(pts);
            double brute = bruteForce(pts);
            assertEquals(brute, fast, 1e-9);
        }
    }

    private static double bruteForce(Point[] pts) {
        double best = Double.POSITIVE_INFINITY;
        for (int i = 0; i < pts.length; i++) {
            for (int j = i + 1; j < pts.length; j++) {
                best = Math.min(best, ClosestPair.dist(pts[i], pts[j]));
            }
        }
        return best;
    }

    @Test
    void testSmallRandom() {
        Random r = new Random(45);
        for (int n = 2; n <= 200; n += 37) {
            Point[] pts = new Point[n];
            for (int i = 0; i < n; i++) {
                pts[i] = new Point(r.nextInt(1000), r.nextInt(1000));
            }
            double fast = ClosestPair.closest(pts);
            double brute = bruteForce(pts);
            assertEquals(brute, fast, 1e-9);
        }
    }
}
