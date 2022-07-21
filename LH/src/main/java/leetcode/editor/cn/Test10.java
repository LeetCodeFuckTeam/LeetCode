package leetcode.editor.cn;


import java.util.*;

public class Test10 {

    private static  int SIZE ;
    private static int count = 0;
    private static int[][] queen ;

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        SIZE = scanner.nextInt();
        queen = new int[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++) {
            place(i, 0);
        }
    }
    private static void place(int i, int start) {
        if (i==0 && start== SIZE) {
            System.out.println(count);
            System.exit(0);
        }
        boolean rowPlaced = false;
        for(int j = start; j < SIZE; j++) {
            if (check(i, j)) {
                queen[i][j] = 1;
                rowPlaced = true;
                if (i== SIZE -1) {
                    count++;
                    queen[i][j] = 0;
                    place(i, j+1);
                }
                break;
            }
        }
        if (!rowPlaced) {
            int index = seek(i - 1);
            reset(i-1, index);
            place(i-1, index+1);
            place(i, 0);
        }
    }

    private static void reset(int i, int j) {
        queen[i][j] = 0;
    }

    private static int seek(int i) {
        for (int j = 0; j < SIZE; j++) {
            if (queen[i][j] == 1) {
                return j;
            }
        }
        return -1;
    }


    private static boolean check(int i, int j) {
        for(int m = 0; m < SIZE; m++) {
            for(int n = 0; n < SIZE; n++) {
                if (queen[m][n]==1) {
                    if (i == m || j == n || Math.abs(i - m) == Math.abs(j - n)) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

}
