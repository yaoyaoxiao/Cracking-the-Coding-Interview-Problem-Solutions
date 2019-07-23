/* 17.15 Longest Word: Given a list of words, write a program to find the longest word made of other words
in the list. */

/*
1. Sort the list so that we can start from the longest word and check them one by one until we found a result
2. recursively, separate the word into two parts in all possible ways. If the first part is a word of the list(build a hash table for easy checking), check whether the second part can be made of words in the list. 
3. Use a dynamic programming/memoization to cache the results between calls so that we dont need to repeatedly check if there is a way to build a word.  
*/

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;


public class LongestWord{
    private String findLongestComboWord(String[] arr){
        sortStringsByLength(arr); //sort the list by length
        HashMap<String, Boolean> map = buildMap(arr); //Build a hash table for quick checking and caching
        for (int i = arr.length-1; i >= 0; i--){ //from the longest to the shortest, check each word, returnt the first one (longest) that can be made of words from the original list.
            if (isComboWord(arr[i],true, map)){
                return arr[i];
            }
        }
        return "";
    }

    private boolean isComboWord(String str, boolean isOriginal, HashMap<String,Boolean> map){
        if (map.containsKey(str) && !isOriginal) {
            return map.get(str);
        }
        for(int i = 1; i < str.length(); i++){
            String first = str.substring(0,i);
            String second = str.substring(i);

            if (map.containsKey(first) && map.get(first) && isComboWord(second, false, map)){
                return true;
            }
        }

        map.put(str,false);
        return false;
    }

    private HashMap<String,Boolean> buildMap(String[] arr){
        HashMap<String, Boolean> res = new HashMap<>();
        for (String str : arr){
            if (!res.containsKey(str)){
                res.put(str,true);
            }
        }
        return res;
    }

    private void sortStringsByLength(String[] arr){
        Arrays.sort(arr, new LenComparator());
    }

    private class LenComparator implements Comparator<String> {
        public int compare(String s1, String s2){
            return Integer.compare(s1.length(), s2.length());
        }
    }

    public static void main(String args[]){
        LongestWord lw = new LongestWord();
        String[] list = {"a", "b", "aa", "ab", "abaa", "abb"};
        String res = lw.findLongestComboWord(list);
        System.out.println("The Longest Combo Word: " + res);
    }
}