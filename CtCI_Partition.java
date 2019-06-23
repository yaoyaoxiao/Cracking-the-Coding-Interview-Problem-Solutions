/* 2.4 Partition: Write code to partition a linked list around a value x, such that all nodes less than x come
before all nodes greater than or equal to x. If x is contained within the list, the values of x only need
to be after the elements less than x (see below). The partition element x can appear anywhere in the
"right partition"; it does not need to appear between the left and right partitions.
EXAMPLE
Input: 3 -> 5 -> 8 -> 5 -> 10 -> 2 -> 1 [partition = 5]
Output: 3 -> 1 -> 2 -> 10 -> 5 -> 5 -> 8  */

LinkedListNode partition(LinkedListNode node, int x){
	LinkedListNode head = node;
	LinkedListNode tail = head;
	while (node != null){
		LinkedListNode next = node.next;
		if (node.data < x){ // add nodes less than x to the front
			node.next = head;
			head = node;
		}
		else{  // add node greater than or equal to x to the end
			tail.next = node;
			tail = node;
		}
		node = next;
	}
	tail.next = null;
	return head;
}

//note that this would not keep the elements "stable". The head and tail change a lot. If we want to keep it stable, we can build two linkedList, one contains all the nodes less than x and the other contains all the node greater than x, then concatenate these two at the end.