import java.util.Map;
import java.util.Iterator;

public class decryptor {
	private Map<Character, Map<Character, Character>> map;
	private String key;
	private String keystream = "";
	private String plain_text = "";
	private String cipher_text;
	
	public decryptor(Map<Character, Map<Character, Character>> _map, String _key, String text) {
		map = _map;
		key = _key;
		cipher_text = text;
	}

	public void decrypt() {
		// do not edit this method
		generate_keystream();
		generate_plain_text();
	}
	
	private void generate_keystream() {
		if (cipher_text.length() < key.length()) { // if the cipher text is shorter than the key
            keystream = key.substring(0, cipher_text.length()); // take the substring of the key
        } 
		
		else if (cipher_text.length() > key.length()) { // if the cipher text is longer than the key
            int repeat = cipher_text.length() / key.length(); // find the number of times the key should be repeated
            int remainder = cipher_text.length() % key.length(); // find the remainder
            keystream = key.repeat(repeat) + key.substring(0, remainder); // repeat the key and add the remainder
        } 
		else { // if the cipher text and key are of the same length
            keystream = key; // set the keystream to the key
        }

	}
	
	private void generate_plain_text() {
		// You must use map.get(iter.next()).keySet() with an iterator in this method
		Iterator<Character> iter = map.keySet().iterator(); // get an iterator for the map
			for (int i = 0; i < cipher_text.length(); i++) { // iterate through the cipher text
				char cipherChar = cipher_text.charAt(i); // get the character at the current index
				char keyChar = keystream.charAt(i); // get the character from the keystream
				
				while (iter.hasNext()) { // iterate through the map
					char rowChar = iter.next(); // get the character from the iterator
					if (map.get(iter.next()).get(keyChar) == cipherChar) { // if the cipher character is found in the map
						plain_text += rowChar; // add the character to the plain text
						break; // break the loop
					}
				}
				// reset the iterator for next iteration
				iter = map.keySet().iterator();
			}
	}

	public String get_keystream() {
	return keystream;	// return the keystream
	}
	
	public String get_plain_text() {
		return plain_text; // return the plain text
	}
}
