/* 16.16 Sub Sort: Given an array of integers, write a method to find indices m and n such that if you sorted
elements m through n, the entire array would be sorted. Minimize n - m (that is, find the smallest
such sequence).
EXAMPLE
Input: 1, 2, 4, 7, 10, 11, 7, 12, 6, 7, 16, 18, 19
Output: (3, 9) */

/*
1. find the left increase ordered part and the max of it; find the right decrease ordered part and the min of it;
2. find the min and max of the middle part, and compare them with the left-max and right-min to enlarge the range until the mid-min >= left-max and mid-max <= right-min.
*/

void findMinRangeOfSubSort(int[] arr){
	if (arr == null || arr.length <= 1) {
		return; 
	}
	//find the left-max of the left increase ordered part
	int leftMaxIndex = 1;
	while(leftMaxIndex < arr.length){
		if (arr[leftMaxIndex] < arr[leftMaxIndex -1]){
			leftMaxIndex -= 1;
			break;
		}
		leftMaxIndex++;
	}
	if (leftMaxIndex >= arr.length - 1) { //already sorted;
		return;
	}

	//find the right-min of the right decrease ordered part
	int rightMinIndex = arr.length-2;
	while(rightMinIndex >= 0) {
		if (arr[rightMinIndex] > arr[rightMinIndex + 1]){
			rightMinIndex += 1;
		}
		rightMinIndex--;
	}

	//find the mid-min and mid-max
	int midMinIndex = leftMaxIndex+1, midMaxIndex = rightMinIndex-1;
	for (int i = leftMaxIndex+1; i < rightMinIndex; i++){
		if (arr[i] < arr[midMinIndex]){
			midMinIndex = i;
		}
		if (arr[i] > arr[midMaxIndex]){
			midMaxIndex = i;
		}
	}

	//push the range left until we found the position where all the left elements are smaller than the mid-min
	while (leftMaxIndex >= 0 && arr[leftMaxIndex] > arr[midMinIndex]){
		leftMaxIndex--;
	}

	//push the range right until we found the position where all the right elements are larger than the mid-max
	while(rightMinIndex <= arr.length -1 && arr[rightMinIndex] < arr[midMaxIndex]){
		rightMinIndex++;
	}

	System.out.prinln(leftMaxIndex + " " + rightMinIndex);
}

/* public class Pair{
	public int left;
	public int right;
	public Pair(int l, int r){
		left = l;
		right = r;
	}
} */