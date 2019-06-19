/* 1.3 URLify: Write a method to replace all spaces in a string with '%20: You may assume that the string
has sufficient space at the end to hold the additional characters, and that you are given the "true"
length of the string. (Note: if implementing in Java, please use a character array so that you can
perform this operation in place.)
EXAMPLE
Input: "Mr John Smith JJ, 13
Output: "Mr%20John%20Smith" */

/*
Need two scans to solve the problem: first scan to find all the whitespaces and calculate the length of the result string; second scan to replace whitespaces with '%20'. 
*/

void URLify(char[] s, int trueLen){
	int len = trueLen;
	for (int i = 0; i < trueLen; i++){
		if (s[i] == ' '){
			len += 2;
		}
	}
	int index = len-1;
	if (trueLen < s.length){s[trueLen] = '\0';}  //end array. is this necessary? 
	for (int j = trueLen-1; j >= 0; j--){
		if (s[j] == ' '){
			s[index--] = '0';
			s[index--] = '2';
			s[index--] = '%';
		}
		else{
			s[index--] = s[j];
		}
	}

}