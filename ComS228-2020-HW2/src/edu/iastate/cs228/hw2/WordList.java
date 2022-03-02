package edu.iastate.cs228.hw2;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;




/**
 * A list of Strings.
 * 
 * @author Erica Hollander
 */
public class WordList implements Cloneable
{
  /**
   * The string array that hold all the strings of "words"
   */
  private String[] words;


  /**
   * Constructs and initializes the list to have exactly the same contents as
   * the given array.
   * 
   * @param contents
   *   the array with the contents of the new list
   * @throws NullPointerException
   *   if {@code contents} is {@code null}
   */
  public WordList(String[] contents)
    throws NullPointerException
  {
	words = new String[contents.length];
    for(int i = 0; i < contents.length; i++) { //copies the strings in contents to the instance var words
    	words[i] = contents[i];
    }
  }

  /**
   * Constructs and initializes the list by reading from the indicated file.
   * The file is read assuming that each line contains a word. The ordering in
   * the file is the order that will be used by the list.
   * 
   * @param filename
   *   the name of the file to read
   * @throws NullPointerException
   *   if {@code filename} is {@code null}
   * @throws FileNotFoundException
   *   if the file cannot be found
   */
  public WordList(String filename)
    throws NullPointerException,
           FileNotFoundException
  {
	  ArrayList<String> lists = new ArrayList<String>();
	  try {
		  File f = new File(filename);
			Scanner scan = new Scanner(f);
			//Scanner scan2 = new Scanner(f);
			
			
			while(scan.hasNext()){
				lists.add(scan.nextLine());
			}
			words = new String[lists.size()];
			for(int i = 0; i < lists.size(); i++) {
				words[i] = lists.get(i);
			}
			scan.close();
	  }
	  catch(NullPointerException e){
		  System.out.println("string is very very null try again!");
	  }
	  catch(FileNotFoundException r){
	  }
		
  }


  /**
   * Returns the number of elements in the list.
   * 
   * @return
   *   the number of elements in the list
   */
  public int length()
  {
    // TODO
	return words.length;
  }

  /**
   * Returns the element of the list at the indicated index.
   * 
   * @param idx
   *   the index of the element to retrieve
   * @return
   *   the element at the indicated index
   * @throws IndexOutOfBoundsException
   *   if {@code idx} is negative or greater than or equal to the length of
   *   the list
   */
  public String get(int idx)
    throws IndexOutOfBoundsException
  {
    // TODO
	if(idx > words.length-1|| idx < 0) {
		throw new IndexOutOfBoundsException();
	}
    return words[idx];
  }

  /**
   * Sets the element of the list at the indicated index to the given value.
   * 
   * @param idx
   *   the index of the element to set
   * @param newValue
   *   the new value of the element
   * @throws IndexOutOfBoundsException
   *   if {@code idx} is negative or greater than or equal to the length of the
   *   list
   */
  public void set(int idx, String newValue)
		  				throws IndexOutOfBoundsException
  {
    // TODO
	  if(idx > words.length-1 || idx < 0) {
			throw new IndexOutOfBoundsException();
		}
	  words[idx] = newValue;
  }

  /**
   * Swaps the indicated elements in the list.
   * 
   * @param idxA
   *   the index of one of the elements to swap
   * @param idxB
   *   the index of the other element to swap
   * @throws IndexOutOfBoundsException
   *   if either of {@code idxA} or {@code idxB} is negative or greater than or
   *   equal to the length of the list
   */
  public void swap(int idxA, int idxB)
    throws IndexOutOfBoundsException
  {
    //since the error will be taken care of when its called, no need to throw IndexOutOfBounds here
	String temp = words[idxA];
	words[idxA] = words[idxB];
	words[idxB] = temp;
	
	
  }

  /**
   * Returns the array used by the list to store its elements.
   * 
   * @return
   *   the array used by the list to store its elements
   */
  public String[] getArray()
  {

    return words;
  }

  /**
   * Performs a deep copy of the list.
 * @throws CloneNotSupportedException 
   */
  @Override
  public WordList clone()
  {
    /*
     * note: since Strings are immutable, you don't need to clone them
     */
	  //these are all the things i tried. Just ignore these comments
	//WordList copy = new WordList(words)
	//WordList copy = (WordList)super.clone(); this is a shallow copy
	//copy.words[i] = words[i]; 
	  int length = words.length;
	  
	  try {
		//  int length = words.length;
		  WordList copy = (WordList)super.clone();
		  copy.words = new String[length];
		  for(int i =0; i < words.length; i++) {
			  copy.words[i] = words[i];
		  }
		  return copy;
	  }
	  catch(CloneNotSupportedException e ) {
		  return null;
	  }

  }
}
