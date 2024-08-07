class Solution {
    /*
        * SORTA SIMILAR TO:
            * 416. Partition Equal Subset Sum
    
        * Time Complexity  = O(n * newTarget); n = size of nums; newTarget = (sum + target) / 2;
        * Space Complexity = O(newTarget); dp size
    */
    public int findTargetSumWays(int[] nums, int target) {
        // Calculate the sum of all numbers
        int sum = Arrays.stream(nums).sum();    // SIMILAR TO 416. Partition Equal Subset Sum

        /*
            * sum < Math.abs(target)
                * [1, 2, 3]; target = 7
                    * 6 < 7 
                    * we can't reach 7
                        * 1 + 1 + 2 + 3 --> WRONG
                            * CAN'T ADD THE SAME NUMBER MORE THAN ONCE UNLESS IT'S ALREADY PRESENT IN THE ARRAY MULTIPLE TIMES
            * (sum + target) % 2 == 1 --> if this is odd, (sum + target) / 2 will be fractional and you won't be able to find INTEGERS
        */
        if (sum < Math.abs(target) || (sum + target) % 2 == 1) {
            return 0;
        }
        
        // This transforms the problem into a subset sum problem so instead of finding TARGET, we find NEWTARGET since it takes SUM into account and you'd already have SUM from NUMS
        int newTarget = (sum + target) / 2;    // SIMILAR TO 416. Partition Equal Subset Sum
        
        // dp[i] will store the number of ways to reach the sum i
        int[] dp = new int[newTarget + 1];
        dp[0] = 1;  // There is one way to reach the sum 0: by taking no numbers

        // LOOP SORTA SIMILAR TO 416. Partition Equal Subset Sum
        // For each number in nums
        for (int n : nums) {
            // For each sum from newTarget to n (inclusive)
            for (int i = newTarget; i >= n; i--) {
                // Add the number of ways to reach the sum i - n to dp[i]
                dp[i] += dp[i - n];
            }
        }
        
        // Return the number of ways to reach the new target
        return dp[newTarget];
    }
}
