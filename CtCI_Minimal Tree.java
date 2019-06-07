/*
4.2 Minimal Tree: Given a sorted (increasing order) array with unique integer elements, write an algorithm
to create a binary search tree with minimal height.

*/

TreeNode createMinimalBST(int array[]){
	return createMinimalBST(array, 0, array.length - 1);
}

TreeNode createMinimalBST(int arr[], int start, int end){
	if (end < start){
		return null;
	}
	int mid = (start + end)/2;
	TreeNode n = new TreeNode(mid);
	n.left = createMinimalBST(arr, start, mid-1);
	n.right = createMinimalBST(arr, mid+1, end);
	return n;


/* O(n)

1. the root is the middle of the array
2. use root.insertNode(int v) method would insert and traverse the tree to get to the right position (O(n log n)). Since the array it ordered, we can build the tree directly
3. very easy to make little off-by-one errors, be sure to test thoroughly.
*/