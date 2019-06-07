/*
4.5 Validate BST: Implement a function to check if a binary tree is a binary search tree. */

boolean isBST(TreeNode root){
	return isBST(root, null, null);
}

boolean isBST(TreeNode root, Integer min, Integer max){
	if (root == null) return true;

	if ((min != null && root.data <= min) || (max != null & root.data > max)){
		return false;
	}

	if (!isBST(root.left, min, root.data) || !isBST(root.right, root.data, max){
		return false;
	}

	return true;
}

/* O(n) time and O(log n) space on a balanced tree.

1. The general idea is, for each node, use the definition of a BST, left <= current < right. Pass the current data down, as the min and max values, to check the left and right subtree recursively. 
2. As we iterate through the tree, we verify against progressively narrower ranges.
3. Start with a range of (min = NULL, max = NULL). NULL indicates that there is no min or max, which the root meets here. 
*/