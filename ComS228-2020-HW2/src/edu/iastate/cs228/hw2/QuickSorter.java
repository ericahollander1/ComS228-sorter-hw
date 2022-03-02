package edu.iastate.cs228.hw2;


import java.util.Comparator;
/**
 * @author Erica Hollander
 */

/**
 * An implementation of {@link Sorter} that performs quick sort
 * to sort the list.
 * 
 * @author Erica Hollander
 */
public class QuickSorter extends Sorter
{
	/**
	 * Calls quickSortRec when the sorter is a QuickSorter ;)
	 */
  @Override
  public void sort(WordList toSort, Comparator<String> comp) throws NullPointerException{
	  if(toSort == null || comp == null) {
		  throw new NullPointerException();
	  }
	  quickSortRec(toSort, comp, 0, toSort.length()-1); 
	  
  }
/**
 * this is my quick sort recursive caller method 
 * it basically call quicksort recursively and contains the base case
 * also has my divider, which calls partition
 * @param list
 * @param comp
 * @param start
 * @param end
 */
  private void quickSortRec(WordList list, Comparator<String> comp, int start, int end){
    // TODO
	if(start < (end)) {
		int divider = partition(list, comp, start, end);
		quickSortRec(list, comp, start, divider-1);
		quickSortRec(list, comp, divider+1, end);
		
	}
  }
/**
 * partition basically compares and swaps 
 * this is really important to quick sort rec because it does the actually "sorting" of the method
 * @param list
 * @param comp
 * @param start
 * @param end
 * @return int which is my dividor or "midpoint"
 */
  private int partition(WordList list, Comparator<String> comp, int start, int end){
    // TODO
	 
	  
	String pivot = list.get(end-1);
	//System.out.println(end);
	int smallerElement = start-1;
	for(int j = start; j < end; j++) {
		//System.out.println()
		if(comp.compare(list.get(j), pivot) == -1) { 
			smallerElement++;
			list.swap(j, smallerElement);
		}
			
	}
	list.swap(smallerElement +1 , end-1);
	
	return smallerElement + 1;
	
}
}
