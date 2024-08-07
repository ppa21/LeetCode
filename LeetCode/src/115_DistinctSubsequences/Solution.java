public class Solution {
    /*
        * Time Complexity  = O(mn); m = size of s; n = size of t
        * Space Complexity = O(mn)
    */
    public int numDistinct(String s, String t) {
        // s = "abb" and t = "ab"
        
        // dp[i][j] represents the number of distinct subsequences of s[0..i] in t[0..j]
        int[][] dp = new int[s.length() + 1][t.length() + 1];

        // Initialize the first row with 1 because an empty string has one subsequence in any string
        for (int i = 0; i <= s.length(); i++) {
            dp[i][0] = 1;
        }

        for (int i = 1; i <= s.length(); i++) {
            for (int j = 1; j <= t.length(); j++) {
                // If the current characters in s and t match (s.charAt(i - 1) == t.charAt(j - 1)), 
                // we have two scenarios:
                 // 1) dp[i - 1][j - 1]: We include the current character match and consider the number of distinct 
                 //    subsequences found so far without the current character. This forms new distinct subsequences.
                 // 2) dp[i - 1][j]: We exclude the current character match in s and consider the number of distinct 
                 //    subsequences found so far. This allows us to continue exploring other characters in s for potential matches.
                 // The total number of distinct subsequences is the sum of the counts from these two scenarios.
                if (s.charAt(i - 1) == t.charAt(j - 1)) {
                    // when i = 1 and j = 1, s.charAt(i - 1) = 'a' and t.charAt(j - 1) = 'a', so they match
                    dp[i][j] = dp[i - 1][j - 1] + dp[i - 1][j];
                } else {
                    // If the current characters don't match, we can only move to the previous character in s and go to the previous sub-problem in s
                    // when i = 1 and j = 2, s.charAt(i - 1) = 'a' and t.charAt(j - 1) = 'b', so they don't match
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }

        // Return the number of distinct subsequences of t in s
        // In our example, dp[s.length()][t.length()] = dp[3][2] = 2, so there are 2 distinct subsequences of "ab" in "abb"
        return dp[s.length()][t.length()];
    }
}
