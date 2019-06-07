/* 4.6 Successor: Write an algorithm to find the "next" node (i.e., in-order successor) of a given node in a
binary search tree. You may assume that each node has a link to its parent. */

TreeNode inorderSucc(TreeNode node){
	if (node == null) return null;

	// if node has right child, return the left most child of the right subtree
	if (node.right != null) return leftMostChild(node.right);
	else {
		// if node is the left child of its parent, return its parent;
		// if node is the right child of its parent, go up until it is the left child
		TreeNode cur = node;
		TreeNode par = cur.parent;
		//go up until it is on the left instead of right
		while (par != null && par.left != cur){
			cur = par;
			par = cur.parent;
		}
		return par;
	}
}

TreeNode leftMostChild(TreeNode n){
	if (n == null) return null;
	while (n.left != null) {
		n = n.left;
	}
	return n;
}

/* O(log n) time and O(1) space.

1. This could be tricky to code perfectly. Be careful when deal with different cases. 
2. Combine the case when the node is on the right side of its parent together with the case when it is on the left by going up until it is on left
*/