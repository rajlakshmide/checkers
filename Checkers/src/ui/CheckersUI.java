/**
 * 
 */
package ui;

import java.util.Scanner;

import model.Checkers;

/**
 * @author rajlakshmide
 *
 */
public class CheckersUI {

	/**
	 * Default constructor
	 */
	public CheckersUI() {
	}

	/**
	 * Main method for running the program
	 * @param args
	 */
	public static void main(String[] args) {
		Checkers manager = new Checkers();
		System.out.println("Welcome to Checkers!");
		
		System.out.println("Instructions: When it's your turn, enter the row and column of the initial spot and final spot of "
				+"\n"+ "your move, all separated by spaces: row1 column1 row2 column2.  If you are making a series of jumps, please "
				+ "\n"+"only enter the start and end positions of the first jump in the series.");
		Scanner scanner = new Scanner(System.in);
		int row1, column1, row2, column2;
		while (true) {
			manager.printBoard();
			System.out.println("Player Red's turn:");
			row1 = scanner.nextInt();
			column1 = scanner.nextInt();
			row2 = scanner.nextInt();
			column2 = scanner.nextInt();
			int result;
			result = manager.play('R', row1, column1, row2, column2);
			while (result != 0 && result != 3) {
				if (result == -1) {
					//Error, should have jumped
					
					System.out.println("ERROR! Must jump. Player Red's turn:");
					row1 = scanner.nextInt();
					column1 = scanner.nextInt();
					row2 = scanner.nextInt();
					column2 = scanner.nextInt();
				}
				if (result == 1) {
					//Needs to jump again
					//save old final position to ensure legality of next move
					int old_row = row2;
					int old_col = column2;
					manager.printBoard();
					System.out.println("Must jump again. Player Red's turn:");
					row1 = scanner.nextInt();
					column1 = scanner.nextInt();
					row2 = scanner.nextInt();
					column2 = scanner.nextInt();
					while (row1 != old_row || column1 != old_col) {
						System.out.println("ERROR! You must move the same piece that previously jumped. Player Red's turn:");
						row1 = scanner.nextInt();
						column1 = scanner.nextInt();
						row2 = scanner.nextInt();
						column2 = scanner.nextInt();
					}
				}
				result = manager.play('R', row1, column1, row2, column2);
			}
			manager.printBoard();
			System.out.println("Player White's turn:");
			row1 = scanner.nextInt();
			column1 = scanner.nextInt();
			row2 = scanner.nextInt();
			column2 = scanner.nextInt();
			result = manager.play('W', row1, column1, row2, column2);
			while (result != 0 && result != 3) {
				if (result == -1) {
					//Error, should have jumped
					
					System.out.println("ERROR! Must jump. Player White's turn:");
					row1 = scanner.nextInt();
					column1 = scanner.nextInt();
					row2 = scanner.nextInt();
					column2 = scanner.nextInt();
				}
				if (result == 1) {
					//Needs to jump again
					//save old final position to ensure legality of next move
					int old_row = row2;
					int old_col = column2;
					manager.printBoard();
					System.out.println("Must jump again. Player White's turn:");
					row1 = scanner.nextInt();
					column1 = scanner.nextInt();
					row2 = scanner.nextInt();
					column2 = scanner.nextInt();
					while (row1 != old_row || column1 != old_col) {
						System.out.println("ERROR! You must move the same piece that previously jumped. Player White's turn:");
						row1 = scanner.nextInt();
						column1 = scanner.nextInt();
						row2 = scanner.nextInt();
						column2 = scanner.nextInt();
					}
				}
				result = manager.play('W', row1, column1, row2, column2);
			}
		}

	}

}
