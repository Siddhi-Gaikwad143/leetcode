class Solution {
    public List<String> removeInvalidParentheses(String s) {
        List<String> ans = new ArrayList<>();

        int left = 0, right = 0;

        for (char c : s.toCharArray()) {
            if (c == '(') {
                left++;
            } else if (c == ')') {
                if (left > 0) {
                    left--;
                } else {
                    right++;
                }
            }
        }

        dfs(s, 0, left, right, 0, new StringBuilder(), ans, false);

        return ans;
    }

    private void dfs(String s, int index,
                     int leftRemove, int rightRemove,
                     int balance, StringBuilder path,
                     List<String> ans, boolean prevRemoved) {

        if (balance < 0) {
            return;
        }

        if (index == s.length()) {
            if (leftRemove == 0 &&
                rightRemove == 0 &&
                balance == 0) {
                ans.add(path.toString());
            }
            return;
        }

        char c = s.charAt(index);

        // REMOVE current character
        if ((c == '(' && leftRemove > 0) ||
            (c == ')' && rightRemove > 0)) {

            // Skip duplicate removal only if previous same
            // character was NOT removed
            if (index == 0 ||
                s.charAt(index - 1) != c ||
                prevRemoved) {

                dfs(s, index + 1,
                    c == '(' ? leftRemove - 1 : leftRemove,
                    c == ')' ? rightRemove - 1 : rightRemove,
                    balance,
                    path,
                    ans,
                    true);
            }
        }

        // KEEP current character
        path.append(c);

        if (c == '(') {
            dfs(s, index + 1,
                leftRemove, rightRemove,
                balance + 1,
                path, ans, false);

        } else if (c == ')') {
            dfs(s, index + 1,
                leftRemove, rightRemove,
                balance - 1,
                path, ans, false);

        } else {
            dfs(s, index + 1,
                leftRemove, rightRemove,
                balance,
                path, ans, false);
        }

        path.deleteCharAt(path.length() - 1);
    }
}