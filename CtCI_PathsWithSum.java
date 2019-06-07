/* 4.12 Paths with Sum: You are given a binary tree in which each node contains an integer value (which
might be positive or negative). Design an algorithm to count the number of paths that sum to a
given value. The path does not need to start or end at the root or a leaf, but it must go downwards
(traveling only from parent nodes to child nodes). */

/*
1. we can use the brute force mathod which traverses to each node and try all paths downwards. Increase the count when a matching sum is found. The runtime is O( n log n) in a balanced tree.

2. Below is a better method by using a hash map to track the running sum and using "running sum - target sum" to find the number of nodes with the "needed sum". So the hash map stores the running sums (from root to a node) and the number of times this running sum appears.

3. Since we just care about the count of paths, we ignore the start/end nodes of a path.

4. Notice that when running sum is equal to target sum, an additional path from root is found. We should increament the total count by 1. 

*/


int countPathWithSum(TreeNode root, int targetSum){
	return countPathWithSum(root, targetSum, 0, new HashMap<integer, integer>());
}

int countPathWithSum(TreeNode root, int targetSum, int curSum, HashMap<Integer, Integer> map){
	if (root == null) return 0; //base case

	//count paths with sum ending at the current node, using curSum - targetSum = needSum
	curSum += root.data;
	int needSum = curSum - targetSum;
	int result = map.getOrDefault(needSum,0);

	//if curSum equals targetSum, we got a path from root, add it to the result
	if (curSum == targetSum) {
		result ++;
	}

	//update hashmap, recurse, and then restore hashmap
	updateHashMap(map, curSum, 1);
	result += countPathWithSum(root.left, targetSum, curSum, map);
	result += countPathWithSum(root.right, targetSum, curSum, map);
	updateHashMap(map, curSum, -1);

	return result;
}

int updateHashMap(HashMap<Integer, Integer> map, int key, int num){
	int newCount = map.getOrDefault(key, 0);
	if (newCount == 0) { //remove when zero to reduce space usage
		map.removeKey(key);
	}
	else{
		map.put(key, newCount);
	}
}

