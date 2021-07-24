package fwf;

public class SS
{
    public static void main(String args[]){
        ListNode listNode1 = new ListNode(1);
        ListNode listNode2 = new ListNode(2);
        ListNode listNode3 = new ListNode(4);
        listNode1.next = listNode2;
        listNode2.next = listNode3;

        ListNode listNode11 = new ListNode(-1);
        ListNode listNode22 = new ListNode(2);
        ListNode listNode33 = new ListNode(3);
        listNode11.next = listNode22;
        listNode22.next = listNode33;
        ListNode listNode = addTwoNumbers(listNode1, listNode11);
        System.out.println(listNode);


    }
    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        // 判空输入
        if (l1==null) {
            return l2;
        } else if (l2==null) {
            return l1;
        }

        // 遍历两个输入链表
        ListNode head = null;
        ListNode first = null;
        int tmp = 0;
        while (l1!=null ||l2!=null) {
            tmp =tmp+(l1!=null?Math.abs(l1.val):0)+(l2!=null?Math.abs(l2.val):0);
            if (first==null) {
                first = new ListNode(tmp%10);
                head = first;
            } else {
                first.next = new ListNode(tmp%10);
                first = first.next;
            }
            l1=(l1!=null?l1.next:null);
            l2=(l2!=null?l2.next:null);
            tmp /= 10;
        }
        // 处理相加的进位
        if (tmp!=0) {
            first.next = new ListNode(tmp);
        }
        return head;
    }




}
