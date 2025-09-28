package algos.dc.metrics;

public class Metrics {
    public long comps = 0;
    public long swaps = 0;
    public long allocs = 0;
    public int depth = 0;
    public int maxDepth = 0;

    public void enter() {
        depth++;
        if (depth > maxDepth) maxDepth = depth;
    }
    public void exit() { depth--; }
    public void resetDepth() { depth = 0; maxDepth = 0; }
    public void resetAll() { comps = swaps = allocs = 0; resetDepth(); }

    public String toCSV(long n, long nanos, String algo) {
        return String.join(",",
                algo,
                Long.toString(n),
                Long.toString(nanos),
                Long.toString(comps),
                Long.toString(swaps),
                Long.toString(allocs),
                Integer.toString(maxDepth)
        );
    }
}