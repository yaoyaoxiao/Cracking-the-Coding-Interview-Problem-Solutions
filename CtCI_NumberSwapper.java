/* 16.1 Number Swapper: Write a function to swap a number in place (that is, without temporary variables). */

void swapNum(int a, int b){
	a = a - b; // a now is the difference between original a and b
	b = b + a; // b equals to the original a now;
	a = b - a; // original a (now is b) - the difference between the original a and b =  original b
}

/*
1. we can use bit manipulation to solve this. This way works for other data types than just integers.
2. Will use XOR to solve this since: a^b will set those bits where a and b are different. And, XORing a bit with 1 always flips the bit, whereas XORing with 0 will never change it. So we can XOR the result of  a^b to get the original a and b
*/

void swapNum2(int a, int b){
	a ^= b;  // all the bits where original a and b are different are set to 1, others are set to 0
	b ^= a;  // b equals to the original a now since if the bit is 1, originally different, the bit on b woule flip so that this bit is the same to the bit on original a; otherwise, original a and b are the same on this bit, this bit remains.
	a ^= b;  // similar to the above, if originally same, remains the same; if originally different, flip the bit and become the same as the original b; 
}
