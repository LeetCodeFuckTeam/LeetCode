package leetcode.editor.cn;//给定一个非负整数 N，找出小于或等于 N 的最大的整数，同时这个整数需要满足其各个位数上的数字是单调递增。
//
// （当且仅当每个相邻位数上的数字 x 和 y 满足 x <= y 时，我们称这个整数是单调递增的。） 
//
// 示例 1: 
//
// 输入: N = 10
//输出: 9
// 
//
// 示例 2: 
//
// 输入: N = 1234
//输出: 1234
// 
//
// 示例 3: 
//
// 输入: N = 332
//输出: 299
// 
//
// 说明: N 是在 [0, 10^9] 范围内的一个整数。 
// Related Topics 贪心算法


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    /**
     *
     * 贪心算法
     * 逐个遍历构造结果集
     * 从变量d 1到9尝试
     * 一直到ans append d重复到字符串全部 > N 的第一个结果出现
     * 那么这个结果此时i指针指向的位置一定为d-1
     * 因为题目要求构造小于N的最大递增整数
     * i指向的d   ans  append ddddd
     * ddddd是递增的序列   第i位如果为d一定会比N大
     * 所以第i位为 d-1
     *
     * 如果 ans append dddd（最小情况）都无法满足   递增的其他序列更无法满足
     * 所以当前位置为 d-1
     *
     *
     * 如果找不到合适的d使得 ans append d重复到字符串全部 > N
     * 就将此位设置为最大9
     *
     *
     *
     * @param N
     * @return
     */
    public static int monotoneIncreasingDigits(int N) {
        String ans = "";
        String s = String.valueOf(N);
        for (int i = 0; i < s.length(); i++) {
            boolean find = false;
            for (char d = '0'; d <= '9'; d++) {
                if(s.compareTo(ans + repeat(d,s.length() - i)) < 0) {
                    ans += (char)(d - 1);
                    find = true;
                    break;
                }

            }
            if(find == false) {
                ans += '9';
            }
        }
        return Integer.parseInt(ans);

    }
    public static String repeat(char d,int count){
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < count; i++) {
            sb.append(d);
        }
        return sb.toString();

    }
    public static void main(String args[]){
        monotoneIncreasingDigits(90);
        }
}
//leetcode submit region end(Prohibit modification and deletion)


