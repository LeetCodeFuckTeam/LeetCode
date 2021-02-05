package leetcode.editor.cn;//给定一个含有 n 个正整数的数组和一个正整数 s ，找出该数组中满足其和 ≥ s 的长度最小的 连续 子数组，并返回其长度。如果不存在符合条件的子数组，返回
// 0。 
//
// 
//
// 示例： 
//
// 输入：s = 7, nums = [2,3,1,2,4,3]
//输出：2
//解释：子数组 [4,3] 是该条件下的长度最小的子数组。
// 
//
// 
//
// 进阶： 
//
// 
// 如果你已经完成了 O(n) 时间复杂度的解法, 请尝试 O(n log n) 时间复杂度的解法。 
// 
// Related Topics 数组 双指针 二分查找


//leetcode submit region begin(Prohibit modification and deletion)
class Solution209 {
    /**
     * 滑动窗口双指针法
     * @param target
     * @param nums
     * @return
     */
    public int minSubArrayLen(int target, int[] nums) {
        int left = 0,right = 0;
        int sum = 0;
        int len = 0;
        while (right < nums.length) {
            sum += nums[right];
            //当无法满足要求则不断增长窗口，如果有边界到头则结束返回说明没有查找到满足的子序列
            //如果满足则右移左边界不断的判断看能否有更优的情况
            //如果不能满足则继续右移右边界等到满足情况
            //再右移左边界继续寻找最优结果
            while (sum >= target) {
                len = len == 0 ? right - left + 1 : Math.min(len, right - left + 1);
                //左边界向右移动的过程中是必然从满足的结果走向不满足的结果势必再次造成右边界右移，所以左边界始终小于右边界
                sum -= nums[left++];
            }
            right++;
        }
        return len;
    }

}
//leetcode submit region end(Prohibit modification and deletion)
