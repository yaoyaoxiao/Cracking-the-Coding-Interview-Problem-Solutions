/* 10.10 Rank from Stream: Imagine you are reading in a stream of integers. Periodically, you wish
to be able to look up the rank of a number x (the number of values less than or equal to x).
Implement the data structures and algorithms to support these operations. That is, implement
the method track(int x), which is called when each number is generated, and the method
getRankOfNumber(int x), which returns the number of values less than or equal to x (not
including x itself).
EXAMPLE
Stream (in order of appearance): 5, 1, 4, 4, 5, 9, 7, 13, 3
getRankOfNumber(1) = 0
getRankOfNumber(3) = 1
getRankOfNumber(4) = 3 */

/*
1. Can use an array to hole all the elements in sorted order. Inserting would be expensive but getRankOfNumber would be very efficient since we can use a binary search and return its index.
2. A better way is to use a binary dearch tree, each node records the number of nodes that are on the left side (smaller numbers). So we dont need to count the size of the left subtree. 
Recursively, the algorithm is the following:
int getRank(Node node, int x) {
	if x is node. data, return node.leftSize()
	if x is on left of node, return getRank(node.left, x)
	if x is on right of node, return node.leftSize() + 1 + getRank(node.right, x)
}
*/

RankNode root = null;
void track(int num){
	if(root == null){
		root = new RankNode(num);
	}
	else{
		root.insert(num);
	}
}

int getRankOfNumber(int num){
	return root.getRank(num);
}

public class RankNode{
	public int left_size;
	public RankNode left, right;
	public int data;
	public Rank(int d){
		data = d;
	}
	public void insert(int d){
		if (d <= data){
			if (left == null){
				left = new RankNode(d);
			}
			else{
				left.insert(d);
			}
			left_size++;
		}
		else{
			if (right == null){
				right = new RankNode(d);
			}
			else {
				right.insert(d);
			}
		}
	}

	public int getRank(int d){
		if (d == data){
			return left_size;
		}
		else if (d < data){
			if (left == null){
				return -1;
			}
			else{
				return left.getRank(d);
			}	
		}
		else{
			int rightRank = right == null ? -1 : right.getRank(d);
			if (rightRank == -1) {
				return -1;
			}
			else {
				return left_size + 1 + rightRank;
			}
		}
	}
}

