package leetcode.editor.cn;

import java.util.*;
public class Greedy {
    static int n,k;
    static int[] machine;
    static int[] task;
    static int minTime;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        k = 3;
        task = new int[n];
        machine = new int[k];
        for(int i=0;i<n;i++) {
            task[i] = sc.nextInt();
            minTime+=task[i];
        }
        recursion(0);
        System.out.println(minTime);
    }
    public static void recursion(int i) {
        if(i<n) {
            for(int j=0;j<k;j++) {
                machine[j]+=task[i];
                if(machine[j]<minTime) {
                    recursion(i+1);
                }
                machine[j]-=task[i];
            }
        }
        else {
            int nowMaxTime = 0;
            for(int j=0;j<k;j++) {
                nowMaxTime = Math.max(nowMaxTime, machine[j]);
            }
            minTime = Math.min(minTime, nowMaxTime);
        }
    }
}