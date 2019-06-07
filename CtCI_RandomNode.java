/* 4.11 Random Node: You are implementing a binary tree class from scratch which, in addition to
insert, find, and delete, has a method getRandomNode() which returns a random node
from the tree. All nodes should be equally likely to be chosen. Design and implement an algorithm
for getRandomNode, and explain how you would implement the rest of the methods. */

/*
Since all nodes should be qually likely to be chosen, if the size is n, then every node has a chance of 1/n --> the odds of picking from the left side is leftSize * 1/n; Likewise, the odds of picking from the right side is rightSize * 1/n. 

To use this approach, we first need to know the size - add a size variable for each node; Second, we can do a recursion to pick a random side until we get a specific node. 
*/

public class TreeNode{
	private int data;
	public TreeNode left;
	public TreeNode right;
	private int size = 0;

	public TreeNode(int d){
		data = d;
		size = 1;
	}

	public TreeNode getRandomNode(){
		int leftSize = left == null ? 0 : left.size();
		Random r = new Random();
		int index = r.nextInt(size);
		if (size < leftSize) {
			return left.getRandomNode();
		}
		else if (size == leftSize){
			return this;
		}
		else{
			return right.getRandomNode();
		}
	}

	public void insertInOrder(int d){
		if (d <= data){
			if (left ==null){
				left = new TreeNode(d);
			}
			else {
				left.insertInOrder(d);
			}
		}
		else {
			if (right == null){
				right = new TreeNode(d);
			}
			else{
				right.insertInOrder(d);
			}
		}
		size++;
	}

	public int size(){ return size; }
	public int data(){ return data; }

	public TreeNode find(ind d){
		if (d == data){
			return this;
		}
		else if (d <= data){
			return left != null ? left.find(d) : null;
		}
		else {
			return right != null? right.find(d) : null;
		}
		return null;
	}
}

/* 
1. The time complexity of the above would be O(log n) if it is a balanced tree where n is the number of the nodes. 
2. Random number calls can be expensive, how can we reduce the number of random numbers calls? We can just use the random number we get for the first time. If it goes to right side, we can just minus leftSize + 1. The other way to think about this is that the ramdon number, i, we get for the first time decides which node, the ith node, we will return.
*/

public class Tree{
	TreeNode2  root= null;
	public int size() {
		return (root == null ? 0 : root.size());
	}
	public TreeNode2 getRandomNode(){
		if (root == null){
			return null;
		}
		Random r = new Random();
		int i = r.nextInt(size());
		return root.getIthNode(i);
	}
	public void insertInOrder(d){
		if (root == null){
			root = new TreeNode2(d);
		}
		else{
			root.insertInOrder(d);
		}
	}
}

public class TreeNode2 {
	private int data;
	private int size = 0;
	public TreeNode2 left;
	public TreeNode2 right;
	public TreeNode2(int d){
		data = d;
		size = 1;
	}

	public TreeNode2 getIthNode(i){
		int leftSize = left == null ? 0 : left.size();
		if (i < leftsize){
			return left.getIthNode(i);
		}
		else if (i == leftSize){
			return this;
		}
		else{
			return right.getIthNode(i - (leftSize+1)); //skpping over leftSize+1 nodes
		}
	}

	public int data(){ return data; }
	public int size(){ return size; }
	public void insertInOrder(int d){
		if (d <= data){
			if (left ==null){
				left = new TreeNode2(d);
			}
			else{
				left.insertInOrder(d);
			}
		}
		else{
			if (right ==null){
				right = new TreeNode2(d);
			}
			else{
				right.insertInOrder(d);
			}
		}
		size++;
	}

	public TreeNode2 find(int d){
		if (d == data){
			return this;
		}
		else if (d <= data && left != null){
			return left.find(d);
		}
		else if (d > data && right != null){
			return right.find(d);
		}
		return null;
	}

}