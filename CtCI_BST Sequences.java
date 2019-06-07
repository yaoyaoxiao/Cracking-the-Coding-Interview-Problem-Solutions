/* 4.9 BST Sequences: A binary search tree was created by traversing through an array from left to right
and inserting each element. Given a binary search tree with distinct elements, print all possible
arrays that could have led to this tree. */

/* the key is that we just need to add root and then add elements subtree by subtree, the order of left and right subtrees doesn't matter. So the problem is translated to merging two arrays (arrays to creat left and right subtrees) in all possible ways while keeping the elements within each array in the same relative order. */

ArrayList<LinkedList<Integer>> BSTSeqs(TreeNode root){
	ArrayList<LinkedList<Integer>> result = new ArrayList<LinkedList<Integer>>();
	if (root == null){
		LinkedList<Integer> r = new LinkedList<Integer>();
		result.add(r);
		return result;
	}
	
	LinkedList<Integer> prefix = new LinkedList<Integer>();
	prefix.add(root.data);

	ArrayList<LinkedList<Integer>> leftSeq = BSTSeqs(root.left);
	ArrayList<LinkedList<Integer>> rightSeq = BSTSeqs(root.right);

	for (LinkedList<Integer> left:leftSeq){
		for (LinkedList<Integer> right:rightSeq){
			ArrayList<LinkedList<Integer>> Seqs = new ArrayList<LinkedList<Integer>>();
			mergeSeqs(left,right,prefix,Seqs);
			result.addAll(Seqs);
		}
	}
	return result;	
}

void mergeSeqs(LinkedList<Integer> first, LinkedList<Integer> second, LinkedList<Integer> prefix, ArrayList<LinkedList<Integer>> result){

	//if one list is empty, add remainder to a cloned prefix and store it into result.
	if (first.size() == 0 || second.size() == 0){
		LinkedList<Integer> curSeq = (LinkedList<Integer>)prefix.clone();
		curSeq.addAll(first);
		curSeq.addAll(second);
		result.add(curSeq);
		return;
	}

	//start from the first list, recursively remove the head to the prefix, break it, merge the remainder, and restore it.
	int firstHead = first.removeFirst();
	prefix.addLast(firstHead);
	mergeSeqs(first, second, prefix, result);
	prefix.removeLast();
	first.addFirst(firstHead);

	//Do the same to the second list
	int secondHead = second.removeFirst();
	prefix.addLast(secondHead);
	mergeSeqs(first, second, prefix, result);
	prefix.removeLast();
	second.addFirst(secondHead);
}

/*
1. two different recursions. Dont mix them, design and implement separately, once one is done, trust it doing correctly and focus on the other. 
*/