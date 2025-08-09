package Practise.String;

public class ReversedString {
    public static void main(String[] args) {
        String str = "Welcome back";
        String rev = "";

        if (str == null || str.isEmpty()) {
            System.out.println("String is empty ");

        }


        // Step 3: Loop through the original string from end to start
        for (int i = str.length() - 1; i >= 0; i--) {
            rev = rev + str.charAt(i);  // Add each character to 'reversed'
        }
        System.out.println("The reversed String is : " + rev);
    }
}
 //[Start]
//   |
//   v
//[Set str = "Welcome back"]
//[Set rev = ""]
//   |
//   v
//[Is str null or empty?]
//   |              \
//  Yes              No
//   |                \
//[Print "String     [For i = str.length()-1 to 0]
//is empty"]        [rev = rev + str.charAt(i)]
//   |                |
//   v                v
//                (repeat for all i)
//   |
//   v
//[Print "The reversed String is: " + rev]
//   |
//   v
//[End]

// | Iteration | i  | str.charAt(i) | reversed      |
//| --------- | -- | ------------- | ------------- |
//| 1         | 10 | `n`           | `n`           |
//| 2         | 9  | `u`           | `nu`          |
//| 3         | 8  | `f`           | `nuf`         |
//| 4         | 7  | ` ` (space)   | `nuf `        |
//| 5         | 6  | `s`           | `nuf s`       |
//| 6         | 5  | `i`           | `nuf si`      |
//| 7         | 4  | ` `           | `nuf si `     |
//| 8         | 3  | `a`           | `nuf si a`    |
//| 9         | 2  | `v`           | `nuf si av`   |
//| 10        | 1  | `a`           | `nuf si ava`  |
//| 11        | 0  | `J`           | `nuf si avaJ` |