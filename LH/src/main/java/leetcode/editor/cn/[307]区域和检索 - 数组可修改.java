package leetcode.editor.cn;//给你一个数组 nums ，请你完成两类查询。
//
// 
// 其中一类查询要求 更新 数组 nums 下标对应的值 
// 另一类查询要求返回数组 nums 中索引 left 和索引 right 之间（ 包含 ）的nums元素的 和 ，其中 left <= right 
// 
//
// 实现 NumArray 类： 
//
// 
// NumArray(int[] nums) 用整数数组 nums 初始化对象 
// void update(int index, int val) 将 nums[index] 的值 更新 为 val 
// int sumRange(int left, int right) 返回数组 nums 中索引 left 和索引 right 之间（ 包含 ）的nums元
//素的 和 （即，nums[left] + nums[left + 1], ..., nums[right]） 
// 
//
// 
//
// 示例 1： 
//
// 
//输入：
//["NumArray", "sumRange", "update", "sumRange"]
//[[[1, 3, 5]], [0, 2], [1, 2], [0, 2]]
//输出：
//[null, 9, null, 8]
//
//解释：
//NumArray numArray = new NumArray([1, 3, 5]);
//numArray.sumRange(0, 2); // 返回 1 + 3 + 5 = 9
//numArray.update(1, 2);   // nums = [1,2,5]
//numArray.sumRange(0, 2); // 返回 1 + 2 + 5 = 8
// 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 3 * 104 
// -100 <= nums[i] <= 100 
// 0 <= index < nums.length 
// -100 <= val <= 100 
// 0 <= left <= right < nums.length 
// 调用 update 和 sumRange 方法次数不大于 3 * 104 
// 
// Related Topics 设计 树状数组 线段树 数组


//leetcode submit region begin(Prohibit modification and deletion)
class NumArray307 {
    static int[] arr = {1, 2, 3, 4, 5};

    public static void main(String args[]) {
        NumArray307 numArray307 = new NumArray307();
        Node root = numArray307.build(arr, 0, arr.length - 1);
        traverse(root);
        numArray307.update(arr, root, 0, arr.length - 1, 2, 4, 1);
        traverse(root);

    }

    //   public Node NumArray(int[] nums) {
//
//    }
//
    //树的中序遍历
    public static void traverse(Node root) {
        if (root == null) return;
        System.out.println(root.val);
        traverse(root.leftNode);
        traverse(root.rightNode);

    }

    public Node build(int[] nums, int start, int end) {
        Node node = new Node();
        //终止条件
        if (start == end) {
            //叶子节点
            node.val = nums[start];
            return node;
        }
        int mid = (start + end) / 2;
        //可以通过backtrace后通过赋值方式将父子节点进行连接
        //也可以直接将当前节点的子节点通过函数参数方式进行传入
        Node leftNode = build(nums, start, mid);
        Node rightNode = build(nums, mid + 1, end);
        node.val = leftNode.val + rightNode.val;
        node.leftNode = leftNode;
        node.rightNode = rightNode;
        return node;
    }

    //更新函数将区间L到R内的全部元素都加val
    //对于更新操作还存在一种叫做动态懒惰标记的方法区间节点进行标记区间内元素还需val求和操作
    // leftNum 和 rightNum 表示左右孩子区间的叶子节点数量
    // 因为如果是「加减」更新操作的话，需要用懒惰标记的值 *️叶子节点的数量
    //    private void pushDown(Node node, int leftNum, int rightNum) {
    //        // 动态开点
    //        if (node.left == null) node.left = new Node();
    //        if (node.right == null) node.right = new Node();
    //        // 如果 add 为 0，表示没有标记
    //        if (node.add == 0) return ;
    //        // 注意：当前节点加上标记值✖️该子树所有叶子节点的数量
    //        node.left.val += node.add * leftNum;
    //        node.right.val += node.add * rightNum;
    //        // 把标记下推给孩子节点
    //        // 对区间进行「加减」的更新操作，下推懒惰标记时需要累加起来，不能直接覆盖
    //        node.left.add += node.add;
    //        node.right.add += node.add;
    //        // 取消当前节点标记
    //        node.add = 0;
    //    }

    public void update(int[] arr, Node node, int start, int end, int L, int R, int val) {
        //终止条件
        if (start > R || end < L) {
            return;
        }
        if (start == end) {
            node.val = node.val + val;
            return;
        }
        int mid = (start + end) / 2;
        update(arr, node.leftNode, start, mid, L, R, val);
        update(arr, node.rightNode, mid + 1, end, L, R, val);
        node.val = node.leftNode.val + node.rightNode.val;
    }

    public void update(int index, int val) {
    }

//    public int sumRange(int left, int right) {
//
//
//    }

}

class Node {
    Node leftNode;
    Node rightNode;
    int val;
}

/**
 * Your NumArray object will be instantiated and called as such:
 * NumArray obj = new NumArray(nums);
 * obj.update(index,val);
 * int param_2 = obj.sumRange(left,right);
 */
//leetcode submit region end(Prohibit modification and deletion)
