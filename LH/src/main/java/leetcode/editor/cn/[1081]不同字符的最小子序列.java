package leetcode.editor.cn;//返回字符串 text 中按字典序排列最小的子序列，该子序列包含 text 中所有不同字符一次。
//
// 
//
// 示例 1： 
//
// 输入："cdadabcc"
//输出："adbc"
// 
//
// 示例 2： 
//
// 输入："abcd"
//输出："abcd"
// 
//
// 示例 3： 
//
// 输入："ecbacba"
//输出："eacb"
// 
//
// 示例 4： 
//
// 输入："leetcode"
//输出："letcod"
// 
//
// 
//
// 提示： 
//
// 
// 1 <= text.length <= 1000 
// text 由小写英文字母组成 
// 
//
// 
//
// 注意：本题目与 316 https://leetcode-cn.com/problems/remove-duplicate-letters/ 相同 
// Related Topics 栈 贪心算法 字符串


import java.util.Stack;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution1081 {
    /**
     *   * 遍历字符串
     *      * 遇到字符如果栈不为空并且栈顶元素大于此元素且字符串从i开始查找后面还存在栈顶元素的话
     *      * 将栈顶元素出栈
     *      * 将新元素压入栈中
     *      *（只有栈顶元素小于当前元素说明按照字典序最小应该保留此元素）
     *      * 否则直接将新元素压入栈中
     * @param s
     * @return
     */
    public String smallestSubsequence(String s) {

        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < s.length(); i++) {
            if (stack.contains(s.charAt(i))){
                continue;
            }
            while (!stack.isEmpty()&&stack.peek()>s.charAt(i)&&s.indexOf(stack.peek(),i)!=-1) {
                stack.pop();
            }
            stack.push(s.charAt(i));
        }
        char[] chars = new char[stack.size()];
        for (int i = 0; i < stack.size(); i++) {
            chars[i] = stack.get(i);
        }

        return new String(chars);

    }
}
//leetcode submit region end(Prohibit modification and deletion)
