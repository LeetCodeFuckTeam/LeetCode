package leetcode.editor.cn;

import java.util.Scanner;

public class Test6
{

    public static void main(String[] args) {

        //调用方法
        mark(5,10);
    }
    //创建方法
    public static void mark(int m,int n) {

        for(int i = 1; i <= 999; i++) {

            //打印不包含m的数
            if(i % 10 != m && i / 10 != m && i / 100 != m) {

                System.out.print(i + "\t");

            }
        }

    }

}
