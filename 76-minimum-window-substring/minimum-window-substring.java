class Solution {
    public String minWindow(String s, String t) {
        int[] need = new int[128];

        for (char ch : t.toCharArray()) {
            need[ch]++;
        }

        int left = 0;
        int right = 0;

        int required = t.length();

        int minLength = Integer.MAX_VALUE;
        int start = 0;

        while (right < s.length()) {

            char ch = s.charAt(right);

            if (need[ch] > 0) {
                required--;
            }

            need[ch]--;

            right++;

            while (required == 0) {

                if (right - left < minLength) {
                    minLength = right - left;
                    start = left;
                }

                char leftChar = s.charAt(left);

                need[leftChar]++;

                if (need[leftChar] > 0) {
                    required++;
                }

                left++;
            }
        }

        if (minLength == Integer.MAX_VALUE) {
            return "";
        }

        return s.substring(start, start + minLength);
    }
}