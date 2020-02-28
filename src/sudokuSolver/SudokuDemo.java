package sudokuSolver;

public class SudokuDemo {

	public static void main(String [] args) {


		int [][] sudokuPuzzleHard = { { 5, 3, 0, 0, 7, 0, 0, 0, 0 }, 
				{ 6, 0, 0, 1, 9, 5, 0, 0, 0 },
				{ 0, 9, 8, 0, 0, 0, 0, 6, 0 },
				{ 8, 0, 0, 0, 6, 0, 0, 0, 3 },
				{ 4, 0, 0, 8, 0, 3, 0, 0, 1 },
				{ 7, 0, 0, 0, 2, 0, 0, 0, 6 },
				{ 0, 6, 0, 0, 0, 0, 2, 8, 0 },
				{ 0, 0, 0, 4, 1, 9, 0, 0, 5 },
				{ 0, 0, 0, 0, 8, 0, 0, 7, 9 }};

		int [][] easySudokuPuzzle = { { 0, 0, 0, 0, 0, 3, 6, 0, 0},
				{ 0, 3, 1, 6, 4, 2, 8, 0, 0},
				{ 0, 0, 7, 0, 9, 0, 0, 0, 4},
				{ 0, 0, 0, 4, 0, 7, 0, 9, 8},
				{ 0, 0, 0, 5, 0, 0, 0, 0, 6},
				{ 0, 0, 6, 0, 0, 0, 7, 0, 2},
				{ 0, 9, 0, 0, 0, 0, 5, 0, 7},
				{ 0, 4, 2, 0, 1, 0, 0, 0, 0},
				{ 6, 7, 0, 9, 0, 8, 4, 0, 0} };

		int [] [] solvedEasyPuzzle = { { 2, 5, 4, 8, 7, 3, 6, 1, 9},
				{ 9, 3, 1, 6, 4, 2, 8, 7, 5},
				{ 8, 6, 7, 1, 9, 5, 2, 3, 4},
				{ 3, 2, 5, 4, 6, 7, 1, 9, 8},
				{ 7, 8, 9, 5, 2, 1, 3, 4, 6},
				{ 4, 1, 6, 3, 8, 9, 7, 5, 2},
				{ 1, 9, 8, 2, 3, 4, 5, 6, 7},
				{ 5, 4, 2, 7, 1, 6, 9, 8, 3},
				{ 6, 7, 3, 9, 5, 8, 4, 2, 1} };



		SudokuPuzzle sudokuSolver = new SudokuPuzzle(sudokuPuzzleHard);

		System.out.println("Before Anything");
		System.out.println(sudokuSolver.howManyUnsolved());
		System.out.println(sudokuSolver.toStringWithSolves(solvedEasyPuzzle));

		/*
		sudokuSolver.scanEveryRow(); 
		System.out.println(sudokuSolver.howManyUnsolved());
		System.out.println(sudokuSolver.toString());

		sudokuSolver.scanEveryCol();
		System.out.println(sudokuSolver.howManyUnsolved());
		System.out.println(sudokuSolver.toString());

		sudokuSolver.scanAllBoxes();
		System.out.println(sudokuSolver.howManyUnsolved());
		System.out.println(sudokuSolver.toString());
		 */

		int i = 1; 
		while ( sudokuSolver.howManyUnsolved() > 0) {
			sudokuSolver.scanPuzzleForPossibilities();
			System.out.println(i + "scans");
			System.out.println(sudokuSolver.howManyUnsolved());
			System.out.println(sudokuSolver.toString());
//			System.out.println(sudokuSolver.toStringWithSolves(solvedEasyPuzzle));

			sudokuSolver.deduceValuesEveryRow(); 
			System.out.println(i + " row deductions");
			System.out.println(sudokuSolver.howManyUnsolved());
			System.out.println(sudokuSolver.toString());
//			System.out.println(sudokuSolver.toStringWithSolves(solvedEasyPuzzle));

			sudokuSolver.scanPuzzleForPossibilities();
			System.out.println(2*i + " scans");
			System.out.println(sudokuSolver.howManyUnsolved());
			System.out.println(sudokuSolver.toString());
//			System.out.println(sudokuSolver.toStringWithSolves(solvedEasyPuzzle));

			sudokuSolver.deduceValuesEveryBox();
			System.out.println(i + " box reductions");
			System.out.println(sudokuSolver.howManyUnsolved());
			System.out.println(sudokuSolver.toString());
//			System.out.println(sudokuSolver.toStringWithSolves(solvedEasyPuzzle));

			sudokuSolver.scanPuzzleForPossibilities();
			System.out.println( (3*i) + " scans");
			System.out.println(sudokuSolver.howManyUnsolved());
			System.out.println(sudokuSolver.toString());
//			System.out.println(sudokuSolver.toStringWithSolves(solvedEasyPuzzle));

			sudokuSolver.deduceValuesEveryCol();
			System.out.println(i + "col deductions");
			System.out.println(sudokuSolver.howManyUnsolved());
			System.out.println(sudokuSolver.toString());
//			System.out.println(sudokuSolver.toStringWithSolves(solvedEasyPuzzle));
			i++;
		}



	}


}
