
public class HuffmanNode {
	
	private Character character;
	private final int frequency;
	public HuffmanNode parent;
    public HuffmanNode leftChild; // 0 branch
    public HuffmanNode rightChild; //1 branch
    
    public HuffmanNode(Character character, int frequency) {
        this.character = character; // will be null if not used
        this.frequency = frequency;
    }
    
    //checks for child for this node
    public boolean emptyLeafNode() {

        return this.leftChild == null && this.rightChild == null;
    }
    
    
    public int getFrequency() {
        return frequency;
    }
    
    public Character getCharacter() {
        return character;
    }
}
