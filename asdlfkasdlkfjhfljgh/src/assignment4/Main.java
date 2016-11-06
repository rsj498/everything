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

import java.io.*;
import java.lang.reflect.Method;

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
        
        final boolean DEBUG = true;
        
        if (DEBUG) { while (true) {
            System.out.print("critters> ");
         //   Scanner strIn = new Scanner(kb.nextLine().trim());
            String line = kb.nextLine();
            String[] strIn = line.split(" ");
            String str = strIn[0];
           // if (strIn.hasNext()) { str = strIn.next(); }
           // else { break; } // TODO: what to do if only enter empty line?
            
            if(str.equals("quit") && strIn.length == 1) {
                break;
            } else if(str.equals("show") && strIn.length == 1){
                Critter.displayWorld(); 
                Critter.printCritterCount(); // TODO: when done, remove this line
            } else if(str.equals("step") && strIn.length <=2){             
            	try{
	                int numSteps = 1;
	                if(strIn.length ==2 ) { numSteps = Integer.parseInt(strIn[1]); }
	                for(int i = 0; i < numSteps; i++){ Critter.worldTimeStep(); }
            	}
            	catch(Exception e){
            		System.out.println("error processing: "+ line);
            	}
            } else if(str.equals("seed") && strIn.length == 2){
            	try{
            		int i = Integer.parseInt(strIn[1]);
            		Critter.setSeed(i);
            	}
            	catch(Exception e){
            		System.out.println("error processing: "+ line);
            	}
            } else if(str.equals("make") && (strIn.length == 3 || strIn.length == 2)){        
            	try{
	                String className = strIn[1];
	                int numCritters = 1;
	                if(strIn.length == 3){ numCritters = Integer.parseInt(strIn[2]); }
	                for(int i = 0; i < numCritters; i++) { Critter.makeCritter(className); }
            	}
            	catch(Exception e){
            		System.out.println("error processing: "+ line);
            	}
            } else if(str.equals("stats") && strIn.length == 2){   
            	try{
            		String fullClassName = myPackage + "." + strIn[1];	
            		Class<?> cls = Class.forName(fullClassName);
	                List<Critter> crittersList = Critter.getInstances(fullClassName);
	                Method runStats = cls.getMethod("runStats", List.class);
	                runStats.invoke(cls, crittersList);
	              }
            	catch(Exception e){
            		System.out.println("error processing: "+ line);
            		System.out.println(e);
            	}
            } else if (str.equals("hs")) { // TODO: remove this when done
                Critter.halfTimeStep();
                Critter.displayWorld();
                Critter.printCritterCount(); 
            }
            else{
            	System.out.println("error processing: "+ line);
            }           
        } }
// ===============================================================================================================
// ===============================================================================================================
        /* Write your code above */
        System.out.flush();

    }
}
