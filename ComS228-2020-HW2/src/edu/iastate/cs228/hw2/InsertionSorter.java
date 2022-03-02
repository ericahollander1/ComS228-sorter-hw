package edu.iastate.cs228.hw2;


import java.util.Comparator;


/**
 * An implementation of {@link Sorter} that performs insertion sort
 * to sort the list.
 * 
 * @author Erica Hollander
 */
public class InsertionSorter extends Sorter
{
	/**
	 * @param toSort //given word list
	 * @param comp //comparator to use
	 * 
	 * Insertion's sort method compares the values in the string array linearly 
	 * 
	 * takes a long time when you have 10,000 strings to compare
	 */
  @Override
  public void sort(WordList toSort, Comparator<String> comp) throws NullPointerException
  {
    
	  String [] sorted = toSort.getArray();
	  int end = toSort.length();
	  for (int i = 1; i < end; i++) {

		  int j = i;
		  while (j>0 && comp.compare(sorted[j-1], sorted[j]) > 0) {
			  toSort.swap(j-1, j); //if the one behind the index of j is greater then swap them
			  j--;
		  }
	  }
} 
}
