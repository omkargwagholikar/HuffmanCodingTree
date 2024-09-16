package FileProcessing;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class FileProcessing {
    private List<String> lines;
    private String path;
    public FileProcessing(String path) {
        this.path = path;
        lines = new ArrayList<>();
    }
    public Map<Character, Integer> generateFreqMap() {
        Map<Character, Integer> map = new HashMap<>();
        try {
            File myObj = new File(path);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine() + "~~";
                lines.add(data);
                for(int i=0; i<data.length(); i++) map.put(data.charAt(i), 1 + map.getOrDefault(data.charAt(i), 0));
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return map;
    }

    public List<String> getLines() {
        return lines;
    }

    public static void writeToFile(String binaryString, Map<String, Character> map, String filePath) {
        int byteArrayLength = (binaryString.length() + 7) / 8; 
        byte[] byteArray = new byte[byteArrayLength];

        for (int i = 0; i < binaryString.length(); i++) {
            if (binaryString.charAt(i) == '1') {
                byteArray[i / 8] |= (1 << (7 - (i % 8))); 
            }
        }
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath))) {
            oos.writeObject(map);
            oos.write(byteArray);        
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    public static Object [] readFromFile(String path) {
        Object []res  = new Object[2];
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(path))) {
            res[0] = (HashMap<String, Character>) ois.readObject();
            byte[] byteArray = ois.readAllBytes();
            StringBuilder binaryString = new StringBuilder();
            for (byte b : byteArray) {
                for (int i = 7; i >= 0; i--) {
                    binaryString.append((b >> i) & 1);
                }
            }
            res[1] = binaryString.toString();
            return res;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void writeStringToFile(String content, String path) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(path))) {
            String [] lines = content.split("~~");
            for(String line: lines) {
                writer.write(line + "\n");
            }
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
