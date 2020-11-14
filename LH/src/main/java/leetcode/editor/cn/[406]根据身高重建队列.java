package leetcode.editor.cn;//假设有打乱顺序的一群人站成一个队列。 每个人由一个整数对(h, k)表示，其中h是这个人的身高，k是排在这个人前面且身高大于或等于h的人数。 编写一个算法来
//重建这个队列。 
//
// 注意： 
//总人数少于1100人。 
//
// 示例 
//
// 
//输入:
//[[7,0], [4,4], [7,1], [5,0], [6,1], [5,2]]
//
//输出:
//[[5,0], [7,0], [5,2], [6,1], [4,4], [7,1]]
// 
// Related Topics 贪心算法


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution406 {

    /**
     * 对所有人先按照身高再按照位置进行排队
     *
     * @param people
     * @return
     */
    public int[][] reconstructQueue(int[][] people) {
        Arrays.sort(people, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if(o1[0] == o2[0]) {
                    //升高相等则按照位置排序
                    return o1[1] - o2[1];
                }else {
                    return o2[0] - o1[0]; //身高从大到小
                }
            }
        });

        List<int[]> list = new ArrayList<>();
        for (int[] person : people) {
            list.add(person[1],person);
        }

        int result[][] = new int[people.length][2];

        for (int i = 0; i < list.size(); i++) {
            result[i] = list.get(i);
        }

        return result;


    }
}
//leetcode submit region end(Prohibit modification and deletion)
