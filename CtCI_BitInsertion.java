/* 5.1 Insertion: You are given two 32-bit numbers, N and M, and two bit positions, i and
j. Write a method to insert M into N such that M starts at bit j and ends at bit i. You
can assume that the bits j through i have enough space to fit all of M. That is, if
M = 10011, you can assume that there are at least 5 bits between j and i. You would not, for
example, have j = 3 and i = 2, because M could not fully fit between bit 3 and bit 2.
EXAMPLE
Input: N = 10000000000, M = 10011, i = 2, j = 6
Output:N = 10001001100 */

/*
1. first clear bits from i to j for N
2. shift M for i bits and then AND the two nums
*/

int BitsInsertion(int N, int M, int i, int j){
	// if (i < 0 || i > 31 || j < 0 || j > 31)

	//clear bits from i to j
	/*for (int b = i; b <= j; b++){
		N &= ~(1 << b);
	}*/

	int left = ((~0) << (j+1); // ~0 is all ones, left shift j + 1 bits, so we get something like 11100000
	int right = ((1 << i) - 1) // 1 << i generates something like 00000100, then -1, we get 00000011
	int mask = left | right; // bit j through j are zeros, others are ones, 11100011
	N &= mask; //cleared N 

	//left shift M for i bits
	M <<= i;

	//AND N and M
	N |= M;

	return N;
}