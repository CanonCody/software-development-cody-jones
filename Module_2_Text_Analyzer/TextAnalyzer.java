import java.io.*;
import java.util.*;

public class TextAnalyzer {

    public static void main(String[] args) {

        String text = "";
        String line;
        List<Word> words = new ArrayList<>();

        //Reads the file - builds into a single string
        try {
            BufferedReader reader = new BufferedReader(new FileReader("TheRavenText.txt"));
            while ((line = reader.readLine()) != null) {
                text = text + " " + line;
            }
            reader.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        //Changes all characters in string to lowercase (case insensitivity)
        text = text.toLowerCase();

        //Will split the string into an array of words
        String[] wordSoup = text.split("[^a-zA-Z]+");
        for (int i = 0; i < wordSoup.length; i++) {
            words.add(new Word(wordSoup[i], 1)); //Adds all words from the string array to a list
        }

        //Organizes the list by removing duplicates - Incrementing count
        for (int i =0; i < words.size(); i++)
        {
            for (int j = i + 1; j < words.size(); j++)
            {
                if (words.get(i).getWord().equals(words.get(j).getWord()))
                {
                    words.get(i).setCount(words.get(i).getCount() + 1);
                    words.remove(j);
                }
            }
        }

        //Sorts the list by count
        Collections.sort(words, Comparator.comparingInt(Word::getCount).reversed());

        //Prints out the list
        words.forEach(System.out::println);
    }
}
