package fwf;

import java.lang.reflect.Array;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class sendAdv {
    
    
    public static void main(String args[]){
        int n =3, k =3;
        Scanner scanner = new Scanner(System.in);
        n = scanner.nextInt();
        k = scanner.nextInt();
        int[] sequence = new int[n];
        Scanner scanner1 = new Scanner(System.in);
        for (int i = 0; i < sequence.length; i++) {
            sequence[i] = scanner1.nextInt();
        }

        Map<Integer,List> hashMap = new ConcurrentHashMap<>();
        for (int i = 0; i < sequence.length; i++) {
            int time = sequence[i];
            for (int i1 = 1; i1 <= k; i1++) {
                if(hashMap.get(i) == null){
                    List<Integer> array = new ArrayList<>();
                    array.add(i1*time);
                    hashMap.put(i,array);
                }else{
                    List<Integer> array = hashMap.get(i);
                    array.add(i1*time);
                    hashMap.remove(i);
                    hashMap.put(i,array);
                }
            }     
        }
       
            int time = 0;
            int sendoff = 0;
            while (sendoff < k){
                boolean flag = false;
                Iterator<Map.Entry<Integer, List>> iterator = hashMap.entrySet().iterator();
                while (iterator.hasNext()){
                Map.Entry<Integer, List> next = iterator.next();
                    Integer key = next.getKey();
                    List<Integer> value = next.getValue();
                for (int i = 0; i < value.size(); i++) {
                    Integer integer = value.get(i);
                    if(integer == time) {
                        List<Integer> integers = hashMap.get(key);
                        integers.remove(integer);
                        hashMap.remove(i);
                        hashMap.put(key,integers);
                        System.out.println(key+1);
                        flag = true;
                        sendoff++;
                        break;
                    }
                }
            }

            time++;
        }
        
        }
}
