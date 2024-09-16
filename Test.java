public class Test {
    public static void main(String[] args) {
        String filePath = "./TextFiles/s1.txt";
        String encoPath = "./TextFiles/s1.bin";
        HuffTree root = new HuffTree(filePath);
        System.out.println(root.getMapping().toString());
        root.compress(encoPath);        
        System.out.println(root.deCompress(encoPath));
    }
}
