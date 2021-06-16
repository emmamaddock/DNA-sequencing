package projectElements;

import java.util.*;

public class Matrix extends DNA {

	private int matchScore;
	private int mismatch;
	private int gapPenalty;
	private int rowLength;
	private int columnLength;
	private int[][] matrix;
	private char[][] tracebackMatrix;
	String shorter;
	String longer;

	Matrix initial;
	Matrix NW;
	Matrix traceback;

	// default constructor for Matrix
	public Matrix() {
		this.matchScore = 0;
		this.mismatch = 0;
		this.gapPenalty = 0;
		this.rowLength = 0;
		this.columnLength = 0;
		this.matrix = new int[0][0];
		this.longer = null;
		this.shorter = null;
		this.tracebackMatrix = null;
	}

	// Matrix constructor that uses the shorter and longer DNA strands and their
	// lengths
	public Matrix(int match, int mismatch, int gap, int j, int i, String longer, String shorter) {
		this.matchScore = match;
		this.mismatch = mismatch;
		this.gapPenalty = gap;
		this.rowLength = j + 1;
		this.columnLength = i + 1;
		this.matrix = new int[i + 1][j + 1];
		this.longer = longer;
		this.shorter = shorter;
		this.tracebackMatrix = new char[i + 1][j + 1];
	}

	// this method accepts parameters from the user to create the NW matrix
	// also takes longer and shorter strands from DNA objects for row/column
	// length
	public void acceptParameters(int j, int i, String longer, String shorter) {

		@SuppressWarnings("resource")
		Scanner scan = new Scanner(System.in);

		int match = 0;
		System.out.println("Please input algorithm parameters Match Score, Mismatch Penalty, and Gap Penalty.");

		// this loop makes sure that the match score is 0 or higher 
		// and that it's only an int
		do {
			System.out.println("Please enter a match score 0 or higher.");
			System.out.println("Match Score: ");

			try {
				match = scan.nextInt();
			}
			catch (InputMismatchException e) {
				System.out.println("That was not an acceptable input, please try again.");
				scan.next();
			}
		} while (match <= 0);

		// also a loop that handles both wrong input and a mismatch score higher than 0
		int mis = 1;
		do {
			System.out.println("Please enter a mismatch penalty of 0 or less: ");
			System.out.println("Mismatch Penalty: ");

			try { 
				mis = scan.nextInt();
			}
			catch (InputMismatchException f) {
				System.out.println("That was not an acceptable input, please try again.");
				scan.next();
			}

		} while (mis > 0);

		// also a loop that handles wrong input and a gap score higher than 0
		int gap = 1;
		do {
			System.out.println("Please enter a gap penalty of 0 or less: ");
			System.out.println("Gap Penalty: ");

			try {
				gap = scan.nextInt();
			}
			catch (InputMismatchException g) {
				System.out.println("That was not an acceptable input, please try again.");
				scan.next();
			}
		} while (gap > 0);

		System.out.println(" ");

		// new initial matrix object with all the parameters provided by the
		// user
		this.initial = new Matrix(match, mis, gap, j, i, longer, shorter);

		// populate initial matrix with 0's in rows and columns
		for (int k = 0; k < i + 1; k++) {
			for (int z = 0; z < j + 1; z++) {
				initial.matrix[k][z] = 0;
			}
		}

		this.longer = longer;
		this.shorter = shorter;

		this.matchScore = match;
		this.mismatch = mis;
		this.gapPenalty = gap;

		this.rowLength = longer.length();
		this.columnLength = shorter.length();

		// creating and populating the NW Matrix using the parameters
		this.NW = new Matrix(match, mis, gap, j, i, longer, shorter);
		this.traceback = new Matrix(match, mis, gap, j, i, longer, shorter);

		// the very leftmost corner is ALWAYS 0
		NW.matrix[0][0] = 0;

		// populating the first row and column with the gap penalty
		for (int u = 0; u < j; u++) {
			NW.matrix[0][u + 1] = gap * (u + 1);
		}

		for (int p = 0; p < i; p++) {
			NW.matrix[p + 1][0] = gap * (p + 1);
		}

		// filling the Needleman-Wunsch matrix using the algorithm
		// as the algorithm is executed, the traceback matrix will also be
		// created
		for (int q = 0; q < i; q++) {
			for (int r = 0; r < j; r++) {
				NW.matrix[q + 1][r + 1] = algorithm(q, r, NW.matrix[q][r], NW.matrix[q][r + 1], NW.matrix[q + 1][r]);
			}
		}

		// populating the first row and column of the traceback matrix

		traceback.tracebackMatrix[0][0] = 'D';
		for (int u = 0; u < j; u++) {
			traceback.tracebackMatrix[0][u + 1] = 'L';
		}

		for (int p = 0; p < i; p++) {
			traceback.tracebackMatrix[p + 1][0] = 'U';
		}

	}

	// getters and setters
	public double getMatchScore() {
		return matchScore;
	}

	public void setMatchScore(int matchScore) {
		this.matchScore = matchScore;
	}

	public double getMismatch() {
		return mismatch;
	}

	public void setMismatch(int mismatch) {
		this.mismatch = mismatch;
	}

	public double getGapPenalty() {
		return gapPenalty;
	}

