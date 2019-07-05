/* 16.19 Pond Sizes: You have an integer matrix representing a plot of land, where the value at that location
represents the height above sea level. A value of zero indicates water. A pond is a region of water
connected vertically, horizontally, or diagonally. The size of the pond is the total number of
connected water cells. Write a method to compute the sizes of all ponds in the matrix.

EXAMPLE
Input:
0 2 1 0
0 1 0 1
1 1 0 1
0 1 0 1
Output: 2, 4, 1 (in any order) */

/*
1. everytime we found a zero/water, we explore to find all the connected water by using recursive calls.
2. mark the visited position as -1, if we dont want to modify the original matrix, we can use a extra boolean matrix to record visited positions.
*/

ArrayList<Integer> pondSizes(int[] land){
	ArrayList<integer> res = new ArrayList<Integer>();
	if (land == null || land.length == 0){
		return res;
	}
	for (int i = 0; i < land.length; i++){
		for (int j = 0; j < land[0].length; j++){
			int size = sizeOfConnectedWater(land, i, j);
			if (size > 0){
				res.add(size);
			}
		}
	}
	return res;
}

int sizeOfConnectedWater(int[] land, int i, int j){
	if (i < 0 || i >= land.length || j < 0 || j >= land[0].length || land[i][j] != 0) {
		return 0;
	}
	int res = 1;
	land[i][j] = -1;

	//we can skip the mid position, (i,j), but it is not necessary since it would return 0 immediately. 
	for (int r = i-1; r <= i+1; r++){
		for (int c = j-1; c <= j+1; c++){
			res += sizeOfConnectedWater(land, r, c);
		}
	}
	return res;
}