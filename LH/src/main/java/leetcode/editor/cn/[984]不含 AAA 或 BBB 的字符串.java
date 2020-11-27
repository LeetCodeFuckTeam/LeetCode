package leetcode.editor.cn;//给定两个整数 A 和 B，返回任意字符串 S，要求满足：
//
// 
// S 的长度为 A + B，且正好包含 A 个 'a' 字母与 B 个 'b' 字母； 
// 子串 'aaa' 没有出现在 S 中； 
// 子串 'bbb' 没有出现在 S 中。 
// 
//
// 
//
// 示例 1： 
//
// 输入：A = 1, B = 2
//输出："abb"
//解释："abb", "bab" 和 "bba" 都是正确答案。
// 
//
// 示例 2： 
//
// 输入：A = 4, B = 1
//输出："aabaa" 
//
// 
//
// 提示： 
//
// 
// 0 <= A <= 100 
// 0 <= B <= 100 
// 对于给定的 A 和 B，保证存在满足要求的 S。 
// 
// Related Topics 贪心算法


//leetcode submit region begin(Prohibit modification and deletion)
class Solution984 {
    /**
     * 贪心算法
     * 拼接结果集
     * 从AB中找出要求个数最多的
     * 然后先安排个数最多的
     * 每安排两个个数多的就后面安排个数小的
     * 只要保证不超过连续三个相同的字符就行
     * @param A
     * @param B
     * @return
     */
    public String strWithout3a3b(int A, int B) {

        StringBuilder sb = new StringBuilder();

        while (A>0||B>0) {
            boolean writeA = false;
            int n = sb.length();
            if(n >= 2 && sb.charAt(n-1) == sb.charAt(n-2)) {
                if(sb.charAt(n-1) == 'b') {
                    writeA = true;
                }

            }else {
                if(A > B) {
                    writeA = true;
                }
            }

            if (writeA) {
                A--;
                sb.append('a');
            } else {
                B--;
                sb.append('b');
            }

              }
        return sb.toString();
    }
}
//leetcode submit region end(Prohibit modification and deletion)
