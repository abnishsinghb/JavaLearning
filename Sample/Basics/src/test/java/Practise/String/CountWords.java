package Practise.String;

public class CountWords {
    public static void main(String[] args) {

        String sentence = "Welcome to java again";

        //Removes unnecessary spaces from start and end
        sentence = sentence.trim();

        // Step 3: Split the sentence into words using space
        String[] words = sentence.split("\\s+");

        // count words
        int count = words.length;

        System.out.println("Total number of words : " + count);

    }
}
//[Start]
//   |
//   v
//[Set sentence = "Welcome to java again"]
//   |
//   v
//[sentence = sentence.trim()]       // Remove leading/trailing spaces
//   |
//   v
//[Split sentence using "\\s+"]
//[words = sentence.split("\\s+")]   // Split into words by spaces
//   |
//   v
//[count = words.length]             // Count number of words
//   |
//   v
//[Print "Total number of words : " + count]
//   |
//   v
//[End]