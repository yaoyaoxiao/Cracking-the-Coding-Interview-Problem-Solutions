/* 17.5 Letters and Numbers: Given an array filled with letters and numbers, find the longest subarray with
an equal number of letters and numbers. */

/*
1. we can build a hash table mapping the cumulative difference between letters and numbers to the indexes
2. if the difference at k is -1, which means we need one less number to balance, then if we can find a position which also has difference of -1, the number of letters and numbers must be the same inbetween.
3. so, when iterating the array, we track differences and if we found a index with same difference with a previous index, check and update the max/result if the subarray between these two indexes is an longer suarray.
*/
import java.util.HashMap;

public class LettersAndNumbers{
    private int[] findLongestEqualSub (char[] arr){
        HashMap<Integer,Integer> map  = new HashMap<>();
        map.put(0,-1); // at the beginning, the number of letters and nums are equal, so we put (0,0) into the map
        int[] res = new int[2];
        int maxLen = 0;
        int numOfLetters = 0, numOfNums = 0;
        for (int i = 0; i < arr.length; i++){
            if (Character.isLetter(arr[i])){
                numOfLetters++;
            }
            else if (Character.isDigit(arr[i])){
                numOfNums++;
            }
            int diff = numOfLetters - numOfNums;
            if (!map.containsKey(diff)){
                map.put(diff,i);
            }
            else{
                int start = map.get(diff); // the first time when the same difference shows
                int curLen = i - start;
                if (maxLen < curLen){ //update the maxLen, start and end positions if find a longer subarray
                    maxLen = curLen;
                    res[0] = start+1;
                    res[1] = i;
                }
            }
        }
        return res;
    }

    public static void main(String[] args){
        char[] testArr = {'a','1','1','a','a','1','a','1','1','1','a'};
        LettersAndNumbers l = new LettersAndNumbers();
        int[] res = l.findLongestEqualSub(testArr);
        for (int i : res){
            System.out.println(i);
        }
    }
}