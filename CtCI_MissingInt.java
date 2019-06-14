/* 10.7 Missing Int: Given an input file with four billion non-negative integers, provide an algorithm to
generate an integer that is not contained in the file. Assume you have 1 GB of memory available for
this task.
FOLLOW UP
What if you have only 10MB of memory? Assume that all the values are distinct and we now have
no more than one billion non-negative integers. */

long numOfInt = ((long)Integer.MAX_VALUE)+1; // Total number of integers. Assume it is ints rather than longs, there are a total of 2^32, 4 billion, distinct integers possible and 2^31 non-negative integers. So, we know that the input file has some duplicates. 

byte[] bitfield = new byte[(int)(numOfInt/8)]; // We have 1 GB of memory, or 8 billion bits, available. So we can map all possible integers to a distinct bit. Create a bit vector with 4 billion bits. A bit vector is an array that compactly stores boolean values by using an array of ints (or another data type). Each int represents 32 boolean values.

String file = ...

void findMissingInt() throws FileNotFoundException{
	Scanner in = new Scanner(new FileReader(file));
	while (in.hasNextInt()){ //scan all the numbers and set the bit
		int n = in.nextInt();
		bitfield[n/8] |= 1 << (n%8);
	}

	//scan the bit vector and return the first index with value of 0.
	for (int i = 0; i < bitfield.length; i++){
		for (int j = 0; j < 8; j++){ 
			if (bitfield[i] & (1 << j) == 0){
				System.out.println (i*8+j);
				return;
			}
		}
		
	}
}


/*
for the follow up question, we can do a two-pass solution:
1. since all integers are distinct, scan all the numbers to count the number of integers in different ranges. We know how many distinct integers in each range, so if the count is less than that, we know there is/are missing numbers. 
2. The second pass then do the same thing as the above algorithm but just in a single range. 

How big the ranges should be?  -- select a value for rangeSize such that the memory from the first pass (the array) and the
second pass (the bit vector) fit.

What if, as a follow up question, you are asked to solve the problem with even less memory? In this case, we
can do repeated passes using the approach from the first step. We'd first check to see how many integers
are found within each sequence of a million elements. Then, in the second pass, we'd check how many integers
are found in each sequence of a thousand elements. Finally, in the third pass, we'd apply the bit vector.
*/
