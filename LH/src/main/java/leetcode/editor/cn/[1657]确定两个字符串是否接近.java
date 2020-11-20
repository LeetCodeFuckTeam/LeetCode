package leetcode.editor.cn;//如果可以使用以下操作从一个字符串得到另一个字符串，则认为两个字符串 接近 ：
//
// 
// 操作 1：交换任意两个 现有 字符。
//
// 
// 例如，abcde -> aecdb 
// 
// 
// 操作 2：将一个 现有 字符的每次出现转换为另一个 现有 字符，并对另一个字符执行相同的操作。
// 
// 例如，aacabb -> bbcbaa（所有 a 转化为 b ，而所有的 b 转换为 a ） 
// 
// 
// 
//
// 你可以根据需要对任意一个字符串多次使用这两种操作。 
//
// 给你两个字符串，word1 和 word2 。如果 word1 和 word2 接近 ，就返回 true ；否则，返回 false 。 
//
// 
//
// 示例 1： 
//
// 
//输入：word1 = "abc", word2 = "bca"
//输出：true
//解释：2 次操作从 word1 获得 word2 。
//执行操作 1："abc" -> "acb"
//执行操作 1："acb" -> "bca"
// 
//
// 示例 2： 
//
// 
//输入：word1 = "a", word2 = "aa"
//输出：false
//解释：不管执行多少次操作，都无法从 word1 得到 word2 ，反之亦然。 
//
// 示例 3： 
//
// 
//输入：word1 = "cabbba", word2 = "abbccc"
//输出：true
//解释：3 次操作从 word1 获得 word2 。
//执行操作 1："cabbba" -> "caabbb"
//执行操作 2："caabbb" -> "baaccc"
//执行操作 2："baaccc" -> "abbccc"
// 
//
// 示例 4： 
//
// 
//输入：word1 = "cabbba", word2 = "aabbss"
//输出：false
//解释：不管执行多少次操作，都无法从 word1 得到 word2 ，反之亦然。 
//
// 
//
// 提示： 
//
// 
// 1 <= word1.length, word2.length <= 105 
// word1 和 word2 仅包含小写英文字母 
// 
// Related Topics 贪心算法


import java.util.Arrays;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution1657 {

    /**
     * 首先判断字符串长度是否等
     * 如果不相等再怎么交换顺序
     * 和字符转换也不可能相同
     *
     * 然后判断
     * 每个字符频率排序后判断是否相等
     *
     * 如ab在word1内频率分别为23
     * 那么ab在word2内频率分别为23或者32才能相等
     * 为了判断是否可以通过交换来获取相等
     * 设计出字频排序再比较  在判断之前先判断对应字符是否相互都包含
     * @param word1
     * @param word2
     * @return
     */
    public boolean closeStrings(String word1, String word2) {
        if(word1.length()!=word2.length()) {
            return false;
        }
        int[] hash1 = new int[26];
        int[] hash2 = new int[26];

        for (int i = 0; i < word1.length(); i++) {
            hash1[word1.charAt(i)-'a'] ++;
            hash2[word2.charAt(i)-'a'] ++;

        }
        for (int i = 0; i < hash1.length; i++) {
            if(hash1[i] == 0 || hash2[i] ==0) {
                if(hash1[i] == 0 && hash2[i] ==0) {
                    continue;
                }
                return false;
            }
        }
        //对字符出现次数排序   判断是否相等
        Arrays.sort(hash1);
        Arrays.sort(hash2);
        return Arrays.equals(hash1,hash2);
    }

}
//leetcode submit region end(Prohibit modification and deletion)
