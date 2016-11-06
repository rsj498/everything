package quiz5;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

/**
 * This is a Sudoku Solver that solves a partial Sudoku Board using recursion.
 * The Sudoku solver fills a N×N (N=9 by default) grid with digits so that each
 * column, each row, and each of the Size ×Size (N=Size × Size while Size =3 by
 * default) subgrids contains all of the digits from 1 to 9.
 * <p>
 * You need to implement the solve(SudokuBoard b, int current) method using
 * recursion and the isSafe(int a, int n) method that is used in the solve()
 * method.
 * </p>
 * <p>
 * Don't change other given methods/fields. You can add some private helper
 * methods but you may not add new public methods or fields.
 * </p>
 */
public class Sudoku {

	public static int noSolves = 0;
	public static int SIZE;

	@SuppressWarnings("resource")
	public static void main(String[] args) throws FileNotFoundException {

		// Initialize board
		Scanner sc = new Scanner(new File("sudoku.in"));

		boolean more = true;
		while (more) {
			int n = sc.nextInt();
			SIZE = n * n;
			int[][] initial = new int[SIZE][SIZE];

			for (int cell = 0; cell < SIZE * SIZE; cell++) {
				initial[cell / SIZE][cell % SIZE] = sc.nextInt();
			}
			solve(initial);
			more = sc.hasNextInt();
			if (more)
				System.out.println(); // Print blank line between testcases

		}
	}

	public static void solve(int[][] initial) {
		if (SIZE == 1) {
			System.out.println(1);
			return;
		}
		SudokuBoard brd = new SudokuBoard(SIZE, initial);
		if (solve(brd, brd.nextEmpty(-1)))
			brd.print();
		else
			System.out.println("NO SOLUTION");
	}

	public static boolean solve(SudokuBoard b, int current) {
		// TODO fill here using recursion
		// current increases from 0 to size^2 -1 -- first along a row, then
		// downwards.
		// IF the current placement is at square size^2, we are done.
		// ELSE
		// LOOP try out all 'size' numbers in the current square.
		// IF any number is safe to place there
		// Place it.
		// Call solve with the next square without an initial value.
		// IF above returns TRUE
		// RETURN TRUE.
		// ELSE
		// Remove the placement, (and try the next possible digit)
		// END LOOP
		// RETURN false (if we fall out of the LOOP without returning true, we
		// have exhausted 1-SIZE)
		//
		if(current == (SIZE*SIZE))
			return true;
		else{
			for(int i = 1; i<=SIZE; i++){
				if(b.isSafe(i, current)){
					b.board[current / SIZE][current %SIZE] = i;
					if(solve(b, b.nextEmpty(current)))
						return true;
					else{
						b.board[current/SIZE][current%SIZE] = 0;
					}
				}
			}
			
		}
		return false;
	}
}

class SudokuBoard {
	int[][] board;
	boolean[][] initial;
	int SIZE;
	int N;

	public SudokuBoard(int size) {
		this.SIZE = size;
		N = (int) (Math.sqrt(SIZE) + 1e-3);
		board = new int[SIZE][SIZE];
	}

	public SudokuBoard(int size, int[][] starting) {
		this(size);
		initial = new boolean[SIZE][SIZE];
		// Is a square is initialized? If not, program may change its value.
		for (int i = 0; i < SIZE; i++) {
			for (int j = 0; j < SIZE; j++) {
				if (starting[i][j] != 0)
					initial[i][j] = true;
			}
		}

		for (int row = 0; row < SIZE; row++) {
			board[row] = Arrays.copyOf(starting[row], SIZE);
		}
	}

	/**
	 * Given a square, find the next one that was not initialized.
	 */
	public int nextEmpty(int n) {
		n++;
		if (n == SIZE * SIZE)
			return n;
		int r = n / SIZE;
		int c = n % SIZE;
		while (initial[r][c]) {
			n++;
			r = n / SIZE;
			c = n % SIZE;
			if (n == SIZE * SIZE)
				return n;
		}
		return n;
	}

	/**
	 * Whether safe to place number 'a' in space 'n'.
	 */
	public boolean isSafe(int a, int n) {
		// TODO fill here
		int row = n / SIZE;
		int col = n % SIZE;
		if(board[row][col]!=0)
			return false;
		for(int i = 0; i<SIZE; i++){
			if(board[i][col] == a)
				return false;
		}
		for(int i = 0; i< SIZE; i++){
			if(board[row][i] == a)
				return false;
		}
		for(int i = row - row%N; i< N; i++){
			for(int j = col - col%N; j< N; j++){
				if(board[i][j]==a)
					return false;
			}	
		}
		return true;
	}

	public void print() {
		for (int r = 0; r < SIZE; r++) {
			String blank = "";
			for (int c = 0; c < SIZE; c++) {
				if (board[r][c] == 0)
					System.out.print(blank + "*");
				else
					System.out.print(blank + board[r][c]);
				blank = " ";
			}
			System.out.println();
		}
	}
}
