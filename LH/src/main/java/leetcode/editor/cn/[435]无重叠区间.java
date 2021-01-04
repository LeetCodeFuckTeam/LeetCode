package leetcode.editor.cn;//给定一个区间的集合，找到需要移除区间的最小数量，使剩余区间互不重叠。
//
// 注意: 
//
// 
// 可以认为区间的终点总是大于它的起点。 
// 区间 [1,2] 和 [2,3] 的边界相互“接触”，但没有相互重叠。 
// 
//
// 示例 1: 
//
// 
//输入: [ [1,2], [2,3], [3,4], [1,3] ]
//
//输出: 1
//
//解释: 移除 [1,3] 后，剩下的区间没有重叠。
// 
//
// 示例 2: 
//
// 
//输入: [ [1,2], [1,2], [1,2] ]
//
//输出: 2
//
//解释: 你需要移除两个 [1,2] 来使剩下的区间没有重叠。
// 
//
// 示例 3: 
//
// 
//输入: [ [1,2], [2,3] ]
//
//输出: 0
//
//解释: 你不需要移除任何区间，因为它们已经是无重叠的了。
// 
// Related Topics 贪心算法


import java.util.Arrays;
import java.util.Comparator;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution435 {
    /**
     * 无重叠区间
     * 思路：
     * 将所有区间按照右边界从小到大排序
     * 选择第一个区间
     * 第二个区间的左边界必须大于第一个区间的右边界
     * 贪心选择策略 删除尽可能少的区间  尽可能保留多的区间
     *
     * @param intervals
     * @return
     */
    public int eraseOverlapIntervals(int[][] intervals) {
        if(intervals.length == 0) {
            return 0;
        }
        int n = intervals.length;


        //将区间按照有边界从小到大排序
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[1] - o2[1];
            }
        });

        int right = intervals[0][1];
        int result = 1;
        for (int i = 1; i < n; i++) {
            if(intervals[i][0] >= right) {
                right = intervals[i][1];
                ++result;
            }
        }
        return n - result;

    }
}
//leetcode submit region end(Prohibit modification and deletion)
