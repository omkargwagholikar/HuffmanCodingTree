package FileProcessing;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class FileProcessing {
    private List<String> lines;
    private String path;
    public FileProcessing(String path) {
        this.path = path;
        lines = new ArrayList<>();
    }
    public Map<Character, Integer> process() {
        Map<Character, Integer> map = new HashMap<>();
        try {
            File myObj = new File(path);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
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
    public void writeStringToFile(String binaryString, String path) {
        int byteArrayLength = (binaryString.length() + 7) / 8; 
        byte[] byteArray = new byte[byteArrayLength];

        for (int i = 0; i < binaryString.length(); i++) {
            if (binaryString.charAt(i) == '1') {
                byteArray[i / 8] |= (1 << (7 - (i % 8))); 
            }
        }

        try (FileOutputStream fos = new FileOutputStream(path)) {
            fos.write(byteArray);
        } catch (Exception e){
            System.err.println(e.getMessage());
        }
    }

    public String readStringFromFile(String path) {
        try {
            FileInputStream fis = new FileInputStream(path);
            byte[] byteArray = fis.readAllBytes();
            StringBuilder binaryString = new StringBuilder();
            for (byte b : byteArray) {
                for (int i = 7; i >= 0; i--) {
                    binaryString.append((b >> i) & 1);
                }
            }
            fis.close();            
            return binaryString.toString();

        } catch(Exception e) {
            System.err.println(e.getMessage());
            return "";
        }
    }
}
