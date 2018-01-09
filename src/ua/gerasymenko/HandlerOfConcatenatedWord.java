package ua.gerasymenko;

import java.util.*;

public class HandlerOfConcatenatedWord {

    private String firstLongestConcatWord = "";
    private String secondLongestConcatWord = "";
    private Map<Character, Set<String>> words;
    private boolean isRecursive = false;
    private int numberOfConcatenatedWords = 0;
    private List<String> cutWord = new LinkedList<>();

    public HandlerOfConcatenatedWord(Map<Character, Set<String>> words) {
        this.words = words;
    }

    public void handle() {
        for (Map.Entry<Character, Set<String>> entry : words.entrySet()) {
            for (String word : entry.getValue()) {
                isRecursive = false;
                if (isConcatenated(word)) {
                    numberOfConcatenatedWords++;
                    checkForFirstPlaces(word);
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

    public int getNumberOfConcatenatedWords() {
        return numberOfConcatenatedWords;
    }

    private void checkForFirstPlaces(String word) {
        if (firstLongestConcatWord.length() < word.length()) {
            secondLongestConcatWord = firstLongestConcatWord;
            firstLongestConcatWord = word;
        } else if (secondLongestConcatWord.length() < word.length()) {
            secondLongestConcatWord = word;
        }
    }

    private boolean isConcatenated(String word) {
        boolean result = false;
        if (words.get(word.charAt(0)) == null) {
            return false;
        }
        Set<String> tempSet = new HashSet<>(words.get(word.charAt(0)));

        if (!isRecursive) {
            tempSet.remove(word);
        }

        for (String str : tempSet) {
            if (word.startsWith(str)) {
                cutWord.add(word.substring(0, str.length()));
                word = word.substring(str.length());
                if (word.length() == 0) {
                    return true;
                } else {
                    isRecursive = true;
                    result = isConcatenated(word);
                    if (!result && !cutWord.isEmpty()) {
                        word = cutWord.get(cutWord.size() - 1) + word;
                        cutWord.remove(cutWord.size() - 1);
                    }
                }
            }
        }
        return result;
    }
}
