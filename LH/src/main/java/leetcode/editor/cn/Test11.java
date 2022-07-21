package leetcode.editor.cn;

public class Test11 {
    static int count = 0;
    public static void main(String args[]) {
        int[] nums = {0,0,0,0,0,0,0,0,1};
        int target = 1;
        findTargetSumWays(nums, target);
    }
        public static int findTargetSumWays(int[] nums, int target) {
            int sum = 0;
            for (int num : nums) {
                sum += num;
            }
            int diff = sum - target;
            if (diff < 0 || diff % 2 != 0) {
                return 0;
            }
            int n = nums.length, neg = diff / 2;
            int[][] dp = new int[n + 1][neg + 1];
            dp[0][0] = 1;
            for (int i = 1; i <= n; i++) {
                int num = nums[i - 1];
                for (int j = 0; j <= neg; j++) {
                    dp[i][j] = dp[i - 1][j];
                    if (j >= num) {
                        dp[i][j] += dp[i - 1][j - num];
                    }
                }
            }
            for (int i = 0; i < dp.length; i++) {
                for (int j = 0; j < dp[0].length; j++) {
                    System.out.print(dp[i][j]);
                    System.out.print("  ");
                }
                System.out.println();
            }
            return dp[n][neg];
        }


}
