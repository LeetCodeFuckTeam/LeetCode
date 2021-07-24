package juc.piped;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Test {


    public static void main(String args[]){



        String n1 = "Zhang San";
        String n2 = "Zhan Ai";


        String[] a1 = n1.split("\\s");
        String[] a2 = n2.split("\\s");
        int ascall = 0;

        int iv = 0;
        while(iv < Math.min(a1.length,a2.length)) {
            List list = new ArrayList();
            String s1 = a1[iv];
            String s2 = a2[iv];

            char[] chars1 = s1.toCharArray();
            char[] chars2 = s2.toCharArray();

            int[][] dp = new int[chars1.length][chars2.length];
            int max = 0;
            //初始化第一行
            for(int j = 0; j < chars2.length; j++){
                if(chars1[0] == chars2[j]) dp[0][j] = 1;
            }
            //初始化第一列
            for(int i = 0; i < chars1.length; i++){
                if(chars1[i] == chars2[0]) dp[i][0] = 1;
            }

            //dp
            for(int i = 1; i < chars1.length; i++) {
                for (int j = 1; j < chars2.length; j++) {
                    if (chars1[i] == chars2[j]) {
                        dp[i][j] = dp[i - 1][j - 1] + 1;
                        max = Math.max(max, dp[i][j]);
                    }
                }
            }


            for(int i = 0; i < chars1.length; i++){
                for(int j = 0; j < chars2.length; j++){
                    if(dp[i][j] == max){
                        for(int k = i - max + 1; k <= i; k++){
                            list.add(chars1[k]);
                        }
                    }
                }
            }

            //剔除
            for (int i = 0; i < chars1.length; i++) {
                if(!list.contains(chars1[i])) {
                    ascall+=(int)chars1[i];
                }

            }
            for (int i = 0; i < chars2.length; i++) {
                if(!list.contains(chars2[i])) {
                    ascall+=(int)chars2[i];
                }
            }


            iv++;
        }
        int m = iv,n = iv;
        while (m<a1.length) {
            char[] chars = a1[m].toCharArray();
            for (int i1 = 0; i1 < chars.length; i1++) {
                ascall += (int) chars[i1];
            }
            m++;

        }
        while (n<a2.length) {
            char[] chars = a2[n].toCharArray();
            for (int i1 = 0; i1 < chars.length; i1++) {
                ascall += (int) chars[i1];
            }
            n++;
            
        }
        System.out.println(ascall);
    }

}
