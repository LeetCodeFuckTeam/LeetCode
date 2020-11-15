package leetcode.editor.cn;//给你一个整数数组 arr 和一个整数 k ，其中数组长度是偶数，值为 n 。
//
// 现在需要把数组恰好分成 n / 2 对，以使每对数字的和都能够被 k 整除。 
//
// 如果存在这样的分法，请返回 True ；否则，返回 False 。 
//
// 
//
// 示例 1： 
//
// 输入：arr = [1,2,3,4,5,10,6,7,8,9], k = 5
//输出：true
//解释：划分后的数字对为 (1,9),(2,8),(3,7),(4,6) 以及 (5,10) 。
// 
//
// 示例 2： 
//
// 输入：arr = [1,2,3,4,5,6], k = 7
//输出：true
//解释：划分后的数字对为 (1,6),(2,5) 以及 (3,4) 。
// 
//
// 示例 3： 
//
// 输入：arr = [1,2,3,4,5,6], k = 10
//输出：false
//解释：无法在将数组中的数字分为三对的同时满足每对数字和能够被 10 整除的条件。
// 
//
// 示例 4： 
//
// 输入：arr = [-10,10], k = 2
//输出：true
// 
//
// 示例 5： 
//
// 输入：arr = [-1,1,-2,2,-3,3,-4,4], k = 3
//输出：true
// 
//
// 
//
// 提示： 
//
// 
// arr.length == n 
// 1 <= n <= 10^5 
// n 为偶数 
// -10^9 <= arr[i] <= 10^9 
// 1 <= k <= 10^5 
// 
// Related Topics 贪心算法 数组 数学


//leetcode submit region begin(Prohibit modification and deletion)
class Solution1497 {

    /**
     * arr数组对是否存在可以被k整除
     * 用数组的每个元素去mod k
     * 产生的余数一定是再0到k之间
     * 判断余数为0的元素是否为对数
     * 说明有两对元素都是k的整数倍
     *
     * 如果k为偶数情况下
     * 余数为k/2的个数也应该数目为偶数
     * 任意余数i的个数加上k-i应该为偶数
     *
     * 如果k为奇数情况下
     * 任意余数i的个数加上k-i应该为偶数
     *
     * @param arr
     * @param k
     * @return
     */
    public boolean canArrange(int[] arr, int k) {
        int yushu[]=new int[k];
        for(int i=0;i<arr.length;i++){
            int a=arr[i]%k<0?arr[i]%k+k:arr[i]%k;
            yushu[a]++;
        }
        if(yushu[0]%2==1){return false;}
        if(k%2==0){
           // if(yushu[k/2]%2==1){return false;}
            for(int i=1;i<k/2;i++){
                if(yushu[i]!=yushu[k-i]){return false;}
            }
        }
        else{
            for(int i=1;i<=k/2;i++){
                if(yushu[i]!=yushu[k-i]){return false;}
            }
        }
        return true;

    }
}
//leetcode submit region end(Prohibit modification and deletion)
