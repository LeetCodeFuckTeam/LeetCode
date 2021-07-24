package fwf;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class zuobi
{
    
    public static void main(String args[]){
        Scanner scanner = new Scanner(System.in);
        int length = scanner.nextInt();
        int k = scanner.nextInt();
        Scanner scanner2 = new Scanner(System.in);

        String s = scanner2.nextLine();
        
        int[] hash = new int[26];
        int maxOccur = ++ hash[s.charAt(0) - 'a'];
        int maxLen = 1;
        int start = 0;
        for (int end = 1;end < s.length();end ++){
            maxOccur = Math.max(maxOccur,++ hash[s.charAt(end) - 'a']);
            if (end - start + 1 > maxOccur + k){
                hash[s.charAt(start ++) - 'a'] --;
            }else{
                maxLen = Math.max(maxLen,end - start + 1);
            }
        }
        System.out.println(maxLen);
        
        }
}
