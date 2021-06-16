package projectElements;

public class Traceback extends Matrix {

	String optimalLong;
	String optimalShort;

	// this method goes through the traceback matrix created in Matrix and prints the
	// optimal alignment based on the directions given
	public void traceback(char[][] tracebackMatrix, String longer, String shorter) {

		int a = shorter.length();
		int b = longer.length();
		int stringCounter = 0;
		String reverseShort = "";
		String reverseLong = "";

		// this loop starts at the bottom rightmost cell and makes its way back
		// to the top leftmost corner using the directions given by the NW algorithm
		// prints a gap or a letter depending on the direction in that cell
		do {

			if (tracebackMatrix[a][b] == 'D') {
				reverseShort = reverseShort + shorter.toUpperCase().charAt(a - 1);
				reverseLong = reverseLong + longer.toUpperCase().charAt(b-1);
				a = a - 1;
				b = b - 1;
				stringCounter = stringCounter + 1;
			} else if (tracebackMatrix[a][b] == 'L') {
				reverseShort = reverseShort + "-";
				reverseLong = reverseLong + longer.toUpperCase().charAt(b-1);
				b = b - 1;
				stringCounter = stringCounter + 1;
			} else if (tracebackMatrix[a][b] == 'U') {
				reverseShort = reverseShort + shorter.toUpperCase().charAt(a-1);
				reverseLong = reverseLong + "-";
				a = a - 1;
			} else {

			}

		} while (a > 0 || b > 0);

		// reverses the created strings since they were populated backwards
		// and prints them
		String reverse1 = new StringBuilder(reverseShort).reverse().toString();
		String reverse2 = new StringBuilder(reverseLong).reverse().toString();
		System.out.println(reverse2);
		System.out.println(reverse1);

		System.out.println();

	}

	// default traceback constructor
	public Traceback() {
		this.optimalLong = null;
		this.optimalShort = null;
	}

	// traceback constructor using the longer and shorter DNA strands
	public Traceback(String longer, String shorter) {
		this.optimalLong = longer;
		this.optimalShort = longer;
		this.longer = longer;
		this.shorter = shorter;
	}

	public void printOptimalAlignment() {

		// this would have been where all optimal alignments were printed
		// if we had gotten to this part

	}


}
