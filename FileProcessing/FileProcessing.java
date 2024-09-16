package FileProcessing;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class FileProcessing {
    String path;
    public FileProcessing(String path) {
        this.path = path;
    }
    public Map<Character, Integer> process() {
        Map<Character, Integer> map = new HashMap<>();
        try {
            File myObj = new File(path);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                for(int i=0; i<data.length(); i++) map.put(data.charAt(i), 1 + map.getOrDefault(data.charAt(i), 0));
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return map;
    }
}
