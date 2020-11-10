package leetcode.editor.cn;//给定两个数组，编写一个函数来计算它们的交集。
//
// 
//
// 示例 1： 
//
// 输入：nums1 = [1,2,2,1], nums2 = [2,2]
//输出：[2]
// 
//
// 示例 2： 
//
// 输入：nums1 = [4,9,5], nums2 = [9,4,9,8,4]
//输出：[9,4] 
//
// 
//
// 说明： 
//
// 
// 输出结果中的每个元素一定是唯一的。 
// 我们可以不考虑输出结果的顺序。 
// 
// Related Topics 排序 哈希表 双指针 二分查找


import java.util.Arrays;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution349 {
    /**
     * 双指针法解决数组交集问题
     * @param nums1
     * @param nums2
     * @return
     */
    public int[] intersection(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        int n1_index = 0;
        int n2_index = 0;
        int index = 0;
        int intersection_length = nums1.length + nums2.length;
        int[] intersection = new int[intersection_length];
        while (n1_index<nums1.length&&n2_index<nums2.length) {
            if(nums1[n1_index] == nums2[n2_index]) {
                if(index == 0||intersection[index-1] != nums1[n1_index]) {
                    intersection[index++] = nums1[n1_index];
                }
                n1_index++;
                n2_index++;
            }else if(nums1[n1_index] > nums2[n2_index]){
                n2_index++;

            }else {
                n1_index++;

            }
        }
        return Arrays.copyOfRange(intersection,0,index);


    }
}
//leetcode submit region end(Prohibit modification and deletion)
