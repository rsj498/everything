/* WORD LADDER Main.java
 * EE422C Project 3 submission by
 * Jessica Slaughter
 * jts3329
 * 16470
 * Rebecca Jiang
 * rsj498
 * 16470
 * Slip days used: <0>
 * Git URL: https://github.com/rsj498/ee422c
 * Fall 2016
 */

package assignment3;

import java.util.ArrayList;
import java.util.Set;

/**
 * This class consists of helper methods for the getWordLadderDFS
 * @author jessicaslaughter
 *
 */
public class Helpers {
	
	/**
	 * This method determines whether a word differs from
	 * another word by one character or not.
	 * @param wordA is the start word
	 * @param wordB is the word to compare to
	 * @return true if the words differ by only one character
	 */
	public boolean isNeighbor(String wordA, String wordB) {
		boolean diffByOne = false;
		for (int i = 0; i < wordA.length(); i++) {
			if (wordA.charAt(i) != wordB.charAt(i)) {
				if (diffByOne) {
					return false;
				}
				else {
					diffByOne = true;
				}
			}
		}
		if (diffByOne) {
			return true;
		}
		return false;
	}
	
	/**
	 * This method determines whether a word differs from
	 * another word by at most two characters.
	 * @param a is the start word
	 * @param b is the word to compare to
	 * @return true of the words differ by at most two characters
	 */
	public boolean diffByTwo(String a, String b) {
		int diffCount = 0;
		for (int i = 0; i < a.length(); i++) {
			if (a.charAt(i) != b.charAt(i)) {
				if (diffCount > 2) {
					return false;
				}
				else {
					diffCount++;
				}
			}
		}
		if (diffCount <= 2) {
			return true;
		}
		return false;
	}
	
	/**
	 * This method searches a given dictionary to find words that
	 * differ from the start word by only one character.
	 * @param start is the start word
	 * @param dict is the dictionary
	 * @param visited is the list of words that cannot be used again
	 * @return an ArrayList of words that differ by one character from the start word
	 */
	public ArrayList<String> getNeighbors(String start, Set<String> dict, ArrayList<String> visited) {
		ArrayList<String> neighbors = new ArrayList<String>();
		for (String word : dict) {
			if (isNeighbor(start, word) && !visited.contains(word)) {
				neighbors.add(word);
			}
		}
		return neighbors;
	}
	/**
	 * This method reverses an ArrayList.
	 * @param path is the word ladder
	 * @return the reversed path
	 */
	public ArrayList<String> reverse (ArrayList<String> path) {
		ArrayList<String> reversed = new ArrayList<String>();
		for (int i = path.size() - 1; i >= 0; i--) {
			reversed.add(path.get(i));
		}
		return reversed;
	}
	
	/**
	 * This method sorts a word's neighbors so that those words
	 * which differ from the end word by at most two characters
	 * are prioritized and placed at the beginning of the 
	 * neighbors ArrayList.
	 * @param neighbors is the list of neighbors for the start word
	 * @param end is the end word
	 * @return an ArrayList of sorted neighbors
	 */
	public ArrayList<String> sortNeighbors (ArrayList<String> neighbors, String end) {
		ArrayList<String> sortedNeighbors = new ArrayList<String>();
		
		for (int i = 0; i < neighbors.size(); i++) {
			if (diffByTwo(neighbors.get(i), end)) {
				sortedNeighbors.add(neighbors.get(i));
			}
		}
		for (int i = 0; i < neighbors.size(); i++) {
			if (!sortedNeighbors.contains(neighbors.get(i))) {
				sortedNeighbors.add(neighbors.get(i));
			}
		}
		return sortedNeighbors;
	}
}
