import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class WordOccurrences {

    String wordString;

    public WordOccurrences(String initialWords) {
        this.wordString = initialWords;
    }

    public String processString() {

        List<Word> words = new ArrayList<>();

        //Changes all characters in string to lowercase (case insensitivity)
        wordString = wordString.toLowerCase();

        //Will split the string into an array of words
        String[] wordSoup = wordString.split("[^a-zA-Z]+");
        for (int i = 0; i < wordSoup.length; i++) {
            //Adds all words from the string array to a list
            words.add(new Word(wordSoup[i], 1));
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

        wordString = "";
        for(int i = 0; i < words.size(); i++) {
            wordString += words.get(i) + "\n";
        }

        return wordString;

    }

}
