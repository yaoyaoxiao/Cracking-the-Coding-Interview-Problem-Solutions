/* 16.17 Contiguous Sequence: You are given an array of integers (both positive and negative). Find the
contiguous sequence with the largest sum. Return the sum.
EXAMPLE
Input 2, -8, 3, -2, 4, -10
Output 5 (i.e., {3, -2, 4}) */

/*
1. don't include negative subsequence -- when the sum is negative, reset sum to 0
2. if all elements are negative, return 0 (subsequence with zero elements). We can also return the largest element or report an error.
*/

int maxSumSubsequence(int[] nums){
	int maxSum = 0, positiveSum = 0;
	for (int num : nums){
		positiveSum += num;
		if (positiveSum > maxSum){
			maxSum = positiveSum;
		}
		else if (positiveSum < 0){
			positiveSum = 0;
		}
	}
	return maxSum;
}