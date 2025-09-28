package algos.dc.bench;

import algos.dc.metrics.Metrics;
import algos.dc.select.DeterministicSelect;
import org.openjdk.jmh.annotations.*;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

/**
 * Benchmark harness: DeterministicSelect vs Arrays.sort
 */
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@State(Scope.Thread)
public class SelectBench {

    @Param({"1000", "10000", "100000"})
    private int n;

    private int[] arr;
    private int k;

    @Setup(Level.Iteration)
    public void setup() {
        arr = randomArray(n);
        k = ThreadLocalRandom.current().nextInt(n);
    }

    @Benchmark
    public int deterministicSelect() {
        int[] copy = Arrays.copyOf(arr, arr.length);
        Metrics m = new Metrics();
        return DeterministicSelect.selectKth(copy, k, m);
    }

    @Benchmark
    public int arraysSort() {
        int[] copy = Arrays.copyOf(arr, arr.length);
        Arrays.sort(copy);
        return copy[k];
    }

    private static int[] randomArray(int n) {
        int[] arr = new int[n];
        Random r = ThreadLocalRandom.current();
        for (int i = 0; i < n; i++) arr[i] = r.nextInt();
        return arr;
    }
}