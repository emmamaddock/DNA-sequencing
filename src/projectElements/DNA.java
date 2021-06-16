package projectElements;

import java.util.*;

public class DNA {

	int id;
	String seq;
	DNA dna1;
	DNA dna2;
	private String seq1;
	private String seq2;
	Random rand = new Random();
	static DNA shorter;
	static DNA longer;
	DNA menuDNA;
	DNA menuMatrix;

	public static void main(String[] args) {

		//program creates an initial DNA and Matrix object to get it into the loop
		DNA menuDNA = new DNA();
		menuDNA.acceptDNA();
		Matrix menuMatrix = new Matrix();
		menuMatrix.acceptParameters(longer.getSeq().length(), shorter.getSeq().length(), longer.getSeq(),
				shorter.getSeq());


		int menuChoice;

		do {

			// while case is not exit, go through the menu
			System.out.println("DNA SEQUENCER MENU");
			System.out.println("1: print both sequences");
			System.out.println("2: print initialized matrix");
			System.out.println("3: result of Needleman-Wunsch algorithm (print entire matrix)");
			System.out.println("4: print one optimal alignment");
			System.out.println("5: print all optimal alignments");
			System.out.println("6: enter two new sequences");
			System.out.println("7: exit");
			System.out.println(" ");
			System.out.println("Choose an option: ");

			Scanner menu = new Scanner(System.in);
			menuChoice = menu.nextInt();

			switch (menuChoice) {

			// 1: print both sequences
			// calls printDNA in the DNA class and prints both sequences entered by the user
			case 1:
				System.out.println("Printing both sequences...");
				menuDNA.printDNA();
				System.out.println(" ");
				break;

				// 2: print initialized matrix
				// calls printInitializedMatrix in the Matrix class
				// prints the initialized NW Matrix as determined by the user
			case 2:
				System.out.println("Printing initialized matrix....");
				menuMatrix.printInitializedMatrix();
				System.out.println(" ");
				break;

				// 3: print NW Matrix
				// populates the NW Matrix as per the NW algorithm and prints the result
			case 3:
				System.out.print("Printing NW Matrix...");
				System.out.println(" ");
				menuMatrix.printNWMatrix();
				System.out.println(" ");
				break;

				// 4: print one optimal alignment
				// uses the Traceback class to follow the path created by
				// the NW algorithm, and prints the optimal alignment 
			case 4:
				System.out.println("Printing one optimal alignment....");
				menuMatrix.printTracebackMatrix();
				System.out.println(" ");
				menuMatrix.traceback();
				break;

				// 5: print all alignments (optional)
				// we did not get to this part
			case 5:
				System.out.println("Printing all alignments...");
				System.out.println("( ͡° ͜ʖ ͡°)");
				System.out.println("Deep down we're all optimal alignments.");
				System.out.println(" ");
				break;

				// 6: two new strands
				// prompts the user to enter two new DNA strands, and creates a new NW matrix
			case 6:
				System.out.println(" ");
				menuDNA.acceptDNA();
				menuMatrix.acceptParameters(longer.getSeq().length(), shorter.getSeq().length(), longer.getSeq(),
						shorter.getSeq());
				break;

				// 7: exit
				// exits the menu, terminates the program program
			case 7:
				System.out.print("Now exiting...");
				menu.close();
				System.exit(0);

				// user has entered a number that is not 1-7	
			default:
				System.out.println("Invalid option. Please try again.");
				System.out.println(" ");
				break;

			}

		} while (menuChoice != 7);
	}

	//DNA object constructor using a string and a randomly generated ID
	public DNA(String seq) {
		this.id = rand.nextInt(100) + 0;
		this.seq = seq;
	}

	//default DNA constructor
	public DNA() {
		this.id = 0;
		this.seq = "SUP";
	}

	// this method accepts DNA sequences from the user and creates DNA objects
	@SuppressWarnings("static-access")
	public void acceptDNA() {

		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);

		// asking for the first sequence
		System.out.println("Please input 2 valid DNA sequences: ");
		System.out.println("Sequence 1: ");
		seq1 = sc.nextLine();

		// this bit is checking to see if the sequence entered is composed
		// of valid DNA parts, G,A,T or C, and prompts the user until
		// a proper DNA strand is entered
		Boolean isThereGATC1 = isGATC(seq1);
		while (isThereGATC1 == false) {
			System.out.println("DNA Sequences are only composed of G,A,T,C.");
			System.out.println("Please re-enter a valid sequence: ");
			seq1 = sc.nextLine();
			isThereGATC1 = isGATC(seq1);
		}

		// asking for the second sequence
		System.out.println("Sequence 2: ");
		seq2 = sc.nextLine();

		Boolean isThereGATC2 = isGATC(seq2);
		while (isThereGATC2 == false) {
			System.out.println("DNA Sequences are only composed of G,A,T,C.");
			System.out.println("Please re-enter a valid sequence: ");
			seq2 = sc.nextLine();
			isThereGATC2 = isGATC(seq2);
		}

		System.out.println(" ");

		//creates two DNA objects from the user's input
		this.dna1 = new DNA(seq1);
		this.dna2 = new DNA(seq2);

		// sorts the two strands into a shorter and a longer one, for use in 
		// creating the matrix and for other purposes in the program
		if (seq1.length() > seq2.length()) {
			this.longer = new DNA(seq1);
			this.shorter = new DNA(seq2);
		} else {
			this.longer = new DNA(seq2);
			this.shorter = new DNA(seq1);
		}
	}

	// getters and setters for the DNA class
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSeq() {
		return seq;
	}

	public void setSeq(String seq) {
		this.seq = seq;
	}

	public DNA getShorter() {
		return shorter;
	}

	@SuppressWarnings("static-access")
	public void setShorter(DNA shorter) {
		this.shorter = shorter;
	}

	public DNA getLonger() {
		return longer;
	}

	@SuppressWarnings("static-access")
	public void setLonger(DNA longer) {
		this.longer = longer;
	}

	// prints both strands of DNA that the user entered, in upper case
	public void printDNA() {
		System.out.println("Sequence 1: " + dna1.getSeq().toUpperCase());
		System.out.println("Sequence 2: " + dna2.getSeq().toUpperCase());
	}

	// this boolean determines if the String is only composed of G,A,T,C
	public Boolean isGATC(String seq) {
		for (int i = 0; i < seq.length(); i++) {
			String upperSeq = seq.toUpperCase();
			if (upperSeq.charAt(i) == 'C') {
			} else if (upperSeq.charAt(i) == 'G') {
			} else if (upperSeq.charAt(i) == 'A') {
			} else if (upperSeq.charAt(i) == 'T') {
			} else {
				return false;
			}
		}

		return true;
	}

}
