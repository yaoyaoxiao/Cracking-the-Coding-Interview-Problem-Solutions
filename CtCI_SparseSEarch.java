/* 10.S Sparse Search: Given a sorted array of strings that is interspersed with empty strings, write a
method to find the location of a given string.
EXAMPLE
Input: ball, {"at", "", "", "", "ball", "","","car", "","","dad","",""}
Output: 4 */

int searchString(String[] arr, String str){
	if (arr == nul || str == null || str == "") {
		return -1;
	}
	return searchString(arr, str, 0, arr.length-1);
}

int searchString(String[] arr, String str, int low, int high){
	ind mid;
	while (low <= high){
		mid = (low + high)/2;
		if (arr[mid] == ""){
			int left = mid-1;
			int right = mid+1;
			while (true){
				if (left < low && right > high){
					return -1;
				}
				else if (right <= high && arr[right] != ""){
					mid = right;
					break;
				}
				else if (left >= first && arr[left] != ""){
					mid = left;
					break;
				}
				left--;
				right++;
			}
		}
		if (str.equals(arr[mid])){
			return mid;
		}
		else if (arr[mid].compareTo(str) < 0){
			low = mid+1;
		}
		else{
			high = mid-1;
		}
	}
	return -1;
}

/*
1. modiy binary search, if the mid is an empty string, find its nearest non-empty element and use that as the mid.
2. worst case runtime is O(n).
*/