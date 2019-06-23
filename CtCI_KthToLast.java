/* 2.2 Return Kth to Last: Implement an algorithm to find the kth to last element of a singly linked list. */

LinkedListNode kthToLast(LinkedListNode head){
	LinkedListNode fast = head;
	LinkedListNode slow = head;

	int cnt = 0;
	while (cnt < k){
		if (fast = null) {
			return null; // out of bound
		}
		fast = fast.next;
		cnt++;
	}
	while (fast != null){ //when fast reaches the end, slow is k away from the end
		fast = fast.next;
		slow = slow.next;
	}
	return slow;
}