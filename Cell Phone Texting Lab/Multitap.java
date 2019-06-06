/**
 * Multitap allows the user to input which characters he/she would like displayed
 * 
 * Author Noah Beer and Nicholas Pumper
 */
public class Multitap implements Tappable{
	/* stores amount of presses **/
	protected int presses;
	/* stores amount of presses **/
	protected int numPresses;
	
	
	/* the arrays below store all upper and lower case chars**/
	protected char[] two;
	protected char[] three;
	protected char[] four;
	protected char[] five;
	protected char[] six;
	protected char[] seven;
	protected char[] eight;
	protected char[] nine;
	protected char[] twoUpper;
	protected char[] threeUpper;
	protected char[] fourUpper;
	protected char[] fiveUpper;
	protected char[] sixUpper;
	protected char[] sevenUpper;
	protected char[] eightUpper;
	protected char[] nineUpper;
	
	/* stores the prior key**/
	protected int oldKey;
	/* toggles shift**/
	protected boolean shift;
	/* stores the old text **/
	protected String oldTxt;

	
	/**
	 * The constructor initializes each variable to its necessary value
	 */
	public Multitap() {
		presses = 0; 
		numPresses = 0;
		two = new char[] {'a','b','c'};
		three = new char[] {'d','e','f'};
		four = new char[] {'g','h','i'};
		five = new char[] {'j','k','l'};
		six = new char[] {'m','n','o'};
		seven = new char[] {'p','q','r','s'};
		eight = new char[] {'t','u','v'};
		nine = new char[] {'w','x','y','z'};
		twoUpper = new char[] {'A','B','C'};
		threeUpper = new char[] {'D','E','F'};
		fourUpper = new char[] {'G','H','I'};
		fiveUpper = new char[] {'J','K','L'};
		sixUpper = new char[] {'M','N','O'};
		sevenUpper = new char[] {'P','Q','R','S'};
		eightUpper = new char[] {'T','U','V'};
		nineUpper = new char[] {'W','X','Y','Z'};
		oldKey = -1;
		shift = false;
	}
	/**
	 * returns numPresses
	 */
	@Override
	public int getNumPresses() {
		return numPresses;
	}
	
	/**
	 * Method signature for append to be implemented
	 * 
	 * @param txt   The text entered by the keypad so far
	 * @param key   The number of the key that's been pressed
	 */
	@Override
	public String append(String txt, int key) {
		numPresses++;
		if(key > 11 || key < 0) {
			throw new IllegalArgumentException("Key is out of bounds");
		}
		
		if(txt.length() == 0 || key == 1 || key == 0 || key == 10) {
			oldTxt = txt;
		}else {
			oldTxt = txt.substring(0, txt.length()-1);
		}
		
		if(key == 10 && txt.length() != 0 && oldKey != 1) {
			oldTxt = txt.substring(0, txt.length()-1);
		}
		
		if(key == 10) {
			key = oldKey;
			shift = !shift;
		}
		
		if (key == 1 || key == 0) {
			oldKey = key;
			presses = 0;
			return oldTxt;

		}
		if(key != oldKey ) {
			oldTxt = txt;
			presses = 0;
		} 
		
		if(key == 11) {
			presses = 0;
			oldKey = -1;
			txt = txt + " ";
		}
		/*
		if(key != 10 && key != 11) {
			numPresses++;
		}*/
		if(key >= 2 && key <= 9 ) {
			if(key != oldKey && presses == 0 && txt.length() != 0) {
				shift = false;
			}
			txt = oldTxt + chooseLetter(key, presses);
			presses++;
			oldKey = key;
			return txt;
			
		}else {return txt;}
	}
	/**
	 * choose the correct letter to return
	 * @param key  the button pushed
	 * @param presses  how many times the button has been pushed
	 * @return   character at key and presses
	 */
	public char chooseLetter(int key, int presses) {
		if(shift) {
			if(key == 2) {
				presses = presses % 3;
				return twoUpper[presses];
			} else if(key == 3) {
				presses = presses % 3;
				return threeUpper[presses];
			} else if(key == 4) {
				presses = presses % 3;
				return fourUpper[presses];
			} else if(key == 5) {
				presses = presses % 3;
				return fiveUpper[presses];
			} else if(key == 6) {
				presses = presses % 3;
				return sixUpper[presses];
			} else if(key == 7) {
				presses = presses % 4;
				return sevenUpper[presses];
			} else if(key == 8) {
				presses = presses % 3;
				return eightUpper[presses];
			} else if(key == 9){
				presses = presses % 4;
				return nineUpper[presses];
			}
			
		}else {
			if(key == 2) {
				presses = presses % 3;
				return two[presses];
			} else if(key == 3) {
				presses = presses % 3;
				return three[presses];
			} else if(key == 4) {
				presses = presses % 3;
				return four[presses];
			} else if(key == 5) {
				presses = presses % 3;
				return five[presses];
			} else if(key == 6) {
				presses = presses % 3;
				return six[presses];
			} else if(key == 7) {
				presses = presses % 4;
				return seven[presses];
			} else if(key == 8) {
				presses = presses % 3;
				return eight[presses];
			} else if(key == 9){
				presses = presses % 4;
				return nine[presses];
			} 
		}
		return 'E';
		
	}
	
	
}
