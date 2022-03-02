package edu.iastate.cs228.hw2;


import java.awt.List;
import java.util.Comparator;
/**
 * @author Erica Hollander
 */

/**
 * An implementation of {@link Sorter} that performs merge sort
 * to sort the list.
 * 
 * @author Erica Hollander
 */
public class MergeSorter extends Sorter
{
	/**
	 * checks for null pointer and calls mergeSortRec
	 */
  @Override
  public void sort(WordList toSort, Comparator<String> comp) throws NullPointerException{
	  if(toSort == null || comp == null) {
		  throw new NullPointerException();
	  }
	  String[] temp = toSort.getArray();
	  mergeSortRec(toSort, comp, 0, temp.length-1);
	  
  }
/**
 * This method makes the Word List into an array and deep copies the values into an array
 * After it calls SortRec, it copies the clone array back into the wordList using wordlist's set function
 * 
 * @param list
 * @param comp
 * @param start
 * @param end
 */
  private void mergeSortRec(WordList list, Comparator<String> comp, int start, int end)
  {

	  String[] array = list.getArray();
	  String[] cloneOfArray = new String[array.length];
	  for(int i = 0; i< array.length; i++) {
		  cloneOfArray[i] = array[i];
	  }
	  SortRec(cloneOfArray, comp, start, end);
	  for(int i = 0; i < cloneOfArray.length; i++) {
		  list.set(i, cloneOfArray[i]);
	  }
  }
  /**
   * This acts as my mergeSortRec but I made this helper method because I merge sort using a only a 
   * string array. 
   * This helper method does the recursion for Merge Sort 
   * This is seperate from mergeSortRec because I use a String array and not wordList during this sort
   * @param arr
   * @param comp
   * @param l
   * @param r
   */
  protected void SortRec(String arr[], Comparator <String> comp, int first, int r) {
	  if(first < r) {
		  int m = (first+r)/2; //finding my midpoint
		  SortRec(arr, comp, first, m);
		  SortRec(arr, comp, m+1, r);
		  
		  mergeHelperMethod(arr, comp, first, m, r);
	  }
  }
  /**
   * Merges the two sub arrays together to create the master sorted array 
   * @param arr
   * @param comp
   * @param l //left starting point
   * @param m //midpoint
   * @param r //right end  
   */
  protected void mergeHelperMethod(String[] arr, Comparator<String> comp, int l, int m, int r) {
	
      int leftSize =m -l +1;

      int rightSize = r - m; 
      
      String L[] = new String[leftSize];
      String R[] = new String[rightSize]; 


      for (int i = 0; i < leftSize; ++i) 
          L[i] = arr[l + i]; 
      for (int j = 0; j < rightSize; ++j) 
          R[j] = arr[m + j + 1]; 

      int i = 0, j = 0; 

      int k = l; //k is equal to l NOT 1 (lol mistake a made earlier) 

      while (i < leftSize && j < rightSize) { 
          if (comp.compare(L[i],R[j]) < 0) { 
              arr[k] = L[i]; 
              i++; 
          } 

          else { 
              arr[k] = R[j]; 
              j++; 
          } 

          k++; 

      } 
//these while loops just fill in the extra when the left and right substring are not the same size
      while (i < leftSize) { 
          arr[k] = L[i]; 
          i++; 
          k++; 
      } 


      while (j < rightSize) { 
          arr[k] = R[j]; 
          j++; 
          k++; 
      } 


  }
	  
  } 


