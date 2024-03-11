package dev.m3s.programming2.homework4;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
    Scanner reader = new Scanner(System.in);
    WordList wordList = new WordList("words.txt");
    Hangman hangman = new Hangman(wordList, 5);

    // the main loop for the game
      while(!hangman.theEnd()){
            System.out.println("The hidden word... ");
            System.out.println(hangman.revealHiddenWord());
            System.out.println("\nGuesses left: " + hangman.guessesLeft());
            System.out.println("Guessed letters: "+ hangman.guesses());
            System.out.println("\nGuess a letter :");
            hangman.guess(reader.nextLine().charAt(0));
        }
        if(hangman.wonGame()){
            System.out.println("Congratulations, you won!");
            System.out.println("The hidden word was: "+ hangman.word());
        } else if(hangman.guessesLeft() <= 0) {
            System.out.println("Sorry, you lost\n");
            System.out.println("The hidden word was: " + hangman.word());
        }
        reader.close();
    }
}