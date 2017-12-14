import java.util.Arrays;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class SudokuMaker {

	public static int[][] solutionBoard;
	public static int[][] displayBoard;
	public static String[][] currentBoard;
	public static String[][] solBoard;
	public static int difficulty = 45;
	public static int[][] saveBoard = new int[9][9];

	public static void print(int[][] needsPrinting) {
		for(int i = 0; i < 9; i++) {
			for(int j = 0; j < 9; j++) {
				System.out.print(needsPrinting[i][j] + "  ");
			}
			System.out.println();
		}
		System.out.println();
	}
	// Creates a String array with the row and column indices of the board.
	public static String[][] gridID() {
		String[][] numberString = new String[9][9];
		for (int i = 0; i < numberString.length; i++) {
			numberString[i][0] = Integer.toString(i+1);
		}
		for (int j = 0; j < numberString.length; j++) {
			numberString[0][j] = Integer.toString(j+1);
		}
		for (int i = 1; i < numberString.length; i++) {
			for (int j = 1; j < numberString[0].length; j++) {
				numberString[i][j] = " ";
			}
		}
	return numberString;
	}
	// returns a solution board from the generator
	public static void fillSolution() {
		SudokuGenerator thing = new SudokuGenerator();
		solutionBoard =  thing.nextBoard(difficulty);
		int temp;
		for (int i = 0; i < 9; i++) {
			for(int j = 0; j < 9; j++) {
				temp = solutionBoard[i][j];
				saveBoard[i][j] = temp;
			}
		}
	}
	// returns a playable board from the generator
	public static void fillBoard() {
		displayBoard = solutionBoard;
		double total = 81;
		double zeros = (double) difficulty;	
		for (int i = 0; i < 9; i++) {
			for(int j = 0; j < 9; j++) {
				double emptyChance = zeros / total;
				if (Math.random() <= emptyChance) {
					displayBoard[i][j] = 0;
					zeros--;
				}
				total--;
			}
		}
	}
	// Returns the String array of the solution
	public static String[][] convert() {
		String[][] stringResult = new String[9][9];
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				stringResult[i][j] = Integer.toString(saveBoard[i][j]);
			}
		}
		return stringResult;
	}
	// converts the string array of the users board from the generator
	public static String[][] convertBoard() {
		String[][] newdisplay = new String[9][9];
		int[][] intBoard = displayBoard;
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				if (intBoard[i][j] == 0) {
					newdisplay[i][j] = " ";
				} else {
					newdisplay[i][j] = Integer.toString(intBoard[i][j]);
				}
			}
		}
		currentBoard = newdisplay;
		return newdisplay;
	}
	// given a string array, displays the sudoku board.
	public static void SudokuDisplay( String[][] numberString ) {
		for (int i = 0; i < 19; i++) {
			if(i % 2 == 1) {
				System.out.println("| " + numberString[(i-1)/ 2][0] + " | " + numberString[(i-1)/ 2][1] + " | "
						+ numberString[(i-1)/ 2][2]+ " | " + numberString[(i-1)/ 2][3]+ " | " + numberString[(i-1)/ 2][4]+ " | "
								+ numberString[(i-1)/ 2][5]+ " | " + numberString[(i-1)/ 2][6] + " | "+ numberString[(i-1)/ 2][7]
										+ " | "	+ numberString[(i-1)/ 2][8]+ " | ");
			}
			if(i % 2 == 0) {
				System.out.println("+---"+"+---"+"+---"+"+---"+"+---"+"+---"+"+---"+"+---" +"+---+");
			}			
		}
	}
	// boolean statement that tries to determine if the user is trying to make a move
	public static boolean playAttempt(String userIn) {
		String withoutSpaces = String.join("", userIn.split(" "));
		char[] use = withoutSpaces.toCharArray();
		if (use[0] == '(' && use[2] == ',' && use[4] == ')' && use.length == 6 && Character.isDigit(use[5])){
			return true;
		}
		return false;
	}
	// boolean statement that tries to determine if the user is trying to make a clear
	public static boolean clearAttempt(String userIn) {
		String withoutSpaces = String.join("", userIn.split(" "));
		char[] use = withoutSpaces.toCharArray();
		if (use[0] == '(' && use[2] == ',' && use[4] == ')' && use[5] == '!' ){
			return true;
		}
		return false;
	}
	// takes the user move string and converts it into a integer array for the coordinates.
	public static int[] userStringToIntArr(String userIn) {
		String withoutSpaces = String.join("", userIn.split(" "));
		char[] use = withoutSpaces.toCharArray();
		int[] returner = new int[3];
		returner[0] = Integer.parseInt( Character.toString(use[1]));
		returner[1] = Integer.parseInt( Character.toString(use[3]));
		returner[2] = Integer.parseInt( Character.toString(use[5]));
		return returner;
	}
	//used for the clear feature
	public static int[] userStringToIntArr2(String userIn) {
		String withoutSpaces = String.join("", userIn.split(" "));
		char[] use = withoutSpaces.toCharArray();
		int[] returner = new int[3];
		returner[0] = Integer.parseInt( Character.toString(use[1]));
		returner[1] = Integer.parseInt( Character.toString(use[3]));
		return returner;
	}
	// receives the old board and makes a move after receiving a set of integers with the coordinates and desired move
	public static String[][] move(String[][] oldBoard, int[] fromMethod) {
		int x = fromMethod[0];
		int y = fromMethod[1];
		int play = fromMethod[2];
		oldBoard[x-1][y-1] = Integer.toString(play);
	    return oldBoard;
	}
	
	// clears the specified spot after receiving an old board and the coordinates for clearing
	public static String[][] moveClear(String[][] oldBoard, int[] fromMethod) {
		int x = fromMethod[0];
		int y = fromMethod[1];
		
		oldBoard[x-1][y-1] = " ";
	    return oldBoard;
	}
	
	
	public static void helpMenu() {
		System.out.println();
		System.out.println("Guide: To view these commands again at any time, just type 'help' into the console");
		System.out.println("To view the Sudoku board's grid numbers: type 'grid' into the console");
		System.out.println("To input a guess: type the following format, (row , column) guess");
		System.out.println("To clear a guess: type the following format, (row , column) !");
		System.out.println("To view solution: type 'solution' ");
		System.out.println();
	}
	
	public static void main(String[] args) {	
		Scanner userInput = new Scanner(System.in);
		System.out.println("Would you like to play Sudoku? ");
		String test = userInput.nextLine();
		if (test.toUpperCase().equals("YES")) {
		helpMenu();
		fillSolution();
		fillBoard();
		while(true) {
			test = userInput.nextLine();
			if(test.toUpperCase().equals("HELP")) {
				helpMenu();
			}
			if(test.toUpperCase().equals("GRID")) {
				SudokuDisplay(gridID());
				}	
			if(test.toUpperCase().equals("SOLUTION")) {
				SudokuDisplay(convert());
				}
			if(test.toUpperCase().equals("BOARD")) {
				SudokuDisplay(convertBoard());
				}
			if(playAttempt(test)) {
				SudokuDisplay(move(currentBoard, userStringToIntArr(test)));
				}
			if(clearAttempt(test)) {
				SudokuDisplay(moveClear(currentBoard, userStringToIntArr2(test)));
				}
			}	
		}
	}
}
