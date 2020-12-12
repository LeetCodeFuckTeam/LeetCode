package leetcode.editor.cn;//给定一个字符串S，检查是否能重新排布其中的字母，使得两相邻的字符不同。
//
// 若可行，输出任意可行的结果。若不可行，返回空字符串。 
//
// 示例 1: 
//
// 
//输入: S = "aab"
//输出: "aba"
// 
//
// 示例 2: 
//
// 
//输入: S = "aaab"
//输出: ""
// 
//
// 注意: 
//
// 
// S 只包含小写字母并且长度在[1, 500]区间内。 
// 
// Related Topics 堆 贪心算法 排序 字符串


//leetcode submit region begin(Prohibit modification and deletion)
class Solution767 {
    /**
     * 统计每个字符的个数
     * 然后找出个数最多的字符
     * 判断字符数是否大于(length+1)/2
     * 假设字符串的长度为 nnn，如果可以重新排布成相邻的字母都不相同的字符串，每个字母最多出现多少次？
     *
     * 当 nnn 是偶数时，有 n/2n/2n/2 个偶数下标和 n/2n/2n/2 个奇数下标，因此每个字母的出现次数都不能超过 n/2n/2n/2 次，否则出现次数最多的字母一定会出现相邻。
     *
     * 当 nnn 是奇数时，由于共有 (n+1)/2(n+1)/2(n+1)/2 个偶数下标，因此每个字母的出现次数都不能超过 (n+1)/2(n+1)/2(n+1)/2 次，否则出现次数最多的字母一定会出现相邻。
     *
     * 由于当 nnn 是偶数时，在整数除法下满足 n/2n/2n/2 和 (n+1)/2(n+1)/2(n+1)/2 相等，因此可以合并 nnn 是偶数与 nnn 是奇数的情况：如果可以重新排布成相邻的字母都不相同的字符串，每个字母最多出现 (n+1)/2(n+1)/2(n+1)/2 次。
     * 如果大于必将有相邻的字符
     *
     * 然后再用新的字符数组去构建新的字符串
     * 从出现次数最多的字符开始
     * 如果用完之后开始用其他的字符填充
     * 直到奇数位全部填充完毕，再依次填充偶数位
     * @param S
     * @return
     */
    public static String reorganizeString(String S) {

        if (S.length() <= 0|| S == null) {
            return "";
        }

        char[] chars = S.toCharArray();
        int len = chars.length;
        int[] charCount = new int[26];
        for (int i = 0; i < chars.length; i++) {
            charCount[chars[i] - 'a']++;
        }
        int maxCount = 0;
        char maxChar = 'a';
        int maxIndex = 0;
        int threshold = (len + 1)/2;
        for (int i = 0; i < charCount.length; i++) {
            if(charCount[i] > maxCount) {
                maxCount = charCount[i];
                maxChar = (char) (i + 'a');
                maxIndex = i;
                if(maxCount > threshold) {
                    return "";
                }
            }
        }
        /**
         * 从奇数位开始进行字符填充
         */
        char[] resultCharArray = new char[len];
        int curIndex = 0;
        while(charCount[maxIndex]-->0) {
            //奇数位填充
            resultCharArray[curIndex] = maxChar;
            curIndex += 2;
        }
        /**
         * 将其余字符串插空填充
         */
        for (int i = 0; i < 26; i++) {
            while (charCount[i]-- > 0) {
                if(curIndex>=len) {
                    curIndex = 1;
                }
                    resultCharArray[curIndex] = (char)(i + 'a');
                    curIndex += 2;
            }
        }

        return new String(resultCharArray);

    }








  }
//leetcode submit region end(Prohibit modification and deletion)
