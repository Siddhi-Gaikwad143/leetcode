class Solution {
    public long countCommas(long n) {

        long ans = 0;

        if (n >= 1000) {
            long end = Math.min(n, 999999L);
            ans += end - 1000 + 1;
        }

        if (n >= 1000000) {
            long end = Math.min(n, 999999999L);
            ans += (end - 1000000 + 1) * 2;
        }

       
        if (n >= 1000000000L) {
            long end = Math.min(n, 999999999999L);
            ans += (end - 1000000000L + 1) * 3;
        }

        
        if (n >= 1000000000000L) {
            long end = Math.min(n, 999999999999999L);
            ans += (end - 1000000000000L + 1) * 4;
        }

        
        if (n >= 1000000000000000L) {
            ans += (n - 1000000000000000L + 1) * 5;
        }

        return ans;
    }
}