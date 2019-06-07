/*
4.4 Check Balanced: Implement a function to check if a binary tree is balanced. For the purposes of
this question, a balanced tree is defined to be a tree such that the heights of the two subtrees of any
node never differ by more than one.
*/

int checkHeight(TreeNode root){
	if (root == null) return -1;

	int leftHeight = checkHeight(root.left);
	if(leftHeight == Integer.MIN_VALUE){
		return Integer.MIN_VALUE;
	}
	int rightHeight = checkHeight(root.right);
	if (rightHeight == Integer.MIN_VALUE){
		return Integer.MIN_VALUE;
	}
	Int dif = leftHeight - rightHeight;
	if (Math.abs(dif) > 1) {
		return Integer.MIN_VALUE;
	}
	else{
		return Math.max(leftHeight,rightHeight)+1;
	}
}

boolean ifBalanced(TreeNode root){
	return checkHeight(root) != Integer.MIN_VALUE;
}

/* O(n) time and O(H) space where H is the height of the tree

1. The general idea is, for each node, get the heights of the left subtree and right subtree and compare the heights. Return an error code if a tree is not balanced.
2. The height of a null tree is generally defined to be -1, so we use Integer.MIN_VALUE as an error code.
*/