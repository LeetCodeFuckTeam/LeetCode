package leetcode.editor.cn;//实现一个 MyCalendar 类来存放你的日程安排。如果要添加的日程安排不会造成 重复预订 ，则可以存储这个新的日程安排。
//
// 当两个日程安排有一些时间上的交叉时（例如两个日程安排都在同一时间内），就会产生 重复预订 。 
//
// 日程可以用一对整数 start 和 end 表示，这里的时间是半开区间，即 [start, end), 实数 x 的范围为， start <= x < e
//nd 。 
//
// 实现 MyCalendar 类： 
//
// 
// MyCalendar() 初始化日历对象。 
// boolean book(int start, int end) 如果可以将日程安排成功添加到日历中而不会导致重复预订，返回 true 。否则，返回 fa
//lse 并且不要将该日程安排添加到日历中。 
// 
//
// 
//
// 示例： 
//
// 
//输入：
//["MyCalendar", "book", "book", "book"]
//[[], [10, 20], [15, 25], [20, 30]]
//输出：
//[null, true, false, true]
//
//解释：
//MyCalendar myCalendar = new MyCalendar();
//myCalendar.book(10, 20); // return True
//myCalendar.book(15, 25); // return False ，这个日程安排不能添加到日历中，因为时间 15 已经被另一个日程安排预订了
//。
//myCalendar.book(20, 30); // return True ，这个日程安排可以添加到日历中，因为第一个日程安排预订的每个时间都小于 20
// ，且不包含时间 20 。 
//
// 
//
// 提示： 
//
// 
// 0 <= start < end <= 109 
// 每个测试用例，调用 book 方法的次数最多不超过 1000 次。 
// 
// Related Topics 设计 线段树 二分查找 有序集合 
// 👍 222 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class MyCalendar729 {

    public void MyCalendar() {

    }
    
    public boolean book(int start, int end) {
        //预定之前先查看当前区间是否已经存在预定信息
        if (query(root, 0 , N, start, end - 1) != 0) return false;
        //不存在预定
        update(root, 0, N, start, end - 1, 1);
        return true;

    }
    private int N = (int) 1e9; //这个是指定的构建的线索树所能存储的最多的数据量，线段树的index进行查找
    private Node root = new Node();
    //并不存在一个数组直接提供所有要用线段树存储的数据
    //此时使用的是链式存储方式实现线段树
    public void update(Node node, int start, int end, int l ,int r, int val) {
        if (r < start || l > end) return;
        //包含区间懒惰标记,分区间更新使用下推更新
        if (l >= start && r <= end) {
            node.val += val;
            node.add += val;
            return;
        }
        pushDown(node);
        int mid = (start + end) >> 1;
        update(node.left, start, mid, l, r, val);
        update(node.right, mid + 1, end, l, r, val);
        pushUp(node);
    }

    private void pushUp(Node node) {
        node.val = Math.max(node.left.val, node.right.val);
    }

    public int query(Node node, int start, int end, int l ,int r) {
        if (r < start || l > end) return 0;
        //所要查询的区间是当前区间的子区间
        if (l <= start && r >= end) return node.val;
        //当前区间不是包含关系需要分区间详细计算
        pushDown(node);
        int mid = (start + end) / 2;
        int left = query(node.left, start, mid, l, r);
        int right = query(node.right, mid + 1, end, l, r);
        //在线索树中查询要查找的区间max
        //这个区间可能一分为二前半部分只预约了一次，后半部分预约了两次，当时我们要在这个总区间内预定，所以查询返回总区间的
        return Math.max(left, right);
    }

    private void pushDown(Node node) {
        //创建出n为空的线索树子区间的节点
        //避免直接查询时候的空指针异常
        if (node.left == null) node.left = new Node();
        if (node.right == null) node.right = new Node();
        if (node.add == 0) return;
        node.left.val += node.add;
        node.right.val += node.add;
        node.left.add += node.add;
        node.right.add += node.add;
        node.add = 0;
    }
    class Node {
        Node left, right;
        //当前值和懒惰标记
        int val, add;
    }

}


/**
 * Your MyCalendar object will be instantiated and called as such:
 * MyCalendar obj = new MyCalendar();
 * boolean param_1 = obj.book(start,end);
 */
//leetcode submit region end(Prohibit modification and deletion)
