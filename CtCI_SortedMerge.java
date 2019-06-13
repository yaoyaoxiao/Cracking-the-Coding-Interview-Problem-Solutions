/* 10.1 Sorted Merge: You are given two sorted arrays, A and B, where A has a large enough buffer at the
end to hold B. Write a method to merge B into A in sorted order. */

void mergeArrays(int[] arrA, int numA int[] arrB, int numB){
	int posA = numA - 1;
	int posB = numB - 1;
	int pos = numA + numB -1;
	while (posB >= 0) {
		if (posA >= 0 && arrA[posA] > arrB[posB]){
			arrA[pos] = arrA[posA];
			posA--;
		}
		else{
			arrA[pos] = arrA[posB];
			posB--;
		}
		pos--;
	}
}

/* After running out of elements in B, we dont need to move contents in A since they are already in place. */