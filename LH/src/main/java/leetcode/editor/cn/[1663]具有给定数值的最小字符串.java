package leetcode.editor.cn;//小写字符 的 数值 是它在字母表中的位置（从 1 开始），因此 a 的数值为 1 ，b 的数值为 2 ，c 的数值为 3 ，以此类推。
//
// 字符串由若干小写字符组成，字符串的数值 为各字符的数值之和。例如，字符串 "abe" 的数值等于 1 + 2 + 5 = 8 。 
//
// 给你两个整数 n 和 k 。返回 长度 等于 n 且 数值 等于 k 的 字典序最小 的字符串。 
//
// 注意，如果字符串 x 在字典排序中位于 y 之前，就认为 x 字典序比 y 小，有以下两种情况： 
//
// 
// x 是 y 的一个前缀； 
// 如果 i 是 x[i] != y[i] 的第一个位置，且 x[i] 在字母表中的位置比 y[i] 靠前。 
// 
//
// 
//
// 示例 1： 
//
// 
//输入：n = 3, k = 27
//输出："aay"
//解释：字符串的数值为 1 + 1 + 25 = 27，它是数值满足要求且长度等于 3 字典序最小的字符串。 
//
// 示例 2： 
//
// 
//输入：n = 5, k = 73
//输出："aaszz"
// 
//
// 
//
// 提示： 
//
// 
// 1 <= n <= 105 
// n <= k <= 26 * n 
// 
// Related Topics 贪心算法


//leetcode submit region begin(Prohibit modification and deletion)
class Solution1663 {
    /**
     * 贪心算法
     * 先为每个位置填充1
     * 也就是a是字典序才能够最小
     * 要保证字典序最小，就要尽量更多字典序最小的安排在前面
     * 只有在没有办法更小的字典序且满足弥补数值上的缺省才进行从z逐个递减填充
     * @param n
     * @param k
     * @return
     */
    public String getSmallestString(int n, int k) {
        int[] arr = new int[n];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = 1;
        }
        k = k - n;
        int p = arr.length -1;
        while(k > 0 && p >= 0 ) {
            if(k >= 25) {
                arr[p] = 26;
                k = k -25;
                p--;
                continue;
            }
            arr[p] += k;
            k = k - k;
            p--;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < arr.length; i++) {
            char c = (char)(arr[i] + 96);
            sb.append(c);
        }
        return sb.toString();
    }



}
//leetcode submit region end(Prohibit modification and deletion)
