/* 1.5 One Away: There are three types of edits that can be performed on strings: insert a character,
remove a character, or replace a character. Given two strings, write a function to check if they are
one edit (or zero edits) away.
EXAMPLE
pale, ple -) true
pales, pale -) true
pale, bale -) true
pale, bae -) false */

/*
1. "one edit away" means that the length difference between two should be less or eqaul to one. We can combine the insert and remove because "remove one from the longer string" would be the same as "insert one into the shorter string". 

2. we can actually also combine replace and insert/remove into one method. Two methods are similar that to ensure that there is only one difference. The methods vary in how they deal with that only difference. This way can make the code more compact but maybe harder to follow. 
*/

boolean oneAway(String str1, String str2){
	int strLen1 = str1.length();
	int strLen2 = str2.length();

	if (Math.abs(strLen1 - strLen2) > 1) { //length difference is more than one
		return false;
	}
	else if (strLen1 == strLen2) {
		return oneReplaceAway(str1, str2);
	}
	else{
		String shortStr = strLen1 - strLen2 < 0 ? strLen1 : strLen2;
		String longStr = strLen1 - strLen2 < 0 ? strLen2 : strLen1;
		return oneInsertAway(shortStr,longStr);
	}
}

boolean oneReplaceAway(String s1, String s2){
	boolean foundDiff = false;
	for (int i = 0; i < s1.length(); i++){
		if (s1.charAt(i) != s2.charAt(i)){
			if (foundDiff){
				return false;
			}
			foundDiff = true;
		}
	}
	return true; // here, if the two strings are exactly same, we will return true. We can return foundDiff if zero edit is needed. 
}

boolean oneInsertAway(String short, String long){
	for (int i = 0, j = 0; i < short.length(), j < long.length(0); i++, j++){
		if (short.charAt(i) != long.charAt(j)){
			if (i != j){
				return false;
			}
			i += 1;
		}
	}
	return true;
}