package leetcode.editor.cn;

import juc.piped.main;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Test12 {
 


    //    public static void main(String args[]){
//        String[]strs = {"10", "0001", "111001", "1", "0"};
//        int m = 5, n = 3;
//        findMaxForm(strs,m,n);
//
//        }
//    public static int findMaxForm(String[] strs, int m, int n) {
//        //dp[i][j]表示i个0和j个1时的最大子集
//        int[][] dp = new int[m + 1][n + 1];
//        int oneNum, zeroNum;
//        for (String str : strs) {
//            oneNum = 0;
//            zeroNum = 0;
//            for (char ch : str.toCharArray()) {
//                if (ch == '0') {
//                    zeroNum++;
//                } else {
//                    oneNum++;
//                }
//            }
//            //倒序遍历
//            for (int i = m; i >= zeroNum; i--) {
//                for (int j = n; j >= oneNum; j--) {
//
//                    dp[i][j] = Math.max(dp[i][j], dp[i - zeroNum][j - oneNum] + 1);
//                }
//            }
//        }
//        return dp[m][n];
//    }
//

    public static void main(String args[]){


        }
    public static boolean wordBreak(String s, List<String> wordDict) {

        //dp[i] : 字符串长度为i的话，dp[i]为true，表示可以拆分为一个或多个在字典中出现的单词。
        Set<String> wd = new HashSet();
        for (int i = 0; i < wordDict.size() ;i++) {
            wd.add(wordDict.get(i));
        }
        int[] dp = new int[s.length() + 1];
        dp[0] = 1;
        for (int i = 1; i <= s.length(); i++) {
            for (int j = 0; j < i; j++) {//遍历物品
                String word = s.substring(j,i);
                if (dp[j] == 1 && wd.contains(word)) {
                    dp[i] = 1;
                }
            }
        }
        return dp[s.length()+1] == 1 ? true : false;


    }
}
