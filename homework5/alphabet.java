import java.util.HashMap;
import java.util.Map;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.Iterator;

public class alphabet {
	private Set<Character> english_alphabet = new LinkedHashSet<Character>(); 
	private Map<Character, Map<Character, Character>> map = new HashMap<Character,  Map<Character, Character>>();
	
	public alphabet() {
		// do not edit this method
		fill_english_alphabet(); // fill the english_alphabet set
		fill_map(); // fill the map variable
	}
	
	private void fill_english_alphabet() { 
		// do not edit this method
		for(char c : "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray()) {
		    english_alphabet.add(c);
		}
	}
	
	private void fill_map() {
		// You must use the "english_alphabet" variable in this method, to fill the "map" variable.
		// You can define 1 or 2 iterators to iterate through the set items.

		Iterator<Character> iteratorRow = english_alphabet.iterator(); // iterator for rows
        Iterator<Character> iteratorCol; // iterator for columns

        while (iteratorRow.hasNext()) { // iterate through the rows
            char rowChar = iteratorRow.next(); // get the row character
            iteratorCol = english_alphabet.iterator(); // initialize the column iterator
            Map<Character, Character> innerMap = new HashMap<Character, Character>(); // inner map for each row

            while (iteratorCol.hasNext()) { // iterate through the columns
                char colChar = iteratorCol.next(); // get the column character
                int shiftedIndex = (colChar - 'A' + (rowChar - 'A')) % 26; // Vigenère shift
                char newValue = (char) ('A' + shiftedIndex); // new value for the column character
                innerMap.put(colChar, newValue); // put the column character and the new value in the inner map
            }
            map.put(rowChar, innerMap); // put the row character and the inner map in the map
        }
	}

	public void print_map() {
		// do not edit this method
		System.out.println("*** Viegenere Cipher ***\n\n");
		System.out.println("    " + english_alphabet);
		System.out.print("    ------------------------------------------------------------------------------");
		for(Character k: map.keySet()) {
			System.out.print("\n" + k + " | ");
			System.out.print(map.get(k).values());
		}
		System.out.println("\n");
		
	}

	public Map get_map() {
		return map; // return the map
	}
}