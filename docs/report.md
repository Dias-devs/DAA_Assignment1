# Divide and Conquer Algorithms Report

## 1. Architecture Notes
- Recursion depth is tracked using the `Metrics` class.
- Memory allocations are minimized for merge buffers (reusable buffer).
- Small-n cutoffs use insertion sort for efficiency.

## 2. Recurrence Analysis

### MergeSort
- **Recurrence:** T(n) = 2T(n/2) + Θ(n)
- **Master Theorem:** Case 2 → T(n) = Θ(n log n)
- **Observations:** Linear merge dominates, reusable buffer reduces allocations.

### QuickSort
- **Recurrence:** T(n) = T(k) + T(n-k-1) + Θ(n)
- **Randomized pivot:** expected O(n log n)
- **Smallest-first recursion:** limits stack depth (~2*log₂ n)

### Deterministic Select
- **Recurrence:** T(n) = T(n/5) + T(7n/10) + Θ(n)
- **Median-of-medians:** O(n)
- **Recurse only into the needed side:** reduces comparisons.

### Closest Pair of Points
- **Recurrence:** T(n) = 2T(n/2) + Θ(n)
- **Strip scan:** O(n) by checking only 7 neighbors in y-order.
- **Total:** O(n log n)

## 3. Plots
- `docs/images/time_vs_n.png` — running time vs input size
- `docs/images/depth_vs_n.png` — recursion depth vs input size

## 4. Discussion
- Measured time matches theoretical bounds.
- Cache effects and small array cutoffs slightly improve practical constants.
- Randomized QuickSort shows lower maximum depth than worst-case bound.

## 5. Summary
- Theory aligns well with measurements.
- Metrics collection (time, comparisons, recursion depth) confirms expected behavior.
