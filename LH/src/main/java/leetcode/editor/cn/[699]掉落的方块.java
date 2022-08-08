package leetcode.editor.cn;//在二维平面上的 x 轴上，放置着一些方块。
//
// 给你一个二维整数数组 positions ，其中 positions[i] = [lefti, sideLengthi] 表示：第 i 个方块边长为 si
//deLengthi ，其左侧边与 x 轴上坐标点 lefti 对齐。 
//
// 每个方块都从一个比目前所有的落地方块更高的高度掉落而下。方块沿 y 轴负方向下落，直到着陆到 另一个正方形的顶边 或者是 x 轴上 。一个方块仅仅是擦过另
//一个方块的左侧边或右侧边不算着陆。一旦着陆，它就会固定在原地，无法移动。 
//
// 在每个方块掉落后，你必须记录目前所有已经落稳的 方块堆叠的最高高度 。 
//
// 返回一个整数数组 ans ，其中 ans[i] 表示在第 i 块方块掉落后堆叠的最高高度。 
//
// 
//
// 示例 1： 
//
// 
//输入：positions = [[1,2],[2,3],[6,1]]
//输出：[2,5,5]
//解释：
//第 1 个方块掉落后，最高的堆叠由方块 1 组成，堆叠的最高高度为 2 。
//第 2 个方块掉落后，最高的堆叠由方块 1 和 2 组成，堆叠的最高高度为 5 。
//第 3 个方块掉落后，最高的堆叠仍然由方块 1 和 2 组成，堆叠的最高高度为 5 。
//因此，返回 [2, 5, 5] 作为答案。
// 
//
// 示例 2： 
//
// 
//输入：positions = [[100,100],[200,100]]
//输出：[100,100]
//解释：
//第 1 个方块掉落后，最高的堆叠由方块 1 组成，堆叠的最高高度为 100 。
//第 2 个方块掉落后，最高的堆叠可以由方块 1 组成也可以由方块 2 组成，堆叠的最高高度为 100 。
//因此，返回 [100, 100] 作为答案。
//注意，方块 2 擦过方块 1 的右侧边，但不会算作在方块 1 上着陆。
// 
//
// 
//
// 提示： 
//
// 
// 1 <= positions.length <= 1000 
// 1 <= lefti <= 108 
// 1 <= sideLengthi <= 106 
// 
// Related Topics 线段树 数组 有序集合 
// 👍 171 👎 0


import java.util.ArrayList;
import java.util.List;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution699 {
    private int N = (int) 1e9;
    private Node root = new Node();
    public List<Integer> fallingSquares(int[][] positions) {
        List<Integer> ans = new ArrayList<>();
        for (int[] position : positions) {
            int x = position[0], h = position[1];
            // 先查询出 [x, x + h] 的值
            int cur = query(root, 0, N, x, x + h - 1);
            // 更新 [x, x + h - 1] 为 cur + h (直接覆盖)
            update(root, 0, N, x, x + h - 1, cur + h);
            ans.add(root.val);
        }
        return ans;
    }


    public void update(Node node, int start, int end, int l, int r, int val) {
        if (l <= start && end <= r) {
            node.val = val;
            node.add = val;
            return;
        }
        pushDown(node);
        int mid = (start + end) >> 1;
        if (l <= mid) update(node.left, start, mid, l, r, val);
        if (r > mid) update(node.right, mid + 1, end, l, r, val);
        pushUp(node);
    }

    public int query(Node node, int start, int end, int l, int r) {
        if (end < l || start > r) return 0;
        if (l <= start && end <= r) {
            return node.val;
        }
        pushDown(node);
        int mid = (start + end) >> 1;
        int leftVal = query(node.left, start, mid, l, r);
        int rightVal = query(node.right, mid + 1, end, l, r);
        return Math.max(leftVal, rightVal);
    }




    private void pushUp(Node node) {
        // 每个节点存的是当前区间的最大值
        node.val = Math.max(node.left.val, node.right.val);
    }
    private void pushDown(Node node) {
        if (node.left == null) node.left = new Node();
        if (node.right == null) node.right = new Node();
        if (node.add == 0) return;
        //注意update(root, 0, N, x, x + h - 1, cur + h); 其中val为cur和h的和是直接进行更新的所以下面直接进行赋值而不是递加
        node.left.val = node.add;
        node.right.val = node.add;
        node.left.add = node.add;
        node.right.add = node.add;
        node.add = 0;

    }
    class Node {
        Node left, right;
        int val, add;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
