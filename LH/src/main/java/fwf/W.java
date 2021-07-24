package fwf;

import org.omg.PortableInterceptor.INACTIVE;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Stack;

public class W
{

    public static void main(String args[]){
        Scanner scanner = new Scanner(System.in);
        int num = scanner.nextInt();
        int[] sequence = new int[num];
        for (int i = 0; i < sequence.length; i++) {
            sequence[i] = scanner.nextInt();
        }
        int score = 0;
        HashMap<Integer, Integer> map = new HashMap();
        for (int i : sequence) {
            if(map.get(i) == null) {
                map.put(i,1);
            }else {
                int s = (int) map.get(i);
                map.put(i,++s);
            }
        }
        int maxIndex = sequence[0];
        int maxNum = 0;
        for (Map.Entry<Integer,Integer> entry : map.entrySet()) {
            Integer key = entry.getKey();
            Integer value = entry.getValue();
            if(value > maxNum) {
                maxIndex = key;
                maxNum = value;
            }
        }

        score = maxNum -1;

        for (int i = sequence.length -1; i >= 0; i--) {
            int cur = sequence[i];
            if(maxIndex == cur){
                continue;
            }
            if(i -1 >=0 && sequence[i-1]==cur) {
                score++;
            }
        }
        
        System.out.println(score);

        }
}
