import java.util.Map;

public class encryptor {
	private Map<Character, Map<Character, Character>> map;
	private String key;
	private String keystream = "";
	private String plain_text;
	private String cipher_text = "";
	
	public encryptor(Map<Character, Map<Character, Character>> _map, String _key, String text) {
		
		map = _map; // store the map
		key = _key; // store the key
		plain_text = text; // store the plain text

	}
	
	public void encrypt() {
		// do not edit this method
		generate_keystream();
		generate_cipher_text();
	}
	
	
	private void generate_keystream() { 

		if (plain_text.length() < key.length()) { // if the plain text is shorter than the key
            keystream = key.substring(0, plain_text.length()); // take the substring of the key
        } 
		
		else if (plain_text.length() > key.length()) { // if the plain text is longer than the key
            int repeat = plain_text.length() / key.length(); // find the number of times the key should be repeated
            int remainder = plain_text.length() % key.length(); // find the remainder
            keystream = key.repeat(repeat) + key.substring(0, remainder); // repeat the key and add the remainder
        } 
		else {
            keystream = key; // if the plain text and key are of the same length
        }
	}
	
	private void generate_cipher_text() { 

		for (int i = 0; i < plain_text.length(); i++) { // iterate through the plain text
            char plainChar = plain_text.charAt(i); // get the character at the current index
            char keyChar = keystream.charAt(i); // get the character from the keystream
            char cipherChar = map.get(plainChar).get(keyChar); // get the cipher character from the map
            cipher_text += cipherChar; // add the cipher character to the cipher text
        }
	}

	public String get_keystream() {
return keystream; // return the keystream
	}
	
	public String get_cipher_text() {
return cipher_text; // return the cipher text
	}
}
