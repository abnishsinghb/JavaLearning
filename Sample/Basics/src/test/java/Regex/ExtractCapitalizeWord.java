package Regex;

// | Regex Part | Meaning                                   |
//| ---------- | ----------------------------------------- |
//| `\\b`      | Word boundary                             |
//| `[A-Z]`    | First letter must be uppercase (A to Z)   |
//| `[a-z]*`   | Zero or more lowercase letters after that |
//| `\\b`      | Word boundary (ensures full word match)   |

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ExtractCapitalizeWord {
    public static void main(String[] args) {
        String str = " India is great ";

        String regex = "\\b[A-Z][a-z]*\\b";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(str);

        System.out.println(" Each Capitalised words are : ");
        while (matcher.find()) {
            System.out.println(matcher.group());
        }
    }
}

// | Task                                                   | Regex Example                        |
//| ------------------------------------------------------ | ------------------------------------ |
//| Match all words (not just capitalized)                 | `\\b\\w+\\b`                         |
//| Match all lowercase words                              | `\\b[a-z]+\\b`                       |
//| Match words that **start and end** with capital letter | `\\b[A-Z][a-z]*[A-Z]\\b` (rare case) |