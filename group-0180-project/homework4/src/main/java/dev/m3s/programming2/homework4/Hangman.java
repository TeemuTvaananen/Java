package dev.m3s.programming2.homework4;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Hangman {
    private int numberOfGuesses;
    private String word;
    private List<Character> guesses = new ArrayList<>();
    WordList wordList;

    public Hangman(WordList wordList, int numberOfGuesses){
        setWordList(wordList);
        setNumberOfGuesses(numberOfGuesses);
        word = getRandomWord();
    }

    public void setWordList(WordList wordList) {
        if(wordList != null){
            this.wordList = wordList;
        }
    }

    public int guessesLeft() {
        return numberOfGuesses;
    }

    public void setNumberOfGuesses(int numberOfGuesses) {
        if(numberOfGuesses > 0){
            this.numberOfGuesses = numberOfGuesses;
        }
    }

    public List<Character> guesses() {
        return guesses;
    }

    public boolean guess (Character c){
        c = Character.toLowerCase(c);
        if(!guesses.contains(c)){
            guesses.add(c);
        }
        if(word.contains(Character.toString(c))){
            return true;
        } else{
           numberOfGuesses--;
            return false;
        }
    }

    public String word(){
        return word;
    }

    public String getRandomWord(){
        if(!wordList.getWords().isEmpty()){
            int randomIndex = new Random().nextInt(wordList.getWords().size());
            return wordList.getWord(randomIndex);
        }
        return "Wordlist is empty";
    }

    public String revealHiddenWord (){
        StringBuilder revealed = new StringBuilder();
        for(char c: word.toCharArray()){
            if( guesses.contains(c)){
                revealed.append(c);
            }else{
                revealed.append("* ");
            }
        }
        return revealed.toString();
    }
    public boolean theEnd(){
        if(numberOfGuesses <= 0){
            return true;
        }
        for(char c: word.toCharArray()){
            if(!guesses.contains(c)){
                return false;
            }
        }
        return true;
    }

    public boolean wonGame(){
        for (char c : word.toCharArray()) {
            if (!guesses.contains(c)) {
                return false;
            }
        }
        return true;
    }

}
