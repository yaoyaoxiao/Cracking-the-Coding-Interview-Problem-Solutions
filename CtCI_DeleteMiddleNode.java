/* 2.3 Delete Middle Node: Implement an algorithm to delete a node in the middle (Le., any node but
the fi rst and last node, not necessarily the exact middle) of a singly linked list, given only access to
that node.
EXAMPLE
Input: the node c from the linked list a -> b -> c -> d -> e -> f
Result: nothing is returned, but the new linked list looks like a->b->d->e->f */

void deleteMiddleNode(LinkedListNode midNode){
	if (midNode != null && midNode.next != null){
		midNode.data = midNode.next.data;
		midNode.next = midNode.next.next;
		return;
	}
}

/* since we dont know the parent of midNode, we can just copy the next node's info to midNode and delete midNode's next */