/**
 * A new KMP instance is created for every substring search performed. Both the
 * pattern and the text are passed to the constructor and the search method. You
 * could, for example, use the constructor to create the match table and the
 * search method to perform the search itself.
 */
public class KMP {

	private String substring;
	private String text;
	private int[] jumpTable;

	public KMP(String substring, String text) {
		if(substring.length() >=1) {
			jumpTable = calculateJumpTable(substring);
		}
		this.substring = substring;
		this.text = text;
	}

	/**
	 * Perform KMP substring search on the given text with the given pattern.
	 *
	 * This should return the starting index of the first substring match if it
	 * exists, or -1 if it doesn't.
	 */
	public int search(String substring, String text) {
		int textStart = 0;
		int substringStart = 0;
		while(textStart + substringStart < text.length()) {
			if(substring.charAt(substringStart) == text.charAt(textStart + substringStart)){
				substringStart++;
				//found substring match
				if(substringStart == substring.length()) {
					return textStart;
				}
			}
			//no match and no overlap
			else if(this.jumpTable[substringStart]== -1){
				textStart += substringStart + 1;
				substringStart = 0;
			}
			//no match and there is overlap
		 else {
			 textStart = substringStart- this.jumpTable[substringStart];
			 substringStart = this.jumpTable[substringStart];
		 }
		}

		return -1;
	}

	public int[] calculateJumpTable(String substring) {
		if(!(substring.length() >=1)) {
			return null;
		}
		int[] jumpTable = new int[substring.length()];
		jumpTable[0] = -1;
		if(substring.length() >=2) {
			jumpTable[1] = 0;
		}
		else {
			return jumpTable;
		}
		int index = 0;
		int pos = 2;

		while(pos < index) {
	    // found match
			if(substring.charAt(pos-1)== substring.charAt(index)) {
				jumpTable[pos] = index +1;
				pos++;
				index++;
			}
		//no match
			else if(index >0) {
				index = jumpTable[index];
			}
		//nothing to match
			else {
				jumpTable[pos] = 0;
				pos++;
			}
		}

		return jumpTable;
	}
}
