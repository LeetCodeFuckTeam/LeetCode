package leetcode.editor.cn;//给你一个区间列表，请你删除列表中被其他区间所覆盖的区间。
//
// 只有当 c <= a 且 b <= d 时，我们才认为区间 [a,b) 被区间 [c,d) 覆盖。 
//
// 在完成所有删除操作后，请你返回列表中剩余区间的数目。 
//
// 
//
// 示例： 
//
// 
//输入：intervals = [[1,4],[3,6],[2,8]]
//输出：2
//解释：区间 [3,6] 被区间 [2,8] 覆盖，所以它被删除了。
// 
//
// 
//
// 提示： 
//
// 
// 1 <= intervals.length <= 1000 
// 0 <= intervals[i][0] < intervals[i][1] <= 10^5 
// 对于所有的 i != j：intervals[i] != intervals[j] 
// 
// Related Topics 贪心算法 排序 Line Sweep


import java.util.Arrays;
import java.util.Comparator;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution1288 {

    /**
     * 对所有区间进行排序
     * 按照起点相同终点大的排在前面
     * 即按照区间的长度进行降序排列
     *
     * 如果起点不同的区间
     * 按照起点小的排在前面的策略
     *
     * 然后将后面的区间和它前一个区间进行对比看前一个是否包含后一个区间
     * @param intervals
     * @return
     */
    public int removeCoveredIntervals(int[][] intervals) {

        int result = intervals.length;
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] == o2[0] ? o2[1] - o1[1] : o1[0] - o2[0];
            }
        });
        int[] pre  = intervals[0];
        for (int i = 1; i < intervals.length; i++) {
            int[] cur = intervals[i];
            if(cur[0] >= pre[0] && cur[1] <= pre[1]) {
                result--;
            }else {
                pre = intervals[i];
            }
        }
        return result;




    }
}
//leetcode submit region end(Prohibit modification and deletion)
