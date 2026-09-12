import java.util.*;

class Solution {

    static class Interval {
        int l, r, w, idx;

        Interval(int l, int r, int w, int idx) {
            this.l = l;
            this.r = r;
            this.w = w;
            this.idx = idx;
        }
    }

    static class State {
        long score;
        int[] ids;

        State(long score, int[] ids) {
            this.score = score;
            this.ids = ids;
        }
    }

    Interval[] arr;
    State[][] dp;

    public int[] maximumWeight(List<List<Integer>> intervals) {

        int n = intervals.size();

        arr = new Interval[n];

        for (int i = 0; i < n; i++) {
            arr[i] = new Interval(
                intervals.get(i).get(0),
                intervals.get(i).get(1),
                intervals.get(i).get(2),
                i
            );
        }

        // Sort by left endpoint
        Arrays.sort(arr, (a, b) -> {
            if (a.l != b.l)
                return Integer.compare(a.l, b.l);

            return Integer.compare(a.r, b.r);
        });

        dp = new State[n + 1][5];

        return solve(0, 4).ids;
    }

    private State solve(int pos, int remaining) {

        if (pos == arr.length || remaining == 0) {
            return new State(0, new int[0]);
        }

        if (dp[pos][remaining] != null) {
            return dp[pos][remaining];
        }

        // Option 1: Skip current interval
        State skip = solve(pos + 1, remaining);

        // Option 2: Take current interval
        int next = findNext(pos);

        State rest = solve(next, remaining - 1);

        int[] ids = new int[rest.ids.length + 1];

        ids[0] = arr[pos].idx;

        System.arraycopy(
            rest.ids,
            0,
            ids,
            1,
            rest.ids.length
        );

        // Sort indices because answer must be lexicographically ordered
        Arrays.sort(ids);

        State take = new State(
            arr[pos].w + rest.score,
            ids
        );

        State best;

        if (take.score > skip.score) {
            best = take;
        } 
        else if (take.score < skip.score) {
            best = skip;
        } 
        else {
            best = smaller(take, skip);
        }

        dp[pos][remaining] = best;

        return best;
    }

    // Find first interval whose left > current right
    private int findNext(int pos) {

        int low = pos + 1;
        int high = arr.length;

        while (low < high) {

            int mid = low + (high - low) / 2;

            if (arr[mid].l > arr[pos].r) {
                high = mid;
            } 
            else {
                low = mid + 1;
            }
        }

        return low;
    }

    // Lexicographically smaller array
    private State smaller(State a, State b) {

        int len = Math.min(a.ids.length, b.ids.length);

        for (int i = 0; i < len; i++) {

            if (a.ids[i] < b.ids[i]) {
                return a;
            }

            if (a.ids[i] > b.ids[i]) {
                return b;
            }
        }

        return a.ids.length <= b.ids.length ? a : b;
    }
}