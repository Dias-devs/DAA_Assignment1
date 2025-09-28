package algos.dc;

import algos.dc.metrics.Metrics;
import algos.dc.sort.MergeSort;
import algos.dc.sort.QuickSort;
import algos.dc.select.DeterministicSelect;
import algos.dc.geometry.ClosestPair;
import algos.dc.geometry.ClosestPair.Point;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EdgeCaseTest {

    @Test
    void testMergeSortEdgeCases() {
        Metrics m = new Metrics();

        int[] a = {};
        MergeSort.sort(a, m);
        assertArrayEquals(new int[]{}, a);

        a = new int[]{42};
        MergeSort.sort(a, m);
        assertArrayEquals(new int[]{42}, a);

        a = new int[]{1,2,3};
        MergeSort.sort(a, m);
        assertArrayEquals(new int[]{1,2,3}, a);

        a = new int[]{3,2,1};
        MergeSort.sort(a, m);
        assertArrayEquals(new int[]{1,2,3}, a);

        a = new int[]{5,5,5,5};
        MergeSort.sort(a, m);
        assertArrayEquals(new int[]{5,5,5,5}, a);
    }

    @Test
    void testQuickSortEdgeCases() {
        Metrics m = new Metrics();
        int[] a = {};
        QuickSort.sort(a, m);
        assertArrayEquals(new int[]{}, a);

        a = new int[]{7};
        QuickSort.sort(a, m);
        assertArrayEquals(new int[]{7}, a);

        a = new int[]{9,9,9};
        QuickSort.sort(a, m);
        assertArrayEquals(new int[]{9,9,9}, a);
    }

    @Test
    void testDeterministicSelectEdgeCases() {
        Metrics m = new Metrics();

        assertThrows(IllegalArgumentException.class,
                () -> DeterministicSelect.selectKth(new int[]{}, 0, m));

        assertEquals(5, DeterministicSelect.selectKth(new int[]{5}, 0, m));

        int[] a = {7,7,7,7};
        assertEquals(7, DeterministicSelect.selectKth(a, 2, m));
    }

    @Test
    void testClosestPairEdgeCases() {
        Point[] pts;

        assertThrows(IllegalArgumentException.class,
                () -> ClosestPair.closest(new Point[]{}));

        pts = new Point[]{ new Point(0,0), new Point(3,4) };
        assertEquals(5.0, ClosestPair.closest(pts), 1e-9);

        pts = new Point[]{ new Point(2,2), new Point(2,2), new Point(2,2) };
        assertEquals(0.0, ClosestPair.closest(pts), 1e-9);
    }
}