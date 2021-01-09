package leetcode.editor.cn;//给你一个用字符数组 tasks 表示的 CPU 需要执行的任务列表。其中每个字母表示一种不同种类的任务。任务可以以任意顺序执行，并且每个任务都可以在 1 个
//单位时间内执行完。在任何一个单位时间，CPU 可以完成一个任务，或者处于待命状态。 
//
// 然而，两个 相同种类 的任务之间必须有长度为整数 n 的冷却时间，因此至少有连续 n 个单位时间内 CPU 在执行不同的任务，或者在待命状态。 
//
// 你需要计算完成所有任务所需要的 最短时间 。 
//
// 
//
// 示例 1： 
//
// 
//输入：tasks = ["A","A","A","B","B","B"], n = 2
//输出：8
//解释：A -> B -> (待命) -> A -> B -> (待命) -> A -> B
//     在本示例中，两个相同类型任务之间必须间隔长度为 n = 2 的冷却时间，而执行一个任务只需要一个单位时间，所以中间出现了（待命）状态。 
//
// 示例 2： 
//
// 
//输入：tasks = ["A","A","A","B","B","B"], n = 0
//输出：6
//解释：在这种情况下，任何大小为 6 的排列都可以满足要求，因为 n = 0
//["A","A","A","B","B","B"]
//["A","B","A","B","A","B"]
//["B","B","B","A","A","A"]
//...
//诸如此类
// 
//
// 示例 3： 
//
// 
//输入：tasks = ["A","A","A","A","A","A","B","C","D","E","F","G"], n = 2
//输出：16
//解释：一种可能的解决方案是：
//     A -> B -> C -> A -> D -> E -> A -> F -> G -> A -> (待命) -> (待命) -> A -> (待
//命) -> (待命) -> A
// 
//
// 
//
// 提示： 
//
// 
// 1 <= task.length <= 104 
// tasks[i] 是大写英文字母 
// n 的取值范围为 [0, 100] 
// 
// Related Topics 贪心算法 队列 数组


import java.util.Arrays;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution621 {
    public int leastInterval(char[] tasks, int n) {


        if (tasks==null || tasks.length==0){
            return 0;
        }
        int len = tasks.length;
        //统计任务的频率
        int[] counter = new int[26];
        for (int i = 0; i < len; i++) {
            counter[tasks[i]-'A']++;
        }
        //頻率最高的放在後面,貪心
        Arrays.sort(counter);
        //行數,貪心 將頻率最大的任務，排成一列，中間間隔 n
        //贪心策略，尽可能让cpu每个周期执行满任务，不要有空闲存在
        //使cpu利用率达到最高
        /*
            AAABBB
            n=2            这里要等待 2 个时间片，所以是两列
     一个cpu周期        A | B  N
     一个cpu周期        A | B  N
     一个cpu周期        A | B
         */
        int row = counter[25]-1;
        //總空間數
        int space = row*n;
        for (int i = 24; i >=0 ; i--) {
            //最後一行因為是執行完了，所以不計入空閒等待
            space-=Math.min(counter[i],row);
        }
        //其實嚴格來說，space是不會小於0的，應為行數是取的頻率最大的任務
        //等於0，就是中間沒有空閒時間分配，cpu達到最大利用率
        return space>0?space+tasks.length:tasks.length;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
