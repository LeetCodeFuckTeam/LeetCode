//给定一个以字符串表示的非负整数 num，移除这个数中的 k 位数字，使得剩下的数字最小。 
//
// 注意: 
//
// 
// num 的长度小于 10002 且 ≥ k。 
// num 不会包含任何前导零。 
// 
//
// 示例 1 : 
//
// 
//输入: num = "1432219", k = 3
//输出: "1219"
//解释: 移除掉三个数字 4, 3, 和 2 形成一个新的最小的数字 1219。
// 
//
// 示例 2 : 
//
// 
//输入: num = "10200", k = 1
//输出: "200"
//解释: 移掉首位的 1 剩下的数字为 200. 注意输出不能有任何前导零。
// 
//
// 示例 3 : 
//
// 
//输入: num = "10", k = 2
//输出: "0"
//解释: 从原数字移除所有的数字，剩余为空就是0。
// 
// Related Topics 栈 贪心算法


import com.sun.org.apache.bcel.internal.generic.ARETURN;

import java.util.Stack;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {

    /**
     * 分析:
     * 相同位数比较大小，当最高位相同时比较次高位
     * 去掉某位数字让结果数最小就要保证去掉数字的结果的最高位最小
     * ，然后是它的次高位。如果我们使用循环的方式去从高位逐步去除保证最高位最小
     * 那么每次遍历又要从头开始。所以采用栈这种数据结构来存储当前数字
     * 从最高位开始入栈，如果第二位数字小于栈顶元素就删除栈顶元素
     * 然后在向后遍历栈中从栈顶到栈顶就是保留的数字，从栈底到栈顶为从小到大的序列
     * 也就是从最高位到次高位的结果序列
     *
     * @param num
     * @param k
     * @return
     */
    public String removeKdigits(String num, int k) {
        Stack<Integer> stack = new Stack<Integer>();
        String result = "";
        char[] chars = num.toCharArray();
        for (int i = 0;i<chars.length;i++) {
            int number = chars[i] - '0';
            while(stack.size()>0&&stack.peek()>number&&k>0) {
                //可以删除元素
                stack.pop();
                k--;
            }
            if(number!=0||stack.size()!=0) {

                //当遇到number为0的情况
                //栈为0表示结果开始直接是0则不压栈，如果0在结果的中间位或最后位才会压栈

                stack.push(number);
            }

        }
        //如果栈不为空但是还有删除的余额还可以继续删除
        while(stack.size()!=0&&k>0) {
            //从最低位开始删除,因为栈中结果为递增序列
            stack.pop();
            k--;
        }
        //拼接结果字符串
        while(stack.size()!=0) {
            result =result+stack.pop();
        }
        if(result == ""){
            result = "0";
        }

        /**
         * 栈中的数据从栈底向栈顶遍历输出拼接结果  
         */
        char[] chars1 = result.toCharArray();
        StringBuilder sb = new StringBuilder();
        for (int i =chars1.length-1;i>=0;i--) {

            sb.append(chars1[i]);
        }
        result = sb.toString();
        System.out.println(result);
        return result;

    }

//
//    public static String reverseResult(String s) {
//        String result = "";
//        char[] chars = s.toCharArray();
//        Stack<Character> stack = new Stack<Character>();
//        for(int i=chars.length-1;i>0;i--) {
//            stack.push(chars[i]);
//        }
//        for(int i=0;i<stack.size();i++) {
//            result = result + stack.pop();
//        }
//        return result;
//    }




    public static void main(String args[]) {
        Solution s = new Solution();
        String s1 = "1432219";
        s.removeKdigits(s1,3);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
