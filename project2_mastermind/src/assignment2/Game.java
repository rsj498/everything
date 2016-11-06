/*  * EE422C Project 2 (Mastermind) submission by 
 * Rebecca Jiang
 * rsj498
 * Slip days used: 0
 * Fall 2016 
 */
package assignment2;
import java.util.Scanner;
public class Game {
		private Scanner scan;		
		private boolean testingMode; 
		
		public Game(Scanner scan, boolean mode){
			this.scan = scan;
			testingMode = mode;
		}
		
		private boolean isValid(String g){
			if(g.equals("HISTORY"))
				return true;
			if(g.length()!=GameConfiguration.pegNumber)
				return false;
			for(int i = 0; i < GameConfiguration.pegNumber; i++){
				boolean found = false;
				for(int j = 0; !found && j < GameConfiguration.colors.length; j++){
					if(Character.toString(g.charAt(i)).equals(GameConfiguration.colors[j]))
							found = true;
				}
				if(!found)
					return false;
			}
			return true;
		}
		
		public void runGame(){
			String result;
			String guess;
			boolean playAgain = false;
			System.out.printf("You have %d guesses to figure out the secret code or you lose the game. Are you ready to play? (Y/N):", GameConfiguration.guessNumber);
			if(scan.nextLine().equals("Y"))
				playAgain = true;
			while(playAgain){
				String code = SecretCodeGenerator.getInstance().getNewSecretCode();
				Board b = new Board();
				System.out.print("\nGenerating secret code ....");
				if (testingMode)
					System.out.print("\t(for this example the secret code is " + code +")\n\n");
				for(int i = GameConfiguration.guessNumber; i > 0 && !b.won; i--){
					System.out.printf("You have %d guesses left.\n", i);
					System.out.println("What is your next guess?");
					System.out.println("Type in the characters for your guess and press enter.");
					System.out.print("Enter guess:");
					guess = scan.nextLine();
					
					String[] temp = guess.split(" ");
					if(temp.length!=0)
						guess = temp[0];
					
					while(!isValid(guess)){
						System.out.println(guess + " -> INVALID GUESS");
						System.out.println("");
						System.out.println("What is your next guess?");
						System.out.println("Type in the characters for your guess and press enter.");
						System.out.print("Enter guess:");
						guess = scan.nextLine();
					
						String[] temp2 = guess.split(" ");
						if(temp2.length!=0)
							guess = temp2[0];
					}
					if(guess.equals("HISTORY")){			
						b.printHistory();
						i++;
					}
					else{
						result = b.processGuess(guess, code);
						System.out.println("\n" + guess + " -> Result: " + result);
						System.out.println();
					}
				}
				if(!b.won)
					System.out.println("(Sorry, you are out of guesses. You lose, boo-hoo.)\n");
				System.out.print("Are you ready for another game (Y/N):");
				if(scan.nextLine().equals("N"))			
					playAgain = false;		
			}
		}
		
}
