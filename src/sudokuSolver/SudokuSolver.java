package sudokuSolver;

/*
 * - check sudoku cell for potential values
 * - 
 */
public class SudokuSolver {

	private SudokuCell[][] sudokuPuzzle = new SudokuCell[9][9];
	
	/*[[ 5 3 0 0 7 0 0 0 0 ]
	   [ 6 0 0 1 9 5 0 0 0 ]
	   [ 0 9 8 0 0 0 0 6 0 ]
	   [ 8 0 0 0 6 0 0 0 3 ]
	   [ 4 0 0 8 0 3 0 0 1 ]
	   [ 7 0 0 0 2 0 0 0 6 ]
	   [ 0 6 0 0 0 0 2 8 0 ]
	   [ 0 0 0 4 1 9 0 0 5 ]
	   [ 0 0 0 0 8 0 0 7 9 ]]
	  
	 * 
	 * 
	 * though: track confirmed values as a list of edges?? 
	 */
	
	
	public SudokuSolver(int[][] givenValues) {
		for (int row = 0; row < sudokuPuzzle.length; row++) {
			for (int col = 0; col < sudokuPuzzle[row].length; col++) {
				if (givenValues[row][col] != 0) {
					sudokuPuzzle[row][col] = new SudokuCell(givenValues[row][col]);
				}
				else {
					sudokuPuzzle[row][col] = new SudokuCell(); 
				}
			}
		}
	}
	
	/*
	 * scanning
	 */
	public void scanPuzzleForPossibilities() {
		scanEveryBox();
		scanEveryRow();
		scanEveryCol();
	}
	
	private void scanEveryBox() {
		for(int row = 0; row < sudokuPuzzle.length; row += 3) {
			for (int col = 0; col < sudokuPuzzle.length; col += 3) {
				scanBoxesFlexible(row, row + 3, col, col + 3);
			}
		}
	}
	
	private void scanBoxesFlexible (int rowFrom, int rowTo, int colFrom, int colTo) {
		for (int row = rowFrom; row < rowTo; row++) {
			for (int col = colFrom; col < colTo; col++) {
				if (sudokuPuzzle[row][col].getConfirmedValue() != 0) {
					for (int otherRow = rowFrom; otherRow < rowTo; otherRow++) {
						for (int otherCol = colFrom; otherCol < colTo; otherCol++) {
							sudokuPuzzle[otherRow][otherCol]
									.eliminatePossibility(sudokuPuzzle[row][col].getConfirmedValue());
						}
					}
				}
			}
		}
	}
	
	private void scanEveryRow() {
		for (int row = 0; row < sudokuPuzzle.length; row++) {
			scan1Row(row);
		}
	}
		
	private void scan1Row(int row) {
		for (int currCol = 0; currCol < sudokuPuzzle[row].length; currCol++) {
			if (sudokuPuzzle[row][currCol].getConfirmedValue() != 0) {
				for (int otherCol = 0; otherCol < currCol; otherCol++) {
						sudokuPuzzle[row][otherCol].eliminatePossibility(sudokuPuzzle[row][currCol].getConfirmedValue());
				}
				for (int otherCol = currCol + 1; otherCol < sudokuPuzzle[row].length; otherCol++) {
						sudokuPuzzle[row][otherCol].eliminatePossibility(sudokuPuzzle[row][currCol].getConfirmedValue());
				}
			}
		}
	}
	
	private void scanEveryCol() {
		for (int col = 0; col < sudokuPuzzle.length; col++) {
			scan1Col(col);
		}
	}
	
	private  void scan1Col(int col) {
		for (int currRow = 0; currRow < sudokuPuzzle.length; currRow++) {
			if (sudokuPuzzle[currRow][col].getConfirmedValue() != 0) {
				for (int otherRow = 0; otherRow < currRow; otherRow++) {
					sudokuPuzzle[otherRow][col].eliminatePossibility(sudokuPuzzle[currRow][col].getConfirmedValue());
				}
				for (int otherRow = currRow + 1; otherRow < sudokuPuzzle.length; otherRow++) {
					sudokuPuzzle[otherRow][col].eliminatePossibility(sudokuPuzzle[currRow][col].getConfirmedValue());
				}
			}
		}
	}
	
	public void deduceValuesEveryRow() {
		for (int row = 0; row < sudokuPuzzle.length; row++) {
			deduceValues1Row(row);
		}
	}
	
	public void deduceValues1Row(int row) {
		for (int col = 0; col < sudokuPuzzle[row].length; col++) {
			if (sudokuPuzzle[row][col].getConfirmedValue() == 0) {
				for (int possibleVal = 1; possibleVal <= 9; possibleVal++) {
					if (sudokuPuzzle[row][col].isPossibility(possibleVal)) {
						boolean originalVal = true;
						for (int otherCol = 0; otherCol < col; otherCol++) {
							if (sudokuPuzzle[row][otherCol].isPossibility(possibleVal)) {
								originalVal = false; 
								otherCol = col; 
							}
						}
						if (originalVal != false) {
							for (int otherCol = col + 1; otherCol < sudokuPuzzle[row].length; otherCol++) {
								if (sudokuPuzzle[row][otherCol].isPossibility(possibleVal)) {
									originalVal = false; 
									otherCol = sudokuPuzzle[row].length; 
								}
							}
						}
						if (originalVal == true) {
							sudokuPuzzle[row][col] = new SudokuCell(possibleVal);
						}
					}
				}	
			}
		}
	}
	
