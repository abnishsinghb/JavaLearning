package strings;

public class PalindromeCheck {
    public static void main(String[] args) {
        String str = "Level";
        str = str.toLowerCase();
        String rev = new StringBuilder(str).reverse().toString();

        if (str.equals(rev)) {
            System.out.println("Palindrome : " + str);
        } else {
            System.out.println("Not Palindrome : " + rev);
        }
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