import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.*;
import java.util.List;

public class TextAnalyzer extends JFrame {

    JLabel label;
    JTextField textField;
    JButton button;

    public TextAnalyzer() {

        setLayout(new FlowLayout());

        label = new JLabel("Enter text to analyze");
        add(label);

        textField = new JTextField(10);
        add(textField);

        button = new JButton("Analyze");
        add(button);

        event e = new event();
        button.addActionListener(e);

    }

    public class event implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            try {

                List<Word> words = new ArrayList<>();
                String word = textField.getText();

                //Changes all characters in string to lowercase (case insensitivity)
                word = word.toLowerCase();

                //Will split the string into an array of words
                String[] wordSoup = word.split("[^a-zA-Z]+");
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

                word = "";
                for(int i = 0; i < words.size(); i++) {
                    word += words.get(i) + "\n";
                }

                FileWriter stream = new FileWriter("Output.txt");
                BufferedWriter out = new BufferedWriter(stream);
                out.write(word);
                out.close();
            } catch(Exception ex) {}
        }
    }

    public static void main(String[] args) {

        TextAnalyzer gui = new TextAnalyzer();
        gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gui.setSize(300, 150);
        gui.setTitle("Text Analyzer");
        gui.setVisible(true);

    }
}
