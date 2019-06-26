/* 2.8 Loop Detection: Given a circular linked list, implement an algorithm that returns the node at the
beginning of the loop.
DEFINITION
Circular linked list: A (corrupt) linked list in which a node's next pointer points to an earlier node, so
as to make a loop in the linked list.
EXAMPLE
Input: A -> B -> C - > D -> E -> C [the same C as earlier)
Output: C */

LinkedListNode findBeginningOfLoop(LinkedListNode head){
	LinkedListNode fast = head;
	LinkedListNode slow = head;

	while (fast != null && fast.next != null){
		slow = slow.next;
		fast = fast.next.next;
		if (slow == fast){  //fast and slow runners get meet in the loop
			break;
		}
	}

	if (fast == null || fast.next == null) {
		return null; // there is no loop
	}

	slow = head; // move one runner to the head 
	while (slow != fast){ // move both runner step by step until they meet at the begin of the loop (the collision spot and the head are same distance away from the beginning of the loop)
		slow = slow.next;
		fast = fast.next;
	}

	return fast; // return either slow or fast runner
}