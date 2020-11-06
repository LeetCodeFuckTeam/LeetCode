package leetcode.editor.cn;

//假设你是一位很棒的家长，想要给你的孩子们一些小饼干。但是，每个孩子最多只能给一块饼干。
//
// 对每个孩子 i，都有一个胃口值 g[i]，这是能让孩子们满足胃口的饼干的最小尺寸；并且每块饼干 j，都有一个尺寸 s[j] 。如果 s[j] >= g[i
//]，我们可以将这个饼干 j 分配给孩子 i ，这个孩子会得到满足。你的目标是尽可能满足越多数量的孩子，并输出这个最大数值。 
// 
//
// 示例 1: 
//
// 
//输入: g = [1,2,3], s = [1,1]
//输出: 1
//解释: 
//你有三个孩子和两块小饼干，3个孩子的胃口值分别是：1,2,3。
//虽然你有两块小饼干，由于他们的尺寸都是1，你只能让胃口值是1的孩子满足。
//所以你应该输出1。
// 
//
// 示例 2: 
//
// 
//输入: g = [1,2], s = [1,2,3]
//输出: 2
//解释: 
//你有两个孩子和三块小饼干，2个孩子的胃口值分别是1,2。
//你拥有的饼干数量和尺寸都足以让所有孩子满足。
//所以你应该输出2.
// 
//
// 
//
// 提示： 
//
// 
// 1 <= g.length <= 3 * 104 
// 0 <= s.length <= 3 * 104 
// 1 <= g[i], s[j] <= 231 - 1 
// 
// Related Topics 贪心算法


import java.util.Arrays;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution455 {
    /**
     * 对每个孩子的胃口和饼干的尺寸进行排序
     * 使用双指针
     * 一个指针从胃口小的孩子开始，另一个指针从饼干的尺寸开始
     * 饼干尺寸从小向大，如果饼干不能满足当前孩子的胃口
     * 那么一定不能满足后面的孩子（后面孩子胃口更大）直接丢弃
     * 贪心目的为了满足更多的孩子（所以从胃口小的孩子开始
     * 某个孩子能用尺寸小的糖果满足则没必要用大的）
     * @param g
     * @param s
     * @return
     */
    public int findContentChildren(int[] g, int[] s) {
        //孩子胃口，饼干排序
        Arrays.sort(g);
        Arrays.sort(s);
        int child = 0;
        int cookie = 0;
        while(child<g.length&&cookie<s.length) {
            if(g[child] <= s[cookie]) {
                child++;
                cookie++;
            }else{
                cookie++;
            }
        }
        return child;

    }
}
//leetcode submit region end(Prohibit modification and deletion)
