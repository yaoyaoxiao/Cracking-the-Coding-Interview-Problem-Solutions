/* 17.14 Smallest K: Design an algorithm to find the smallest K numbers in an array. */

/*
1. One way to do this is just sort the array and then take the first K elements (O(n log n)).
2. A better way is to only sort K eletments, especially when k is much smaller than n - use a heap(priority queue) to firstly store the first k elements of the array, then for the rest, insert each element and update the heap if the element is samller than the root (O(n log k)).
3. since we dont care about the detialed order of the elements of the smallest K elements. We can use Selection Rank Algorithm - some idea similar to quick sort - take a pivot and separate the elements into two (when elements are unique) or three (when there are duplicates) portion and to see in which portion the 'ending' element would be.
*/

class SmallestKElements{
    private int[] findSmallestKElements(int[] arr, int k){
        if (k <= 0 || k > arr.length){
            throw new IllegalArgumentException();
        }
        int edge = findSmallestKElements(arr, k, 0, arr.length-1);
        int[] res = new int[k];
        int i = 0;
        //first add all the element smaller than edge into the result array
        while (i < k && arr[i] < edge){
            res[i] = arr[i];
            i++;
        }
        //if there are more space need to be filled, fill them up with edge
        while (i < k){
            res[i++] = edge;
        }
        return res;
    }

    private int findSmallestKElements(int[] arr, int k, int start, int end){
        int pivot = arr[(start+end)/2]; //pick the middle element as the pivot
        int[] partitionPositions = partition(arr, start, end, pivot);
        int smallersLen = partitionPositions[0];
        int pivotsLen = partitionPositions[1];

        if (k < smallersLen){ //edge is on the left portion
            return findSmallestKElements(arr, k, start, start+smallersLen-1);
        }
        else if (k < smallersLen + pivotsLen) { //edge is in the middle where all elements are pivots
            return pivot;
        }
        else { //edge is on the right side
            return findSmallestKElements(arr, k-smallersLen-pivotsLen, start + smallersLen + pivotsLen, end);
        }
    }

    private int[] partition(int[] arr, int start, int end, int pivot){
        int smaller = start, equal = start, larger = end;
        while(equal <= larger){
            if(arr[equal] < pivot){ //arr[smaller] is either equal to or smaller than pivot. Anyways, swap to make sure pivots are on the right side
                swap(arr, smaller, equal);
                smaller++;
                equal++;
            }
            else if(arr[equal] == pivot){
                equal++;
            }
            else{ //not sure about the current element on larger, swap so that larger has an element larger than pivot and equal has a new unknown element
                swap(arr,equal,larger);
                larger--;
            }
        }
        //return the size of smaller elements and equal elements
        return new int[]{smaller-start, larger-smaller+1};
    }

    private void swap(int[] arr, int i, int j){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args){
        SmallestKElements ske = new SmallestKElements();
        int[] input = {0,5,8,-2,90,3,4,3,3,5};
        int[] res = ske.findSmallestKElements(input,3);
        for(int i : res){
            System.out.println(i);
        }
    }
}
