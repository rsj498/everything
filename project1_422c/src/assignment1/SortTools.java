
/*
 * EE422C Project 1 submission by
 * Rebecca Jiang
 * rsj498
 * 16470
 * Fall 2016
 */

package assignment1;
import java.util.Arrays;

public class SortTools {
	
	/**
	  * This method tests to see if the given array is sorted.
	  * @param x is the array
	  * @param n is the size of the input to be checked
	  * @return true if array is sorted
	  */
	public static boolean isSorted(int[] x, int n) {
		if(n==0 || x.length == 0)		
			return false;
		for(int i = 0; i < n-1; i++){	//traverse array
			if(x[i+1] < x[i])			//if a number in the next index is less than the one at this index, it's not sorted so return false
				return false;
		}
		return true;		//if you made it through the array, return true
	}
	
	/**
	  * This method finds a key in an array.
	  * @param x is the array
	  * @param n is the size of the input to be checked
	  * @param v is the key 
	  * @return index of key in array, -1 if not found
	  */	
	public static int find(int[] x, int n, int v){
		int low = 0;
		int high = n-1;
		int mid = (high - low)/2;
		while(high>=low){	
			if(x[mid]==v)		//the key is found
				return mid;
			else if(v<x[mid]){	//if the key is less than the middle number, search the first half of the array
				high = mid-1;		
			}
			else if(v>x[mid]){	//if the key is greater than the middle number, search the latter half of the array
				low = mid + 1;
			}
			mid = low + ((high-low)/2);		//reset middle 
		}
		return -1;		//key was not found
	}
	
	/**
	  * This method inserts a number into a non-decreasingly sorted array 
	  * in order unless the number is already in the array, returns new array
	  * @param x is the original, sorted array
	  * @param n is the size of the array
	  * @param v number to be inserted
	  * @return a new array with v inserted if v was not in the array already
	  */
	public static int[] insertGeneral(int[] x, int n, int v){
		if(find(x,n,v) != -1)
			return Arrays.copyOf(x, n);		//make copy of array if the key is found 
		else{
			int[] newArray = new int[n+1];
			int i = 0;
			while(i<n && v>x[i]){		//copy the array until the number that needs to be inserted
				newArray[i] = x[i];
				i++;
			}
			newArray[i]= v;				//insert number into the new array
			i++;
			while(i<n+1){
				newArray[i]= x[i-1];	//copy the rest of the array
				i++;
			}
			return newArray;
		}
	}
	
	/**
	  * This method modifies a sorted array so that a number is inserted in order
	  * @param x is the array
	  * @param n is the size of the input
	  * @param v the number to be inserted
	  * @return modified array 
	  */
	public static int[] insertInPlace(int[] x, int n, int v){
		if(find(x,n,v) != -1)
			return x;			//don't modify array if the key is already in array
		else{
			int i = 0;
			while(i<n && v>x[i])	//traverse until the key is larger than a number in array
				i++;
			int temp = x[i];		
			x[i]=v;					//put the key there
			i++;
			while(i<n+1){			//shift the rest of the array up til n down one index
				int copy = x[i];
				x[i]=temp;
				temp = copy;
				i++;
			}
			return x;
		}
	}
	
	/**
	  * This method sorts an array using insertion sort
	  * @param x is the array to be sorted
	  * @param n is the size of the array
	  */
	public static void insertSort(int[] x, int n){
		for(int i=0; i<n ;i++){			//traverse the array
			int k = i;
			while(k>0 && x[k-1]>x[k]){	//if you find a number that is not in place, keep switching it with the number before it until it's in place
				int temp1=x[k];
				int temp2=x[k-1];
				x[k-1] = temp1;
				x[k] = temp2;
				k--;
			}
		}
	}
}
