package assignment3;

import static org.junit.Assert.*;
import org.junit.*;
import java.util.*;

import org.junit.Test;

public class Testing {
	Scanner kb = new Scanner(System.in);
	
	@Before
	public void setUp() {
		Main.initialize();
	}
	
	@Test
	public void testDFS1() {
		Main.initialize();
		ArrayList<String> ladder = Main.getWordLadderDFS("STONE", "MONEY");
		Main.printLadder(ladder);
		HashSet<String> ladderSet = new HashSet<String>(ladder);
		assertEquals(ladder.size(), ladderSet.size());
	}
	
	@Test
	public void testDFS2() {
		ArrayList<String> ladder = Main.getWordLadderDFS("HELLO", "SAILS");
		Main.printLadder(ladder);
		HashSet<String> ladderSet = new HashSet<String>(ladder);
		assertEquals(ladder.size(), ladderSet.size());
	}

	@Test
	public void testDFS3() {
		ArrayList<String> ladder = Main.getWordLadderDFS("SMART", "MONEY");
		Main.printLadder(ladder);
		HashSet<String> ladderSet = new HashSet<String>(ladder);
		assertEquals(ladder.size(), ladderSet.size());
	} 

	@Test
	public void testBFS2() {
//		Main.initialize();
		ArrayList<String> ladder = Main.getWordLadderBFS("SMART", "MONEY");
//		Main.printLadder(ladder);
		HashSet<String> ladderSet = new HashSet<String>(ladder);
		assertEquals(ladder.size(), ladderSet.size());
	}
	
	
	public void testParse() {
		ArrayList<String> input = Main.parse(kb);
		ArrayList<String> expected = new ArrayList<String>();
		expected.add("HELLO");
		expected.add("SAILS");
		assertEquals(input.get(0), expected.get(0));
		assertEquals(input.get(1), expected.get(1));
	}
	
	@Test (timeout = 30000)
	public void testBFS1() {
	Main.initialize();
	ArrayList<String> res = Main.getWordLadderBFS("HELLO", "CELLS");
	HashSet<String> set = new HashSet<String>(res);
	assertEquals(set.size(), res.size());
	assertFalse(res == null || res.size() == 0);
	assertTrue(res.size() < 4);
	}
	
	public void testQuit() {
		Main.parse(kb);
		// Program should quit
	}
}