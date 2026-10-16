
class Solution {
    private static final int MOD = 1_000_000_007;

    public int sumCounts(int[] nums) {
        int n = nums.length;

        SegmentTree tree = new SegmentTree(n);
        int[] last = new int[100001];

        java.util.Arrays.fill(last, -1);

        long answer = 0;

        for (int i = 0; i < n; i++) {
            int value = nums[i];
            int prev = last[value];

            tree.update(prev + 1, i, 1);
            answer = (answer + tree.sumSq[1]) % MOD;

            last[value] = i;
        }

        return (int) answer;
    }

    static class SegmentTree {
        long[] sum;
        long[] sumSq;
        long[] lazy;
        int n;

        SegmentTree(int n) {
            this.n = n;
            sum = new long[4 * n];
            sumSq = new long[4 * n];
            lazy = new long[4 * n];
        }

        void apply(int node, int start, int end, long value) {
            long len = end - start + 1;

            sumSq[node] += 2 * value * sum[node]
                         + value * value * len;

            sum[node] += value * len;
            lazy[node] += value;
        }

        void pushDown(int node, int start, int end) {
            if (lazy[node] == 0 || start == end) {
                return;
            }

            int mid = start + (end - start) / 2;

            apply(node * 2, start, mid, lazy[node]);
            apply(node * 2 + 1, mid + 1, end, lazy[node]);

            lazy[node] = 0;
        }

        void update(int left, int right, int value) {
            if (left > right) {
                return;
            }

            update(1, 0, n - 1, left, right, value);
        }

        void update(int node, int start, int end,
                    int left, int right, long value) {

            if (left <= start && end <= right) {
                apply(node, start, end, value);
                return;
            }

            pushDown(node, start, end);

            int mid = start + (end - start) / 2;

            if (left <= mid) {
                update(node * 2, start, mid,
                       left, right, value);
            }

            if (right > mid) {
                update(node * 2 + 1, mid + 1, end,
                       left, right, value);
            }

            sum[node] = sum[node * 2] + sum[node * 2 + 1];

            sumSq[node] = sumSq[node * 2]
                        + sumSq[node * 2 + 1];
        }
    }
}
