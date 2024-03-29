class Solution {
    public int[] productExceptSelf(int[] nums) {
        int N = nums.length;
        int[] leftOutput = new int[N];
        int[] rightOutput = new int[N];
        int[] finalOutput = new int[N];

        /*
            Input: nums = [1, 2, 3, 4]

            leftOutput = [1, 1, 2, 6]
            rightOutput = [24, 12, 4, 1]
            finalOutput = [24, 12, 8, 6]
         */

        leftOutput[0] = 1;
        for(int i = 1; i < N; i++) {
            leftOutput[i] = leftOutput[i - 1] * nums[i - 1];
        }

        rightOutput[N - 1] = 1;
        for(int i = N - 2; i >= 0; i--) {
            rightOutput[i] = rightOutput[i + 1] * nums[i + 1];
        }

        for(int i = 0; i < N; i++) {
            finalOutput[i] = leftOutput[i] * rightOutput[i];
        }

        return finalOutput;
    }
}