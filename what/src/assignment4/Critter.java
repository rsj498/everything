/* CRITTERS Critter.java
 * EE422C Project 4 submission by
 * Justin Nguyen
 * jhn545
 * 16475
 * TODO: Rebecca's 'Right Hand' Jiang
 * rsj498
 * TODO: <Student2 5-digit Unique No.>
 * Slip days used: <0>
 * Fall 2016
 */
package assignment4;

import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import java.io.*; // TODO: remove me when finished with this lab

/* see the PDF for descriptions of the methods and fields in this class
 * you may add fields, methods or inner classes to Critter ONLY if you make your additions private
 * no new public, protected or default-package code or data can be added to Critter
 */

// TODO: when die, move, add critters, or give birth --> check to see if critterCount was updated correctly

public abstract class Critter {
    private static String myPackage;
    private static List<Critter> population = new java.util.ArrayList<Critter>();
    private static List<Critter> babies = new java.util.ArrayList<Critter>();
    private static int[][] critterCount = new int[Params.world_width][Params.world_height];
    private static boolean isFightingPhase = false;
    private int energy = 0;
    private int x_coord;
    private int y_coord;

    // Gets the package name.  This assumes that Critter and its subclasses are all in the same package.
    static {
        myPackage = Critter.class.getPackage().toString().split(" ")[1];
    }

    private static java.util.Random rand = new java.util.Random();
    public static int getRandomInt(int max) {
        return rand.nextInt(max);
    }

    public static void setSeed(long new_seed) {
        rand = new java.util.Random(new_seed);
    }

    // a one-character long string that visually depicts your critter in the ASCII interface
    public String toString() { return " "; }

    protected int getEnergy() { return energy; }

    // TODO: write me
    protected final void walk(int direction) {
    	
    }

    // TODO: write me
    protected final void run(int direction) {

    }

    /**
     * Call this function to give birth to new life.
     * @param offspring the child Critter being created
     * @param direction the direction the child will initially face 
     * @return none
     */
    protected final void reproduce(Critter offspring, int direction) {
        // Invalid reproduction
        if (energy < Params.min_reproduce_energy  ||  ! isAlive()) { return; }

        // offspring.energy = energy / 2;
        // energy = Math.ceil(energy / 2);
    }

    // Abstract functions --> TODO: children write me
    public abstract void doTimeStep();
    public abstract boolean fight(String opponent);

    /**
     * Helper function for the overloaded fight method (more natural).
     * @return true if the critter wants to fight
     */
    private boolean fight(Critter c) {
        return fight(c.toString());
    }

    /**
     * create and initialize a Critter subclass.
     * critter_class_name must be the unqualified name of a concrete subclass of Critter, if not,
     * an InvalidCritterException must be thrown.
     * (Java weirdness: Exception throwing does not work properly if the parameter has lower-case instead of
     * upper. For example, if craig is supplied instead of Craig, an error is thrown instead of
     * an Exception.)
     * @param critter_class_name
     * @throws InvalidCritterException
     */
    // TODO: fix wrap around edges --> if go off x border, return from other side "I must have called a thousand times"
    public static void makeCritter(String critter_class_name) throws InvalidCritterException {
        String fullClassName = myPackage + "." + critter_class_name;
        try {
            Object obj = Class.forName(fullClassName).newInstance();
            if (obj instanceof Critter) { // valid critter --> put into the world
                int randX = getRandomInt(Params.world_width);
                int randY = getRandomInt(Params.world_height);
                
                Critter c = (Critter) obj;
                c.energy = Params.start_energy;
                c.x_coord = randX;
                c.y_coord = randY;

                population.add(c);
                critterCount[c.x_coord][c.y_coord] += 1;
            }
        } catch (ClassNotFoundException e) {
            throw new InvalidCritterException(critter_class_name);
        } catch (InstantiationException | IllegalAccessException e) {
            throw new InvalidCritterException(critter_class_name);
        }
    }

    /**
     * Gets a list of critters of a specific type.
     * @param critter_class_name What kind of Critter is to be listed.  Unqualified class name.
     * @return List of Critters.
     * @throws InvalidCritterException
     */
    public static List<Critter> getInstances(String critter_class_name) throws InvalidCritterException {
        List<Critter> res = new java.util.ArrayList<Critter>();
        // TODO
        String fullClassName = myPackage + "." + critter_class_name;
        try{
        	for(Critter c: population){
        		if(Class.forName(fullClassName).isInstance(c))
        			res.add(c);
        	}
        } catch (ClassNotFoundException e) {
            throw new InvalidCritterException(critter_class_name);
        } 
        return res;
    }

