package leetcode.editor.cn;//给你一个字符串 s 和一个整数数组 cost ，其中 cost[i] 是从 s 中删除字符 i 的代价。
//
// 返回使字符串任意相邻两个字母不相同的最小删除成本。 
//
// 请注意，删除一个字符后，删除其他字符的成本不会改变。 
//
// 
//
// 示例 1： 
//
// 
//输入：s = "abaac", cost = [1,2,3,4,5]
//输出：3
//解释：删除字母 "a" 的成本为 3，然后得到 "abac"（字符串中相邻两个字母不相同）。
// 
//
// 示例 2： 
//
// 
//输入：s = "abc", cost = [1,2,3]
//输出：0
//解释：无需删除任何字母，因为字符串中不存在相邻两个字母相同的情况。
// 
//
// 示例 3： 
//
// 
//输入：s = "aabaa", cost = [1,2,3,4,1]
//输出：2
//解释：删除第一个和最后一个字母，得到字符串 ("aba") 。
// 
//
// 
//
// 提示： 
//
// 
// s.length == cost.length 
// 1 <= s.length, cost.length <= 10^5 
// 1 <= cost[i] <= 10^4 
// s 中只含有小写英文字母 
// 
// Related Topics 贪心算法



//leetcode submit region begin(Prohibit modification and deletion)
class Solution1578 {


    /**
     * lastDele指针指向最后一个未删除的元素
     * 进行字符串字符遍历
     * 当遍历的元素和最后一个未被删除的元素相同时
     * 则贪心选择最小花费的进行记录
     * 如果最下花费的是要删除lastDele执行的元素
     * 则删除后要更新lastDele指针
     * 不是lastDele的话直接计算cost  循环会继续执行
     *
     *
     * 当lastDele和i指向的字符不同时
     * 更新lastDele为当前i   i继续循环而增加
     *
     * @param s
     * @param cost
     * @return
     */
    public int minCost(String s, int[] cost) {
        if(s.length()<2) {
            return 0;
        }

        int lastDele = 0;
        int result = 0;
        char[] chars = s.toCharArray();
        for (int i = 1; i < chars.length; i++) {
            if(chars[i] == chars[lastDele]) {
                if(cost[lastDele] < cost[i]) {
                    result += cost[lastDele];
                    lastDele = i;
                }else {
                   result += cost[i];
                }
            }else {
                lastDele = i;   // 不同则不需要删除 last往前推进
            }
        }


        return result;

    }
    

}
//leetcode submit region end(Prohibit modification and deletion)
