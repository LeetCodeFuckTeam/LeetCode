package DataStructure;

import org.apache.xpath.objects.XNodeSet;

//线段树（数组存储）
public class seg_tree {
    private static final int MAX_SIZE = 1000;
    public static void buildTree(int[] arr, int[] tree, int node, int start, int end) {
        //build每次目的是向node指向的节点进行赋值操作start到end之间元素的和
        if (start == end) {
            tree[node] = arr[start];
            return;
        }
        int mid = (start + end) / 2;
        int leftNode = 2 * node + 1;
        int rightNode = 2 * node + 2;

        buildTree(arr, tree, leftNode, start, mid);
        buildTree(arr, tree, rightNode, mid + 1, end);
        tree[node] = tree[leftNode] + tree[rightNode];
    }
    public static void updateTree(int[] arr, int[] tree, int node, int start, int end, int idx, int value) {
        if (start == end) {
            arr[idx] = value;
            tree[node] = value;
            return;
        }
        //将arr数组中下表为idx的元素的值改为value
        int mid = (start + end) / 2;
        int leftNode = 2 * node + 1;
        int rightNode = 2 * node + 2;
        if (idx >= start && idx <= mid) {
            //更新左分枝
            updateTree(arr, tree, leftNode, start, mid, idx, value);
        }
        else {
            updateTree(arr, tree, rightNode, mid + 1, end, idx, value);
        }
        //改完左边分枝或者右边分枝之后需要在回溯过程中对当前节点进行更新操作
        tree[node] = tree[leftNode] + tree[rightNode];

    }

    //查找arr数组中index从L到R的元素的和
    public static int queryTree(int[] arr, int[] tree, int node, int start, int end, int L, int R) {
        //递归终止条件
        if (L > end || R < start) return 0;
        if (L <= start && end <= R) return tree[node];
        //首先start和end是属计算的区间
        if (start == end) return tree[node];

        int mid = (start + end) / 2;
        int leftNode = node * 2 + 1;
        int rightNode = node * 2 + 2;

        int leftSum = queryTree(arr, tree, leftNode, start, mid, L, R);
        int rightSum = queryTree(arr, tree, rightNode, mid + 1, end, L ,R);
        return leftSum + rightSum;

    }
    public static void main(String args[]){
        int[] arr = {1, 3, 5, 7, 9, 11};
        int size = arr.length;
        int[] tree = new int[MAX_SIZE];

        for (int i = 0; i < tree.length; i++) {
            tree[i] = 0;
        }
        buildTree(arr, tree, 0, 0, arr.length - 1);
        updateTree(arr, tree, 0, 0, arr.length - 1,3, 2);
        for (int i = 0; i < tree.length; i++) {
            System.out.println(tree[i]);
        }
        int sum = queryTree(arr, tree, 0, 0, arr.length - 1, 0, 2);
        System.out.println(sum);

        
    }
}
