package Practise.collection;

import java.util.HashMap;
import java.util.Map;

//| Step | Code Part                                           | Explanation                             |
//| ---- | --------------------------------------------------- | --------------------------------------- |
//| 1    | `sentence.split(" ")`                               | Splits sentence by space into words     |
//| 2    | `Map<String, Integer> wordCount = new HashMap<>();` | To store word → count mapping           |
//| 3    | `getOrDefault(word, 0) + 1`                         | Get existing count or 0, then increment |
//| 4    | `entrySet()`                                        | Loops through each word and its count   |
public class WordFrequencyCount {
    public static void main(String[] args) {

        // Step 1: Input sentence
        String sentence = " Java is powerful and object oriented programing Language";

        // Step 2: Split sentence into words
        String[] words = sentence.split(" ");

        // Step 3: Create HashMap to count frequency
        Map<String, Integer> wordCount = new HashMap<>();

        for (String word : words) {
            wordCount.put(word, wordCount.getOrDefault(word, 0) + 1);
        }

        // Step 4: Print word frequencies
        System.out.println(" \n Word Frequency is ");
        for (Map.Entry<String, Integer> entry : wordCount.entrySet()) {
            System.out.println(entry.getKey() + " --> " + entry.getValue());
        }

    }
}
