
public class BruteForce {

private String substring;
private String text;

public BruteForce(String text, String substring) {
	this.text = text;
	this.substring= substring;
}
public int search(String substring, String text) {
	int maxIndex = text.length()-substring.length();
	boolean found;
	for(int k = 0; k < maxIndex; k++) {
		found = true;
		for(int i = 0; i < substring.length()-1; i++) {
			if(substring.charAt(i) != text.charAt(k+i)) {
				found = false;
				break;
			}
		}
		if(found == true) {
			return k;
		}
	}
	return -1;

}
}
