package Practise.String;

public class PalindromeString {
    public static void main(String[] args) {
        String str = "Gadag";
        int len = str.length();
        String rev = "";

        str = str.toLowerCase();

        if (str == null || str.isEmpty()) {
            System.out.println(" Empty String");
        }
        for (int i = len - 1; i >= 0; i--) {
            rev = rev + str.charAt(i);
        }
        if (str.equals(rev)) {
            System.out.println(" Palindrome ");
        } else {
            System.out.println("Not Palindrome");
        }
    }
}
//[Start]
//   |
//   v
//[Set str = "Gadag"]
//   |
//   v
//[len = str.length()]
//[rev = ""]
//   |
//   v
//[str = str.toLowerCase()]
//   |
//   v
//[Is str null or empty?]
//   |             \
//  Yes             No
//   |               \
//[Print "Empty     [For i = len-1 to 0]
// String"]        [rev = rev + str.charAt(i)]
//   |               |
//   v               v
//               (repeat for all i)
//   |
//   v
//[Is str.equals(rev)?]
//   |             \
//  Yes             No
//   |               \
//[Print "Palindrome"] [Print "Not Palindrome"]
//   |               |
//   v               v
//               [End]
//   |
//   v
// [End]