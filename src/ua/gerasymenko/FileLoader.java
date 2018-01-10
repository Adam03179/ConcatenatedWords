package ua.gerasymenko;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class FileLoader {

    private Set<String> wordsSet = new HashSet<>();

    //reading words from file and saving them into Set
    public FileLoader(String filename) {

        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String sCurrentLine;
            while ((sCurrentLine = br.readLine()) != null) {
                wordsSet.add(sCurrentLine);
            }

        } catch (IOException e) {
            System.err.println(String.format("error reading from file %s", filename));
            e.printStackTrace();
        }
    }

    //storing words into LinkedHashSet from larger to smaller
    public LinkedHashSet<String> getSortedWordsSet() {
        return wordsSet.stream()
                .sorted((o1, o2) -> Integer.compare(o2.length(), o1.length()))
                .collect(Collectors.toCollection(LinkedHashSet::new));
    }
}
