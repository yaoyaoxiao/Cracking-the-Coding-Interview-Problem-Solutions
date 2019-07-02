/* 2.6 Palindrome: Implement a function to check if a linked list is a palindrome. */

/*
1. use two runners to find the middle node and push the first half nodes into a stack 
2. compare the second half and the nodes in the stack
*/

boolean isPalindrome(LinkedListNode node){
	LinkedListNode fast = node;
	LinkedListNode slow = node;
	Stack<Integer> s = new Stack<Integer>();

	while (fast != null && fast.next != null){
		fast = fast.next.next;
		s.push(slow.data); //save the first half nodes into a stack in reverse order
		slow = slow.next;
	}

	if (fast != null) { // linked list has odd number of nodes, dont push the middle node into the stack
		slow = slow.next;
	}

	while (slow != null){
		if (slow.data != stack.pop().intValue()){
			return false;
		}
		slow = slow.next;
	}
	return true;
}

/*
1. can also use a recursive way to solve this - recursively compare the first and the last nodes. 
2. need to use a arapper class since the recursive call should return both the result (whether the sublist is a palindrome) and the 'last' node which is need to be compared with the 'first' node.
*/

boolean isPalindrome2(LinkedListNode node){
	int len = getLength(node);
	ResultAndEndNode res = isPalindrome(node, len);
	return res.result;
}

ResultAndEndNode isPalindrome(LinkedListNode node, int len){
	if (node == null || len <= 0){ //even number of nodes
		return new ResultAndEndNode(node, true);
	}
	else if(len == 1){ // odd number of nodes
		return new ResultAndEndNode(node.next, true);
	}

	ResultAndEndNode innerResult = isPalindrome(node.next, len-2);
	if (!innerResult.result || innerResult.node == null){
		return innerResult;
	}

	innerResult.result = innerResult.node.data == node.data;
	innerResult.node = innerResult.node.next;

	return innerResult;
}

int getLength(LinkedListNode node){
	int len = 0;
	while (node != null){
		len ++; 
		node = node.next;
	}
	return len;
}

public class ResultAndEndNode{
	public LinkedListNode node;
	public boolean result;
}