	public void setGapPenalty(int gapPenalty) {
		this.gapPenalty = gapPenalty;
	}

	public int getRowLength() {
		return rowLength;
	}

	public void setRowLength(int rowLength) {
		this.rowLength = rowLength;
	}

	public int getColumnLength() {
		return columnLength;
	}

	public void setColumnLength(int columnLength) {
		this.columnLength = columnLength;
	}

	public Matrix getInitial() {
		return initial;
	}

	public void setInitial(Matrix initial) {
		this.initial = initial;
	}

	public Matrix getNW() {
		return NW;
	}

	public void setNW(Matrix nW) {
		NW = nW;
	}

	public int[][] getMatrix() {
		return matrix;
	}

	public void setMatrix(int[][] matrix) {
		this.matrix = matrix;
	}

	// prints the initial NW matrix based on the DNA strand lengths, and filled
	// with 0s
	public void printInitializedMatrix() {

		System.out.print(" " + " " + " " + " ");

		for (int i = 0; i < longer.length(); i++) {
			System.out.print(longer.toUpperCase().charAt(i) + " ");
		}

		System.out.println();
		System.out.print(" " + " ");

		for (int i = 0; i < initial.columnLength; i++) {
			for (int j = 0; j < initial.rowLength; j++) {
				System.out.print(initial.matrix[i][j] + " ");
			}
			System.out.println();
			if (i < initial.columnLength - 1) {
				System.out.print(shorter.toUpperCase().charAt(i) + " ");
			}
		}
	}

	// prints the filled NW Matrix, that used the NW algorithm to fill it
	public void printNWMatrix() {

		// detecting the format to print based on the gap penalty value
		// since the - adds another character

		// this part prints the longer and shorter DNA strands along the matrix
		if (gapPenalty == 0) {
			System.out.print(" " + " " + " " + " ");
		} else {
			System.out.print(" " + " " + " " + " " + " " + " ");
		}

		if (gapPenalty == 0) {
			for (int i = 0; i < longer.length(); i++) {
				System.out.print(longer.toUpperCase().charAt(i) + " ");
			}
		} else {
			for (int i = 0; i < longer.length(); i++) {
				System.out.print(longer.toUpperCase().charAt(i) + " " + " ");
			}
		}

		System.out.println();

		// again, detecting format based on value
		if (gapPenalty == 0) {
			System.out.print(" " + " ");
		} else {
			System.out.print(" " + " " + " ");
		}

		// now printing the actual matrix
		for (int i = 0; i < NW.columnLength; i++) {
			for (int j = 0; j < NW.rowLength; j++) {
				// in row 2 on, print 2 spaces if gap penalty is not 0
				if (gapPenalty == 0) {
					System.out.print(NW.matrix[i][j] + " ");
				} else {
					System.out.print(NW.matrix[i][j] + " ");
					if (i > 0) {
						System.out.print(" ");
					}
				}
			}
			System.out.println();
			if (i < NW.columnLength - 1) {
				System.out.print(shorter.toUpperCase().charAt(i) + " ");
			}
		}

	}

	// executes the NW algorithm: determines which block gives the highest score
	// and fills the NW cell with that score
	// also populates the traceback matrix with the location of which block gave
	// the highest score
	public int algorithm(int a, int b, int diag, int up, int left) {
		int diagScore, leftScore, upScore, max;

		// checks whether to assign a match or a mismatch to the current DNA
		// letters
		if (shorter.charAt(a) == longer.charAt(b)) {
			diagScore = diag + matchScore;
		} else {
			diagScore = diag + mismatch;
		}

		upScore = up + gapPenalty;
		leftScore = left + gapPenalty;

		// determines the maximum value
		max = Math.max(diagScore, Math.max(leftScore, upScore));

		// populating the traceback matrix to keep track of where the
		// highest score is coming from
		if (diagScore == Math.max(diagScore, Math.max(leftScore, upScore))) {
			traceback.tracebackMatrix[a + 1][b + 1] = 'D';
		} else if (leftScore == Math.max(diagScore, Math.max(leftScore, upScore))) {
			traceback.tracebackMatrix[a + 1][b + 1] = 'L';
		} else {
			traceback.tracebackMatrix[a + 1][b + 1] = 'U';
		}

		return max;
	}

	// this method creates a traceback object and executes the traceback method
	// in that object using
	// the initialized traceback matrix from this class
	public void traceback() {

		Traceback matrixTraceback = new Traceback(longer, shorter);
		matrixTraceback.traceback(traceback.tracebackMatrix, longer, shorter);

	}

	// prints the populated Traceback matrix with the paths to follow
	public void printTracebackMatrix() {

		System.out.print(" " + " " + " " + " ");

		for (int i = 0; i < longer.length(); i++) {
			System.out.print(longer.toUpperCase().charAt(i) + " ");
		}

		System.out.println();
		System.out.print(" " + " ");

		for (int i = 0; i < traceback.columnLength; i++) {
			for (int j = 0; j < traceback.rowLength; j++) {
				System.out.print(traceback.tracebackMatrix[i][j] + " ");
			}
			System.out.println();
			if (i < traceback.columnLength - 1) {
				System.out.print(shorter.toUpperCase().charAt(i) + " ");
			}
		}
	}

}
