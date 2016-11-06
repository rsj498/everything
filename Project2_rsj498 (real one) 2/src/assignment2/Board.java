/*  * EE422C Project 2 (Mastermind) submission by
 * Rebecca Jiang
 * rsj498
 * Slip days used: 0
 * Fall 2016
 */

package assignment2;

public class Board {
	private int numBlackPegs;			
	private int numWhitePegs;
	private String[] history;
	private int[] blackPegHistory;
	private int[] whitePegHistory;
	private int numGuessed;
	public boolean won; 
	
	public Board(){
		numBlackPegs = 0;
		numWhitePegs = 0;
		numGuessed = 0;
		history = new String[GameConfiguration.guessNumber];
		blackPegHistory = new int[GameConfiguration.guessNumber];;
		whitePegHistory = new int[GameConfiguration.guessNumber];;
		won = false;
	}
	


	public String processGuess(String guess, String code) {
		numBlackPegs = 0;
		numWhitePegs = 0;
		numGuessed++;
		StringBuilder g = new StringBuilder(guess);
		StringBuilder ans = new StringBuilder(code);
		int len = guess.length();				
		for(int i = 0; i < len; i++){			//black pegs
			if(g.charAt(i)== ans.charAt(i)){
				g.setCharAt(i, '-');
				ans.setCharAt(i, '-');
				numBlackPegs++;
			}
		}
		
		if(numBlackPegs==len && len == 1){
			won = true;
			return "1 black peg! - You win!!";
		}
		if(numBlackPegs == len){
			won = true;
			return numBlackPegs + " black pegs! - You win!!";
		}
		
		for(int i = 0; i< len; i++){			//white pegs
			if(g.charAt(i)!= '-'){
				for(int j = 0; j<len; j++){
					if(g.charAt(i) == ans.charAt(j)){
						g.setCharAt(i, '-');
						ans.setCharAt(j, '-');
						numWhitePegs++;		
						j = len;
					}
				}
			}
		}
		history[numGuessed - 1] = guess;
		blackPegHistory[numGuessed - 1] = numBlackPegs;
		whitePegHistory[numGuessed - 1] = numWhitePegs;
		if(numBlackPegs == 0){
			if(numWhitePegs == 0)
				return "No pegs";
			if(numWhitePegs == 1)
				return "1 white peg";
			else return numWhitePegs + " white pegs";
		}
		if(numBlackPegs == 1){
			if(numWhitePegs == 0)
				return "1 black peg";
			else if(numWhitePegs == 1)
				return "1 black peg, 1 white peg";
			else return "1 black peg, " + numWhitePegs + " white pegs";
		}
		if(numWhitePegs == 0)
			return numBlackPegs + " black pegs";
		if(numWhitePegs == 1)
			return numBlackPegs + " black pegs, 1 white peg" ;
		else
			return numBlackPegs + " black pegs, " + numWhitePegs + " white pegs";
	}
	
	public void printHistory(){
		System.out.println("");
		for(int i = 0; i < numGuessed; i++){
			System.out.printf(history[i] + "\t\t%dB_%dW\n", blackPegHistory[i], whitePegHistory[i]);
		}
		System.out.println("");
	}



}

