package leetcode.editor.cn;//给定一个非负整数数组，你最初位于数组的第一个位置。
//
// 数组中的每个元素代表你在该位置可以跳跃的最大长度。 
//
// 判断你是否能够到达最后一个位置。 
//
// 示例 1: 
//
// 输入: [2,3,1,1,4]
//输出: true
//解释: 我们可以先跳 1 步，从位置 0 到达 位置 1, 然后再从位置 1 跳 3 步到达最后一个位置。
// 
//
// 示例 2: 
//
// 输入: [3,2,1,0,4]
//输出: false
//解释: 无论怎样，你总会到达索引为 3 的位置。但该位置的最大跳跃长度是 0 ， 所以你永远不可能到达最后一个位置。
// 
// Related Topics 贪心算法 数组


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    /**
     * 分析：
     * 从当前跳跃点开始跳跃
     * 第一步直接计算出在当前跳跃点的可跳跃的最大下标
     * 贪心选择性质：每次跳跃必须是最远的
     * 设置数组记录当前下标可以跳跃的最远下标
     * 假设index【i】为最大跳跃距离
     * 我们那么i坐标比任意坐标的视野都要远
     * max_index 代表着全局的最远下标
     *
     *
     * 在跳跃过程中如果在max_length下标只内我们可以任意选择
     * 开始跳跃的下标来计算跳跃的最远下标并实时更新最远跳跃下标
     *
     *
     * @param nums
     * @return
     */
    public boolean canJump(int[] nums) {

        //设置数组计算每个nums处能够跳跃的最大下标
        int index[] = new int[nums.length];
        for (int i = 0; i < index.length; i++) {
            index[i] = nums[i] + i;
        }
        //定义能够跳跃的最远下标
        int max_index = index[0];
        int jump = 0;//从第一个下标开始,表示当前跳跃坐标
        while(jump<index.length&&jump<=max_index) {
            if(max_index<index[jump]) {
                max_index = index[jump];

            }
            jump++;
        }
        if(jump == index.length) {
            return true;
        }
        return false;

    }
}
//leetcode submit region end(Prohibit modification and deletion)
