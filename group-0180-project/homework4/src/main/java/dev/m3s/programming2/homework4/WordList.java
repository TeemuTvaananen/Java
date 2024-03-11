package dev.m3s.programming2.homework4;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class WordList {
    private String fileName;
    private int count;
    private List<String> words = new ArrayList<>();
    public WordList(String fileName){
        try  {
            BufferedReader reader = new BufferedReader( new FileReader(fileName));
            String line;
            while ((line = reader.readLine()) != null) {
                words.add(line.toLowerCase());
            }
            reader.close();
        } catch (IOException e) {
            System.out.println("There was an error with reading the file");
        }
    }

    public WordList(List<String> words){
        this.words = words;
    }

    public List<String> getWords() {
        return words;
    }


    public List<String> giveWords() {
        List<String>newWords = new ArrayList<>();
        newWords.addAll(words);
        return newWords;
    }

    public int getCount() {
        return count;
    }

    public String getWord(int i){
        return words.get(i);
    }

   public WordList theWordsOfLength(int length){
        List<String>validWords = new ArrayList<>();
        for(String word: words){
            if(word.length() == length){
                validWords.add(word);
            }
        }
       return new WordList(validWords);
   }

   public WordList theWordsWithCharacters(String someString){
        List<String>validWords = new ArrayList<>();
        String searchPattern = someString.replace("_", ".");
        for(String word: words){
            if(word.matches(searchPattern)){
                validWords.add(word);
            }
        }
       return new WordList(validWords);
   }

}
