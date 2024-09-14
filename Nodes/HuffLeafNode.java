package Nodes;

public class HuffLeafNode extends HuffBaseNode{
    private char val;
    public HuffLeafNode() {
        isLeaf = true;
        val = '~';
        weight = Long.MIN_VALUE;
    }
    public HuffLeafNode(char val, long weight) {
        this.val = val;
        this.weight = weight;
    }
    public void get() {
        System.out.println("everything works");
    }
    
    public char getChar() {return val;}
}