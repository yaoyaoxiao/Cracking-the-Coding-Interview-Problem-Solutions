/* 16.6 Smallest Difference: Given two arrays of integers, compute the pair of values (one value in each
array) with the smallest (non-negative) difference. Return the difference.
EXAMPLE
Input{1,3,15,11,2}, {23,127,235,19,8}
Output 3. That is, the pair (11, 8). */

/* 
1. first sort these two arrays (O(n log n) where n is the size of the larger array)
2. interate through the array to find the min difference. specifically, use a variable, min, remember the current min, move the pointer which pointing to the smaller number to the next to try to get a new 'min'. (O(n) where n is the size of the larger array)
*/

int smallestDiff(int[] arr1, int[] arr2){
	Arrays.sort(arr1);
	Arrays.sort(arr2);

	int p1 = 0, p2 = 0;
	int min = Interger.MAX_VALUE;
	while (p1 < arr1.length && p2 < arr2.length){
		if (arr1[p1] < arr2[p2]){
			min = arr2[p2] - arr1[p1] < min ? arr2[p2] - arr1[p1] : min;
			p1++;
		}
		else{
			min = arr1[p1] - arr2[p2] < min ? arr1[p1] - arr2[p2] : min;
			p2++;
		}
	}
	return min;
}

