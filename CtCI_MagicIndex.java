/* 8.3 Magic Index: A magic index in an array A [e ... n -1] is defined to be an index such that A[i] =
i. Given a sorted array of distinct integers, write a method to find a magic index, if one exists, in
array A.
FOLLOW UP
What if the values are not distinct? */

int magicIndex(int[] a){
	return magicIndex(a, 0, a.length-1);
}

int magicIndex(int[] a, int start, int end){
	if (end < start) {
		return -1;
	}
	int mid = (start + end)/2;
	if (a[mid] == mid) {
		return mid;
	}
	else if (a[mid] < mid){
		return magicIndex(a, mid+1, end);
	}
	else{
		return magicIndex(a, start, mid-1);
	}
}

/* Why the binary search works? Because the array is sorted and all elements are distinct. So, if the middle element is too small, then when we move to the left, substracting k indexes and (at least) k values, all subqequent element will also be too samll. Same for moving right when mid is too large. 

Now, what if the values are not distinct? */

int magicIndex2(int[] a) {
	return magicIndex2(a, 0, a.length-1);
}

int magicIndex2(int[] a, int start, int end){
	if (end < start) {
		return -1;
	}
	int mid = (start + end)/2;
	int midValue = a[mid];

	if (mid == midValue){
		return mid;
	}

	/* we need to search both the left side and the right side. if midValue < mid, we can skip bounch of elements and search between start to midValue (a[mid]) since a[mid] is the first element that could be a magic index. 

	so, the we can resursively search the left and right sides as follows: 
	1. left side: search indices start through Math.min(mid-1, midvalue) 
	2. right side: search indices Math.max(mid+1, midvalue) through end.

	Note that in the below code, the method operates almost identically to the case when all the elements are distinct. 
	*/
	int leftIndex = Math.min(mid-1, midValue);
	int leftResult = magicIndex2(a, start, leftIndex);
	if (leftResult != -1) {
		return leftResult;		
	}

	int rightIndex = Math.max(mid+1, midValue);
	return magicIndex2(a, rightIndex, end);
}