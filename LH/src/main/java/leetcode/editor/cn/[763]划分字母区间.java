package leetcode.editor.cn;//字符串 S 由小写字母组成。我们要把这个字符串划分为尽可能多的片段，同一字母最多出现在一个片段中。返回一个表示每个字符串片段的长度的列表。
//
// 
//
// 示例： 
//
// 
//输入：S = "ababcbacadefegdehijhklij"
//输出：[9,7,8]
//解释：
//划分结果为 "ababcbaca", "defegde", "hijhklij"。
//每个字母最多出现在一个片段中。
//像 "ababcbacadefegde", "hijhklij" 的划分是错误的，因为划分的片段数较少。
// 
//
// 
//
// 提示： 
//
// 
// S的长度在[1, 500]之间。 
// S只包含小写字母 'a' 到 'z' 。 
// 
// Related Topics 贪心算法 双指针


import java.util.ArrayList;
import java.util.List;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution763 {

    /**
     * 要求切分出包含字母的最短的程序段
     * 贪心算法
     * 证明：最小的程序段无法再切分否则一个字母回出现在多个分段中
     *
     * @param S
     * @return
     */
    public List<Integer> partitionLabels(String S) {

        int[] last = new int[26];
        //遍历保存每一个字母最后出现的位置
        char[] chars = S.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            last[chars[i]-'a'] = i;
        }
        List result = new ArrayList();
        int start = 0;
        int end = 0;
        for (int i = 0; i < chars.length; i++) {
            end = Math.max(end,last[chars[i]-'a']);
            if(i == end) {
                result.add(end - start + 1);
                start = end + 1;
            }
        }
        return result;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
