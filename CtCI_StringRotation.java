/* 1.9 String Rotation: Assume you have a method isSubstring which checks if one word is a substring
of another. Given two strings, s1 and s2, write code to check if s2 is a rotation of s1 using only one
call to isSubstring (e.g., "waterbottle" is a rotation of "erbottlewat"). */

boolean isRotation(String s1, String s2){
	if (s1 == null || s2 == null || s1.length() != s2.length()){ //has to be same length
		return false;
	}
	String twoS1 = s1+s1; //catenate two s1 and then check whether s2 is a substring of it
	return isSubstring(twoS1, s2);
}