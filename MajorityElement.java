/* 17.10 Majority Element: A majority element is an element that makes up more than half of the items in
an array. Given a positive integers array, find the majority element. If there is no majority element,
return -1. Do this in O( N) time and O( 1) space.
Input: 1 2 5 9 5 9 5 5 5
Output: 5
*/

/*
1. If we cannot find a majority element in a previous subarray, we can temperarily dump this subsrray since the majority element still has the majority property and will still be found later in the remainders.
2. Iterate twice, the first scan finds out the candidate majority element, then the second scan count and validate it.
*/

public class MajorityElement{
    private int findMajorityElement(int[] array){
        int candi = findCandidate(array);
        if (validate(array, candi)){
            return candi;
        }
        return -1; //the input array has only positive integers, so return -1 if there is no majority element
    }

    private int findCandidate(int[] arr){
        int res = -1, count = 0;
        for (int i : arr){
            if (count == 0){ // "count == 0" means that previous elements are not majority element of the subarray
                res = i;
            }
            else if (i == res){
                count++;
            }
            else{
                count--;
            }
        }
        return res;
    }
    private boolean validate(int[] arr, int candi){
        int count = 0;
        for(int i : arr){
            if(i == candi){
                count++;
            }
        }
        return (count > arr.length/2);
    }

    public static void main(String args[]){
        MajorityElement m = new MajorityElement();
        int[] input = {1, 2, 5, 9, 5, 9, 5, 5, 5};
        int res = m.findMajorityElement(input);
        System.out.println(res);
    }
}