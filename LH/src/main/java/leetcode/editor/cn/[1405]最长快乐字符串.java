package leetcode.editor.cn;//如果字符串中不含有任何 'aaa'，'bbb' 或 'ccc' 这样的字符串作为子串，那么该字符串就是一个「快乐字符串」。
//
// 给你三个整数 a，b ，c，请你返回 任意一个 满足下列全部条件的字符串 s： 
//
// 
// s 是一个尽可能长的快乐字符串。 
// s 中 最多 有a 个字母 'a'、b 个字母 'b'、c 个字母 'c' 。 
// s 中只含有 'a'、'b' 、'c' 三种字母。 
// 
//
// 如果不存在这样的字符串 s ，请返回一个空字符串 ""。 
//
// 
//
// 示例 1： 
//
// 输入：a = 1, b = 1, c = 7
//输出："ccaccbcc"
//解释："ccbccacc" 也是一种正确答案。
// 
//
// 示例 2： 
//
// 输入：a = 2, b = 2, c = 1
//输出："aabbc"
// 
//
// 示例 3： 
//
// 输入：a = 7, b = 1, c = 0
//输出："aabaa"
//解释：这是该测试用例的唯一正确答案。 
//
// 
//
// 提示： 
//
// 
// 0 <= a, b, c <= 100 
// a + b + c > 0 
// 
// Related Topics 贪心算法 动态规划


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution1405 {
    //a个a，b个b,c个c

    /**
     * 将abc和它最多能够放入的数目进行排序
     * 构造字符串
     * 连续两个数目最多的字符后必须是其他的字符
     * 如果数目最多的字符用完直接重新排序
     *
     * @param a
     * @param b
     * @param c
     * @return
     */
    public String longestDiverseString(int a, int b, int c) {
        List<Entry> list = new ArrayList<>();
        list.add(new Entry('a', a));
        list.add(new Entry('b', b));
        list.add(new Entry('c', c));
        //把个数多的字母放在前面
        Collections.sort(list, (e1, e2) -> e2.num - e1.num);
        StringBuilder res = new StringBuilder();
        while(list.get(0).num > 0){
            if(res.length() < 2){
                res.append(list.get(0).c);
                list.get(0).num --;
                //如果res的最后两个字母和最多个数的字母是一样的，则取后面的字母放进去
            }else if(res.charAt(res.length() - 1) == list.get(0).c &&
                    res.charAt(res.length() - 2) == list.get(0).c){
                if(list.get(1).num == 0){
                    break;
                }
                res.append(list.get(1).c);
                list.get(1).num --;
            }else{
                res.append(list.get(0).c);
                list.get(0).num --;
            }
            Collections.sort(list, (e1, e2) -> e2.num - e1.num);
        }
        return res.toString();
    }
}
class Entry{
    char c;
    int num;
    public Entry(char c, int num){
        this.c = c;
        this.num = num;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
