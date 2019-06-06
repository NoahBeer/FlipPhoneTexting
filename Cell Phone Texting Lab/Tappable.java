public interface Tappable 
{
	/**
	 * @author Nicholas Pumper and Noah Beer
	 * 10 March 2018
	 * Last Mod: 10 March 2018
	 * 
	  * Implement this so the Keypad can use your interface.
	 */
	
	/**
	 * This method is invoked by the keypad whenever a key is pressed. It's passed
	 * the entire contents of the display along with the number of the pressed key.
	 * We just tack a new character onto the display's text and return the new
	 * string.
	 * 
	 * @param txt
	 * 		The text this Keypad will append to.
	 * 
	 * @param key
	 * 		The key that was pressed on this Keypad.
	 * 
	 * @return
	 * 		Return the string with the appended characters.
	 */
	public String append(String txt, int key);
	
	/**
	 * Accessor for returning the total number of presses.
	 * 
	 * @return Total number of presses
	 */
	public int getNumPresses();
}
