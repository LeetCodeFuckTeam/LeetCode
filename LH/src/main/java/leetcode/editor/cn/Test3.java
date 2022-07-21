package leetcode.editor.cn;

import org.omg.PortableInterceptor.INACTIVE;

import java.util.*;

public class Test3
{
    public static void main(String args[]){
        List<Integer> list3 = new ArrayList();
        List<Integer> list4 = new ArrayList();
        list3.add(4);
        list4.add(6);
        list3.add(7);
        list4.add(7);
        list3.add(6);
        findDuplicate(list3,list4);

        }
    public static void findDuplicate(List<Integer> list1, List<Integer> list2) {
        List<Integer> duplicate = new ArrayList<Integer>();
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        //1.去重
        List<Integer> list3 = new ArrayList<Integer>(new HashSet<Integer>(list1));
        List<Integer> list4 = new ArrayList<Integer>(new HashSet<Integer>(list2));
        //2.将list中的元素加入map中并进行统计
        for (Integer s1 : list3) {
            map.put(s1, 1);
        }
        //3.将list中的元素加入map中并进行统计
        for (Integer s2 : list4) {
            map.put(s2, (map.get(s2) == null ? 1 : 2));
        }
        //4.将重复的元素放入list中
        for (Map.Entry<Integer, Integer> m : map.entrySet()) {
            if (m.getValue() == 2) {
                duplicate.add(m.getKey());
            }
        }
        Collections.sort(duplicate);
        int count = 0;
        for (Integer integer : duplicate) {
            if(count == 5) break;
            System.out.println(integer);
            count++;
        }

    }
}
