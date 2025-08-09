package strings;

public class WordsTotal {
    public static void main(String[] args) {
        String sentence = " India is great Country ";

        sentence = sentence.toLowerCase();
        sentence = sentence.trim();  // Removes unwanted spaces at begining or end

        String[] word = sentence.split("\\s+");  // Splits on one or more spaces

        System.out.println(" Enter the count of Words " + word.length); // Total count
    }
}
