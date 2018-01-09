package ua.gerasymenko;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class FileLoader {

    private  String filename;
    private  Map<Character, Set<String>> words = new HashMap<>();

    public FileLoader(String filename) {
        this.filename = filename;
    }

    public Map<Character, Set<String>> loadFileToMap() {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String sCurrentLine;
            while ((sCurrentLine = br.readLine()) != null) {
                if (words.containsKey(sCurrentLine.charAt(0))) {
                    words.get(sCurrentLine.charAt(0)).add(sCurrentLine);
                } else {
                    words.put(sCurrentLine.charAt(0), new HashSet<>());
                    words.get(sCurrentLine.charAt(0)).add(sCurrentLine);
                }
            }
        } catch (IOException e) {
            System.err.println(String.format("error reading from file %s", filename));
            e.printStackTrace();
        }
        return words;
    }


}
