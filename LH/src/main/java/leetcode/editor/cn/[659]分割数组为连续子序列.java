package leetcode.editor.cn;//给你一个按升序排序的整数数组 num（可能包含重复数字），请你将它们分割成一个或多个子序列，其中每个子序列都由连续整数组成且长度至少为 3 。
//
// 如果可以完成上述分割，则返回 true ；否则，返回 false 。 
//
// 
//
// 示例 1： 
//
// 输入: [1,2,3,3,4,5]
//输出: True
//解释:
//你可以分割出这样两个连续子序列 : 
//1, 2, 3
//3, 4, 5
// 
//
// 
//
// 示例 2： 
//
// 输入: [1,2,3,3,4,4,5,5]
//输出: True
//解释:
//你可以分割出这样两个连续子序列 : 
//1, 2, 3, 4, 5
//3, 4, 5
// 
//
// 
//
// 示例 3： 
//
// 输入: [1,2,3,4,4,5]
//输出: False
// 
//
// 
//
// 提示： 
//
// 
// 输入的数组长度范围为 [1, 10000] 
// 
//
// 
// Related Topics 堆 贪心算法


import java.util.HashMap;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    /**
     * 遍历数组中的每一个元素
     * 随着hashmap的变化动态构造chain
     * temp为模拟的虚拟前一个数字
     * 只有当前元素个数>=temp才会加入chain
     *
     * chain中元素小于3个直接返回false
     *
     * 适用hashmap记录每个元素的出现个数
     *
     * 对于随机元素
     * 随机元素+1的出现个数大于等于随机元素出现个数才能够加入chain
     * 以此来确保元素附加到chain后面而不是构造新的chain
     * 可能造成以随机元素为开始的chain数目只有1、2个
     *
     *
     * @param nums
     * @return
     */
    public static boolean isPossible(int[] nums) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num,map.getOrDefault(num,0) + 1);
        }

        for (int num : nums) {
            int cur = num;
            int subNum = 0;
            int temp = 1;
            // 限定策略：后面元素的数量要大于或等于前面元素的数量才可以加入到序列中
            while (map.getOrDefault(cur,0) >= temp) {
                temp = map.getOrDefault(cur,0);
                map.put(cur,temp-1);
                cur++;
                subNum++;
            }
            if (subNum < 3 && subNum > 0) {
                return false;
            }
        }
        return true;
    }

    public static void main(String args[]){
        isPossible(new int[]{1,2,3,3,4,4,5,5});
        }
}
//leetcode submit region end(Prohibit modification and deletion)
