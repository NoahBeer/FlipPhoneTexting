public class EntryStats {

	/**
	 * Takes a Tappable object and a string, and counts how many presses it took to create that string in the Tappable object.
	 * 
	 * @author Nick Pumper & Noah Beer
	 * March 18 2018
	 */
	
	private Tappable entryMethod; // The Keypad we find the entry statistics of.
	private Tappable entryMethodTestDouble; // The Keypad double we use the find out which key each character is on.
	private double totalNumberOfCharacters = 0; // counts the number of characters put through the entryMethod.
	
	/**
	 * 	Constructor for an EntryStats object
	 * 
	 * @param entryMethodInput -- The Tappable object we will use find the entry stats of for a block of text.
	 */
	public EntryStats(Tappable entryMethodInput) {
		
		// error catch
		if (entryMethodInput == null) {
			try {
				throw new IllegalArgumentException("ERROR! --- There is no valid Tappable object given to the EntryStats constructor.");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		this.entryMethod = entryMethodInput;
		
		try {
			entryMethodTestDouble = entryMethodInput.getClass().newInstance();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	} // EntryStats(Tappable entryMethodInput)
	
	/**
	 * Determines the sequence of key presses necessary to generate the given String
	 * by punching the string into the entryMethod.
	 * 
	 * @param input
	 */
	public void generateText (String input) {
		int prevKey = 0;
		String inputTypedOnKeypad = "";
		
		// for every character on the strings
		for (int i = 0; i < input.length(); i++)
		{
			int keyForThisChar = -1;
			
			// System.out.println("at " + (input.charAt(i)) + " we have keyPresses = " + this.entryMethod.getNumPresses());
			// System.out.println("genText(): Type in the letter " + input.charAt(i));
						
			// define which key this character is on and store it so we don't search again.
			// this should be -1 if it's a space or a character not in the keyboard
			keyForThisChar = getKeyOfCurChar(input.charAt(i));
			
			// if it's a space, type a space, otherwise find the character.
			// OR
			// type a space if it's a character not in the keyboard
			if (input.charAt(i) == ' ') {
				inputTypedOnKeypad = entryMethod.append(inputTypedOnKeypad, 11);
				// System.out.println("Theres a space or invalid character");
			}
			else {
				
				// If the previous key is the same, type the 1 key first so we dont cycle through on the same key and get the wrong letter
				if (keyForThisChar == prevKey) {
					inputTypedOnKeypad = entryMethod.append(inputTypedOnKeypad, 1);
				} 
				
				
				// press the key, that has the character, once (initial key press)
				inputTypedOnKeypad = entryMethod.append(inputTypedOnKeypad, keyForThisChar);

				// if it's an uppercase letter, press shift before we find the character.
				// we see if its an uppercase by calling toUpperCase on the current character and seeing if
				// that character and the uppercase version are the same.
				// our current implementation of Multitap doesnt shift after a space or 1 key, so we have to put this after an initial key press
				if (input.charAt(i) == Character.toUpperCase(input.charAt(i))) {
					// System.out.println("The current character " + input.charAt(i) + " is a capital letter!");
					inputTypedOnKeypad = entryMethod.append(inputTypedOnKeypad, 10);
				}
				
				// Press the key until the character matches up with the corresponding character from input				
				int count = 0;
				while (inputTypedOnKeypad.charAt(i) != input.charAt(i)) {
					// System.out.println(inputTypedOnKeypad);
					inputTypedOnKeypad = entryMethod.append(inputTypedOnKeypad, keyForThisChar);
					count++;
					if(count > 26) {
						inputTypedOnKeypad = entryMethod.append(inputTypedOnKeypad, 10);
					}
					if(count > 52) {
						try {
							throw new Exception("ERROR! --- Character could not be found on key.");
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
				
				//this.totalMappingKeyPresses = totalMappingKeyPresses + count;
			}
			
			// System.out.println("inputTypedOnKeypad = " + inputTypedOnKeypad + " and i = " + i);
			prevKey = keyForThisChar;
					
			// System.out.println("genText(): and our string is " + inputTypedOnKeypad);
			
			this.totalNumberOfCharacters++;
		}
		
		// System.out.println("Gen text quits!!!");
	} // generateText (String input) 
		
	/**
	 * Return the integer of the key that the given character is on.
	 * 
	 * @param character -- the character that we're trying to find on the given KeyPad
	 * @return The integer of the key that the given character is on. Returns -1 if there is no key that it is on.
	 */
	private int getKeyOfCurChar (char character) {
		// First map them key's characters
		int highestKey = 9;
		
		// System.out.println("Find " + character);
		
		// convert it to lowercase so we can find the damn thing
		character = Character.toLowerCase(character);
		
		// iterate through the keys
		for (int key = 2; key <= highestKey; key++) {
			
			String 	charsOnThisKey = "";
			boolean repeated = false;
			int keyPresses = 0;

			// press the lower-case keys until we find a repeated letter
			while (!repeated) {
				
				// Press the key
				for (int i = 0; i < keyPresses + 1; i++) {
					charsOnThisKey = this.entryMethodTestDouble.append(charsOnThisKey, key);
				}
				
				// confirm this key
				charsOnThisKey = entryMethodTestDouble.append(charsOnThisKey, 1);
				
				// System.out.println(charsOnThisKey);
				keyPresses++;
								
				// find a match in the characters
				for (int i = 0; i< charsOnThisKey.length(); i++) {	
					// if it does repeat, stop
					if (charsOnThisKey.charAt(i) == charsOnThisKey.charAt(0) && i != 0) {
						// If it repeated, stop this while loop and move to the next key.
						repeated = true;
					}
					
					// System.out.println("Comparing: " + charsOnThisKey.charAt(i) + " and " + character);
					if (charsOnThisKey.charAt(i) == character) {
						// System.out.println("Returning the key " + key + " for the character " + character);
						return key;
					}
				} // for i
				
				
			} // while we haven't found a repeated letter
		}  // for loop to iterate through all the keys
		
		// if nothing is found
		return -1;
	} // int getKeyOfCurChar (char character) 
	
	/**
	 * Returns a string containing the class name of the entryMethod, the total number of key
	 * presses performed, the total number of characters generated, and the average number 
	 * presses per character.
	 */
	public String toString() {
		return "The name of the entry methods class is: " + entryMethod.getClass().getName() + "\nThe total number of key Presses is: " + (entryMethod.getNumPresses()) + "\nThe total number of characters generated is : " + this.totalNumberOfCharacters + "\nThe average number of key presses per character is: " +  (entryMethod.getNumPresses()) / this.totalNumberOfCharacters;
	} // String toString()
}
