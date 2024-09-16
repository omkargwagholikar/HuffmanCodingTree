package FileProcessing;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Stack;

public class BitProcessor {
    public void bitWriter(String path, byte [] vals) {                
        Stack<Integer> stack = new Stack<>();
        for(byte val: vals) {            
            for(int i=0; i<8; i++) {
                stack.push(val & 1);
                val >>= 1;
            }
            
        }
        byte [] fin = new byte[(int)Math.ceil((double)stack.size() / 8)];
        int pos = 0;
        System.out.println("final byte array: ");
        while(!stack.empty()) {
            byte v = 0;
            for(int i=0; i<8; i++) {                
                System.out.println(v);
                v <<= 1;
                if(!stack.empty()) v ^= stack.pop();
            }
            fin[pos] = v;
            pos += 1;
        }
        System.out.println("");

        BufferedOutputStream bs = null;
        try {
            FileOutputStream fs = new FileOutputStream(new File(path));
            bs = new BufferedOutputStream(fs);
            bs.write(fin);
            bs.close();
            bs = null;

        } catch (Exception e) {
            e.printStackTrace();
        }
        if (bs != null) try { bs.close(); } catch (Exception e) {}
    }
}
