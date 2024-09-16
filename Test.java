import FileProcessing.FileProcessing;

public class Test {
    public static void main(String[] args) {
        String filePath = "./TextFiles/test.txt";
        String mapFile = "./TextFiles/s1.bin";
        HuffTree root = new HuffTree(filePath);
        System.out.println(root.getMapping().toString());
        FileProcessing.writeMapToFile(root.getMapping(), mapFile);
        System.out.println(FileProcessing.readMapFromFile(mapFile).toString());
    }
}
