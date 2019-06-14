/*  10.3 Search in Rotated Array: Given a sorted array of n integers that has been rotated an unknown
number of times, write code to find an element in the array. You may assume that the array was
originally sorted in increasing order.

EXAMPLE
Input find 5 in {15, 16, 19, 20, 25, 1, 3,4,5,7,10, 14}
Output 8 (the index of 5 in the array)  */

int searchInRotatedArray(int[] arr, int num){
	return searchInRotatedArray(arr, num, 0, arr.length-1);
}

int searchInRotatedArray(int[] arr, int num, int start, int end){
	if (start > end) {
		return -1;
	}
	int mid = (start + end)/2;
	if (arr[mid] == num) {
		return mid;
	}
	else if (arr[mid] > arr[start]) { //left part is normally ordered
		if (arr[mid] > num && arr[start] <= num) {
			return searchInRotatedArray(arr, num, start, mid-1);
		}
		else{
			return searchInRotatedArray(arr, num, mid+1, end);
		}
	}
	else if (arr[mid] < arr[start]) { //right part is normally ordered
		if (arr[mid] < num && arr[end] >= num){
			return searchInRotatedArray(arr, num, mid+1, end);
		}
		else{
			return searchInRotatedArray(arr, num, start; mid-1);
		}
	}
	else { //arr[mid] == arr[start], one part is all repeats. If arr[end] != arr[mid], we search the right side, otherwise, we need to seach both sides.
		int result = searchInRotatedArray(arr, num, mid+1, end);
		if (result != -1) {
			return result;
		}
		else {
			return searchInRotatedArray(arr, num, start; mid-1);
		}
	}
	return -1;
}

/*
1. the key idea is to modify binary search, compare arr[start] and arr[mid] to decide which half is normally ordered and which side we should search. the tricky part is when arr[start] == arr[mid].
2. If the elements are unique, the runtime is O(log n). With many duplicates, the runtime is O(n) since we will often need to search both sides. 
*/

