import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.sql.*;
import java.util.ArrayList;

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


    public static Connection getConnection() throws Exception {
        try{
            String url = "jdbc:mysql://localhost:3306/word_occurrences";
            String username = "root";
            String password = "wordpass";

            Connection conn = DriverManager.getConnection(url, username, password);
            System.out.println("Connected");
            return conn;
        } catch(Exception e){System.out.println(e);}

        return null;
    }

    public static void createTable() throws Exception{
        try {
            Connection conn = getConnection();
            PreparedStatement create = conn.prepareStatement("CREATE TABLE IF NOT EXISTS word(id int NOT NULL AUTO_INCREMENT, word varchar(45) UNIQUE, count int, PRIMARY KEY(id))");
            create.executeUpdate();

        }catch(Exception e){System.out.println(e);}
    }

    public static ArrayList<String> get() throws Exception{
        try{
            Connection conn = getConnection();
            PreparedStatement statement = conn.prepareStatement("SELECT word, count FROM word ORDER BY count DESC");

            ResultSet result = statement.executeQuery();

            ArrayList<String> array = new ArrayList<String>();
            while(result.next()){
                System.out.println(result.getString("word"));

                array.add(result.getString("word"));
            }
            System.out.println("All records have been selected");
            return array;
        }catch(Exception e){System.out.println(e);}
        return null;
    }

    public static void main(String[] args) throws Exception {

        TextAnalyzer gui = new TextAnalyzer();
        gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gui.setSize(300, 150);
        gui.setTitle("Text Analyzer");
        gui.setVisible(true);

        getConnection();
        createTable();
    }
}
