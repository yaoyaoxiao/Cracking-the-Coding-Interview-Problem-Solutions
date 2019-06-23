/* 2.1 Remove Dups: Write code to remove duplicates from an unsorted linked list.
FOLLOW UP
How would you solve this problem if a temporary buffer is not allowed? */

void removeDups(LinkedListNode node){
	HashSet<Integer> hashset = new HashSet<Integer>();
	LinkedListNode pre = null;
	while (node != null){
		if (!hashset.contains(node.data)){
			hashset.add(node.data);
			pre = node;
		}
		else{
			pre.next = node.next;
		}
		node = node.next;
	}
}

/*
1. use a hash table to track duplicates
2. for FOLLOW UP, we can use a runner which checks all the subsequent node for the current node. (runtime is O(n^2)).
*/