	public void deduceValuesEveryBox() {
		for(int row = 0; row < sudokuPuzzle.length; row += 3) {
			for (int col = 0; col < sudokuPuzzle.length; col += 3) {
				deduceValuesBoxes(row, row + 3, col, col + 3);
			}
		}
	}
	
	public void deduceValuesBoxes(int rowFrom, int rowTo, int colFrom, int colTo) {
		for (int row = rowFrom; row < rowTo; row++) {
			for (int col = colFrom; col < colTo; col++) {
				if (sudokuPuzzle[row][col].getConfirmedValue() == 0) {                //no confirmed val
					for (int possibleVal = 1; possibleVal <= 9; possibleVal++) {      //every possible val
						if(sudokuPuzzle[row][col].isPossibility(possibleVal)) {       // is it on this guys list?
							boolean originalVal = true;                              
							for (int otherRow = rowFrom; otherRow < rowTo; otherRow++) {
								for (int otherCol = colFrom; otherCol < colTo; otherCol++) {
									if ( !(row == otherRow && col == otherCol) && 
											( (sudokuPuzzle[otherRow][otherCol].getConfirmedValue() == possibleVal)
												|| (sudokuPuzzle[otherRow][otherCol].isPossibility(possibleVal)) ) ){  // is it on anyone elses list
										originalVal = false; 
										otherRow = rowTo;
										otherCol = colTo;
									}
								}	
							}	
							if (originalVal) {
								sudokuPuzzle[row][col] = new SudokuCell(possibleVal);
								possibleVal = 9; 
							}
						}
					}
				}
			}
		}
	}
	
	public int howManyUnsolved() {
		int tally = 0; 
		for (int row = 0; row < sudokuPuzzle.length; row++) {
			for (int col = 0; col < sudokuPuzzle[row].length; col++ ) {
				if (sudokuPuzzle[row][col].getConfirmedValue() == 0) {
					tally++;
				}
			}
		}
		return tally; 
	}
	
	@Override
	public String toString() {
		StringBuilder stringBuilder = new StringBuilder(); 
		for (int row = 0; row < sudokuPuzzle.length; row++) {
			for (int col = 0; col < sudokuPuzzle[row].length; col++) {
					stringBuilder.append(sudokuPuzzle[row][col].getConfirmedValue() + " ");
			}
			stringBuilder.append("\n");
		}
		return stringBuilder.toString();
	}
	
	public String toStringWithSolves(int [][] answerKey) {
		StringBuilder stringBuilder = new StringBuilder(); 
		int solvedRight = -31; 
		int solvedWrong = 0;
		for (int row = 0; row < answerKey.length; row++) {
			for (int col = 0; col < answerKey[row].length; col++) {
				if (sudokuPuzzle[row][col].getConfirmedValue() == 0) {
					stringBuilder.append("0 ");
				} else if(sudokuPuzzle[row][col].getConfirmedValue() == answerKey[row][col]) {
					stringBuilder.append(sudokuPuzzle[row][col].getConfirmedValue() + " ");
					solvedRight ++;
				} else {
					stringBuilder.append("X ");
					solvedWrong ++; 
				}
			}
			stringBuilder.append("\n");
		}		
		stringBuilder.append("\n solved right: " + solvedRight);
		stringBuilder.append("\n solved wrong: " + solvedWrong);
		
		return stringBuilder.toString();
	}
	
	/*
	 * 
	 */
	
	/*aybe should have done int rowFrom, int rowTo, int colFrom, int colTo
	private void scanBoxes() {
		//scan top 3 boxes
		for (int row = 0; row < 3; row++) {
			for (int col1 = 0; col1 < 3; col1++) {
				if (sudokuPuzzle[row][col1].getConfirmedValue() != 0) {
					for (int otherRow = 0; otherRow < 3; otherRow++) {
						for (int otherCol = 0; otherCol < 3; otherCol++) {
							sudokuPuzzle[otherRow][otherCol]
									.eliminatePossibility(sudokuPuzzle[row][col1].getConfirmedValue());
						}
					}
				}
			}
			
			for (int col2 = 3; col2 < 6; col2++) {
				if (sudokuPuzzle[row][col2].getConfirmedValue() != 0) {
					for (int otherRow = 0; otherRow < 3; otherRow++) {
						for (int otherCol = 3; otherCol < 6; otherCol++) {
							sudokuPuzzle[otherRow][otherCol]
									.eliminatePossibility(sudokuPuzzle[row][col2].getConfirmedValue());
						}
					}
				}
			}
			
			for (int col3 = 6; col3 < 9; col3++) {
				if (sudokuPuzzle[row][col3].getConfirmedValue() != 0) {
					for (int otherRow = 0; otherRow < 3; otherRow++) {
						for (int otherCol = 6; otherCol < 9; otherCol++) {
							sudokuPuzzle[otherRow][otherCol]
									.eliminatePossibility(sudokuPuzzle[row][col3].getConfirmedValue());
						}
					}
				}
			}
		}	
	}
	
	*/
}
