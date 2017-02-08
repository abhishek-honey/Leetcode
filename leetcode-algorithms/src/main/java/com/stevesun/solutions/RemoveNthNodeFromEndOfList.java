package com.stevesun.solutions;

import com.stevesun.common.classes.ListNode;
import com.stevesun.common.utils.CommonUtils;

/**19. Remove Nth Node From End of List
Given a linked list, remove the nth node from the end of list and return its head.

For example,

   Given linked list: 1->2->3->4->5, and n = 2.

   After removing the second node from the end, the linked list becomes 1->2->3->5.
Note:
Given n will always be valid.
Try to do this in one pass.*/
public class RemoveNthNodeFromEndOfList {
    
    /**Naive/most straightforward approach:
     * go through the list, find its total length, then go through the list a second time:
     * this time, pause at the delta point, then assign its next.next pointer to next.
     * This approach has to traverse the list twice, not one-pass.*/
    public ListNode removeNthFromEnd_two_passes(ListNode head, int n) {
        ListNode temp = head;
        int len = 0;
        while(temp != null){
            temp = temp.next;
            len++;
        }
        if(n == len) return head.next;
        
        temp = head;
        int cut = len-n;
        while(cut-- > 1){
            temp = temp.next;
        }
        if(temp.next != null){
            temp.next = temp.next.next;
            return head;
        }
        return null;
    }
    
    public static void main(String...strings){
        int n = 2;
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        RemoveNthNodeFromEndOfList test = new RemoveNthNodeFromEndOfList();
//        ListNode res = test.removeNthFromEnd_two_passes(head, n);
        ListNode res = test.removeNthFromEnd_one_pass(head, n);
        CommonUtils.printList(res);
    }
    
    public ListNode removeNthFromEnd_one_pass(ListNode head, int n) {
        //this approach uses two pointers, fast moves first for n nodes, when fast reaches n, then we start to move slow
        //then, when fast reaches null, slow reaches the point where the node should be deleted.
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode slow = head, fast = head;
        int tempN = n;
        while(tempN-- > 0){
            fast = fast.next;
        }
        
        if(fast == null) {
            if(n > 0) {
                // this is for cases like this: [1,2] 2 or [1,2,3,4] 4, namely, remove the head of
                // the list and return the second node from the original list
                dummy.next = dummy.next.next;
            }
            return dummy.next;
        }
        
        fast = fast.next;//we'll have to move fast pointer one node forward before moving the two together, this way,
        //when fast reaches null, slow will be at the previous node to the node that should be deleted, thus, we can change the next pointer easily
        
        while(fast != null){
            fast = fast.next;
            slow = slow.next;
        }
        
        if(slow.next != null) slow.next = slow.next.next;
        return dummy.next;
    }
    
    //a more concise version using the same idea found on Discuss
    public ListNode removeNthFromEnd_one_pass_more_concise_version(ListNode head, int n) {
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode slow = dummy, fast = dummy;
        while(fast.next != null){
            if(n <= 0) slow = slow.next;
            fast = fast.next;
            n--;
        }
        if(slow.next != null) slow.next = slow.next.next;
        return dummy.next;
    }
}
