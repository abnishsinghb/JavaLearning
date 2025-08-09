package Regex;
// | Feature                 | Method Used           |
//| ----------------------- | --------------------- |
//| Split by non-letters    | `split("[^a-zA-Z]+")` |
//| Removes symbols         | ✅ Yes                 |
//| Handles multiple spaces | ✅ Yes                 |
//| Ignores empty strings   | ✅ Yes                 |


public class SplitWord {
    public static void main(String[] args) {
        String str = " I am good, in! java.";

        String[] words = str.split("[^a-zA-Z]+"); // str.split

        System.out.println(" words found are : ");
        for (String word : words) {

            if (!word.isEmpty()) {
                System.out.println(word);
            }
        }

    }
}
