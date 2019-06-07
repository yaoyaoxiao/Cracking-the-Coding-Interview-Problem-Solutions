/* 8.4 Power Set: Write a method to return all subsets of a set. */

ArrayList<ArrayList<Integer>> allSubsets(ArrayList<Integer> set){
	ArrayList<ArrayList<Integer>> allSubs = new ArrayList<ArrayList<Integer>>();
	allSubs.add(new ArrayList<Integer>()); //empty set

	for (int i = 0; i < set.size(); i++){ 
		int item = set.get(i);
		ArrayList<ArrayList<Integer>> newSubs = new ArrayList<ArrayList<Integer>>();
		//add the current item into each previously found subsets and add these new generated subsets into allSubs and iterate to the next item
		for (ArrayList<Integer> sub : allSubs){
			ArrayList<Integer> newSub = new ArrayList<Integer>();
			newSub.addAll(sub);
			newSub.add(item);
			newSubs.add(newSub);
		}
		allSubs.addAll(newSubs);
	}
	return allSubs;
}

/*
1. First understand that the best time/space is actually the total number of elements across all of those subsets. Since each element can choose to BE or NOT TO BE in a subset, so there are 2^n subsets and each element will be contained in half of these subsets (2^(n-1) subsets). Therefore, the total numner of elements across all those subsets is n*2^(n-1). Which means we will not be able to beat O(n2^n) in time or space complexity.

2. How to create P(3) from P(2) where P(n) is the power set (all the subsets) of a set with n elememts? We can simply clone the subsets in P(2) and add the third element to them. 

3. The above iterative solution is O(n2^n) in time and space. We can also use recursion which cost slightly more time and space.
*/

/*
- Another solution is representing each subset as a binary string since each member can be either in a subset (Yes - 1) or not (No - 0). 

- Generate all subsets, then, really just comes down to generating all binary numbers(all integers). We interate through all numbers from 0 to 2^n (exclusive) and traslate the binary representation of the numbers into a set. 

- There is nothing substaincially better or worse about this solution compared to the first one.
*/

ArrayList<ArrayList<Integer>> allSubsets2(ArrayList<Integer> set){
	ArrayList<ArrayList<Integer>> allSubs = new ArrayList<ArrayList<Integer>>();
	int max = 1 << set.size(); //compute 2^n

	for (int k = 0; k < max, k++){
		ArrayList<Integer> subSet = convertIntToSub(k, set);
		allSubs.add(subSet);
	}
	return allSubs;
}

ArrayList<Integer> convertIntToSub(int x, ArrayList<Integer> set){
	ArrayList<Integer> sub = new ArrayList<Integer>();
	int i = 0;
	for (int k = x; k > 0; k >>= 1){
		if (k & 1 == 1){
			subSet.add(set.get(i));
		}
		i++;
	}
	return subset;
}
