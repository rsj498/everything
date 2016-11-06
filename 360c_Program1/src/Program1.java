/*
 * Name: <your name>
 * EID: <your EID>
 */

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Your solution goes in this class.
 * 
 * Please do not modify the other files we have provided for you, as we will use
 * our own versions of those files when grading your project. You are
 * responsible for ensuring that your solution works with the original version
 * of all the other files we have provided for you.
 * 
 * That said, please feel free to add additional files and classes to your
 * solution, as you see fit. We will use ALL of your additional files when
 * grading your solution.
 */
public class Program1 extends AbstractProgram1 {
    /**
     * Determines whether a candidate Matching represents a solution to the
     * Stable Marriage problem. Study the description of a Matching in the
     * project documentation to help you with this.
     */
    
    public boolean isStableMatching(Matching marriage) {
    	        /* TODO implement this function */
    	    	for(int i = 0; i < marriage.getWomenCount(); i++){			//i is woman
    	    		int currentPref = marriage.getWomenMatching().get(i);
    	    		for(int k = 0; k< marriage.getMenCount(); k++){			//k is index of men, get(k) is her pref for that man 
    	    			if(marriage.getWomenPreference().get(i).get(k)<currentPref){//go through her preferences
    	    				//need to get man's pref for her + pref for his current wife
    	    				int prefForHer = marriage.getMenPreference().get(k).get(i);		
    	    				int currentWife = -1;
    	    				for(int j = 0; j<marriage.getWomenCount();j++){
    	    					if(marriage.getWomenMatching().get(j)==k){
    	    						currentWife = j;
    	    						break;
    	    					}
    	    				}
    	    				int prefForCurWife = marriage.getMenPreference().get(k).get(currentWife);
    	    				if(prefForCurWife<prefForHer)
    	    					return false;   				
    	    			}
    	    		}
    	    	}
    	    	return true;
    }
    
    

    /**
     * Determines a solution to the Stable Marriage problem from the given input
     * set. Study the project description to understand the variables which
     * represent the input to your solution.
     * 
     * @return A stable Matching.
     */
    public Matching stableMarriageGaleShapley(Matching marriage) {
        /* TODO implement this function */
    	/*ArrayList<Man> men = new ArrayList<Man>();
    	int numFreeMen = 0;
    	ArrayList<Integer> womatching = new ArrayList<Integer>();
    	for(int i = 0; i < marriage.getWomenCount();i++){
    		womatching.add(-1);
    	}
    	
    	for(int i = 0; i < marriage.getMenCount();i++){
    		Man m = new Man(i, marriage.getMenPreference().get(i));
    		men.add(m);
    		numFreeMen++;
    	}
    	int w = -1;
    	while(numFreeMen>0){
    		Man thisMan = free_men.get(0);
    		if(thisMan.getNumProposed()  < thisMan.getPref().size()){	//he still has ppl he hasn't proposed to
    		while(w==-1){
    			for(int k = 0; k < thisMan.getPref().size(); k++){		//traverse his prefs for ==
    				if(thisMan.getPref().get(k)==thisMan.getWifeNumber())
    					for(int j = 0; j< thisMan.getNumProposed(); j++){
    						if(!thisMan.getProposedList().contains(k)){		//he hasn't proposed to her yet
    							w= k;
    						}
    					}
    			}
    			thisMan.incrementWifeNumber();
    		}
    			
    			if(womatching.get(w)==-1){			//if woman is free
    				womatching.set(w, thisMan.getID());
    				thisMan.addProposed(w);
					thisMan.incrementProposed();
    				free_men.remove(0);
    			} 
    			else {
    				int otherMan = womatching.get(w);
    				if(marriage.getWomenPreference().get(w).get(otherMan)<marriage.getWomenPreference().get(w).get(thisMan.getID()))		//if she likes thisMan more than her current man
    				{
    					womatching.set(w, thisMan.getID());
    					thisMan.addProposed(w);
    					thisMan.incrementProposed();
    					free_men.remove(0);
    					free_men.add(new Man(otherMan, marriage.getMenPreference().get(otherMan)));
    					
    				}
    			}

    		}
    	}*/
    	boolean free[] = new boolean[marriage.getMenCount()];
    	for(int i = 0; i < marriage.getMenCount();i++){
    		free[i] = true;
    	}
    	boolean keepGoing = true;
    	ArrayList<ArrayList<Integer>> sortedPref = new ArrayList<ArrayList<Integer>>();
    	ArrayList<Integer> womatching = new ArrayList<Integer>();
    	for(int i = 0; i < marriage.getWomenCount();i++){
    		womatching.add(-1);
    	}
    	for(int i = 0;i< marriage.getMenCount();i++){
    		sortedPref.add(i, new ArrayList<Integer>());
    	}
    	for(int i = 0; i < marriage.getMenCount();i++){		
    		//for each man
    		int pref = 1;
    		while(sortedPref.get(i).size()<marriage.getWomenCount()){		//max pref #?
	    		for(int j = 0; j < marriage.getWomenCount() && sortedPref.size()<=marriage.getWomenCount(); j++){
	    			if (marriage.getMenPreference().get(i).get(j) == pref){
	    				sortedPref.get(i).add(j);
	    			}
	    		}
	    		pref++;
    		}
    	}
    	//System.out.println(sortedPref);
    	
    	while(keepGoing){
    		int m = -1;
    		for(int i = 0; i < free.length; i++){
    			if(free[i] == true){
    				m = i;
    				break;
    			}
    		}
    		
    		int w = sortedPref.get(m).get(0);		//make sure to remove woman after they propose to them
    		if(womatching.get(w)==-1){				//free
    			womatching.set(w, m);
    			free[m] = false;
    			sortedPref.get(m).remove(0);
    		}
    		else {
    			int otherman = womatching.get(w);
    			if(marriage.getWomenPreference().get(w).get(otherman)>marriage.getWomenPreference().get(w).get(m)){
    				free[otherman] = true;
    				womatching.set(w, m);
    				free[m] = false;
    				sortedPref.get(m).remove(0);
    			}
    		}
 
    		for(int i = 0; i< free.length; i++){
    			if(free[i] ==true){
    				keepGoing = true;
    				break;
    			}
    		}
    		if(keepGoing == false)
    			break;
    		
    	}
    	marriage.setWomanMatching(womatching);
        return marriage; /* TODO remove this line */
    }
}
