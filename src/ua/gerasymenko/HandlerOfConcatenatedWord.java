package ua.gerasymenko;

import java.util.*;

public class HandlerOfConcatenatedWord {

    private String firstLongestConcatWord = "";
    private String secondLongestConcatWord = "";
    private String originalWord;

    private Set<String> sortedWords;
    private Set<String> tempSet;
    private Set<String> concatenatedWords;


    public HandlerOfConcatenatedWord(Set<String> sortedWords) {
        this.sortedWords = sortedWords;
        this.concatenatedWords = new LinkedHashSet<>();
        this.tempSet = new HashSet<>(sortedWords);
    }

    //First two words which are concatenated is largest in the File, because words are sorted by length
    public void handle() {

        for (String word : sortedWords) {
            originalWord = word;
            checkForConcatenated(word);
            if (!concatenatedWords.isEmpty()) {
                if (concatenatedWords.size() == 2) {
                    Object[] result = concatenatedWords.toArray();
                    firstLongestConcatWord = (String) result[0];
                    secondLongestConcatWord = (String) result[1];
                }
            }

        }
    }

    public String getFirstLongestConcatWord() {
        return firstLongestConcatWord;
    }

    public String getSecondLongestConcatWord() {
        return secondLongestConcatWord;
    }

    public Set<String> getConcatenatedWords() {
        return concatenatedWords;
    }

    //recursively checking word if it concatenated
    private void checkForConcatenated(String word) {

        for (int i = 0; i < word.length(); i++) {
            String left = word.substring(0, i + 1);
            String right = word.substring(i + 1);
            if (tempSet.contains(left)) {
                if (tempSet.contains(right)) {
                    concatenatedWords.add(originalWord);
                    break;
                }
                checkForConcatenated(right);
            }
        }
    }
}
