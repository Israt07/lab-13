package demotcpservertext;

public class TextProcessor {

    /**
     * Counts the number of words in the given text.
     *
     * @param text The text to be processed.
     * @return The count of words in the text.
     */
    public int countWords(String text) {
        // Split the text into words using regular expression "\s+" (one or more whitespace characters)
        String[] words = text.split("\\s+");
        
        // Return the count of words
        return words.length;
    }
}
