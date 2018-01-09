package ua.gerasymenko;


public class ConcatenatedWordsApp {

    public static void main(String[] args) {
        FileLoader fileLoader = new FileLoader("resources/words.txt");
        HandlerOfConcatenatedWord handler = new HandlerOfConcatenatedWord(fileLoader.loadFileToMap());
        handler.handle();
        System.out.println("1st place = " + handler.getFirstLongestConcatWord());
        System.out.println("2nd place = " + handler.getSecondLongestConcatWord());
        System.out.println("Number of concatenated words is " + handler.getNumberOfConcatenatedWords());
    }
}
