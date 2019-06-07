/* 4.8 First Common Ancestor: Design an algorithm and write code to find the first common ancestor
of two nodes in a binary tree. Avoid storing additional nodes in a data structure. NOTE: This is not
necessarily a binary search tree. */

/* Does a node have access to its parent? 
- If so, compare the path length, move the one up with the longer path to the same length, move both up until they are the same node, the first common ancestor, or NULL, which means there is no common ancestor. */

TreeNode firstCommonAncestor(TreeNode root, TreeNode p, TreeNode q){
	int pathDif = pathLength(root, p) - pathLength(root, q);
	TreeNode deepNode = pathDif > 0? p : q;
	TreeNode shallowNode = pathDif > 0? q : p;

	while (pathDif > 0 && deepNode != null){
		deepNode = deepNode.parent;
		pathDif--;
	}

	while (deepNode != shallowNode && deepNode != null && shallowNode != null){
		deepNode = deepNode.parent;
		shallowNode = shallowNode.parent;
	}

	if (deepNode == null || shallowNode == null) return null;
	return deepNode;
}

int pathLength(TreeNode root, TreeNode node){
	int r = 0;
	while (node != null){
		node = node.parent;
		r++;
	}
	return r;
}

/*
1. Time complexity: O(H) where H is the depth of the deeper node.
2. We can instead trace p's path upwards and check if any parent covers q. This could receive better worst case runtime. O(t) where t s the size of the subtree for the first common ancestor. In the worst case, it is O(n).
*/
TreeNode firstCommonAncestor2(TreeNode root, TreeNode p, TreeNode q){
	// check if either node is not in the tree, or it one covers the other
	if (!covers(root, p) || !covers(root, q)) {
		return null;
	}
	if (covers(p, q)) {
		return p;
	}
	if (covers(q, p)) {
		return q;
	}

	// Traverse upwards until we find a node that covers q.
	TreeNode sibling = getSibling(p);
	TreeNode parent = p.parent;
	while(!covers(sibling, q)){
		sibling = getSibling(parent);
		parent = parent.parent;
	}
	return parent;
}

Boolean covers(TreeNode root, TreeNode node){
	if (root == null) return false;
	if (root == node) return true;
	return covers(root.left, node) || covers (root.right, node);
}

TreeNode getSibling(TreeNode node){
	if (node == null || node.parent == null) {
		return null;
	}
	TreeNode parent = node.parent;
	return parent.left == node ? parent.right:parent.left;
}

/*
- If no link to its parent, we can search the entire tree to find these two nodes and their first common ancestor, which is the first time where they are not on the same subtree. 

1. return p, if subtree includes p and not q
2. return q, if subtree includes q but not p
3. return null, if neither p nor q are in the subtree
4. else, return the common ancestor of p and q
5. be careful about cases where 1) p is a child of q (or q is a child of p); 2) p is in the tree but q is not (or q is in the tree but p is not)
*/

public class Result{
	public TreeNode node;
	public boolean isAnc;
	public Result(TreeNode n, Boolean isAncestor){
		node = n;
		isAnc = isAncestor;
	}
}

TreeNode firstCommonAncestorNoParentLink(TreeNode root, TreeNode p, TreeNode q){
	Result r = commonAncestorHelper(root, p, q);
	if (r.isAnc){
		return r.node;
	}
	return null;
}

Result commonAncestorHelper()(TreeNode root, TreeNode p, TreeNode q){
	if (root == null) {
		return new Result(null, false);
	}
	if (root == p && root == q) {
		return new Result(root, true);
	}

	Result lr = commonAncestorHelper(root.left, p, q);
	if (lr.isAnc) {
		return lr;
	}

	Result rr = commonAncestorHelper(root.right, p, q);
	if (rr.isAnc){
		return rr;
	}

	if (lr.node != null && rr.node != null) {
		return new Result(root, true);
	}
	else if (root == p || root == q){
		//if we are currently at p or q, and we also found one of those nodes in a subtree, then this is truly an ancestor and the flag should be true. 
		boolean isAncestor = lr.node != null || rr.node != null;
		return new Result(root, isAncestor);
	}
	else{
		return new Result (lr.node != null? lr.node : rr.node, false);
	}
}
