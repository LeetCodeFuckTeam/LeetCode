package leetcode.editor.cn;//给定一个非负整数数组，你最初位于数组的第一个位置。
//
// 数组中的每个元素代表你在该位置可以跳跃的最大长度。 
//
// 你的目标是使用最少的跳跃次数到达数组的最后一个位置。 
//
// 示例: 
//
// 输入: [2,3,1,1,4]
//输出: 2
//解释: 跳到最后一个位置的最小跳跃数是 2。
//     从下标为 0 跳到下标为 1 的位置，跳 1 步，然后跳 3 步到达数组的最后一个位置。
// 
//
// 说明: 
//
// 假设你总是可以到达数组的最后一个位置。 
// Related Topics 贪心算法 数组


//leetcode submit region begin(Prohibit modification and deletion)
class Solution45 {

    /**
     * 应该选择可以跳跃的合适时机进行跳跃
     * 一直不进行跳跃直到发现有不能跳跃到更远地方的点
     * 那么在其区间内必然有必要的跳跃
     *
     *
     * current_max_index表示当前可跳跃的最远位置
     * pre_max_max_index表示在遍历过程中各个位置所能到达的最远位置
     *
     *
     * 如果在跳跃过程中i表示当前预跳跃的位置
     * 小于pre_max_max_index的就要更新pre_max_max_index
     * @param nums
     * @return
     */
    public int jump(int[] nums) {
        if(nums.length<2){
            return 0;
        }
        int current_max_index = nums[0];
        int pre_max_max_index = nums[0];
        int min_jump = 1;
        for (int i = 1; i < nums.length; i++) {
            if(i>current_max_index) {
                //表示此时无法跳跃的点之前必存在可跳跃的位置
                min_jump++;
                //更新当前可跳跃的最远位置
                current_max_index = pre_max_max_index;
            }
            if(pre_max_max_index<nums[i]+i) {
                pre_max_max_index = nums[i]+i;
            }
        }
        return min_jump;

    }
}
//leetcode submit region end(Prohibit modification and deletion)
