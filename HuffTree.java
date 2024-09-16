import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

import FileProcessing.FileProcessing;
import Nodes.HuffBaseNode;
import Nodes.HuffInternalNode;
import Nodes.HuffLeafNode;

class HuffTree {
    private HuffBaseNode root;
    private Map<Character, String> mapping;
    private Map<Character, Integer> freqMap;
    private FileProcessing file;
    private String encodedFile;

    public HuffTree(String path) {
        root = null;
        mapping = new HashMap<>();
        
        file = new FileProcessing(path);
        freqMap = file.generateFreqMap();

        createTree(freqMap);
        generateEncodings(root, "");
    }

    public Map<Character, String> getMapping() { return mapping;}
    public Map<Character, Integer>getFreqMap(){ return freqMap;}
    public String getEncodedFile(){ return encodedFile;}

    private void createTree(Map<Character, Integer> freqMap) {
        Queue<HuffBaseNode> pq = new PriorityQueue<>(
            (a, b) -> Long.compare(a.weight(), b.weight())
        );
        for(char c: freqMap.keySet()) {
            pq.offer(new HuffLeafNode(c, (long)freqMap.get(c)));
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

    public void generateEncodings(HuffBaseNode curr, String val) {
        if(curr == null) return;
        if(curr.isLeaf()) {
            mapping.put(((HuffLeafNode) curr).getChar(), val);
            return;
        }
              
        generateEncodings(((HuffInternalNode)curr).left(), val+"0");
        generateEncodings(((HuffInternalNode) curr).right(), val+"1");
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

    public void getChar(String enc) {
        HuffBaseNode temp = root;
        try {
            for (int i = 0; i < enc.length(); i++) {
                char c = enc.charAt(i);
                if (temp.isLeaf()) {
                    throw new IllegalArgumentException("Invalid encoding, reached a leaf node prematurely.");
                }
                
                if (c == '0') {
                    temp = ((HuffInternalNode) temp).left();
                } else if (c == '1') {
                    temp = ((HuffInternalNode) temp).right();
                } else {
                    throw new IllegalArgumentException("Invalid character in encoding: " + c);
                }
            }
            
            if (!temp.isLeaf()) {
                throw new IllegalArgumentException("The encoding does not represent a leaf node.");
            }            
            System.out.println(((HuffLeafNode) temp).getChar());            
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    public void compress(String path) {
        List<String> lines = file.getLines();
        StringBuilder sb = new StringBuilder();
        Map<String, Character> map = new HashMap<>();
        for(char c: getMapping().keySet()) map.put(mapping.get(c), c);
        for(String line: lines){
            for(int i=0; i<line.length(); i++) sb.append(mapping.get(line.charAt(i)));
        }
        encodedFile = sb.toString();
        FileProcessing.writeToFile(encodedFile, map, path);
    }

    @SuppressWarnings("unchecked")
    public void deCompress(String compPath, String opPath) {
        Object [] objs = FileProcessing.readFromFile(compPath);
        StringBuilder sb = new StringBuilder();

        Map<String, Character> map = (Map<String, Character>) objs[0];
        encodedFile = (String) objs[1];
        String curr = "";
        for(int i=0; i<encodedFile.length(); i++) {
            curr += encodedFile.charAt(i);
            if(map.containsKey(curr)) {
                sb.append(map.get(curr));
                curr = "";
            }
        }
        FileProcessing.writeStringToFile(sb.toString(), opPath);
    }
}
