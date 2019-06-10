/* 8.5 Recursive Multiply: Write a recursive function to multiply two positive integers without using the
* operator. You can use addition, subtraction, and bit shifting, but you should minimize the number
of those operations. */

int recursiveMultiply(int a, int b){
	int bigger = a > b ? a : b;
	int smaller = a > b ? b : a;

	return recursiveMultiplyHelper(int smaller, int bigger){
		if (smaller == 0) {
			return 0;
		}
		else if (smaller == 1){
			return bigger;
		}

		int halfSmaller = smaller >> 1; //divide by 2
		int halfResult = recursiveMultiplyHelper(halfSmaller, bigger);
		if (smaller % 2 == 0){
			return halfResult + halfResult;
		}
		else{
			return halfResult + halfResult + bigger;
		}
	} 
}

/*
1. the idea is to recursively calculate half of the product and add itself.

2. For uneven numbers, just add an extra bigger.
*/