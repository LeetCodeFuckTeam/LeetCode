package leetcode.editor.cn;//你的初始 能量 为 P，初始 分数 为 0，只有一包令牌 tokens 。其中 tokens[i] 是第 i 个令牌的值（下标从 0 开始）。
//
// 令牌可能的两种使用方法如下： 
//
// 
// 如果你至少有 token[i] 点 能量 ，可以将令牌 i 置为正面朝上，失去 token[i] 点 能量 ，并得到 1 分 。 
// 如果我们至少有 1 分 ，可以将令牌 i 置为反面朝上，获得 token[i] 点 能量 ，并失去 1 分 。 
// 
//
// 每个令牌 最多 只能使用一次，使用 顺序不限 ，不需 使用所有令牌。 
//
// 在使用任意数量的令牌后，返回我们可以得到的最大 分数 。 
//
// 
//
// 
// 
//
// 示例 1： 
//
// 
//输入：tokens = [100], P = 50
//输出：0
//解释：无法使用唯一的令牌，因为能量和分数都太少了。 
//
// 示例 2： 
//
// 
//输入：tokens = [100,200], P = 150
//输出：1
//解释：令牌 0 正面朝上，能量变为 50，分数变为 1 。不必使用令牌 1 ，因为你无法使用它来提高分数。 
//
// 示例 3： 
//
// 
//输入：tokens = [100,200,300,400], P = 200
//输出：2
//解释：按下面顺序使用令牌可以得到 2 分：
//1. 令牌 0 正面朝上，能量变为 100 ，分数变为 1
//2. 令牌 3 正面朝下，能量变为 500 ，分数变为 0
//3. 令牌 1 正面朝上，能量变为 300 ，分数变为 1
//4. 令牌 2 正面朝上，能量变为 0 ，分数变为 2 
//
// 
//
// 提示： 
//
// 
// 0 <= tokens.length <= 1000 
// 0 <= tokens[i], P < 104 
// 
// Related Topics 贪心算法 排序 双指针




import java.util.Arrays;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution948 {


    /**
     * 贪心算法
     * 令牌按照从小到达顺序排序
     * 小的令牌消耗能量来换取更多的分数
     * 大的令牌用分数来换取能量
     *
     * 从小令牌开始知道能量消耗完
     * 然后通过大令牌换取能量
     * 让小令牌换取分数
     *
     * @param tokens
     * @param P
     * @return
     */
    public static int bagOfTokensScore(int[] tokens, int P) {

        if(tokens.length == 0) {
            return 0;
        }
        if(tokens.length == 1) {
            if(tokens[0] > P) {
                return 0;
            }else {
                return 1;
            }
        }

        Arrays.sort(tokens);
        if (tokens[0] > P) {
            return 0;
        }
        int score = 0;
        int left = 0;
        int right = tokens.length-1;
        while (left <= right) {
            while (left <= right && P >= tokens[left]) {

                P = P -tokens[left];
                score++;
                left++;
            }
            if (left >= right) {
                //指针此时指向的元素只能被使用一次当然选择加分数
                //看最后一个是否能够满足能量
                //如果满足则加分数，如果不满足则直接返回结果不去换取能量
                return score;
            }
            if(score > 0) {
                P = P + tokens[right];
                right--;
                score--;
            }


        }
        return score;
    }

    public static void main(String args[]){
        bagOfTokensScore(new int[]{100,200},150);
        }




}
//leetcode submit region end(Prohibit modification and deletion)
