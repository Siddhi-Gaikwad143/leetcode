class Solution {
    public int scoreOfParentheses(String s) {

        Stack<Integer> stack = new Stack<>();
        stack.push(0);

        for (char ch : s.toCharArray()) {

            if (ch == '(') {
                // Start a new nested level
                stack.push(0);

            } else {
                // Get the score inside current parentheses
                int innerScore = stack.pop();

                // () = 1
                // (A) = 2 * A
                int score = Math.max(1, 2 * innerScore);

                // Add score to the previous level
                stack.push(stack.pop() + score);
            }
        }

        return stack.pop();
    }
}