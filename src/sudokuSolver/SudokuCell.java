package sudokuSolver;

/*
 * What we need to be able to do with a cell 
 * - track if there is a confirmed value, 
 * ---> shown with confirmed Value
 * - (maybe) evaluate what it could possibly be
 * - track what values it could possibly be
 */
public class SudokuCell {

	int [] potentialValues = new int[9]; 
	int confirmedValue = 0;
	
	// if there is no confirmed number, fiull the cell with possibilities
	public SudokuCell() {
		for (int i = 0; i < potentialValues.length; i++) {
			potentialValues[i] = (i + 1);
		}
	}
	
	// if theres already a confirmed value given for the cell, just instantiate it and 
	// be done
	public SudokuCell(int confirmedValue) {
		this.confirmedValue = confirmedValue;
		potentialValues = null; 
	}
	
	// once a possible # becomes impossible, 
	public void eliminatePossibility(int value) {
		if (potentialValues != null){
			potentialValues[value - 1] = 0;	
			updateConfirmedValue();
		}
	}
	
	public boolean isPossibility(int value) {
		
		boolean isPossibility; 
		if	((potentialValues != null) && (potentialValues[value-1] == value)) {
			isPossibility = true;
		} else {
			isPossibility = false; 
		}
		return isPossibility; 
	}
	
	// for when you know that the # of potential values has decreased, and you need 
	/* the size of the array to be shortened
	private int[] updatePotentialValuesLength(int deletedValue) {
		
		int[] currentPotentialValues = potentialValues; 
		
		int[] newPotentialValues = new int[currentPotentialValues.length - 1];
		
		for (int i = 0; i < (deletedValue - 1); i++) {
			newPotentialValues[i] = currentPotentialValues[i];
		}
		
		for (int i = (deletedValue - 1); i < newPotentialValues.length; i++) {
			newPotentialValues[i] = currentPotentialValues[i + 1];
		}
		potentialValues = newPotentialValues; 
	}
	*/
	
	public void updateConfirmedValue() {
		int tally = 0; 
		int value = 0; 
		
		for (int i = 0; i < potentialValues.length; i++) {
			if (potentialValues[i] != 0) {
				tally++; 
				value = potentialValues[i];
			}
		}
		
		if (tally == 1) {
			potentialValues = null; 
			confirmedValue = value; 
		}
	}
	
	public int getConfirmedValue() {
		return confirmedValue; 
	}
	/*
	public void setConfirmedValue(int value) {
		confirmedValue = value; 
		potentialValues = null; 
	}
	*/
	@Override
	public String toString() {
		StringBuilder stringBuilder = new StringBuilder();
		
		if (potentialValues != null) {
		stringBuilder.append("PotentialValues in Cell: [ ");
		
		for (int i = 0; i < potentialValues.length; i++) {
			if (potentialValues[i] != 0) {
				stringBuilder.append(potentialValues[i]+ " ");
			}
		}
		stringBuilder.append("]");
		}
		else {
			stringBuilder.append(confirmedValue);
		}
		return stringBuilder.toString(); 
	}
}
