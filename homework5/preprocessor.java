public class preprocessor {
	private String initial_string;
	private String preprocessed_string;
		
	public preprocessor(String str) {
		// do not edit this method
		initial_string = str; // store the initial string
		preprocessed_string = ""; // initialize the preprocessed string
		
	}

	public void preprocess() {
		// do not edit this method
		capitalize(); // capitalize the string
		clean(); // remove non-letter characters 
	}
	
	private void capitalize() {
		
		StringBuilder capitalizedString = new StringBuilder(); // to store the capitalized string
		for (int i = 0; i < initial_string.length(); i++) { // iterate through the string
			char c = initial_string.charAt(i); // get the character at index i
			if(c >= 'a' && c <= 'z'){ // if the character is a lowercase letter
				c = (char) (c - 32); // convert it to uppercase
				capitalizedString.append(c); // append it to the capitalized string
			}
			else if(c >= 'A' && c <= 'Z'){ // if the character is already uppercase
				capitalizedString.append(c); // append it to the capitalized string
			}
			else{ // if the character is not a letter
				continue; // skip it
			}

		}
		preprocessed_string = capitalizedString.toString(); // store the capitalized string in the preprocessed_string variable
	}

	private void clean() {
		StringBuilder sb = new StringBuilder(); // to store the cleaned string
		for (int i = 0; i < preprocessed_string.length(); i++) { // iterate through the preprocessed string
			char c = preprocessed_string.charAt(i); // get the character at index i
			if (c >= 'A' && c <= 'Z') { // if the character is a letter
				sb.append(c); // append it to the cleaned string
			}
		}
		preprocessed_string = sb.toString(); // store the cleaned string in the preprocessed_string variable
	}
	
	public String get_preprocessed_string() { 
		return preprocessed_string; // return the preprocessed string
	}
}