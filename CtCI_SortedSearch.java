/* 10.4 Sorted Search, No Size: You are given an array-like data structure Listy which lacks a size
method. It does, however, have an elementAt(i) method that returns the element at index i in
O(1) time. If i is beyond the bounds of the data structure, it returns - 1. (For this reason, the data
structure only supports positive integers.) Given a Listy which contains sorted, positive integers,
find the index at which an element x occurs. If x occurs multiple times, you may return any index. */

int search(Listy listy, int num){
	if (listy == null || num < 0) {
		return -1;
	}

	/*
	if (listy.elementAt(0) == num) {
		return 0;
	}
	int start = 1;
	int end = -1;
	while ( end = -1){
		if (listy.elementAt(start) == num) {
			return start;
		}
		else if (listy.elementAt(start) != -1 && listy.elementAt(start) < num){
			start *= 2;
		}
		else{ //elementAt(start) == -1 || elementAt(start) > num
			end = start;
			start /= 2;
		}
	}
	*/
	int index = 1;
	while(listy.elementAt(start) != -1 && listy.elementAt(start) < num){  
		index *= 2;
	}
	return search(listy, num, index/2, index);
}

int search(Listy listy, int num, int start, int end){
	int mid;
	while (start <= end){
		mid = (start + end)/2;
		if (listy.elementAt(mid) == num){
			return mid;
		}
		else if (listy.elementAt(mid) > num || listy.elementAt(mid) == -1){
			end = mid-1;
		}
		else{
			start = mid+1;
		}
	}
	return -1;
}