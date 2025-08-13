package Practise.String;

public class CapitaliseEachWord {
    public static void main(String[] args) {
        String str = "i am a boy";

        str = str.toLowerCase();

        // Step 3: Split the sentence into words
        String[] words = str.split(" ");

        // Step 4: Capitalize each word

        StringBuilder result = new StringBuilder();
        for (String word : words) {
            if (word.length() == 0)
                continue;

            char firstChar = Character.toUpperCase(word.charAt(0));
            String remaining = word.substring(1);
            result.append(firstChar).append(remaining).append(" ");
        }
        System.out.println(" Capitalising each word : ");
        System.out.println(result.toString().trim());
    }
}
//[Start]
//   |
//   v
//[Set str = "i am a boy"]
//   |
//   v
//[str = str.toLowerCase()]
//   |
//   v
//[Split str into words]
//   |
//   v
//[words = str.split(" ")]
//   |
//   v
//[Initialize result = ""]
//   |
//   v
//[For each word in words]
//   |
//   v
//[If word.length == 0]
//   |            \
//  Yes            No
//   |              \
//[continue]   [firstChar = uppercase(word[0])]
//              [remaining = word.substring(1)]
//              [result = result + firstChar + remaining + " "]
//   |
//   v
//(repeat for all words)
//   |
//   v
//[Print "Capitalising each word:"]
//[Print result.trim()]
//   |
//   v
//[End]