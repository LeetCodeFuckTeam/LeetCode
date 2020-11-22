package leetcode.editor.cn;//
//
// 如上图所示，电影院的观影厅中有 n 行座位，行编号从 1 到 n ，且每一行内总共有 10 个座位，列编号从 1 到 10 。 
//
// 给你数组 reservedSeats ，包含所有已经被预约了的座位。比如说，researvedSeats[i]=[3,8] ，它表示第 3 行第 8 个座
//位被预约了。 
//
// 请你返回 最多能安排多少个 4 人家庭 。4 人家庭要占据 同一行内连续 的 4 个座位。隔着过道的座位（比方说 [3,3] 和 [3,4]）不是连续的座
//位，但是如果你可以将 4 人家庭拆成过道两边各坐 2 人，这样子是允许的。 
//
// 
//
// 示例 1： 
//
// 
//
// 输入：n = 3, reservedSeats = [[1,2],[1,3],[1,8],[2,6],[3,1],[3,10]]
//输出：4
//解释：上图所示是最优的安排方案，总共可以安排 4 个家庭。蓝色的叉表示被预约的座位，橙色的连续座位表示一个 4 人家庭。
// 
//
// 示例 2： 
//
// 输入：n = 2, reservedSeats = [[2,1],[1,8],[2,6]]
//输出：2
// 
//
// 示例 3： 
//
// 输入：n = 4, reservedSeats = [[4,3],[1,4],[4,6],[1,7]]
//输出：4
// 
//
// 
//
// 提示： 
//
// 
// 1 <= n <= 10^9 
// 1 <= reservedSeats.length <= min(10*n, 10^4) 
// reservedSeats[i].length == 2 
// 1 <= reservedSeats[i][0] <= n 
// 1 <= reservedSeats[i][1] <= 10 
// 所有 reservedSeats[i] 都是互不相同的。 
// 
// Related Topics 贪心算法 数组


import java.util.HashMap;
import java.util.Map;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution1386 {

    /*
       贪心：尽可能的从最左边算起

       当某行没有被任何一个人坐时，那么必定可以坐 2 个家庭
       那么我们就查找那些坐了人的行，根据题意，我们只需要遍历 [2,3,4,5] 和 [4,5,6,7] 和 [6,7,8,9] 位置即可
       当查找完所有坐过人的行的家庭数 res，那么我们直接求没有坐过人的行的家庭数 (n - map.size()) * 2（因为 map 存储的是有坐人的行）
       两者相加返回，即 return res + (n - map.size()) * 2

       不能直接遍历，因为最大的行数 n = 1000000000，如果从 [1, n] 遍历那么必定超时，因此直接不遍历没坐人的行
       */
    public int maxNumberOfFamilies(int n, int[][] reservedSeats) {
        Map<Integer,boolean[]> map = new HashMap<>();
        for (int i = 0; i < reservedSeats.length; i++) {
            if(!map.containsKey(reservedSeats[i][0])) {
                map.put(reservedSeats[i][0],new boolean[11]);

            }
            map.get(reservedSeats[i][0])[reservedSeats[i][1]] = true;
        }
        int result = 0;
        for (Map.Entry<Integer,boolean[]> entry: map.entrySet()) {
            Integer key = entry.getKey();
            boolean[] value = entry.getValue();
            if(isOK(value, 2, 5)){
                //如果 [2,3,4,5] 位置可以坐人，那么查找 [6,7,8,9] 是否可以坐人
                result += isOK(value, 6, 9) ? 2 : 1;
            }else{
                if(isOK(value, 4, 7)){
                    //如果 [4,5,6,7] 可以坐人，那么 [6,7,8,9] 不能坐人
                    result++;
                }else{
                    //否则 判断 [6,7,8,9] 是否可以坐人
                    result += isOK(value, 6, 9) ? 1 : 0;
                }
            }


        }

        return result + (n - map.size()) * 2;

    }
    public boolean isOK(boolean[] value,int start,int end) {
        for (int i = start; i <= end ; i++) {
            if(value[i] == true) {
                return false;
            }

        }
        return true;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
