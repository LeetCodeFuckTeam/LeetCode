package leetcode.editor.cn;//给你一个仅由大写英文字母组成的字符串，你可以将任意位置上的字符替换成另外的字符，总共可最多替换 k 次。在执行上述操作后，找到包含重复字母的最长子串的长度。
// 
//
// 注意：字符串长度 和 k 不会超过 104。 
//
// 
//
// 示例 1： 
//
// 
//输入：s = "ABAB", k = 2
//输出：4
//解释：用两个'A'替换为两个'B',反之亦然。
// 
//
// 示例 2： 
//
// 
//输入：s = "AABABBA", k = 1
//输出：4
//解释：
//将中间的一个'A'替换为'B',字符串变为 "AABBBBA"。
//子串 "BBBB" 有最长重复字母, 答案为 4。
// 
// Related Topics 双指针 Sliding Window


//leetcode submit region begin(Prohibit modification and deletion)
class Solution424 {
    /**
     * 双指针滑动窗口
     *
     *
     * @param s
     * @param k
     * @return
     */
    public int characterReplacement(String s, int k) {
        if(s.length() <= 1) {
            return s.length();
        }
        char[] chars = s.toCharArray();
        int left = 0,right = 0;
        int maxCount = 0;
        int[] frequency = new int[26];//保存当前窗口内字母最多出现的个数
        int result = 0;
        while (right < chars.length) {
            //计算每个元素出现频率
            frequency[chars[right] - 'A']++;
            //计算当前有效的连续的最长重复字符长度
            maxCount = Math.max(maxCount,frequency[chars[right] - 'A']);
            right++;
            //如果当前滑动窗口大于最长重复字符长度加上可以替换的字符个数，就将滑动窗口的左边界右移动继续判断
            if(right - left > maxCount + k) {
                frequency[chars[left] - 'A']--;
                left++;
            }
            //最终结果就是滑动窗口的最大长度
            result = Math.max(result,right - left);

        }
        return result;

    }
}
//leetcode submit region end(Prohibit modification and deletion)
