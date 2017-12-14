import java.util.*;

public class SudokuGenerator {
	public static final int WIDTH = 9;
	public static final int HEIGHT = 9;	
	int[][] game;
	
   public SudokuGenerator() {
	   game = new int[WIDTH][HEIGHT];
   }	
	public int[][] nextBoard(int difficulty) {
		game = new int[WIDTH][WIDTH];
		nextCell(0,0);
		return game;
	}
	public boolean nextCell(int x, int y) {
		int X = x;
		int Y = y;
		int[] moves = {1,2,3,4,5,6,7,8,9};
		Random num = new Random();
		int temp = 0;
		int current = 0;
		int top = moves.length;
   		for (int i=top-1;i>0;i--) {
		    current = num.nextInt(i);
		    temp = moves[current];
		    moves[current] = moves[i];
		    moves[i] = temp;
    	}
		for (int i = 0; i < moves.length; i++) {
			if (Check(x, y, moves[i])) {
				game[x][y] = moves[i];
				if (x == 8) {
					if (y == 8) {
						return true; 
					} else {
						X = 0;
						Y = y + 1;
					}
				} else {
					X = x + 1;
				}
				if (nextCell(X, Y)) {
					return true;
				}
			}
		}
		game[x][y] = 0;
		return false;
	}
	private boolean Check(int x, int y, int current) {
		for (int i = 0; i < 9; i++) {
			if (current == game[x][i]) {
				return false;
			}
		}
		for (int i = 0; i < 9; i++) {
			if (current == game[i][y]) {
				return false;
			}
		}
		int X = 0;
		int Y = 0;
		if (x > 2) {
			if (x > 5) {
				X = 6;
		} else {
				X = 3;
		}
		}
		if (y > 2) {
			if (y > 5) {
				Y = 6;
		} else {
				Y = 3;
		}
		}
		for (int i = X; i <= 9 && i < X + 3; i++) {
			for (int j = Y; j <= 9 && j < Y + 3; j++) {
				if (current == game[i][j]) {
					return false;
				}
			}
		}
		return true;
	}
}
