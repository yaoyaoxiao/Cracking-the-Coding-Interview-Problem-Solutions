/* 10.11 Peaks and Valleys: In an array of integers, a "peak" is an element which is greater than or equal
to the adjacent integers and a "valley" is an element which is less than or equal to the adjacent
integers. For example, in the array {5, 8, 6, 2, 3, 4, 6}, {8, 6} are peaks and {5, 2} are valleys. Given an
array of integers, sort the array into an alternating sequence of peaks and valleys.
EXAMPLE
Input: {5, 3, 1,2, 3}
Output: {5, 1,3,2, 3} */
void peaksAndValleys(int[] arr){
	if (arr == null || arr.length <=2){
		return arr;
	}
	int sign = arr[0] - arr[1] >= 0 ? 1 : -1;
	int index = 2;
	while (index < arr.length){
		sign *= -1;
		if ((sign < 0 && arr[index] <= arr[index-1]) || (sign > 0 && arr[index] >= arr[index-1])){
			index++;
		}
		else{
			int temp = arr[index];
			arr[index] = arr[index-1];
			arr[index-1] = temp;
			index++;
		}
	}
}

/*
The above algorithm uses a varibale, sign, to track the next element should be peak or valley, if the sign and the value dont match, swap arr[index] and arr[index-1]. Will this break the previously sorted part? It is not an issue here because:
1. If the next should be valley then arr[index-1] is a peak, if now arr[index] > arr[index-1] and we swap, arr[index-1] will be an even "higher" peak and arr[index] becomes valley.
2. Similarly, if the next should be peak then arr[index-1] is a valley, if now arr[index] < arr[index-1] and after swap, arr[index-1] will be a an even "lower" valley and arr[index] becomes peak. 

A similar method is to compare three consecutive elements and swap the MAX of them with the center elements and make it a peak. We know that peaks are shown every two elements, so we jump two elements at a time.
*/

void sortPeakValley(int[] arr){
	if (arr == null || arr.length <=2){
		return arr;
	}
	for (int i = 1; i < arr.length; i += 2){
		int maxIndex = findMaxIndex(arr, i-1, i, i+1);
		if (i != maxIndex){ //swap the Max and the center element
			int temp = arr[i];
			arr[i] = arr[maxIndex];
			arr[maxIndex] = temp;
		}
	}
}

int findMaxIndex(int[] arr, int a, int b, int c){
	int aNum = a >= 0 && a < arr.length ? arr[a] : Integer.MIN_VALUE;
	int bNum = b >= 0 && b < arr.length ? arr[b] : Integer.MIN_VALUE;
	int cNum = c >= 0 && c < arr.length ? arr[c] : Integer.MIN_VALUE;

	int maxNum = Math.max(aNum, Math.max(bNum, cNum));
	if (aNum == maxNum){
		return a;
	}
	else if (bNum == maxNum){
		return b;
	}
	else{
		return c;
	}
}