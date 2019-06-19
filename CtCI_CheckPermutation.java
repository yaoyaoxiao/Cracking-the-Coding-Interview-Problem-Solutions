/* 1.2 Check Permutation: Given two strings, write a method to decide if one is a permutation of the
other. */

/*
1. one method might be sort both strings (O(n log n)) and then see if they are equal
*/

boolean isPermutation(String s1, String s2){
	if (s1.length() != s2.length()){
		return false;
	}
	String str1 = sortString(s1);
	String str2 = sortString(s2);
	return str1.equals(str2);
}

String sortString(String s){
	int char[] str = s.toCharArray();
	java.Util.Arrays.sort(str);
	return new String(str);
}

/* 
2. another method is to check whether these two strings contain the same # of each characters 
*/

// suppose it is using the ASKII (128 characters)
boolean isPermutation2(String s1, String s2){
	if (s1.length() != s2.length()){
		return false;
	}
	int[] flags = new int[128];
	for (int i = 0; i < s1.length(); i++){
		flags[s1.charAt(i) - 'a']++;
	}
	for (int j = 0; j < s2.length(); j++){
		int index = s2.charAt(j) - 'a';
		flags[index]--;
		if (flags[index] < 0) {
			return false;
		}

	}
	return true;
}
