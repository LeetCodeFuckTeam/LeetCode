package leetcode.editor.cn;//给你一个整数数组 arr。你可以从中选出一个整数集合，并删除这些整数在数组中的每次出现。
//
// 返回 至少 能删除数组中的一半整数的整数集合的最小大小。 
//
// 
//
// 示例 1： 
//
// 输入：arr = [3,3,3,3,5,5,5,2,2,7]
//输出：2
//解释：选择 {3,7} 使得结果数组为 [5,5,5,2,2]、长度为 5（原数组长度的一半）。
//大小为 2 的可行集合有 {3,5},{3,2},{5,2}。
//选择 {2,7} 是不可行的，它的结果数组为 [3,3,3,3,5,5,5]，新数组长度大于原数组的二分之一。
// 
//
// 示例 2： 
//
// 输入：arr = [7,7,7,7,7,7]
//输出：1
//解释：我们只能选择集合 {7}，结果数组为空。
// 
//
// 示例 3： 
//
// 输入：arr = [1,9]
//输出：1
// 
//
// 示例 4： 
//
// 输入：arr = [1000,1000,3,7]
//输出：1
// 
//
// 示例 5： 
//
// 输入：arr = [1,2,3,4,5,6,7,8,9,10]
//输出：5
// 
//
// 
//
// 提示： 
//
// 
// 1 <= arr.length <= 10^5 
// arr.length 为偶数 
// 1 <= arr[i] <= 10^5 
// 
// Related Topics 贪心算法 数组




import java.util.*;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution1338 {

    /**
     * 遍历arr使用hashmap存储对应元素的出现次数
     * 转换成List进行排序然后根据出现次数进行排序
     * 然后遍历按照次数最多的逐个往下删除
     * @param arr
     * @return
     */
    public int minSetSize(int[] arr) {
        int n=arr.length/2;
        Map<Integer , Integer>  map =new HashMap<Integer ,Integer>();
        for(int i=0;i<arr.length;i++){
            if(!map.containsKey(arr[i])){
                map.put(arr[i],1);
            }else{
                int number=map.get(arr[i]);
                number++;
                map.put(arr[i],number);
            }
        }
        Map<Integer,Integer> sortedMap =new LinkedHashMap<Integer , Integer>();
        List<Map.Entry<Integer ,Integer>> list =new ArrayList<Map.Entry<Integer , Integer>>(map.entrySet());
        Collections.sort(list , (o1,o2)-> { return o2.getValue()-o1.getValue(); } );
        Iterator<Map.Entry<Integer ,Integer>> iter= list.iterator();
        Map.Entry<Integer ,Integer> tempEntry=null;
        while(iter.hasNext()){
            tempEntry=iter.next();
            sortedMap.put(tempEntry.getKey() , tempEntry.getValue() );
        }



        int count=0;
        for(Integer key :sortedMap.keySet()){
            if(n>0){
                // System.out.println(sortedMap.get(key)+" "+n);
                n-=sortedMap.get(key);
                count++;
            }
        }
        return count;


    }
}
//leetcode submit region end(Prohibit modification and deletion)
