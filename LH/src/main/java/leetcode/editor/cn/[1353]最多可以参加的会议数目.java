package leetcode.editor.cn;//给你一个数组 events，其中 events[i] = [startDayi, endDayi] ，表示会议 i 开始于 startDayi ，结束于 e
//ndDayi 。 
//
// 你可以在满足 startDayi <= d <= endDayi 中的任意一天 d 参加会议 i 。注意，一天只能参加一个会议。 
//
// 请你返回你可以参加的 最大 会议数目。 
//
// 
//
// 示例 1： 
//
// 
//
// 输入：events = [[1,2],[2,3],[3,4]]
//输出：3
//解释：你可以参加所有的三个会议。
//安排会议的一种方案如上图。
//第 1 天参加第一个会议。
//第 2 天参加第二个会议。
//第 3 天参加第三个会议。
// 
//
// 示例 2： 
//
// 输入：events= [[1,2],[2,3],[3,4],[1,2]]
//输出：4
// 
//
// 示例 3： 
//
// 输入：events = [[1,4],[4,4],[2,2],[3,4],[1,1]]
//输出：4
// 
//
// 示例 4： 
//
// 输入：events = [[1,100000]]
//输出：1
// 
//
// 示例 5： 
//
// 输入：events = [[1,1],[1,2],[1,3],[1,4],[1,5],[1,6],[1,7]]
//输出：7
// 
//
// 
//
// 提示： 
//
// 
// 1 <= events.length <= 10^5 
// events[i].length == 2 
// 1 <= events[i][0] <= events[i][1] <= 10^5 
// 
// Related Topics 贪心算法 排序 线段树


import java.util.*;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution1353 {
    /**
     * 贪心算法
     * 和活动安排问题相似
     * 先按照会议的结束时间从小到大排序
     * 如果结束时间相等的话开始时间早的排在前面
     *
     * @param events
     * @return
     */
    public static int maxEvents(int[][] events) {
//
//        Arrays.sort(events, new Comparator<int[]>() {
//            @Override
//            public int compare(int[] o1, int[] o2) {
//                if (o1[1] != o2[1]) {
//                    //按照结束时间递增排序
//                    return o1[1] - o2[1];
//                }else {
//                    //按照开始时间递增排序
//                    return o1[0] - o2[0];
//                }
//            }
//        });
//
//        int result =0;
//        int max = 0;
//        for (int[] event : events) {
//            if(max <event[1]) {
//                max = event[1];
//            }
//        }
//        boolean day[] = new boolean[max+1];
//        for (int[] event : events) {
//            for (int i = event[0]; i <= event[1]; i++) {
//                if (day[i] == false) {
//                    day[i] = true;
//                    result++;
//                    break;
//                }
//
//            }
//        }
//
//            return result;
//
//        Set<Integer> set = new HashSet<>();
//        Arrays.sort(events, (first, second) -> first[1]==second[1]?
//                first[0]-second[0]:first[1]-second[1]);
//
//        for(int[] event: events) {
//            for(int i = event[0]; i<=event[1]; i++)
//                if(set.add(i)) break;
//        }
//        return set.size();


        /**
         * 在区间内只要有一天空闲就可以参加会议
         * 先将所有会议区间按照开始时间递增排序
         * 从第一天开始  逐天往后寻找是否有合适的会议
         * 从第一天开始 将开始为同一天的会议添加到优先队列
         * 优先队列是按照结束时间越早而优先
         * 然后在优先队列中寻找会议就行
         * 只要会议的结束时间大于或等于就可以当天参加会议   然后
         * 继续遍历后面的每一天，安排会议到后面的每一天
         */

        Arrays.sort(events,((o1, o2) ->o1[0]-o2[0]));//按开始时间排序
        PriorityQueue<int[]> pq=new PriorityQueue<>(((o1, o2) -> o1[1]-o2[1]));//按结束时间排序
        int res=0,i=0,day=1;
        while (!pq.isEmpty()||i<events.length)
        {
            while (i<events.length&&events[i][0]==day)//将同一开始时间的入队
            {
                pq.offer(events[i]);
                i++;
            }
            while (!pq.isEmpty())//找出吻合当前day的结束时间
            {
                int[] temp=pq.poll();
                if(temp[1]>=day)
                {
                    res++;
                    break;
                }

            }
            day++;//查找下一天
        }
        return res;
    }

    public static void main(String args[]){
        int[][] nums = {{1,2},{2,3},{3,4}};
        maxEvents(nums);
        }
}
//leetcode submit region end(Prohibit modification and deletion)
