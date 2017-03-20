/**
 * 
 */
package model;

/**
 * @author rajlakshmide
 *
 */
public class Checkers {
	
	/** The current board */
	private char[][] board;
	/** length and width of board */
	private final static int SIZE = 8;
	

	/**
	 * Default constructor
	 */
	public Checkers() {
		board = new char[SIZE][SIZE];
		
		//Initialize initial board
		for (int i = 0; i < SIZE; i++) {
			for (int j = 0; j < SIZE; j++) {
				board[i][j] = ' ';
			}
		}
		for (int i = 0; i < SIZE; i++) {
			if (i == 0 || i == 2) {
				for (int j = 1; j < SIZE; j+=2) {
					board[i][j] = 'W';
				}
			}
			if (i == 1) {
				for (int j = 0; j < SIZE; j+=2) {
					board[i][j] = 'W';
				}
			}
			if (i == 5 || i == 7) {
				for (int j = 0; j < SIZE; j+=2) {
					board[i][j] = 'R';
				}
			}
			if (i == 6) {
				for (int j = 1; j < SIZE; j+=2) {
					board[i][j] = 'R';
				}
			}
			if (i == 3) {
				for (int j = 0; j < SIZE; j+=2) {
					board[i][j] = 'O';
				}
			}
			if (i == 4) {
				for (int j = 1; j < SIZE; j+=2) {
					board[i][j] = 'O';
				}
			}
		}
		
	}

	/**
	 * Print the board
	 */
	public void printBoard() {
		System.out.println("  0 1 2 3 4 5 6 7");
		for (int i = 0; i < SIZE; i++) {
			System.out.print((i) + " ");
			for (int j = 0; j < SIZE; j++) {
				System.out.print(board[i][j] + " ");
			}
			System.out.println();
		}
	}

	/**
	 * Tests whether the move is a jump assuming the move is legal
	 * @return true if jump, false otherwise
	 */
	public boolean isJump(int row1, int column1, int row2, int column2) {
		return (Math.abs(row1 - row2) == 2);
	}
	
	/**
	 * Tests whether a jump is legal from an initial row, column to final row, column.
	 * @param player R or W
	 * @param row1 initial row
	 * @param column1 initial column
	 * @param row2 final row
	 * @param column2 final column
	 * @return true if a jump is possible/legal
	 */
	public boolean canJump(char player, int row1, int column1, int row2, int column2) {
		if (row2 < 0 || row2 > 7 || column2 <0 || column2 > 7) {
			return false;
		}
		if (player == 'W') {
			if (board[row2][column2] == 'O' && board[(row1+row2)/2][(column1+column2)/2] == 'R') {
				return true;
			}
				
		}
		if (player == 'R') {
			if (board[row2][column2] == 'O' && board[(row1+row2)/2][(column1+column2)/2] == 'W') {
				return true;
			}	
		}	
		return false;
	}
	
	/**
	 * Makes a move for the next player
	 * @param row1 initial row position
	 * @param column1 initial column position
	 * @param row2 final row position
	 * @param column2 final column position
	 */
	public int play(char player, int row1, int column1, int row2, int column2) {
		if (isJump(row1, column1, row2, column2)) {
			board[row1][column1] = 'O';
			board[row2][column2] = player;
			for (int row = 0; row <8; row++) {
				for (int col = 0; col <8; col++) {
					if (board[row][col] == player) {
						if (player == 'R') {
							if (canJump('R', row, col, row-2, col-2) || canJump('R', row, col, row-2, col+2))
								return 1; //must jump again
						}
						else {
							if (canJump('W', row, col, row+2, col-2) || canJump('W', row, col, row+2, col+2))
								return 1; //must jump again
						}
					}
	
				}
			}
			return 3; //no more jumps available
		}
		else { //not a jump
			//need to see if a jump was possible
			for (int row = 0; row <8; row++) {
				for (int col = 0; col <8; col++) {
					if (board[row][col] == player) {
						if (player == 'R') {
							if (canJump('R', row, col, row-2, col-2) || canJump('R', row, col, row-2, col+2)) {
								return -1;  //ERROR!  user should have jumped
							}	
						}
						else {
							if (canJump('W', row, col, row+2, col-2) || canJump('W', row, col, row+2, col+2))
								return -1;  //ERROR!  user should have jumped
						}
					}
				}
			}
			board[row1][column1] = 'O';
			board[row2][column2] = player;
			return 0; //successful regular move
		}

		
	}
	


}
