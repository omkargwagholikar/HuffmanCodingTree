public class Test {
    public static void main(String[] args) {
        String path = "./TextFiles/source.txt";
        HuffTree root = new HuffTree(path);
        System.out.println(root.getMapping().toString());
        root.encodeFile("./TextFiles/encodedFile.bin");
    }
}

// Map<Character, Integer> map = new HashMap<>();
// int [] freq = {77, 17, 32, 42, 120, 24, 17, 50, 76, 4, 7, 42, 24, 67, 67, 20, 5, 59, 67, 85, 37, 12, 22, 4, 22, 2};
// for(int i=0; i<26; i++) map.put((char)('a'+i), freq[i]);

// int [] freq = {32,42,120,7,42,24,2,37};
// char []vals = {'C','D','E','K','L','M','Z','U'};
// for(int i=0; i<freq.length; i++) map.put(vals[i], freq[i]);