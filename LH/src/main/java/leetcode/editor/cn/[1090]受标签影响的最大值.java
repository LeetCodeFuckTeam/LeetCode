package leetcode.editor.cn;//我们有一个项的集合，其中第 i 项的值为 values[i]，标签为 labels[i]。
//
// 我们从这些项中选出一个子集 S，这样一来： 
//
// 
// |S| <= num_wanted 
// 对于任意的标签 L，子集 S 中标签为 L 的项的数目总满足 <= use_limit。 
// 
//
// 返回子集 S 的最大可能的 和。 
//
// 
//
// 示例 1： 
//
// 输入：values = [5,4,3,2,1], labels = [1,1,2,2,3], num_wanted = 3, use_limit = 1
//输出：9
//解释：选出的子集是第一项，第三项和第五项。
// 
//
// 示例 2： 
//
// 输入：values = [5,4,3,2,1], labels = [1,3,3,3,2], num_wanted = 3, use_limit = 2
//输出：12
//解释：选出的子集是第一项，第二项和第三项。
// 
//
// 示例 3： 
//
// 输入：values = [9,8,8,7,6], labels = [0,0,0,1,1], num_wanted = 3, use_limit = 1
//输出：16
//解释：选出的子集是第一项和第四项。
// 
//
// 示例 4： 
//
// 输入：values = [9,8,8,7,6], labels = [0,0,0,1,1], num_wanted = 3, use_limit = 2
//输出：24
//解释：选出的子集是第一项，第二项和第四项。
// 
//
// 
//
// 提示： 
//
// 
// 1 <= values.length == labels.length <= 20000 
// 0 <= values[i], labels[i] <= 20000 
// 1 <= num_wanted, use_limit <= values.length 
// 
// Related Topics 贪心算法 哈希表


import java.util.Arrays;
import java.util.Comparator;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {

    /**
     * 将数值和对应的标签使用限制进行组合对应
     * 然后将这些映射按照数值大小从大到小排序
     * 循环遍历
     * 子集中相同标签数的数目小于userlimit
     * 用一个count数组专门存储元素的使用个数
     *
     *
     * @param values
     * @param labels
     * @param num_wanted
     * @param use_limit
     * @return
     */
    public static int largestValsFromLabels(int[] values, int[] labels, int num_wanted, int use_limit) {
        int[][] temp = new int[values.length][2];
        for (int i = 0; i < values.length; i++) {
            temp[i][0] = values[i];
            temp[i][1] = labels[i];
        }
    Arrays.sort(temp, new Comparator<int[]>() {
        @Override
        public int compare(int[] o1, int[] o2) {
            return o2[0] - o1[0];
        }
    });
    int c = 0;
    int sum = 0;
    int[] count = new int[20001];
        for (int i = 0; i < temp.length; i++) {
            if(count[temp[i][1]] == use_limit) {
                continue;
            }
            if (c == num_wanted) {
                return sum;
            }
            sum = sum + temp[i][0];
            count[temp[i][1]] ++;
            c++;
        }

        return sum;
    }
    public static void main(String args[]){
        int[] value = {5,4,3,2,1};
        int[] label = {1,1,2,2,3};
        System.out.println(largestValsFromLabels(value,label,3,1));




        }
}
//leetcode submit region end(Prohibit modification and deletion)
