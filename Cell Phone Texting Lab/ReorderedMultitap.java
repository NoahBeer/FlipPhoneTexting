/**
 * ReorderedMultitap extends Multitap and calls super() in the constructor to have access to both of the
 * methods implemented in Multitap. ReorderedMultitap then rearranges the characters in each array so that 
 * they appear in the order they are most frequently used.
 * 
 * @author Noah Beer and Nicholas Pumper
 */
public class ReorderedMultitap extends Multitap {
	
	/**
	 * The constructor calls super() in order to use Multitap's constructor and also rearanges the characters of
	 * each array.
	 */
	public ReorderedMultitap() {
		super(); // super call
		super.two = new char[] {'a','c','b'};
		super.three = new char[] {'e','d','f'};
		super.four = new char[] {'i','h','g'};
		super.five = new char[] {'l','k','j'};
		super.six = new char[] {'o','n','m'};
		super.seven = new char[] {'s','r','p','q'};
		super.eight = new char[] {'t','u','v'};
		super.nine = new char[] {'w','y','x','z'};
		super.twoUpper = new char[] {'A','C','B'};
		super.threeUpper = new char[] {'E','D','F'};
		super.fourUpper = new char[] {'I','H','G'};
		super.fiveUpper = new char[] {'L','K','J'};
		super.sixUpper = new char[] {'O','N','M'};
		super.sevenUpper = new char[] {'S','R','P','Q'};
		super.eightUpper = new char[] {'T','U','V'};
		super.nineUpper = new char[] {'W','Y','X','Z'};
	}
}
