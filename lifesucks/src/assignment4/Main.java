/* CRITTERS Main.java
 * EE422C Project 4 submission by
 * Justin Nguyen
 * jhn545
 * 16475
 * TODO: 'ThiCCCbeCCCa Jiang'
 * TODO: <Student2 EID>
 * TODO: <Student2 5-digit Unique No.>
 * Slip days used: <0>
 * Fall 2016
 */
package assignment4;
import java.util.*;
import java.lang.reflect.*;
import java.io.*;

/*
 * Usage: java assignment4.Main <input file> test
 * Input file is optional. If input file is specified, the word 'test' is optional.
 * May not use 'test' argument without specifying input file.
 */
public class Main {

    static Scanner kb;  // scanner connected to keyboard input, or input file
    private static String inputFile;    // input file, used instead of keyboard input if specified
    static ByteArrayOutputStream testOutputString;  // if test specified, holds all console output
    private static String myPackage;    // package of Critter file.  Critter cannot be in default pkg.
    private static boolean DEBUG = false; // Use it or not, as you wish!
    static PrintStream old = System.out;    // if you want to restore output to console

    // Gets the package name. The usage assumes that Critter and its subclasses are all in the same package.
    static {
        myPackage = Critter.class.getPackage().toString().split(" ")[1];
    }

    /**
     * Main method.
     * @param args args can be empty.  If not empty, provide two parameters -- the first is a file name, 
     * and the second is test (for test output, where all output to be directed to a String), or nothing.
     */
    public static void main(String[] args) throws InvalidCritterException { 
        if (args.length != 0) {
            try {
                inputFile = args[0];
                kb = new Scanner(new File(inputFile));          
            } catch (FileNotFoundException e) {
                System.out.println("USAGE: java Main OR java Main <input file> <test output>");
                e.printStackTrace();
            } catch (NullPointerException e) {
                System.out.println("USAGE: java Main OR java Main <input file>  <test output>");
            }
            if (args.length >= 2) {
                if (args[1].equals("test")) { // if the word "test" is the second argument to java
                    // Create a stream to hold the output
                    testOutputString = new ByteArrayOutputStream();
                    PrintStream ps = new PrintStream(testOutputString);
                    // Save the old System.out.
                    old = System.out;
                    // Tell Java to use the special stream; all console output will be redirected here from now
                    System.setOut(ps);
                }
            }
        } else { // if no arguments to main
            kb = new Scanner(System.in); // use keyboard and console
        }

        /* Do not alter the code above for your submission. */
        /* Write your code below. */
// ===============================================================================================================
// ===============================================================================================================
        
        
        Critter.makeCritter("Craig");
//         Critter.worldTimeStep();
        Critter.displayWorld();
        while (true) {
            System.out.print("critters> ");
            String str = kb.next();
            if(str.equals("quit"))
                break;
            else if(str.equals("show")){
                Critter.displayWorld(); 
                kb.nextLine();
            }
            else if(str.equals("step")){
                int i = 1;
                if(kb.hasNextInt())
                    i = kb.nextInt();
                for(int j = 0; j>i;j++){
                    Critter.worldTimeStep();
                }           
                kb.nextLine();
            }
            else if(str.equals("seed")){
                int i = kb.nextInt();
                Critter.setSeed(i);
                kb.nextLine();
            } 
            else if(str.equals("make")){
                String className = kb.next();
                int i = 1;
                if(kb.hasNextInt()){
                    i = kb.nextInt();
                }
                for(int j = 0; j < i; j++){
                    Critter.makeCritter(className);
                }
                kb.nextLine();
            }
            else if(str.equals("stats")){
                String className = kb.next();
                List<Critter> crittersList = Critter.getInstances(className);
        //        Critter.runStats(crittersList);
               
                try{
                	
                	Class cls = Class.forName(className);
                	System.out.println(cls.getClass());
	                Method method = cls.getMethod("runStats");
	                method.invoke(crittersList);
                } 
                catch(Exception e){
                	System.out.print(e);
                }
                kb.nextLine();
            }
            else {
            	System.out.println("invalid command: " + str + " " + kb.nextLine());
            }
           
        }
// ===============================================================================================================
// ===============================================================================================================
        /* Write your code above */
        System.out.flush();

    }
}
