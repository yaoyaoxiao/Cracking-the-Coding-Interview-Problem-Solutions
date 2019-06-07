/* 4.10 Check Subtree: T1 and T2 are two very large binary trees, with T1 much bigger than T2. Create an
algorithm to determine if T2 is a subtree of T1.
A tree T2 is a subtree of T1 if there exists a node n in T1 such that the subtree of n is identical to T2.
That is, if you cut off the tree at node n, the two trees would be identical. */

/*
1. A simple approach is to compare the string representations of traversals of each tree. If T2 is a subtree of T1, its traversal should be a substring of T1's. 
2. Notice that we cannot use an in-order traversal:consider a scenario with BSTs. BST's in-order traversal are ordered so two different BSTs with the same values always have the same in-order traversals. 
3. Use pre-order traversal. Be careful, different structure could still have the same pre-order traverals - a simple fix is storing NULL nodes as a special character.
4. Also, keep in mind that we are checking a "data equality" not "referencial equality". 
*/

boolean isSubStree(TreeNode t1, TreeNode t2){
	if (t1 == t2) {
		return true;
	}
	StringBuilder s1 = new StringBuilder();
	getPreOrderTraversal(t1, s1);

	String s2 = new StringBuilder();
	getPreOrderTraversal(t2, s2);

	return s1.indexOf(s2.toString()) != -1;
}

void getPreOrderTraversal(TreeNode t, StringBuilder result){
	if (t == null) {
		result.append("X");  //add NULL indicator
		return;
	}
	result.append(t.data + " "); //add root
	getPreOrderTraversal(t.left, result);
	getPreOrderTraversal(t.right, result);
}

/*
1. The above approach takes O(n + m) time and O(n + m) space.
2. How to reduce the space complexity? - seach through T1, each time we find a node matchs the root node of T2, we compare the two trees to see if they are the same.
3. The second approach takes O(nm) technically, but it could be much better, O(n + km) where k is the number of times we find matches to the root of t2. Actually, this still overstates the runtime since we exit matchTrees once we find a difference. 
*/

boolean containsTree(TreeNode t1, TreeNode t2){
	if (t2 == null) {
		return true;
	}
	return subTree(t1, t2);
}
boolean subTree(TreeNode t1, TreeNode t2){
	//larger tree is null while small tree hasn't been found
	if (t1 == null){
		return false
	}
	else if(t1.data == t2.data && matchTrees(t1, t2)){ //find a matching node for the root of t2, the rest should be exactly the same.
		return true;
	}
	return containsTree(t1.left, t2) || containsTree(t1.right, t2);
}

boolean matchTrees(t1, t2){
	if (t1 == null && t2 == null) {  //nothing left in the the subtree
		return true;
	}
	else if(t1 == null || t2 == null){ //one tree is empty, exactly trees dont match
		return false;  
	}
	else if (t1.data != t2.data){
		return false; //data doesnt match
	}
	else{
		return matchTrees(t1.left, t2.left) && matchTrees(t1.right, t2.right);
	}
}
