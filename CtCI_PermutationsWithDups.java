/* 8.8 Permutations with Dups: Write a method to compute all permutations of a string whose characters
are not necessarily unique. The list of permutations should not have duplicates. */

ArrayList<String> allPermutationsWithDups(String str){
	ArrayList<String> result = new ArrayList<String>();

	//calculate the frequency for each character
	HashMap<Character, Integer> charFrequency = new HashMap<Character, Integer>();
	for (char c : str){
		if (!charFrequency.containsKey(c)){
			charFrequency.put(c, 1);
		}
		else {
			charFrequency.put(c, charFrequency.get(c) + 1);
		}
	}
	allPermutationsWithDups(charFrequency, str.length(), "", result);
	return result;
}

void allPermutationsWithDups(HashMap<Character, Integer> charFrequency, int remainder, String prefix, ArrayList<String> result){
	if (remainder == 0){ //all the characters are added, add the permutation into the result arraylist
		result.add(prefix);
		return;
	}
	for (Character chr : charFrequency.keySet()){
		int count = charFrequency.get(chr);
		if (count > 0){
			//for each distinct character, add it to each permutation generated from the remaining characters
			charFrequency.put(chr, charFrequency.get(chr) - 1);
			allPermutationsWithDups(charFrequency, remainder-1, prefix + chr, result);
			charFrequency.put(chr, charFrequency.get(chr) + 1);
		}
	}
}

/*
1. in the situation with duplicates, first get the frequency map of the characters. For each distinct character, we can remove this character and get all the sub-permutations generated with the remaining characters, and then add this character at the front to get all the permutations starting with this character. 

2. Always remember to update the requency hash table.
*/