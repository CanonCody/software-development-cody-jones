import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WordOccurrencesTest {

    @Test
    public void testProcessString() throws Exception {
        WordOccurrences test = new WordOccurrences("Mary had had a little lamb @ , 0 -1 ! . / !");
        assertEquals("had: 2\nmary: 1\na: 1\nlittle: 1\nlamb: 1\n", test.processString());
    }

}