public class Test {
    public static void main(String[] args) {
        String filePath = "./TextFiles/s1.txt";
        String compPath = "./TextFiles/s1Comp.txt";
        String deCompPath = "./TextFiles/s1deComp.txt";
        HuffTree root = new HuffTree(filePath);
        root.compress(compPath);
        root.deCompress(compPath, deCompPath);
    }
}