    /**
     * Prints out how many Critters of each type there are on the board.
     * @param critters List of Critters.
     */
    public static void runStats(List<Critter> critters) {
        System.out.print("" + critters.size() + " critters as follows -- ");
        java.util.Map<String, Integer> critter_count = new java.util.HashMap<String, Integer>();
        for (Critter crit : critters) {
            String crit_string = crit.toString();
            Integer old_count = critter_count.get(crit_string);
            if (old_count == null) {
                critter_count.put(crit_string,  1);
            } else {
                critter_count.put(crit_string, old_count.intValue() + 1);
            }
        }
        String prefix = "";
        for (String s : critter_count.keySet()) {
            System.out.print(prefix + s + ":" + critter_count.get(s));
            prefix = ", ";
        }
        System.out.println();
    }

    /* the TestCritter class allows some critters to "cheat". If you want to
     * create tests of your Critter model, you can create subclasses of this class
     * and then use the setter functions contained here.
     *
     * NOTE: you must make sure that the setter functions work with your implementation
     * of Critter. That means, if you're recording the positions of your critters
     * using some sort of external grid or some other data structure in addition
     * to the x_coord and y_coord functions, then you MUST update these setter functions
     * so that they correctly update your grid/data structure.
     */
    static abstract class TestCritter extends Critter {
        protected void setEnergy(int new_energy_value) {
            super.energy = new_energy_value;
        }

        protected void setX_coord(int new_x_coord) {
            super.x_coord = new_x_coord;
        }

        protected void setY_coord(int new_y_coord) {
            super.y_coord = new_y_coord;
        }

        protected int getX_coord() {
            return super.x_coord;
        }

        protected int getY_coord() {
            return super.y_coord;
        }


        /*
         * This method getPopulation has to be modified by you if you are not using the population
         * ArrayList that has been provided in the starter code.  In any case, it has to be
         * implemented for grading tests to work.
         */
        protected static List<Critter> getPopulation() {
            return population;
        }

        /*
         * This method getBabies has to be modified by you if you are not using the babies
         * ArrayList that has been provided in the starter code.  In any case, it has to be
         * implemented for grading tests to work.  Babies should be added to the general population
         * at either the beginning OR the end of every timestep.
         */
        protected static List<Critter> getBabies() {
            return babies;
        }
    }

    /**
     * Clear the world of all critters, dead and alive
     * @return none
     */
    // TODO: test me
    public static void clearWorld() {
        List<Critter> population = new java.util.ArrayList<Critter>();
        critterCount = new int[Params.world_width][Params.world_height];
    }

    /**
     * Test to see if the two critters are in the same location.
     * @param critter1 the first critter
     * @param critter2 the second critter
     * @return true if the critters are in the same location, else false
     */
    private static boolean areInSameLoc(Critter critter1, Critter critter2) {
        return (critter1.x_coord == critter2.x_coord  &&
                critter1.y_coord == critter2.y_coord);
    }

    /**
     * Test to see if the critter is alive
     * @return true if the critter's energy is above zero, false otherwise
     */
    private boolean isAlive() {
        return (energy > 0);
    }

    /**
     * Helper method for worldTimeStep(). Performs the individual time steps.
     * @return none
     */
    private static void doIndividTimeSteps() {
        Iterator<Critter> itr = population.iterator();
        while (itr.hasNext()) {
            Critter c = itr.next();
            
            if (c.energy <= 0) { // TODO: remove me
                System.err.println("Error in Critter.worldTimeStep: calling doTimeStep on dead critter");
            }

            if (c.isAlive()) { c.doTimeStep(); }
            if (! c.isAlive()) { // could have died after performing the time step
                critterCount[c.x_coord][c.y_coord] -= 1; 
                itr.remove(); 
            }
        }
    }

