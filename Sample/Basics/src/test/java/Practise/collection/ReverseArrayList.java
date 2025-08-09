package Practise.collection;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ReverseArrayList {
    public static void main(String[] args) {

        // Step 1: Create and populate the list
        List<String> fruits = new ArrayList<>();
        fruits.add("Apple");
        fruits.add("Banana");
        fruits.add("Mango");
        fruits.add("Peach");

        System.out.println("\nOrginal List : " + fruits);

        // Step 2: Reverse the list
        Collections.reverse(fruits);

        // Step 3: Print the reversed list
        System.out.println("\nThe Reversed List is : " + fruits);

        // Sort list
        Collections.sort(fruits);
        System.out.println("\nSorted List is : " + fruits);
    }
}


// String orginal ="Hello World"; String reversed ="";
// Loop through the original string from the last character to the first
//        for (int i = original.length() - 1; i >= 0; i--) {
//            // Append each character to the new 'reversed' string
//            reversed = reversed + original.charAt(i);
//

//| Step | Code Part                                  | Explanation                        |
//| ---- | ------------------------------------------ | ---------------------------------- |
//| 1    | `List<String> fruits = new ArrayList<>();` | Create a list of strings           |
//| 2    | `Collections.reverse(fruits);`             | Reverses the elements **in-place** |
//| 3    | `System.out.println(fruits);`              | Displays the list after reversing  |