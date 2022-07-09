import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

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
                WordOccurrences wo = new WordOccurrences(textField.getText());
                FileWriter stream = new FileWriter("Output.txt");
                BufferedWriter out = new BufferedWriter(stream);
                out.write(wo.processString());
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
