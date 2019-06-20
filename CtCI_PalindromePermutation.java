/* 1.4 Palindrome Permutation: Given a string, write a function to check if it is a permutation of
a palindrome. A palindrome is a word or phrase that is the same forwards and backwards. A
permutation is a rearrangement of letters. The palindrome does not need to be limited to just
dictionary words.
EXAMPLE
Input: Tact Coa
Output: True (permutations:"taco cat'; "atco cta'; etc.) */


boolean isPalindromepermutation(String s){
	//if (s == null) { return false; }
	//int len = s.length();
	//if (len == 1) return true;
	int[] frequency = getFrequency(String s); //scan the string and count the number of each character
	return noMoreThanOneOdd(frequency); // to be a palindrome, there cannot be more than one odd count. We dont need to separate the even or odd length of the string since if the string length is even, there cannot be only one odd count. 
}

int[] getFrequency(String s){
	int[] result = new int[Character.getNumericValue('z') - Character.getNumericValue('a') + 1];
	char[] chrArray = s.toCharArray();
	for (char c : chrArray){
		int value = getNumericNum(c);
		if (value != -1){
			result[value]++;
		}
	}
	return result;
}

boolean noMoreThanOneOdd(int[] frequncy){
	boolean foundOdd = false;
	for (int i = 0; i < frequncy.length; i++){
		if (frequncy[i] % 2 == 1){
			if (foundOdd){
				return false;
			}
			foundOdd = true;
		}
	}
	return true;
}

// match a numeric value (0 - 25) for each character ('a' - 'z'). It is case insensitive - getNumericValue returns same value for lower and upper cases
int getNumericNum(Character chr){
	int a = Character.getNumericValue('a');
	int z = Character.getNumericValue('z');
	int c = Character.getNumericValue(chr);
	if (a <= c && c <= z){ //
		return c - a;
	}
	return -1; //return -1 for non-letter characters
}

/* 
1. A better solution is that we dont need to know the count of each character. All we need to know is whether the counts are odd. So we can use a bit vector to record the information by flipping the bit everytime the corresponding char shows. 

2. And there is an easy way to check whether there is no more than one odd. How to check whether a number is power of 2? -- only one bit is set. 00010000 --> 00010000 - 1 = 00001111 --> 00010000 & 00001111 = 0.

*/

boolean isPalindromepermutation2(String s){
	int bv = 0;
	char[] chrArray = s.toCharArray();
	for (char chr : chrArray){
		int chrNum = getNumericNum(chr);
		if (chrNum != -1){
			int mask = 1 << chrNum;
			if (bv & mask == 0) { //bit was not set
				bv |= mask;
			}
			else{
				bv &= ~mask;
			}
		}
	}
	return bv == 0 || bv & (bv - 1) == 0;
}

