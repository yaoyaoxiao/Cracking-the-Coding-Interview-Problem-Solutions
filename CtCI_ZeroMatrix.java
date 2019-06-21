/* 1.8 Zero Matrix: Write an algorithm such that if an element in an MxN matrix is 0, its entire row and
column are set to 0. */

void zeroMatrix(int[][] matrix){

	//remember whether the first row has zero
	boolean rowOneHasZero = false;
	for (int i = 0; i < matrix.length; i++){
		if (matrix[i][0] == 0){
			rowOneHasZero = true;
			break;
		}
	}
	//remember whether the first column has zero
	boolean colOneHasZero = false;
	for (int i = 0; i < matrix[0].length; i++){
		if (matrix[0][i] == 0){
			colOneHasZero = true;
			break;
		}
	}

	// check the rest (the whole matrix except for the first row and first column). If found a zero, mark the elements on the first row and column with the same row/column number to 0.
	for (int i = 1; i < matrix.length; i++){
		for (int j = 1; j < matrix[0].length; j++){
			if (matrix[i][j] == 0){
				matrix[i][0] = 0;
				matrix[0][j] = 0;
			}
		}
	}

	// check the first column, if found a zero, make the row all zeros
	for (int i = 1; i < matrix.length; i++){
		if (matrix[i][0] == 0) {
			for (int j = 0; j < matrix[0].length; j++){
				matrix[i][j] = 0;
			}
		}
	}

	// check the first row, if found a zero, make the column all zeros
	for (int j = 1; j < matrix[0].length; j++){
		if (matrix[0][j] == 0){
			for (int i = 0; i < matrix.length; i++){
				matrix[i][j] = 0;
			}
		}
	}

	//if originally the first row/column has zero, make the entire first row/column all zeros.
	if (rowOneHasZero){
		for (int j = 0; j < matrix[0].length; j++){
				matrix[0][j] = 0;
			}
	}
	if (colOneHasZero){
		for (int i = 0; i < matrix.length; i++){
				matrix[i][0] = 0;
			}
	}
}