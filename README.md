# Divide-and-Conquer Algorithms Assignment

## Student Full Name: Yestemes Dias
## Group Name: SE-2421

## Architecture Notes
We implemented four classic divide-and-conquer algorithms in Java:

- **MergeSort**
    - Uses linear merge procedure.
    - Reuses a single buffer array to reduce allocations.
    - Switches to insertion sort for small subproblems (`n < cutoff`).
- **QuickSort**
    - Uses a randomized pivot.
    - Recurses on the smaller partition, iterates over the larger one to bound recursion depth (≈ O(log n)).
- **Deterministic Select (Median-of-Medians)**
    - Groups elements in 5s, computes median-of-medians as pivot.
    - In-place partition.
    - Recurse only into the needed partition, preferring the smaller side.
- **Closest Pair of Points (2D)**
    - Sorts by x-coordinate.
    - Splits recursively, maintains points sorted by y in merge step.
    - Checks only 7–8 neighbors in the strip.

### Depth & Allocation Control
- **MergeSort:** Reuses buffer array; avoids excessive allocations.
- **QuickSort:** Tail recursion eliminated; stack depth ≈ O(log n).
- **Select:** Only recurses into one side.
- **Closest Pair:** Depth O(log n); careful array copying.

---

## Recurrence Analysis

- **MergeSort**
    - Recurrence: T(n) = 2T(n/2) + Θ(n)
    - Master Theorem Case 2 → Θ(n log n).

- **QuickSort**
    - Randomized pivot expected recurrence: T(n) = T(αn) + T((1−α)n) + Θ(n).
    - Expected Θ(n log n), worst case Θ(n²).
    - Recursion bounded to smaller side → depth O(log n).

- **Deterministic Select**
    - Recurrence: T(n) = T(n/5) + T(7n/10) + Θ(n).
    - Solves to Θ(n).

- **Closest Pair**
    - Recurrence: T(n) = 2T(n/2) + Θ(n).
    - Master Case 2 → Θ(n log n).

---

## Plots
*(to be added once benchmarks are run)*

- Time vs n (log-log scale).
- Recursion depth vs n.
- Comparisons and allocations vs n.

---

## Constant-Factor Discussion
- **MergeSort** benefits from cache locality on sequential merges.
- **QuickSort** usually faster in practice due to in-place partitioning, but sensitive to pivot randomness.
- **Select** has higher constants than QuickSort, but guaranteed O(n).
- **Closest Pair** is dominated by sorting + linear scan per level.

---

## Summary
- Theoretical asymptotics align with expected results:
    - MergeSort & QuickSort ≈ O(n log n).
    - Deterministic Select ≈ O(n).
    - Closest Pair ≈ O(n log n).
- Measurements will validate constants and recursion depth.
- Cache effects and allocation patterns will influence real-world performance.

---

## Git Workflow
- Branches:
    - `feature/mergesort`
    - `feature/quicksort`
    - `feature/select`
    - `feature/closest`
    - `feature/metrics`
    - `docs/report`
- Tags:
    - `v0.1` (initial baseline)
    - `v1.0` (final release)

---
