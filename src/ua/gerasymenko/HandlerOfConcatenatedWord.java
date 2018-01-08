package ua.gerasymenko;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class HandlerOfConcatenatedWord {

    private  String firstLongestConcatWord = "";
    private  String secondLongestConcatWord = "";
    private  Map<Character, Set<String>> words;
    private  boolean isRecursiveFlag = false;
    private  boolean isConcatenatedFlag = false;
    private int numberOfConcatenatedWords = 0;


    public HandlerOfConcatenatedWord(Map<Character, Set<String>> words) {

        this.words = words;
    }


    public void handle() {


        for (Map.Entry<Character, Set<String>> entry : words.entrySet()) {

            for (String word : entry.getValue()) {
                isRecursiveFlag = false;
                isConcatenatedFlag = false;
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

        if (words.get(word.charAt(0)) == null) {
            return false;
        }
        Set<String> tempSet = new HashSet<>(words.get(word.charAt(0)));

        if(!isRecursiveFlag){
            tempSet.remove(word);
        }

        for (String str : tempSet) {
            if (word.startsWith(str)) {
                word = word.substring(str.length());
                if (word.length() == 0) {
                    isConcatenatedFlag = true;
                }else {
                    isRecursiveFlag = true;
                    isConcatenated(word);
                }
            }
        }
        return isConcatenatedFlag;
    }
}
