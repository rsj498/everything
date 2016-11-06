package quiz2;

import static org.junit.Assert.*;

import org.junit.Test;

public class SelectionSortTest {

	@Test
	public void test() {
		int x[] = {5,3,4,1,2,7,100,2};
		int y[] = {1,2,2,3,4,5,7,100};
		SelectionSort.sort(x);
		assertArrayEquals(x,y);
		
		int a[]= {3,6,7,1,9,0};
		int b[]={0,1,3,6,7,9};
		SelectionSort.sort(a);
		assertArrayEquals(a,b);
	}

}
