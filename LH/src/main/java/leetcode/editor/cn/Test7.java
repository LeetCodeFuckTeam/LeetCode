package leetcode.editor.cn;

import java.util.Scanner;

public class Test7 {
    static int dp[][][] = new int[210][210][210];
    static int color[] = new int[210];
    static int leng[] = new int[210];

    public static void main(String args[]) {

            int ans, n, m, num, j, l, i;
            Scanner scanner = new Scanner(System.in);
                n = scanner.nextInt();
                initDP(dp);
                for (ans = -1, i = 1, j = 0; i <= n; ++i) {
                    m = scanner.nextInt();
                    if (ans != m) {
                        j++;
                        color[j] = m;
                        leng[j] = 1;
                        ans = m;
                    } else {
                        leng[j]++;
                    }
                }
                num = Dp(0, j, 0);
                System.out.println(num);

        }


    public static int Dp(int i, int j, int len) {
        if (dp[i][j][len] != -1)
            return dp[i][j][len];
        int ans = (leng[j] + len) * (leng[j] + len);
        if (i == j)
            return ans;
        ans += Dp(i, j - 1, 0);
        for (int k = i; k < j; k++) {
            if (color[k] == color[j])
                ans = Math.max(ans, Dp(i, k, leng[j] + len) + Dp(k + 1, j - 1, 0));
        }
        dp[i][j][len] = ans;
        return ans;
    }
    public static void initDP(int[][][] dp) {
        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp[0].length; j++) {
                for (int k = 0; k < dp[0][0].length; k++) {
                    dp[i][j][k] = -1;
                }
            }

        }

    }

}

