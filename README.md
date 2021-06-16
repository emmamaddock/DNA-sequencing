# Java DNA Sequencer
*By Emma Maddock and Megan McEwen, Carleton University, 2017*

## Description

A Java-based DNA sequencer that takes 2 DNA sequences and determines their similarity.

## How to Launch

### In Eclipse IDE (works in v4.17 and under)
1. Download bin and src files and unzip if zipped
2. Open Eclipse
3. Select File > Import Projects from File System or Archive
4. Next to Import Source click the Directory... button
5. Select the folder where the src and bin folders are located in the window that opens and click OK
6. In the Import Projects window, select the checkboxes next to the bin and src folders and hit Finish at the bottom
7. If not already opened, open the Package Explorer under Window > Show View > Package Explorer
8. Once the folder path has appeared in the Package Explorer, expand the folder, then src > projectElemts, and open DNA.java
9. If not already opened, open the Console under Window > Show View > Console
10. Press the green Run button in the button toolbar at the top or go to Run > Run
11. If you've successfully launched the program, you will see a prompt in the console to enter the first sequence

## Theory
Essentially, the sequencer takes 2 user inputs that act as DNA strands and outputs an "optimal alignment" of the two using the Needleman-Wunsch algorithm. It generates a matrix composed of both sequences, one as the row length and the other as the column length, and populates the matrix by comparing the character values at each location and using the user's scoring settings input at the beginning. Then, during the traceback sequence, the program starts at the bottom rightmost cell and navigates to the top leftmost cell based on the values, and generates the optimal alignment while doing so.
For more info on this, look at documentation on the Needleman-Wunsch algorithm and how it works.

![Needleman-Wunsch Algorithm Matrix](https://www.researchgate.net/profile/Dzmitry-Razmyslovich/publication/278675646/figure/fig2/AS:391973853777922@1470465257538/An-example-of-an-alignment-by-Smith-Waterman-algorithm.png)

## Using the Sequencer
The sequencer asks the user for 2 consecutive DNA sequences, which are made out of 'G', 'C', 'T', or 'A' characters.
The user may enter any 2 random strings composed of these letters, uppercase or lowercase.
If there's a character that's not one of the 4 accepted, it will ask again to enter a valid string.

The program asks next for a match score, mismatch score, and  gap penalty. Essentially, these feed into the scoring system that the Needlman-Wunsch algorithm (explained above) in the system uses to come up with the alignments.
* **Match score:** 
   * Contributes to the optimal score of the alignment if there's a match between characters in the row and column of the cells
   * Should be a positive value, 1+
* **Mismatch score:** 
   * Adds a penalty for the alignment's optimal score if there's a mismatch between the row and column of the cell
   * Should be a null or negative value, 0 or less
* **Gap penalty:**
   * Adds a penalty for the optimal score if the algorithm generates a gap to align the strands at that cell
   * Should be a null or negative value, 0 or less


After setting the score settings, the user is taken to a menu.
* **1: print both sequences**
   * prints both user-input DNA sequences
* **2: print initialized matrix**
   * prints the initial matrix that the algorithm populates with both alignments, filled with 0s
* **3: result of Needleman-Wunsch algorithm (print entire matrix)**
   * prints the matrix with the values generated by the Needlamn-Wunsch algorithm (not very aligned in the console but accurate)
* **4: print one optimal alignment**
   * prints a matrix showing the directions that the traceback method took based on the values in the cell (L for Left, U for Up, D for Down)
* **5: print all optimal alignments**
   * this feature was never fully designed or implemented, since time ran out before the semester ended and this was optional
* **6: enter two new sequences**
   * allows the user to enter 2 new DNA sequences and restart the program
* **7: exit**
   * exits the program


A full report and in-depth description of theory, development, testing, and use can be found in the repository as "Project-Report-Deliverable-3.docx".

