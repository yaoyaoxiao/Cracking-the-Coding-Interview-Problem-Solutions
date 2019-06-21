/* 1.6 String Compression: Implement a method to perform basic string compression using the counts
of repeated characters. For example, the string aabcccccaaa would become a2blc5a3. If the
"compressed" string would not become smaller than the original string, your method should return
the original string. You can assume the string has only uppercase and lowercase letters (a - z). */

String compressString(String str){
	int len = str.length();
	if (len <= 1) return str;
	StringBuilder sb = new StringBuilder();
	int count = 1;
	for (int i = 1; i < len; i++){
		if (i + 1 >= len || str.charAt(i) != str.charAt(i-1)){
			sb.append(str.charAt(i-1));
			sb.append(count);
			count = 1;
		}
		else{
			count++;
		}
	}
	return len < sb.length() ? str : sb.toString();
}

//since build a new string is expensive, if we dont have a large number of repeating characters, we can first scan the string first to check whether the new string would be shorter than the original string. If so, build the new string, or else just return the original string. 