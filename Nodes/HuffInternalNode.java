package Nodes;

public class HuffInternalNode extends HuffBaseNode {
    private HuffBaseNode left;
    private HuffBaseNode right;

    public HuffInternalNode() {
        isLeaf = false;
        weight = Long.MIN_VALUE;
        left = right = null;
    }
    public HuffInternalNode(HuffBaseNode left, HuffBaseNode right, long weight) {
        this.left = left;
        this.right = right;
        this.weight = weight;
    }
}