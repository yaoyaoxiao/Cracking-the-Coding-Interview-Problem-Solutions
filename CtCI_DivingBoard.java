/* 16.11 Diving Board: You are building a diving board by placing a bunch of planks of wood end-to-end.
There are two types of planks, one of length shorter and one of length longer. You must use
exactly K planks of wood. Write a method to generate all possible lengths for the diving board. */

/*
1. the number of planks is fixed, k, so we can only control the lengths by arranging the number of the shorter and the longer
2. easily, we know the length range (k * shorter ~ k * longer). Assume that we initially used x shorters, then we can switch shorter to longer one by one, thus every switch, we get a new length of k * shorter + (longer - shorter), until all k planks are longers. 
3. we will have k+1 different lengths if shorter != longer. Otherwise, we have only 1 length.
*/

int[] divingBoardLengths(int shorter, int longer, int k){
	if (shorter == longer) {
		int len = k * shorter;
		return {len};
	}
	int[] res = new int[k+1];
	for (int i = 0; i <= k; i++){
		res[i] = Longer * i + shorter * (k-i); //when we use i longers, k-i shorters would be used
	}
	return res;
}

