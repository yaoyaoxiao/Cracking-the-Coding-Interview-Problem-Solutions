/*
4.3 List of Depths: Given a binary tree, design an algorithm which creates a linked list of all the nodes
at each depth (e.g., if you have a tree with depth 0, you'll have 0 linked lists).
*/

ArrayList<LinkedList<TreeNode>> findLevelLinkedList(TreeNode root){
	ArrayList<LinkedList<TreeNode>> result = new ArrayList<LinkedList<TreeNode>>();
	LinkedList<TreeNode> preLevel = new LinkedList<TreeNode>();

	if (root == null) return result;

	preLevel.add(root); // the first level has only one node
	

	while (!preLevel.isEmpty()){
		result.add(preLevel); 
		LinkedList<TreeNode> curLevel = new LinkedList<TreeNode>();
		for (TreeNode parent:preLevel){
			if (parent.left != null){
				curLevel.add(parent.left);
			}
			if (parent.right != null){
				curLevel.add(parent.right);
			}
		}
		preLevel = curLevel;

	}
	return result;
}



/* O(n)

1. use a modification of BFS. use the previous level as the input for the next level.
*/