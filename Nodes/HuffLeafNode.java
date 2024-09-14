package Nodes;

public class HuffLeafNode extends HuffBaseNode{
    char val;
    public HuffLeafNode() {
        val = '~';
        weight = Long.MIN_VALUE;
    }
    HuffLeafNode(char val, long weight) {
        this.val = val;
        this.weight = weight;
    }
    public void get() {
        System.out.println("everything works");
    }
}