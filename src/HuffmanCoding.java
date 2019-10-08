import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * A new instance of HuffmanCoding is created for every run. The constructor is
 * passed the full text to be encoded or decoded, so this is a good place to
 * construct the tree. You should store this tree in a field and then use it in
 * the encode and decode methods.
 */
public class HuffmanCoding {
	private Map<Character, String> charToBinaryMap = new HashMap<>();
	private Map<String, Character> binaryToCharMap = new HashMap<>();
	private HuffmanNode root;
	private String text;

	/**
	 * This would be a good place to compute and store the tree.
	 */
	public HuffmanCoding(String text) {
		this.text = text;
		this.root = HuffmanCoding.createHuffmanTree(HuffmanCoding.calcCharFrequency(this.text));
		this.charToBinaryMap = HuffmanCoding.createBinaryMap(this.root);
		this.binaryToCharMap = stringToCharMap(this.charToBinaryMap);
	}

	//changes string to a char because of the string being 1's and 0's therefore easier to find matching char
	private static Map<String, Character> stringToCharMap(Map<Character, String> map) {
		Map<String, Character> charMap = new HashMap<>();
		for(Character character: map.keySet()){
			charMap.put(map.get(character), character);
		}
		return charMap;
	}
	public static Map<Character, String> createBinaryMap(HuffmanNode node) {

		return createBinaryRecursive(node, "");

	}

	private static Map<Character, String> createBinaryRecursive(HuffmanNode node, String parent) {
		// TODO Auto-generated method stub
		Map<Character, String> binaryMap = new HashMap<>();
		if (!node.emptyLeafNode()) {
			binaryMap.putAll(createBinaryRecursive(node.leftChild, parent + "0"));
			binaryMap.putAll(createBinaryRecursive(node.rightChild, parent + "1"));
		} else {
			binaryMap.put(node.getCharacter(), parent);
		}
		return binaryMap;
	}

	public static HuffmanNode createHuffmanTree(List<HuffmanNode> frequencyOfChar) {
		PriorityQueue<HuffmanNode> queue = new PriorityQueue<>((a, b) -> a.getFrequency() - b.getFrequency());
		queue.addAll(frequencyOfChar);
		while (!queue.isEmpty()) {
			if(queue.size() == 1) {
				return queue.poll();
			}
			HuffmanNode temp1 = queue.poll();
			HuffmanNode temp2 = queue.poll();
			HuffmanNode tempParent = new HuffmanNode(null, temp1.getFrequency() + temp2.getFrequency());
			queue.add(tempParent);
			tempParent.leftChild = temp1;
			tempParent.rightChild = temp2;
			temp1.parent = tempParent;
			temp2.parent = tempParent;
		}
		throw new IllegalStateException("Error with tree");
	}

	/**
	 * Calculates the frequency of characters. The frequencies are a simple integer.
	 */
	public static List<HuffmanNode> calcCharFrequency(String text) {
		Map<Character, Integer> tellyMap = new HashMap<>();
		List<HuffmanNode> nodeList = new ArrayList<>();
		int textLength = text.toCharArray().length;
		char[] textArray = text.toCharArray();

		for (int i = 0; i < textLength; i++) {
			Character character = textArray[i];
		//for (Character character : text.toCharArray()) {
			if (tellyMap.containsKey(character)) {
				tellyMap.put(character, tellyMap.get(character) + 1);
			}
			else {
				tellyMap.put(character, 1);
			}
		}
		for (Character character : tellyMap.keySet()) {
			nodeList.add(new HuffmanNode(character, tellyMap.get(character)));
		}

		return nodeList;
	}

	/**
	 * Take an input string, text, and encode it with the stored tree. Should return
	 * the encoded text as a binary string, that is, a string containing only 1 and
	 * 0.
	 */
	public String encode(String text) {
		StringBuilder stringBuilder = new StringBuilder(text.length());
		//char[] textArray = text.toCharArray();

		//for (int q = 0; q < textArray.length; q++) {
		//	Character character = textArray[q];
		for (Character character : text.toCharArray()) {
			stringBuilder.append(this.charToBinaryMap.get(character));
		}
		return stringBuilder.toString();
	}

	/**
	 * Take encoded input as a binary string, decode it using the stored tree, and
	 * return the decoded text as a text string.
	 */
	public String decode(String encoded) {
		StringBuilder stringBuilder = new StringBuilder();
		char[] encodeArray = encoded.toCharArray();
		String currentTempBinary = "";
		int d = 0;


		while (d < encodeArray.length) {
			currentTempBinary += encodeArray[d];

			//match found
			if (this.binaryToCharMap.containsKey(currentTempBinary)) {
				Character character = this.binaryToCharMap.get(currentTempBinary);
				currentTempBinary = "";

				stringBuilder.append(character);
			}
			d++;
		}

		//This should be empty after decoding.
		if (!currentTempBinary.isEmpty())
			throw new IllegalArgumentException("Invalid encoded string");

		return stringBuilder.toString();

	}

	/**
	 * The getInformation method is here for your convenience, you don't need to
	 * fill it in if you don't wan to. It is called on every run and its return
	 * value is displayed on-screen. You could use this, for example, to print out
	 * the encoding tree.
	 */
	public String getInformation() {
		return "";
	}
}
