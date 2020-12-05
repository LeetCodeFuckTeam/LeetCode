package leetcode.editor.cn;//给你一个字符串 s ，请你去除字符串中重复的字母，使得每个字母只出现一次。需保证 返回结果的字典序最小（要求不能打乱其他字符的相对位置）。
//
// 注意：该题与 1081 https://leetcode-cn.com/problems/smallest-subsequence-of-distinct
//-characters 相同 
//
// 
//
// 示例 1： 
//
// 
//输入：s = "bcabc"
//输出："abc"
// 
//
// 示例 2： 
//
// 
//输入：s = "cbacdcbc"
//输出："acdb" 
//
// 
//
// 提示： 
//
// 
// 1 <= s.length <= 104 
// s 由小写英文字母组成 
// 
// Related Topics 栈 贪心算法 字符串


import java.util.Stack;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution316 {
    /**
     * 遍历字符串
     * 遇到字符如果栈不为空并且栈顶元素大于此元素且字符串从i开始查找后面还存在栈顶元素的话
     * 将栈顶元素出栈
     * 将新元素压入栈中
     *（只有栈顶元素小于当前元素说明按照字典序最小应该保留此元素）
     * 否则直接将新元素压入栈中
     * @param s
     * @return
     */
    public String removeDuplicateLetters(String s) {

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
