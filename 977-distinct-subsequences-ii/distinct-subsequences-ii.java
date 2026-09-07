class Solution {
    public int distinctSubseqII(String s) {

        final long MOD = 1_000_000_007L;

        long[] last = new long[26];
        long dp = 1;

        for (char ch : s.toCharArray()) {

            int idx = ch - 'a';

            long newDp = (2 * dp - last[idx] + MOD) % MOD;

            last[idx] = dp;
            dp = newDp;
        }

        return (int) ((dp - 1 + MOD) % MOD);
    }
}