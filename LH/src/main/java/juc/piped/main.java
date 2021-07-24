package juc.piped;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class main {

    public static void main(String args[]){
        int target = 5;
        int[] array = {0,1,2,1,2};
        Map<Integer,List<Integer>> map= new HashMap<>();
        for (int i = 0; i < array.length; i++) {
            if(map.containsKey(array[i])) {
                List<Integer> indexs = map.get(array[i]);
                boolean add = indexs.add(i);
                map.put(array[i],indexs);
            }else {
                List list = new ArrayList();
                list.add(i);
                map.put(array[i],list);
            }
        }
        int i = 0;
        int leapNum = 0;
        while(i < array.length) {
            if(i == target - 1) break;
            int curNum = array[i];
            int newPostion = getMinGapIndex(array,i,map,target);
            if(i!= newPostion) {
                i = newPostion;
                leapNum++;
            }else{
                i++;
                leapNum++;
            }


        }
        System.out.println(leapNum);

        }


        public static Integer getMinGapIndex(int[] array,int curIndex,Map map,int targetIndex) {
            int gap = targetIndex - curIndex;
            List<Integer> indexs = (List) map.get(array[curIndex]);
            for (int index : indexs) {
                int lastGap = gap;
                gap = Math.min(targetIndex - index,gap);
                if(lastGap > gap) {
                    curIndex = index;
                }
            }
            return curIndex;

        }
}
