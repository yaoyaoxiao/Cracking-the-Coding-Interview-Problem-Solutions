/* 8.1 Triple Step: A child is running up a staircase with n steps and can hop either 1 step, 2 steps, or 3
steps at a time. Implement a method to count how many possible ways the child can run up the
stairs. */

int countWays(int n){
	int[] counts = new int[n+1];
	Array.fill(counts, -1);
	return countWays(n, counts);
}

int countWays(int n, int[] counts){
	if (n < 0) {
		return 0;
	}
	else if (n == 0) {
		return 1;
	}
	else if (counts[n] > -1) {
		return counts[n];
	}
	else {
		counts[n] = countWays(n-1, counts) + countWays(n-2, counts) + countWays(n-3, counts);
		return counts[n];
	}
}

/*
1. if dont cache (dont use counts to memorize the previous calculated values), the algorithm is exponential since each call branches out to three recursion calles, O(3^n).

2. Cache the values so we can reduce the time complexity to O(n).

3. The number of ways will quickly overflow the bounds of an integer (when n = 37, the result has already overflowed). Using a long will delay but not completely solve this issue. A work around could be using a BigInteger class. 
*/