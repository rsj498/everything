/* 
 * This file is how you might test out your code.  Don't submit this, and don't 
 * have a main method in SortTools.java.
 */

package assignment1;

public class Main {
	public static void main(String [] args) {
	//	SortTools tools = new SortTools();
		int x[] = {1, 2, 3, 4, 5, 17, 18, 20};
		// call your test methods here
		System.out.println(SortTools.isSorted(x, 3));
		System.out.println(SortTools.find(x, 5, 20));
		int y[] = SortTools.insertGeneral(x, 8, 0);
		for(int i=0;i<9;i++){
			System.out.print(y[i] + " ");
		}
		System.out.print("\n");
		SortTools.insertInPlace(x, 6, 16);
		for(int i=0;i<8;i++){
			System.out.print(x[i] + " ");
		}
		System.out.print("\n");
		int z[] = {30, 5, 0, 3, 20, 5, 20, 0,0};
	//	int z[] = {8,7,6,5,4,3,3,2,1};
		SortTools.insertSort(z, 9);
		for(int i=0;i<9;i++){
			System.out.print(z[i] + " ");
		}
	}
}
