package fwf;

public class Test {
    
    public static void main(String args[]) {
        System.out.println("...");
        ListNode listNode1 = new ListNode(2);
        ListNode listNode2 = new ListNode(2);
        listNode1.next = listNode2;
        System.out.println(listNode1);
        
        }

    static class ListNode {
        int val;
        ListNode next;

        public ListNode(int val) {
            this.val = val;
        }

        public void setVal(int val) {
            this.val = val;
        }

        @Override
        public String toString() {
            return "ListNode{" +
                    "val=" + val +
                    ", next=" + next +
                    '}';
        }
    }
}
