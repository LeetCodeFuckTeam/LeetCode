package juc.piped;

import java.util.Scanner;

public class Treasure {


    public static void main(String args[]) {
        int N = 5, M = 10, K = 2;
        Scanner scanner = new Scanner(System.in);
        N = scanner.nextInt();
        M = scanner.nextInt();
        K = scanner.nextInt();
        int coinNum = 0;
        int[] treasures = new int[100*100];
        for (int i = 0; i < treasures.length; i++) {
            treasures[i] = 0;
        }

        for (int i = 0; i < N; i++) {
            Scanner scanner2 = new Scanner(System.in);
            int i1 = scanner2.nextInt();
            int i2 = scanner2.nextInt();
            treasures[i1] = i2;
        }
//        treasures[0] = 5;
//        treasures[8] = 6;
//        treasures[10] = 8;
//        treasures[18] = 12;
//        treasures[22] = 15;
        int j = K;
        int currentPosition = 0;
        coinNum += treasures[currentPosition];
        while (j > 0) {
            int Gap = 0;
            if(j > 0) {
                Gap += currentPosition + 10;
            }
            int curMaxCoinMum = 0;
            int newPosition = currentPosition;
            for (int k = currentPosition + 1; k <= Gap; k++) {
                if (treasures[k] > curMaxCoinMum) {
                    curMaxCoinMum = treasures[k];
                    newPosition = k;
                }
            }
            coinNum += curMaxCoinMum;
            currentPosition = newPosition;
            j--;
        }

        System.out.println(coinNum);

    }
}
