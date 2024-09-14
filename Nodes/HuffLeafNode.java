package Nodes;

public class HuffLeafNode extends HuffBaseNode{
    private char val;
    public HuffLeafNode() {
        isLeaf = true;
        val = '~'; // ~ is used as an arbitrary value for default initialization
        weight = Long.MIN_VALUE;
    }
    public HuffLeafNode(char val, long weight) {
        isLeaf = true;
        this.val = val;
        this.weight = weight;
    }
    public void get() {
        System.out.println("everything works");
    }

    public boolean isLeaf() {return isLeaf;}
    public char getChar() {return val;}
    public String toString() {
        return "{isLeaf: true, weight: " + weight +", val: " + val + " }";
    }
}