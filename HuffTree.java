import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

import Nodes.*;

class HuffTree {
    public HuffBaseNode root;

    public HuffTree(Map<Character, Integer> map) {
        root = null;
        createTree(map);
    }
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

    public List<HuffBaseNode> bfs() {
        List<HuffBaseNode> res = new ArrayList<>();
        Queue<HuffBaseNode> q = new LinkedList<>();
        q.offer(root);
        while (q.size() > 0) {
            HuffBaseNode curr = q.poll();
            res.add(curr);
    
            if (!curr.isLeaf()) {
                try{
                    HuffInternalNode internalNode = (HuffInternalNode) curr;
                    if (internalNode.left() != null) q.offer(internalNode.left());
                    if (internalNode.right() != null) q.offer(internalNode.right());
                } catch(Exception e) {

                }
            }
        }
        return res;
    }
    
}