package leetcode.editor.cn;//给出一个无重叠的 ，按照区间起始端点排序的区间列表。
//
// 在列表中插入一个新的区间，你需要确保列表中的区间仍然有序且不重叠（如果有必要的话，可以合并区间）。 
//
// 
//
// 示例 1： 
//
// 输入：intervals = [[1,3],[6,9]], newInterval = [2,5]
//输出：[[1,5],[6,9]]
// 
//
// 示例 2： 
//
// 输入：intervals = [[1,2],[3,5],[6,7],[8,10],[12,16]], newInterval = [4,8]
//输出：[[1,2],[3,10],[12,16]]
//解释：这是因为新的区间 [4,8] 与 [3,5],[6,7],[8,10] 重叠。
// 
//
// 
//
// 注意：输入类型已在 2019 年 4 月 15 日更改。请重置为默认代码定义以获取新的方法签名。 
// Related Topics 排序 数组


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution57 {
    /**
     * 根据题目给出的区间都是按照区间左端进行排序的
     * 而且给定的区间不含有相互重叠的不部分
     * 遍历intervals
     * 判断和newInterval的左边界和有边界的关系
     * 如果interval的右端<newInterval的左端
     * 那么直接加入结果集
     * 如果interval的左端>newInterval的右端
     * 直接加入结果集
     * 如果产生交集
     * 将interval和newInterval的并集更新为新的newInterval
     *
     * 关键点 ：
     * 何时将newInterval加入结果集呢？
     * interval遍历如果遇到第一个和newinterval无交集的区间时
     * 那么后面的区间一定不会产生交集，此时将newInterval加入
     * 还有如果interval全部遍历完还没有将newInterval加入结果集  就加入
     * @param intervals
     * @param newInterval
     * @return
     */
    public int[][] insert(int[][] intervals, int[] newInterval) {
        boolean placed = false;
        int begin = newInterval[0];
        int end = newInterval[1];
        List<int[]> resultList = new ArrayList<>();
        for (int[] interval : intervals) {
            if(interval[0] > end) {
                if(!placed) {
                    resultList.add(new int[]{begin,end});
                    placed = true;
                }
                resultList.add(interval);
            }else if(interval[1] < begin) {
                resultList.add(interval);
            }else {
                //将begin和end更新为newInterval和interval并集
                begin = Math.min(begin,interval[0]);
                end = Math.max(end,interval[1]);
            }
        }
        if(!placed) {
            resultList.add(new int[]{begin,end});
        }
        int[][] result = new int[resultList.size()][2];
        for (int i = 0; i < resultList.size(); i++) {
            int[] ints = resultList.get(i);
            result[i] = ints;
        }
        return result;


    }

}
//leetcode submit region end(Prohibit modification and deletion)
