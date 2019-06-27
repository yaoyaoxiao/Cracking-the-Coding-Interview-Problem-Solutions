/* 16.2 Word Frequencies: Design a method to find the frequency of occurrences of any given word in a
book. What if we were running this algorithm multiple times? */

int wordFrequency(String[] book, String word){
	if (book == null || word == null){
		return -1;
	}
	int count = 0;
	word = word.trim().toLowerCase();
	for (String w : book){
		if (w.trim().toLowerCase().equals(word)){
			count++;
		}
	}
	return count;
}

/*
1. runtime: O(n). We cannot bit this because we have to scan every word. 
2. if we need to run this for many times, we can build a hash table to save each word and its requency, such that we can get the frequency for any given word in O(1) time. Of course, it requires O(n) time and O(n) space to build the hash table.
*/

HashMap<String, Integer> buildFrequency(String[] book, String word){
	HashMap<String, Integer> frequencyTable = new HashMap<String, Integer>();
	for (String w : book){
		w = w.trim().toLowerCase();
		if (w != ""){
			if (!frequencyTable.containsKey(w)){
				frequencyTable.put(w,1);
			}
			else{
				frequencyTable.put(w, frequencyTable.get(w) + 1);
			}
		}
	}
	return frequencyTable;
}

int wordFrequencyFromHashTable(HashMap<String, Integer> map, String word){
	if (map == null || word == null){
		return -1;
	}
	word = word.trim().toLowerCase();
	if (map.containsKey(word)){
		return map.get(word);
	}
	return 0;
}