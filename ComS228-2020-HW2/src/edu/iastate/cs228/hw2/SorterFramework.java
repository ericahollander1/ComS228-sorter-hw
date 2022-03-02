package edu.iastate.cs228.hw2;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.Comparator;
import java.util.Scanner;


/**
 * An class that compares various methods of sorting.
 * 
 * @author Erica Hollander
 */
public class SorterFramework
{
  /**
   * Loads data necessary to run the sorter statistics output, and runs it.
   * The only logic within this method should be that necessary to use the
   * given file names to create the {@link AlphabetComparator},
   * {@link WordList}, and sorters to use, and then using them to run the
   * sorter statistics output.
   * 
   * @param args
   *   an array expected to contain two arguments:
   *    - the name of a file containing the ordering to use to compare
   *      characters
   *    - the name of a file containing words containing only characters in the
   *      other file
   */
  public static void main(String[] args)
  {
    // TODO check arguments

	  Alphabet alphabet = null;
	  AlphabetComparator comparator;
	  WordList words = null;
	  Sorter[] sorters;
	  try {

		alphabet = new Alphabet(args[0]);
//		System.out.println(alphabet.getPosition('@'));
		
		words = new WordList(args[1]);

	} catch (FileNotFoundException e) {
		e.printStackTrace();
	}
	  
	  comparator = new AlphabetComparator(alphabet);
	  sorters = new Sorter[3];
	  sorters[0] = new MergeSorter();
	  sorters[1] = new QuickSorter();
	  sorters[2] = new InsertionSorter();
	  
	  SorterFramework toRun = new SorterFramework(sorters, comparator, words, 1000000); 		
	  toRun.run();
	  

	  
  }


  /**
   * The comparator to use for sorting.
   */
  private Comparator<String> comparator;

  /**
   * The words to sort.
   */
  private WordList words;

  /**
   * The array of sorters to use for sorting.
   */
  private Sorter[] sorters;

  /**
   * The total amount of words expected to be sorted by each sorter.
   */
  private int totalToSort;


  /**
   * Constructs and initializes the SorterFramework.
   * 
   * @param sorters
   *   the array of sorters to use for sorting
   * @param comparator
   *   the comparator to use for sorting
   * @param words
   *   the words to sort
   * @param totalToSort
   *   the total amount of words expected to be sorted by each sorter
   * @throws NullPointerException
   *   if any of {@code sorters}, {@code comparator}, {@code words}, or
   *   elements of {@code sorters} are {@code null}
   * @throws IllegalArgumentException
   *   if {@code totalToSort} is negative
   */
  public
  SorterFramework(Sorter[] sorters, Comparator<String> comparator,
                  WordList words, int totalToSort)
    throws NullPointerException,
           IllegalArgumentException
  {
    // TODO
	  try {
		  this.totalToSort = totalToSort;
		  this.comparator = comparator;
		  this.sorters = sorters;
		  this.words = words;
	  }
	  catch(NullPointerException e) {
		  e.printStackTrace();
	  }
	  catch(IllegalArgumentException e){
		  e.printStackTrace();
	 }
	
	  
  }


  /**
   * Runs all sorters using
   * {@link Sorter#sortWithStatistics(WordList, Comparator, int)
   * sortWithStatistics()}, and then outputs the following information for each
   * sorter:
   *  - the name of the sorter
   *  - the length of the word list sorted each time
   *  - the total number of words sorted
   *  - the total time used to sort words
   *  - the average time to sort the word list
   *  - the number of elements sorted per second
   *  - the total number of comparisons performed
   */
  public void run()
  {
    // TODO
	 // WordList copy = words.clone();
	  for(Sorter s : sorters) {
		  s.sortWithStatistics(words, comparator, totalToSort);
		  System.out.println("Algorithm " + s.getName());
		  System.out.println("Length " + words.length());
		  System.out.println("Total Words Sorted " + s.getTotalWordsSorted());
		  System.out.println("Total Time To Sort " + s.getTotalSortingTime());
		  System.out.println("AverageTime " + s.getTotalSortingTime() * words.length()/ s.getTotalWordsSorted());
		  System.out.println("Elements-sorted/sec " + s.getTotalWordsSorted()*1000/ s.getTotalSortingTime()); //times by 1000 because we are converting millisecond to seconds
		  System.out.println("Total Comparisons " + s.getTotalComparisons());
		  System.out.println();
	  }
  }
}
