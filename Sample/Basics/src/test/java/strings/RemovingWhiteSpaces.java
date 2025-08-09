package strings;
// | Regex  | Meaning                              |
//| ------ | ------------------------------------ |
//| `\\s`  | Matches any whitespace character     |
//| `\\s+` | Matches one or more whitespaces      |
//| `""`   | Replaces them with nothing (removes) |

public class RemovingWhiteSpaces {
    public static void main(String[] args) {
        String str = " India is \t\n ";
        String result = str.replaceAll("\\s+", "");
        System.out.println("Original : " + str);
        System.out.println(" The result is :" + result);
    }
}
//[Start]
//   |
//   v
//[Set str = "Level"]
//   |
//   v
//[str = str.toLowerCase()]         // Convert to lowercase
//   |
//   v
//[rev = new StringBuilder(str).reverse().toString()]  // Reverse string
//   |
//   v
//[Is str.equals(rev)?]
//   |             \
//  Yes             No
//   |               \
//[Print "Palindrome : " + str]   [Print "Not Palindrome : " + rev]
//   |               |
//   v               v
//               [End]
//   |
//   v
// [End]
