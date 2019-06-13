/* 10.2 Group Anagrams: Write a method to sort an array of strings so that all the anagrams are next to
each other. */

public class anagramComparator implements Comparator<String>{
	public String sortString(String s){
		char[] chrArr = s.toCharArray();
		Array.sort(chrArr);
		return new String(chrArr);
	}

	public int compare(String s1, String s2){
		return sortString(s1).compareTo(sortString(s2));
	}
}

void sortStringArray(String[] strArr){
	return Array.sort(strArr, new anagramComparator());
}

/* 
1. this would sort the charaters in each string and compare the sorted version to sort strings. This will take O(n log n) time.
2. we dont need to fully sort the array. We just need to group the strings by anagram. So, the algorithm below uses a hash table which maps the sorted version of the string to a list of its anagrams. Once all the words are added into these lists, we add them back to the array. This is a modification of a bucket sort.
*/

void sortStringArray2(String[] strArr){
	HashMapList<String,String> mapList = new HashMapList<String, String>();
	for (String s : strArr){
		String key = sortString(s);
		mapList.put(key, s);
	}

	int index = 0;
	for (String k : mapList.keySet()){
		ArrayList<String> strings = mapList.get(k);
		for (String str : strings){
			strArr[index] = str;
			index++;
		}
	}
}

void sortString(String s){
	char[] chrArr = s.toCharArray();
	Array.sort(chrArr);
	return new String(chrArr);
}

/* HashMapList<String, String> is a Hash Map that maps from Strings to ArrayList<String>. */