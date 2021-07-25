/*
 * @lc app=leetcode.cn id=665 lang=cpp
 *
 * [665] 非递减数列
 * 
 * 当序列元素小于三个数字时一定可以变换为非降序排列
 * 设置指针从第一个元素开始遍历   
 * 当前第i个元素大于i+1个元素时将已修改变为true
 * 那么一次机会已经使用我们修改逆序元素之后继续判断
 * 如何修改首先肯定选择将当前元素改为i+元素保证数列递增最小
 * 如果i+1和小于i—1时没有办法只能将i+1元素修改为较大的值
 * 
 * 
 *  */

// @lc code=start
class Solution {
public:
    bool checkPossibility(vector<int> &nums) {
        int n = nums.size(), cnt = 0;
        for (int i = 0; i < n - 1; ++i) {
            int x = nums[i], y = nums[i + 1];
            if (x > y) {
                cnt++;
                if (cnt > 1) {
                    return false;
                }
                if (i > 0 && y < nums[i - 1]) {
                    nums[i + 1] = x;
                }
            }
        }
        return true;
    }
};

// @lc code=end