    /**
     * Helper method for worldTimeStep(). Resolve encounters.
     * @return none
     */
    private static void resolveEncounters() {
        // Iterate over each combination of critters
        for (int i = 0; i < population.size(); ++i) {
            Critter c1 = population.get(i);
            if (! c1.isAlive()) { continue; }
            for (int j = i+1; j < population.size(); ++j) {
                Critter c2 = population.get(j);
                if (! c2.isAlive()) { continue; }
                
                // Invoke both fight methods to determine if there is indeed a fight
                if (areInSameLoc(c1, c2)) {
                    isFightingPhase = true;
                    boolean c1WantsToFight = c1.fight(c2);
                    boolean c2WantsToFight = c2.fight(c1);
                    boolean itsTimeToRumble = c1WantsToFight  &&  c2WantsToFight;
                    if (itsTimeToRumble) {
                        if (! c1.isAlive()) {
                            critterCount[c1.x_coord][c1.y_coord] -= 1; 
                            if (! c2.isAlive()) { critterCount[c2.x_coord][c2.y_coord] -= 1; }
                            break;
                        } 
                        if (! c2.isAlive()) { 
                            critterCount[c2.x_coord][c2.y_coord] -= 1; 
                            continue; 
                        } 

                        // Both still alive and in same location --> time to fight
                        if (areInSameLoc(c1, c2)) {
                            int c1WinningChance; int c2WinningChance;
                            if (! c1WantsToFight) { c1WinningChance = 0; }
                            else { c1WinningChance = getRandomInt(c1.energy + 1); }
                            if (! c2WantsToFight) { c2WinningChance = 0; }
                            else { c2WinningChance = getRandomInt(c2.energy + 1); }

                            if (c1WinningChance >= c2WinningChance) {
                                c1.energy += c2.energy / 2;
                                c2.energy = 0;
                                critterCount[c2.x_coord][c2.y_coord] -= 1;
                            } else {
                                c2.energy += c1.energy / 2;
                                c1.energy = 0;
                                critterCount[c1.x_coord][c1.y_coord] -= 1;
                            }
                        }
                    }
                }
                
                isFightingPhase = false;
            }
        }
    }

    
    /**
     * Helper method for worldTimeStep(). 
     * Subtract rest_energy and do final check to remove dead critters.
     * @return none
     */
    private static void finalCheckForDeadCritters() {
        Iterator<Critter> itr = population.iterator();
        while (itr.hasNext()) {
            Critter c = itr.next();
            if (! c.isAlive()) { population.remove(c); continue; }
            c.energy -= Params.rest_energy_cost;
            if (! c.isAlive()) { // died because of rest_energy cost
                critterCount[c.x_coord][c.y_coord] -= 1;
                population.remove(c); 
            }
        }
    }
    
    /**
     * Simulates one time step for every critter still alive.
     * Resolves all encounters (at the end of each time step),
     *   until each location has at most one critter.
     * All dead critters should be removed by the end of this function.
     *
     * @return none
     */
    public static void worldTimeStep() {
        doIndividTimeSteps();
        resolveEncounters();
        
        finalCheckForDeadCritters();


        // TODO: making babies goes in here somewhere, rated PG-13

    }
    
    // Critter world will always have the same border based on Params.java
    // So, just statically initialize it.
    private static List<StringBuilder> critterWorld = new ArrayList<StringBuilder>();
    static {
        // set the top border
        StringBuilder topBorder = new StringBuilder("+");
        for (int i = 0; i < Params.world_width; ++i) { topBorder.append("-"); }
        topBorder.append("+");
        critterWorld.add(0, topBorder);

        // set the middle elements to empty locations
        StringBuilder midEl = new StringBuilder("|");
        for (int i = 0; i < Params.world_width; ++i) { midEl.append(" "); }
        midEl.append("|");
        for (int i = 0; i < Params.world_height; ++i) { critterWorld.add(midEl); }

        // set the bottom border;
        critterWorld.add(Params.world_height + 1, topBorder);
    }
    
    /**
     * Display our critter world.
     * Example 5x5 world with 4 Algae and 2 Craig critters.
     * +-----+
     * | @  C|
     * |     |
     * |   @ |
     * | @   |
     * |C @  |
     * +-----+
     */
    public static void displayWorld() {
        // set the middle elements with critters to their actual values
        for (Critter c : population) {
            int xLoc = c.x_coord; int yLoc = c.y_coord;
            char critterChar = c.toString().charAt(0); // the string only has one value

            // plus one because the border takes up one slot
            StringBuilder actualMidEl = new StringBuilder(critterWorld.get(yLoc + 1));
            actualMidEl.setCharAt(xLoc + 1, critterChar);

            critterWorld.set(yLoc + 1, actualMidEl);
        }
 
        // output the world
        for (StringBuilder s : critterWorld) {
            System.out.println(s);
        }
    }
}
