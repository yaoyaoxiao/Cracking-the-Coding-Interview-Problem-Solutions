/* 10.8 Find Duplicates: You have an array with all the numbers from 1 to N, where N is at most 32,000. The
array may have duplicate entries and you do not know what N is. With only 4 kilobytes of memory
available, how would you print all duplicate elements in the array? */

void findDuplicates(int[] arr){
	BitSet bs = new BitSet(32000);
	for (int i = 0; i < arr.length-1; i++){
		int num = arr[i] -1; // the numbers in the array start from 1 while the bitset starts from 0 
		if(bs.get(num){
			System.out.println(arr[i] + " ");
		}
		else{
			bs.set(num);
		}
	}
}

public class BitSet{
	int[] bs;
	public BitSet(int bitSize){
		bs = new int[(bitSize >> 5) + 1]; //devide by 32
	} 

	boolean get(int num){
		int posNum = (num >> 5); // devide by 32
		int bitNum = (num & 0x1F); // mode by 32
		return bs[posNum] & (1 << bitNum) != 0;
	}

	void set(int num){
		int posNum = (num >> 5);
		int bitNum = (num & 0x1F);
		bs[posNum] |= 1 << bitNum;
	}
}

/*
1. First, we need to do a calculation. We have 4 kilobytes of memory available, which means we have 2^10 * 4 * 8 bits which is more than N (at most 32000). So we can use a BitSet to flag a bit for each integer. 
2. To make it clear, we build a Bit Vector class to hold a large bit vector for this problem. We might be asked to use Java's built in BitSet class. 
*/