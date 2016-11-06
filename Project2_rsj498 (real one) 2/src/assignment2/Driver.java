/*  * EE422C Project 2 (Mastermind) submission by 
 * Rebecca Jiang
 * rsj498
 * Slip days used: 0
 * Fall 2016 
 */
package assignment2;

import java.util.Scanner;
public class Driver {
	public static void main(String [] args) {
		Scanner scan = new Scanner(System.in);
		boolean testingMode = false;
		if(args.length==1 && args[0].equals("1"))
			testingMode = true;
		System.out.println("Welcome to Mastermind.  Here are the rules.\n");
		System.out.println("This is a text version of the classic board game Mastermind.");
		System.out.println("The computer will think of a secret code. The code consists of 4 colored pegs.");
		System.out.println("The pegs MUST be one of six colors: blue, green, orange, purple, red, or yellow. A color may appear more than once in the code. You try to guess what colored pegs are in the code and what order they are in. After you make a valid guess the result (feedback) will be displayed.");
		System.out.println("The result consists of a black peg for each peg you have guessed exactly correct (color and position) in your guess. For each peg in the guess that is the correct color, but is out of position, you get a white peg. For each peg that is fully incorrect, you get no feedback.");
		System.out.println("Only the first letter of the color is displayed. B for Blue, R for Red, and so forth.");
		System.out.println("When entering guesses you only need to enter the first character of each color as a capital letter.\n");
		Game newGame = new Game(scan, testingMode);
		newGame.runGame();
	}
}
