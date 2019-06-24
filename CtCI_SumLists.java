/* 2.5 Sum Lists: You have two numbers represented by a linked list, where each node contains a single
digit. The digits are stored in reverse order, such that the 1 's digit is at the head of the list. Write a
function that adds the two numbers and returns the sum as a linked list.
EXAMPLE
Input: (7-> 1 -> 6) + (5 -> 9 -> 2) .That is,617 + 295.
Output: 2 - > 1 - > 9. That is, 912.

FOLLOW UP
Suppose the digits are stored in forward order. Repeat the above problem.
Input: (6 -> 1 -> 7) + (2 -> 9 -> 5).That is,617 + 295.
Output: 9 - > 1 - > 2. That is, 912. */

LinkedListNode sumLists(LinkedListNode n1, LinkedListNode n2){
	LinkedListNode result = new LinkedListNode();
	LinkedListNode node = result;
	int levelUp = 0;
	int sum = 0, data = 0;
	while (n1 != null || n2 != null || levelUp != 0){
		if (n1 != null) {
			sum += n1.data;
			n1 = n1.next;
		}
		if (n2 != null){
			sum += n2.data;
			n2 = n2.next;
		}
		if (levelUp != 0){
			sum += levelUp;
		}
		data = sum % 10;
		levelUp = sum /10; // 1 or 0

		node.data = data;
		LinkedListNode newNode = new LinkedListNode();
		node.next = newNode;
		node = node.next;
	}
	node = null;
	return result;
}

/*
1. we can also implement the above algorithm by using recursion
2. for the FOLLOW UP, since the length might be different, we need to check the length of each linked list first. Then, we can insert several 0s in front of the short list to make they have same length. Then we can use recursive calls to return both the data and the levelUp (a wrapper class). 
*/

LinkedListNode sumLists2(LinkedListNode n1, LinkedListNode n2){
	int len1 = findLen(n1);
	int len2 = findLen(n2);

	//insert zeros to the beginning of the short list
	if (len1 < len2){
		n1 = insertZeros(n1, len2-len1);
	}
	else if (len1 > len2){
		n2 = insertZeros(n2, len1 -len2);
	}

	ResultAndLevelUp res = sumListsHelper(n1, n2);
	if (res.levelUp != 0){
		LinkedListNode result = insertBefore(res.node, res.levelUp);
		return result;
	}
	else{
		return res.node;
	}
}

ResultAndLevelUp sumListsHelper(LinkedListNode n1, LinkedListNode n2){
	ResultAndLevelUp res = new ResultAndLevelUp();
	if (n1 == null && n2 == null){
		return res;
	}

	res = sumListsHelper(n1.next, n2.next); //add smaller digits recursively

	int data = res.levelUp + n1.data + n2.data;

	LinkedListNode resultNode = insertBefore(res.node, data%10);

	res.node = resultNode;
	res.levelUp = data / 10;
	return res;
}

LinkedListNode insertZeros(LinkedListNode node, int num){
	LinkedListNode n = node;
	for (int i = 0; i < num. i++){
		n = insertBefore(n, 0);
	}
	return n;
}

LinkedListNode insertBefore(LinkedListNode node, int val){
	LinkedListNode n = new LinkedListNode(val):
	n.next = node;
	return n;
}


int findLen{LinkedListNode node}{
	int res = 0;
	while (node != null){
		res++;
		node = node.next;
	}
	return res;
}

public class ResultAndLevelUp{
	public LinkedListNode node = null;
	public int levelUp = 0;
}