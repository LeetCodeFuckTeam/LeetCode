package leetcode.editor.cn;


import java.util.*;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode() {}
    TreeNode(int val) { this.val = val; }
    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}
public class Test {

    static TreeNode createBT(Integer[] array){
        if (array.length == 0){
            return null;
        }
        // 创建第一个结点
        TreeNode root = new TreeNode(array[0]);
        Deque<TreeNode> deque = new LinkedList<>(); //创建一个双端队列
        deque.addFirst(root);
        // 遍历子结点的方向
        boolean isLeft = true;
        for (int i = 1; i < array.length; i++) {
            // 取出队尾元素
            TreeNode node = deque.getLast();
            if (isLeft){
                if (array[i]!=null){
                    node.left = new TreeNode(array[i]);
                    deque.addFirst(node.left);
                }
                isLeft = false;
            }else{
                if (array[i]!=null){
                    node.right = new TreeNode(array[i]);
                    deque.addFirst(node.right);
                }
                // 删除队尾元素
                deque.removeLast();
                isLeft = true;
            }
        }
        return root;
    }

    static List<List<Integer>> res = new ArrayList();
    public static void traversal(TreeNode root, int targetSum, List<Integer> path, int sum) {
        path.add(root.val);
        //终止条件
        if (root.left == null && root.right == null) {
            if (sum == targetSum) {
                res.add(new ArrayList<>(path));
                return;
            }else {
                return;
            }
        }
        if (root.left != null) {
            traversal(root.left, targetSum, path, sum + root.left.val);
            path.remove(path.size()-1);
        }
        if (root.right != null) {
            traversal(root.right, targetSum, path, sum + root.right.val);
            path.remove(path.size()-1);
        }
    }

    public static void main(String args[]){
        //List<String> res = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        Integer[] array = {5,4,8,11,null,13,4,7,2,null,null,5,1};
        TreeNode bt = createBT(array);
        //System.out.println(countNodes(bt));
        traversal(bt,22, path,0 + bt.val);
        
    }
//    public static void traversal(TreeNode root,List<String> path, List<String> res) {
//        path.add(String.valueOf(root.val));
//        //判断当前节点是否是叶子节点  终止条件
//        if (root.left == null && root.right == null) {
//            String resPath = "";
//            for (int i = 0; i < path.size() - 1; i++) {
//                resPath += path.get(i);
//                resPath += "->";
//            }
//            resPath += String.valueOf(path.get(path.size() - 1));
//            res.add(resPath);
//        }
//        if (root.left != null) {
//            traversal(root.left,path,res);
//            path.remove(path.size()-1);
//        }
//        if (root.right != null) {
//            traversal(root.right,path,res);
//            path.remove(path.size()-1);
//        }
//
//    }

    public static int countNodes(TreeNode root) {
        //递归终止条件
        if(root == null) return 0;
        //判断是否为满二叉树
        int leftHeight = 0;
        TreeNode leftNode = root.left;
        while(leftNode != null) {
            leftHeight++;
            leftNode = leftNode.left;
        }
        int rightHeight = 0;
        TreeNode rightNode = root.right;
        while(rightNode != null) {
            rightHeight++;
            rightNode = rightNode.right;
        }
        if(leftHeight == rightHeight) {

            return (int) (Math.pow(2,leftHeight+1) - 1);

        }
        int leftNum = countNodes(root.left);
        int rightNum = countNodes(root.right);
        return leftNum + rightNum + 1;
    }
    public static void infixorderPrint(TreeNode root) {
        //终止条件
        if (root == null) return;
        infixorderPrint(root.left);
        System.out.println(root.val);
        infixorderPrint(root.right);

    }



//    public static void main(String args[]){
//        String[] strings = {
//                "sadsad",
//                "sadd",
//                "sadf",
//                "sdfgfgg"
//        };
//        Map<Integer, List<String>> integerListMap = groupStrings(strings);
//        for(Map.Entry<Integer,List<String>> entry : integerListMap.entrySet()) {
//            System.out.println(entry.getKey());
//            List<String> value = entry.getValue();
//            for (String s : value) {
//                System.out.println(s);
//            }
//        }
//
//    }
//        public static Map<Integer, List<String>> groupStrings(String[] strings) {
//            Map<Integer,List<String>> map = new HashMap<>();
//            for (String string : strings) {
//                int length = string.length();
//                List<String> stringList = map.get(length);
//                if(stringList == null) {
//                    stringList  = new ArrayList<>();
//                    stringList.add(string);
//                    map.put(length,stringList);
//                }else {
//                    stringList.add(string);
//                    map.put(length,stringList);
//                }
//
//            }
//            return map;
//        }


    ;
}
