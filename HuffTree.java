import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

import Nodes.*;

class HuffTree {
    private HuffBaseNode root;
    private Map<Character, Byte> mapping;

    public HuffTree(Map<Character, Integer> map) {
        root = null;
        mapping = new HashMap<>();
        createTree(map);
        generateEncodings(root, (byte)0);
    }

    public Map<Character, Byte> getMapping() { return mapping;}

    private void createTree(Map<Character, Integer> map) {
        Queue<HuffBaseNode> pq = new PriorityQueue<>(
            (a, b) -> Long.compare(a.weight(), b.weight())
        );
        for(char c: map.keySet()) {
            pq.offer(new HuffLeafNode(c, (long)map.get(c)));
        }
        if(pq.size() == 0) {
            System.out.println("The map seems to be empty");
            return;
        }
        while (pq.size() >= 2) {
            HuffBaseNode left = pq.poll();
            HuffBaseNode right = pq.poll();
            HuffInternalNode newNode = new HuffInternalNode(left, right, left.weight() + right.weight());
            pq.offer(newNode);
        }
        HuffBaseNode newRoot = pq.poll();
        root = newRoot;
    }

    public void generateEncodings(HuffBaseNode curr, byte val) {
        if(curr == null) return;
        if(curr.isLeaf()) {
            mapping.put(((HuffLeafNode) curr).getChar(), val);
            return;
        }
        val <<= 1;        
        generateEncodings(((HuffInternalNode)curr).left(), val);
        val ^= (byte)1;
        generateEncodings(((HuffInternalNode) curr).right(), val);
    }

    public void bfs() {
        Queue<HuffBaseNode> q = new LinkedList<>();
        if(root == null) {
            System.out.println("null");
            return;
        }
        q.offer(root);
        q.offer(null);
        while (q.size() > 1) {
            HuffBaseNode curr = q.poll();
            if(curr == null) {
                System.out.println();
                q.offer(null);
                continue;
            }
            System.out.print(curr.toString() + " ");
            if (!curr.isLeaf()) {
                HuffInternalNode internalNode = (HuffInternalNode) curr;
                if (internalNode.left() != null) q.offer(internalNode.left());
                if (internalNode.right() != null) q.offer(internalNode.right());
            }
        }
    }
    
}