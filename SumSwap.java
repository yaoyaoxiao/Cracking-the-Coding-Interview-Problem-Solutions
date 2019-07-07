/* 16.21 Sum Swap: Given two arrays of integers, find a pair of values (one value from each array) that you
can swap to give the two arrays the same sum.
EXAMPLE
Input:{4, 1, 2, 1, 1, 2}and{3, 6, 3, 3}
Output: {1, 3} */

/*
1. calculate the sum for both arrays and get the difference between two swap elements which is (sum1-sum2)/2;
2. brute force way is to iterate each element of array1 and check each element of array2 to see if their difference matches (O(n1*n2))
3. to improve the runtime, we can build a hash set on array2, so that we can iterate array1 and find out whether array2 has a match in constant time. (the runtime for this algorithm: O(n1+n2))
4. if both arrays are sorted, we can save the space of making a hash set. But if they are unsorted, the runtime would be O(n1 log n1 + n2 log n2)
*/

import java.util.HashSet;
import java.lang.Integer;

public class SumSwap{
    static int[] swapElements(int[] arr1, int[] arr2){
        int sumDiff = getSumDiff(arr1, arr2);
        if(sumDiff %2 != 0) { //the difference has to be even
            return null;
        }
        int eleDiff = sumDiff/2;
        HashSet<Integer> arr2Set = buildHashSet(arr2);
        for (int i : arr1){
            if (arr2Set.contains(i+eleDiff)){
                int[] res = {i, i+eleDiff};
                return res;
            }
        }
        return null;
    }

    static HashSet<Integer> buildHashSet(int[] arr){
        HashSet<Integer> res = new HashSet<>();
        for (int i : arr){
            res.add(i);
        }
        return res;
    }

    static int getSum(int[] arr){
        int sum = 0;
        for (int i : arr){
            sum += i;
        }
        return sum;
    }

    static int getSumDiff(int[] arr1, int[] arr2){
        return getSum(arr2) - getSum(arr1);
    }

    public static void main(String[] args){
        int[] a1 = {1,2,1,1,2,4};
        int[] a2 = {3,6,3,3};
        int[] result = swapElements(a1,a2);
        if (result != null){
            for(int i : result){
                System.out.println(i);
            }
        }
    }
}
