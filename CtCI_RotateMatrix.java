/* 1.7 Rotate Matrix: Given an image represented by an NxN matrix, where each pixel in the image is 4
bytes, write a method to rotate the image by 90 degrees. Can you do this in place? */

boolean rotateMatrix(int[][] matrix){
	if (matrix.length == 0 || matrix.length != matrix[0].length) {
		return false;
	}
	int n = matrix.length;
	for (int row = 0; row < n/2; row++){
		int first = row;
		int last = n - 1 - row;
		for (int col = first; col < last; col++){
			int offset = col - first;

			int top = matrix[first][col]; // remember top

			matrix[first][col] = matrix[last-offset][first]; // left --> top
			matrix[last-offset][first] = matrix[last][last-offset]; // bottom --> left
			matrix[last][last-offset] = matrix[col][last]; // right --> bottom
			matrix[col][last] = top; // top --> right
		}
	}
	return true;
}