package juc.piped;

public class niuniu
{

    public static void main(String[] args) {
        // 测试， m>2 且 n > m
        int n = 1000;
        int m = 100;
        int[][][] dp = new int[n+1][m+1][m+1];
        // 初始化
        for(int i = 1; i <= m; i++) {
            dp[i][i][0] = 1;
        }
        // 状态转移
        for(int i = 1; i <= n; i ++) {
            for(int j = 1; j <= m; j++) {
                for(int k = 0; k <= m; k++) {
                    // 不满足条件
                    if(k == j || i < j || i < k || dp[i][j][k] == 0) {
                        continue;
                    }
                    for(int cur = 1; cur <= m; cur++) {
                        // 不满足条件
                        if(cur == j || cur == k || cur + i > n) {
                            continue;
                        }
                        // 满足条件
                        dp[i + cur][cur][j] += dp[i][j][k];
                        dp[i + cur][cur][j] %= 1000000007;
                    }
                }
            }
        }
        int ans = 0;
        // 求和 dp[n][j][k]
        for(int j = 1; j <= m; j++) {
            for(int k = 0; k <= m; k++) {
                if(k == j) {
                    continue;
                }
                ans += dp[n][j][k];
                ans %= 1000000007;
            }
        }
        System.out.println(ans);
    }
}

