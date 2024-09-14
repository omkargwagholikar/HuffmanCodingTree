import java.util.HashMap;
import java.util.Map;

import Nodes.HuffBaseNode;
import Nodes.HuffLeafNode;

public class Test {
    public static void main(String[] args) {
        Map<Character, Integer> map = new HashMap<>();        
        // int [] freq = {77, 17, 32, 42, 120, 24, 17, 50, 76, 4, 7, 42, 24, 67, 67, 20, 5, 59, 67, 85, 37, 12, 22, 4, 22, 2};
        // for(int i=0; i<26; i++) map.put((char)('a'+i), freq[i]);
        map.put('C',32);
        map.put('D',42);
        map.put('E',120);
        map.put('K',7);
        map.put('L',42);
        map.put('M',24);
        map.put('Z',2);
        map.put('U',37);
        HuffTree root = new HuffTree(map);
        for(HuffBaseNode r: root.bfs()) {
            if(r.isLeaf()) {
                HuffLeafNode leaf = (HuffLeafNode)r;
                System.out.println(leaf.getChar() + " " + leaf.weight() + " -> ");
            } else {
                System.out.print(r.weight() + " -> ");
            }
        }
    }
}
