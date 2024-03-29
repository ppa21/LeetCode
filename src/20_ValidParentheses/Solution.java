class Solution {
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack();

        /*
            consider s = "([)]" -> false
            consider s = "{[]}" -> true
         */
        for(char c : s.toCharArray()) {
            /*
             we check if c is equal to ( or [ or { and
             we push its closing bracket AKA ) or ] or } on stack
             */
            if(c == '(') {
                stack.push(')');
            } else if(c == '[') {
                stack.push(']');
            } else if(c == '{') {
                stack.push('}');
            } else if(stack.isEmpty() || stack.pop() != c) {
                return false;
            }
        }

        return stack.isEmpty();
    }
}