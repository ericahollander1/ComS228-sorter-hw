package edu.iastate.cs228.hw2;
/**
 * @author Erica Hollander
 * Due October 1st, 2020
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;


/**
 * 
 * The alphabet class creates a new ordering of characters.
 * This class stores the characters in an object call CharAndPos that 
 * contains the characters sorted according the of the character's ascii value
 * and the position of the character in the new alphabet ordering
 * 
 * @author Erica Hollander
 */
public class Alphabet
{
  /**
   * 
   * A table that contains characters and their positions
   * Sorted by the characters' ascii values
   * Position corresponds to the position in the new alphabet ordering
   * 
   */
  private CharAndPos[] lookup;


  /**
   * Constructs the alphabet and initalizes the lookup table
   * 
   * @param ordering
   *   the array containing the characters, in the new order
   * @throws NullPointerException
   *   if {@code ordering} is {@code null}
   */
  public Alphabet(char[] ordering) throws NullPointerException {
    // TODO
	  if(ordering == null) {
		  throw new NullPointerException();
	  }
	  helperMethod(ordering);
	  
  }

  /**
   * 
   * 
   * Constructs the alphabet and initalizes the lookup table by 
   * by taking in a file scanning that file to obtain the character array
   * Then calls a helper method to create the lookup table
   * 
   * @param filename
   *   the name of the file to read
   * @throws NullPointerException
   *   if {@code filename} is {@code null}
   * @throws FileNotFoundException
   *   if the file cannot be found
   */
  public Alphabet(String filename) throws NullPointerException, FileNotFoundException{
    // TODO
	File f = new File(filename);
	Scanner scan = new Scanner(f);
	int i = 0;
	while(scan.hasNextLine()) {
//		char c = scan.next().charAt(0);
		++i;
		scan.nextLine();
	}
	char[] c = new char[i];
	Scanner scan2 = new Scanner(f);
	int j =0;
	while(scan2.hasNextLine() && j < i) {
		c[j] = scan2.next().charAt(0);
		j++;
	}
	scan.close();
	scan2.close();
	helperMethod(c);
  }

  /**
   * Helper method for constructors
   * Takes in a character array and sorts the character array based on the ascii table
   * Stores the character and its corresponding position in the new alphabet ordering in the 
   * lookup CharAndPos which acts as a lookup table
   * 
   * @param char array: ordering
   */
  protected void helperMethod(char[] ordering) {
	  int length = ordering.length;
	  char[] c = new char[length];
	  for(int j =0; j< length; j++) {
		  c[j] = ordering[j];
	  }
	  lookup = new CharAndPos[length];
	  
	  Arrays.sort(c);
	  int foundIndex = 0;
	  for(int i = 0; i < length; i++) {
		  for(int q = 0; q < length; q++) {
			  if(c[i] == ordering[q])
				  foundIndex = q;
		  }
		 lookup[i] = new CharAndPos(c[i], foundIndex);
	  }
  }
  
  /**
   * Returns true if the given character is present in the
   * ordering
   * Uses binarySeach to find the character
   * 
   * @param c
   *   the character to test
   * @return
   *   true if and only if the given character is present in the ordering
   */
  public boolean isValid(char c){
    int result = binarySearch(c);
    if(result != -1)
    	return true;
    return false;
//	  return true;
  }

  /**
   * Returns the position of the given character in the ordering.
   * Returns a negative value if the given character is not present in the
   * ordering.
   * 
   * @param c
   *   the character of which the position will be determined
   * @return
   *   the position of the given character, or a negative value if the given
   *   character is not present in the ordering
   */
  public int getPosition(char c){
    // TODO
	//calls binarySearch
	int searchResult = binarySearch(c);
	//System.out.println(searchResult);
	
	if(!isValid(c))
		return -1;
	return lookup[searchResult].position; //or lookup[searchResult].position but i think they are the same
  }

  /**
   * Returns the index of the spot containing the given character within the
   * lookup table 
   * Returns a negative value if the given character does not have an entry in
   * the table.
   * 
   * @param toFind
   *   the character for which to search
   * @return
   *   the index of the entry containing the given character, or a negative
   *   value if the given character does not have an entry in the table
   */
  private int binarySearch(char toFind){
    
	  int start = 0;
	  int end = lookup.length-1;
	  while(start <= end) {
		  int mid = (start+end) /2;
		  if(lookup[mid].character == toFind) { 
			  return mid;
		  }
		  else if(lookup[mid].character < toFind) {
			  start = mid+1;
		  }
		  else 
			  end = mid-1;
	  }
	  
   return -1;
  }


  /**
   * A PODT class containing a character and a position.
   * Used as the entry type within {@link Alphabet#lookup lookup}.
   * This class was given by the instructors
   */
  /* already completed */
  private static class CharAndPos{
    /**
     * The character of the entry.
     */
    public char character;

    /**
     * The position of the entry in the ordering.
     */
    public int position;


    /**
     * Constructs and initializes the entry with the given values.
     * 
     * @param character
     *   the character of the entry
     * @param position
     *   the position of the entry
     */
    public CharAndPos(char character, int position)
    {
      this.character = character;
      this.position = position;
    }


    @Override
    public boolean equals(Object obj)
    {
      if (null == obj || this.getClass() != obj.getClass())
      {
        return false;
      }

      CharAndPos o = (CharAndPos) obj;

      return this.character == o.character && this.position == o.position;
    }

    @Override
    public int hashCode(){
      return character ^ position;
    }

    @Override public String toString(){
      return "{" + character + ", " + position + "}";
    }
  }
}
