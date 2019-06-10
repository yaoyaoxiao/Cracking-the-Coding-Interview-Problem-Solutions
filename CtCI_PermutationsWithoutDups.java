/* 8.7 Permutations without Dups: Write a method to compute all permutations of a string of unique
characters. */

/* take the first char out, generate all permutations for the remainder, insert the first to each position */

ArrayList<String> allPermutaionsNoDup(String s){
	if (s == null) {
		return null;
	}

	ArrayList<String> result = new ArrayList<String>();

	if (s.length() == 0){
		result.add(s);
		return result;
	}
	
	char chr = s.charAt(0);
	String remainder = s.substring(1);
	ArrayList<String> subPerms = allPermutaionsNoDup(remainder);
	for (String subStr : subPerms){
		for (int i = 0, i < subStr.length(); i++){
			String pre = subStr.substring(0,i);
			String post = subStr.substring(i);
			result.add(pre + chr + post);
		}
	}
	return result;
}

